package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProfileManage extends AppCompatActivity {

    Button btnNEXT;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    public static String email, pass, type, uid;
    EditText FirstName, LastName, Gender, Country, City, Address , Contact;
    String FirstN, LastN, Gend, Count, Cit, Addr , imageUrl , Contact_No;
    ImageView ProfileImage;
    private static final int PICK_IMAGE_REQUEST = 234 ;
    private Uri filepath;
    private StorageReference storageReference;
    String LocationID , CityID , CountryID, TodayDate;

    public ProfileManage() {
    }

    public ProfileManage(String email, String pass, String type) {
        this.email = email;
        this.pass = pass;
        this.type = type;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manage);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        storageReference = FirebaseStorage.getInstance().getReference();
        btnNEXT = (Button) findViewById(R.id.NEXT);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        Gender = (EditText) findViewById(R.id.Gender);
        Address = (EditText) findViewById(R.id.AddressName);
        Country = (EditText) findViewById(R.id.CountryName);
        City = (EditText) findViewById(R.id.CityName);
        Contact = (EditText) findViewById(R.id.ContactNo);
        ProfileImage = (ImageView) findViewById(R.id.ProfileImage);


        /// Getting Current Date and Time //
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        TodayDate = dateFormat.format(cal.getTime());
        ///



        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();

            }
        });

//        Toast.makeText(ProfileManage.this, "Chk" + email + pass + type, Toast.LENGTH_SHORT).show();
        btnNEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (FirstName.getText().toString().isEmpty() || LastName.getText().toString().isEmpty() ||
                        Gender.getText().toString().isEmpty() || Country.getText().toString().isEmpty() ||
                        City.getText().toString().isEmpty()) {


                    Toast.makeText(ProfileManage.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();

                } else {


                    FirstN = FirstName.getText().toString().trim();
                    LastN = LastName.getText().toString().trim();
                    Gend = Gender.getText().toString().trim();
                    Count = Country.getText().toString().trim();
                    Cit = City.getText().toString().trim();
                    Addr = Address.getText().toString().trim();
                    Contact_No = Contact.getText().toString().trim();

                    PhotographerSignUp();

                }


            }
        });
    }

    public void PhotographerSignUp() {


        if (!(email.isEmpty() && pass.isEmpty())) {


            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(ProfileManage.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                }
            }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {


                        uid = firebaseAuth.getCurrentUser().getUid();
                        uploadfile();



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(ProfileManage.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            });

        }else {

            Toast.makeText(ProfileManage.this, "Email Pass Null", Toast.LENGTH_SHORT).show();

        }


    }

    private void uploadfile(){


        if (filepath != null){
            StorageReference riversRef = storageReference.child("images/"+uid+"/profile.jpg");
            /// MAIN FOLDER MAIN JA K UID KA FOLDER HOGA THEN IMAGE HOGI:
            riversRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downUrl = taskSnapshot.getDownloadUrl();
                            // Log.d("downUrl" , downUrl.toString());
                            imageUrl = downUrl.toString();
                            // ValidationMethod();
                           SaveData();
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

        }else {

            Toast.makeText(ProfileManage.this , " This error from upload file" , Toast.LENGTH_SHORT).show();

        }


    }

    public void SaveData(){


        LocationID =  databaseReference.push().getKey();
        CityID = databaseReference.push().getKey();
        CountryID =  databaseReference.push().getKey();

        UserModel Mod = new UserModel(uid , email , pass , type , FirstN , LastN , Gend , imageUrl , LocationID ,  Contact_No , TodayDate , Cit);


        databaseReference.child("Users").child("Photographer").child(uid).setValue(Mod, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseReference.equals(databaseError)){

                    Toast.makeText(ProfileManage.this , "Error in Saving" , Toast.LENGTH_SHORT).show();
                }else {

                    Location();

                }

            }
        });

    }

    public  void Location(){


        UserModel userModel = new UserModel(uid , LocationID , Addr , CityID , "NIL");

        databaseReference.child("Users").child("Photographer").child("Location").child(LocationID).setValue(userModel, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                if (databaseReference.equals(databaseError)){

                    Toast.makeText(ProfileManage.this , "Error in Saving" , Toast.LENGTH_SHORT).show();
                }else {

                 City();

                }
            }
        });


    }


    public void City(){


        UserModel userModel = new UserModel(uid , CityID , Cit , CountryID);

        databaseReference.child("Users").child("Photographer").child("City").child(CityID).setValue(userModel, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                if (databaseReference.equals(databaseError)){

                    Toast.makeText(ProfileManage.this , "Error in Saving" , Toast.LENGTH_SHORT).show();
                }else {

                    Country();

                }
            }
        });
    }


    public void  Country(){


        UserModel userModel = new UserModel(uid , CountryID , Count);

        databaseReference.child("Users").child("Photographer").child("Country").child(CountryID).setValue(userModel, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseReference.equals(databaseError)){

                    Toast.makeText(ProfileManage.this , "Error in Saving" , Toast.LENGTH_SHORT).show();
                }else {

                    Intent intent = new Intent(ProfileManage.this , PhotographerPackages.class);
                    startActivity(intent);
                }



            }
        });


    }




    public void OpenGallery(){


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                ProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }



}
