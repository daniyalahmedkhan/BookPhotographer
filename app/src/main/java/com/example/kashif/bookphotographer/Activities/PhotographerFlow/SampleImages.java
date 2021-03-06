package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.app.ProgressDialog;
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

import com.example.kashif.bookphotographer.Activities.UserAuth.LoginActivity;
import com.example.kashif.bookphotographer.Activities.ModelClass.GalleryClass;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SampleImages extends AppCompatActivity {

    Button btnNEXT , btnUpload;
    int counterCheck = 0;
    ImageView image1  , image2  , image3 , image4 , image5 , image6;
    private static final int PICK_IMAGE_REQUEST = 234 ;
    private Uri filepath;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ImageView CurrentImageView = null;
    ArrayList<Uri> arrayList;
    FirebaseDatabase firebaseDatabase;
    private StorageReference storageReference;
    EditText EditCategory;
    ProgressDialog progressDialog;

    String  imageUrl1 , imageUrl2 , imageUrl3 , imageUrl4 , imageUrl5 , imageUrl6,  Gallery_ID , Category_ID  , Date , Image_ID;
    ArrayList img;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_images);
        image1 = (ImageView) findViewById(R.id.Image1);
        image2 = (ImageView) findViewById(R.id.Image2);
        image3 = (ImageView) findViewById(R.id.Image3);
        image4 = (ImageView) findViewById(R.id.Image4);
        image5 = (ImageView) findViewById(R.id.Image5);
        image6 = (ImageView) findViewById(R.id.Image6);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        storageReference = FirebaseStorage.getInstance().getReference();

        EditCategory = (EditText) findViewById(R.id.EditCategory);

        arrayList = new ArrayList<>();
        img = new ArrayList();


        /// Getting Current Date and Time //
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date = dateFormat.format(cal.getTime());
        ///


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                counterCheck++;
                try {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);
                CurrentImageView = (ImageView) view;
                }catch (Exception e){

                }
            }
        });


        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                counterCheck++;

                try {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);
                CurrentImageView  = (ImageView) view;

                }catch (Exception e){


                }

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                counterCheck++;

                try {


                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);
                    CurrentImageView  = (ImageView) view;

                }catch (Exception e){


                }

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                counterCheck++;
                try {


                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);
                    CurrentImageView  = (ImageView) view;

                }catch (Exception e){


                }

            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                counterCheck++;

                try {


                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);
                    CurrentImageView  = (ImageView) view;

                }catch (Exception e){


                }

            }
        });


        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                counterCheck++;

                try {


                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);
                    CurrentImageView  = (ImageView) view;

                }catch (Exception e){


                }

            }
        });

        btnNEXT = (Button) findViewById(R.id.NEXT);
        btnUpload = (Button) findViewById(R.id.Upload);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(counterCheck<6){

                    Toast.makeText(SampleImages.this, "Please Select All Images", Toast.LENGTH_SHORT).show();

                }else {

                    uploadfile();



                }



            }
        });
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
                counterCheck++;
            } catch (IOException e) {
                e.printStackTrace();
            }


        }break;

        default:
            break;

       }

    }

    private void uploadfile(){

    progressDialog.show();
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
                            img.add(downUrl.toString());
                            saveData();

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



        }

    }



    public  void  saveData() {


        try {


            imageUrl1 = String.valueOf(img.get(0));
            imageUrl2 = String.valueOf(img.get(1));
            imageUrl3 = String.valueOf(img.get(2));
            imageUrl4 = String.valueOf(img.get(3));
            imageUrl5 = String.valueOf(img.get(4));
            imageUrl6 = String.valueOf(img.get(5));

            Image_ID = databaseReference.push().getKey();
            Gallery_ID = databaseReference.push().getKey();
            Category_ID = PhotographerPackages.Category_ID.toString();


            GalleryClass galleryClass = new GalleryClass(Image_ID , Category_ID , Date ,imageUrl1, imageUrl2 , imageUrl3 , imageUrl4 , imageUrl5 , imageUrl6);

            databaseReference.child("Gallery").child(ProfileManage.uid).child(Gallery_ID).setValue(galleryClass, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                    if (databaseReference.equals(databaseError)) {
//                    progressDialog.dismiss();
                        Toast.makeText(SampleImages.this, "Error in Saving", Toast.LENGTH_SHORT).show();
                    } else {

                    SaveCategory();


                    }
                }
            });


        }catch (Exception e){


//            Intent intent = getIntent();
//            finish();
//            startActivity(intent);



        }


    }


    public void SaveCategory(){



        String Parent_Category_ID = databaseReference.push().getKey();
        String Category_Des  = EditCategory.getText().toString();

        GalleryClass CategoryData = new GalleryClass(Category_Des  , Parent_Category_ID);

        databaseReference.child("Gallery").child("Category").child(ProfileManage.uid).child(Category_ID).setValue(CategoryData, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                if (databaseReference.equals(databaseError)) {

                    Toast.makeText(SampleImages.this, "Error in Saving", Toast.LENGTH_SHORT).show();
                } else {


                    progressDialog.dismiss();
                    Intent intent  = new Intent(SampleImages.this , LoginActivity.class);
                    startActivity(intent);

                }

            }
        });


    }

}
