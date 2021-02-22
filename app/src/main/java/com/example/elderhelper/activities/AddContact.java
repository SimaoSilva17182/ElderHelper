package com.example.elderhelper.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.elderhelper.R;
import com.example.elderhelper.db.ContactDB;
import com.example.elderhelper.model.Contact;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;


public class AddContact extends AppCompatActivity {

    private CheckBox addToEC;
    private Button confirm;
    private Button back;
    private ImageButton picture;
    private ImageView realPic;
    private EditText name;
    private EditText number;
    private StorageReference storageReference;
    private FirebaseFirestore db;
    private static final int REQUEST_CAMERA_CODE = 1000;
    private static final int REQUEST_GALLERY_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__contact);

        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("Images");
        name = findViewById(R.id.addname);
        number = findViewById(R.id.phone_number);
        confirm = findViewById(R.id.confirm);
        back = findViewById(R.id.back);
        picture = findViewById(R.id.picture);
        realPic = findViewById(R.id.Realpic);
        addToEC = findViewById(R.id.addToEmergencyCall);


        if (ContextCompat.checkSelfPermission(AddContact.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddContact.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_CODE);
        }

        if (ContextCompat.checkSelfPermission(AddContact.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddContact.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALLERY_CODE);
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(addToEC.isChecked()){
                    Contact contact = new Contact();
                    contact.setName(name.getText().toString());
                    contact.setNumber(number.getText().toString());

                    ContactDB.getInstance(getApplicationContext()).emergencyContactDao().insert(contact);
                }

                saveToFireBase();
                //Intent intent = new Intent(AddContact.this, Contacts.class); //
                //startActivity(intent);
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContact.this, AddContact.class);
                startActivity(intent);
            }
        });

        /**Method so that when you click on picture it gives you a pop-up dialog box so that you can choose to add picture
           from gallery, to take photo with camera or to cancel de process
         */

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
                AlertDialog.Builder builder = new AlertDialog.Builder(AddContact.this); // this will create a pop-up dialog box that will ask the user to choose the options above
                builder.setTitle("Add Photo");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo"))
                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQUEST_CAMERA_CODE);
                        }
                        else if (options[item].equals("Choose from Gallery"))
                        {
                            Intent intent = new   Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent, REQUEST_GALLERY_CODE);
                        }
                        else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  //this activity is the activity to take the picture for the contact
        super.onActivityResult(requestCode, resultCode, data);

        /**Method so that you can use camera to take picture
         */
        if(requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            realPic.setImageBitmap(bitmap); // picture that is being taken
            picture.setVisibility(View.INVISIBLE); // here we have the Imagebutton invisible so that the user can click on the Imageview
        }

        /**Method so that you can use gallery to add photo from gallery
         */
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == RESULT_OK){
            realPic.setImageURI(data.getData());
            picture.setVisibility(View.INVISIBLE);
            }
        }

        private void addContact(String name, String number, String imageURL) {

            if (!name.equals("") && !number.equals("")){
                Contact contact = new Contact(name, number, imageURL);
                db.collection("contact").add(contact).addOnSuccessListener(documentReference -> {

                }).addOnFailureListener(e -> {

                });
            }
        }

    /**
     * Method to save images to firabase cloud storage
     * in this method we also have the conversion of our images into byte so we can upload the image to the firebase
     */
    private void saveToFireBase() {

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference databaseReference = storage.getReference();
            StorageReference storageReference = databaseReference.child("images/" + this.name.getText().toString() + ".jpg");

            Contact contact = new Contact(name.getText().toString(),number.getText().toString(),realPic.getDrawable().toString());

            /**check for empty inputs
             */
            if(TextUtils.isEmpty(name.getText().toString())){
                name.setError("Enter a name");
            }else if (TextUtils.isEmpty(number.getText().toString())){
                number.setError("Insert phone number");
            }

            if (!name.getText().toString().equals("") && !(number.getText().toString().equals("")) && realPic.getDrawable() != null) {

                Bitmap bitmap = ((BitmapDrawable) realPic.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = storageReference.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {

                                addContact(name.getText().toString(),number.getText().toString(),task.toString());
                                finish();
                            }
                        });
                    }
                });
            }
        }

}
