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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.LoginActivity;
import com.example.kashif.bookphotographer.Activities.ModelClass.SampleImag;
import com.example.kashif.bookphotographer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class SampleImages extends AppCompatActivity {

    Button btnNEXT , btnUpload;
    ImageView image1  , image2  , image3 ;
    private static final int PICK_IMAGE_REQUEST = 234 ;
    private Uri filepath;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ImageView CurrentImageView = null;
    ArrayList<Uri> arrayList;
    private StorageReference storageReference;

    String  imageUrl1 , imageUrl2;
    ArrayList img;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_images);
        image1 = (ImageView) findViewById(R.id.Image1);
        image2 = (ImageView) findViewById(R.id.Image2);
        image3 = (ImageView) findViewById(R.id.Image3);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        storageReference = FirebaseStorage.getInstance().getReference();
        arrayList = new ArrayList<>();
        img = new ArrayList();


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);
                CurrentImageView = (ImageView) view;
                }catch (Exception e){

//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);

                }
            }
        });


        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);
                CurrentImageView  = (ImageView) view;

                }catch (Exception e){

//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);


                }

            }
        });

        btnNEXT = (Button) findViewById(R.id.NEXT);
        btnUpload = (Button) findViewById(R.id.Upload);

        btnNEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                saveData();

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadfile();
//                saveData();
//                Intent intent = new Intent(SampleImag.this , MyProfile.class);
//                startActivity(intent);

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


       switch (requestCode){
           case PICK_IMAGE_REQUEST:



        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                CurrentImageView.setImageBitmap(bitmap);
                arrayList.add(filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }break;

        default:
            break;

       }

    }

    private void uploadfile(){


        try {


        if (arrayList != null){

            for (int i =0; i<arrayList.size(); i++){
            StorageReference riversRef = storageReference.child("images/"+ProfileManage.uid+"/ img"+i);
            /// MAIN FOLDER MAIN JA K UID KA FOLDER HOGA THEN IMAGE HOGI:
            riversRef.putFile(arrayList.get(i))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downUrl = taskSnapshot.getDownloadUrl();
                            // Log.d("downUrl" , downUrl.toString());
                            img.add(downUrl.toString());
                            // ValidationMethod();
//                           saveData();
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
            }

        }else {

            Toast.makeText(SampleImages.this , " This error from upload file" , Toast.LENGTH_SHORT).show();

        }
        }catch (Exception e){

//            Intent intent = getIntent();
//            finish();
//            startActivity(intent);



        }

    }



    public  void  saveData() {


        try {


            imageUrl1 = String.valueOf(img.get(0));
            imageUrl2 = String.valueOf(img.get(1));

            SampleImag sampleImag = new SampleImag(imageUrl1, imageUrl2);

            databaseReference.child("sampleImages").child(ProfileManage.uid).setValue(sampleImag, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                    if (databaseReference.equals(databaseError)) {
//                    progressDialog.dismiss();
                        Toast.makeText(SampleImages.this, "Error in Saving", Toast.LENGTH_SHORT).show();
                    } else {


//                     progressDialog.dismiss();
                        Intent i = new Intent(SampleImages.this, LoginActivity.class);
                        startActivity(i);
                    }
                }
            });


        }catch (Exception e){


//            Intent intent = getIntent();
//            finish();
//            startActivity(intent);



        }


    }

}
