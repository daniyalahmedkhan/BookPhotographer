package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kashif.bookphotographer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class EditProfile extends AppCompatActivity  {

    EditText FirstName , LastName , PkgTname , PkgTprice , PkgTdays , PkgTdescription;

    EditText PkgTname2 , PkgTprice2 , PkgTdays2 , PkgTdescription2;
    EditText PkgTname3 , PkgTprice3 , PkgTdays3 , PkgTdescription3;
    EditText PkgTname4 , PkgTprice4 , PkgTdays4 , PkgTdescription4;
    EditText PkgTname5 , PkgTprice5 , PkgTdays5 , PkgTdescription5;


    ImageView HeadImg;
    String profile_Img;
    Button SaveButtn;
    Boolean flag = false;

    ProgressDialog progressDialog;

    private static final int PICK_IMAGE_REQUEST = 234 ;
    private Uri filepath;
    private StorageReference storageReference;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    RelativeLayout RelativeBronze, RelativeSilver, RelativeGold, RelativePlatinum, RelativeDiamond;
    RelativeLayout RBronzeDetail, RSilverDetail, RGoldDetail, RPlatinumDetail, RDiamondDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Saving Data");
        progressDialog.setCancelable(false);


        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        FirstName = (EditText) findViewById(R.id.PhotographerFname);
        LastName = (EditText) findViewById(R.id.PhotographerLname);
        HeadImg = (ImageView) findViewById(R.id.HeadImg);
        SaveButtn = (Button) findViewById(R.id.SaveButtn);


        PkgTname = (EditText) findViewById(R.id.PgkTname1);
        PkgTprice = (EditText) findViewById(R.id.PkgTprice1);
        PkgTdays = (EditText) findViewById(R.id.Services1);
        PkgTdescription = (EditText) findViewById(R.id.PkgTdes1);

        PkgTname2 = (EditText) findViewById(R.id.PkgTname2);
        PkgTprice2 = (EditText) findViewById(R.id.PkgTprice2);
        PkgTdays2 = (EditText) findViewById(R.id.Services2);
        PkgTdescription2  = (EditText) findViewById(R.id.PkgTdes2);


        PkgTname3 = (EditText) findViewById(R.id.PkgTname3);
        PkgTprice3 = (EditText) findViewById(R.id.PkgTprice3);
        PkgTdays3 = (EditText) findViewById(R.id.Services3);
        PkgTdescription3  = (EditText) findViewById(R.id.PkgTdes3);

        PkgTname4 = (EditText) findViewById(R.id.PkgTname4);
        PkgTprice4 = (EditText) findViewById(R.id.PkgTprice4);
        PkgTdays4 = (EditText) findViewById(R.id.Services4);
        PkgTdescription4  = (EditText) findViewById(R.id.PkgTdes4);


        PkgTname5 = (EditText) findViewById(R.id.PkgTname5);
        PkgTprice5 = (EditText) findViewById(R.id.PkgTprice5);
        PkgTdays5 = (EditText) findViewById(R.id.Services5);
        PkgTdescription5  = (EditText) findViewById(R.id.PkgTdes5);


        RelativeBronze = (RelativeLayout) findViewById(R.id.RelativeBronze);
        RelativeSilver = (RelativeLayout) findViewById(R.id.RelativeSilver);
        RelativeGold = (RelativeLayout) findViewById(R.id.RelativeGolden);
        RelativePlatinum = (RelativeLayout) findViewById(R.id.RelativePlatinum);
        RelativeDiamond = (RelativeLayout) findViewById(R.id.RelativeDiamond);

        RBronzeDetail = (RelativeLayout) findViewById(R.id.RelativeBronzeDetail);
        RSilverDetail = (RelativeLayout) findViewById(R.id.RelativeSilverDetail);
        RGoldDetail = (RelativeLayout) findViewById(R.id.RelativeGoldenDetail);
        RPlatinumDetail = (RelativeLayout) findViewById(R.id.RelativePlatinumDetail);
        RDiamondDetail = (RelativeLayout) findViewById(R.id.RelativeDiamondDetail);



        HeadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag = true;
                OpenGallery();



            }
        });

        SaveButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    progressDialog.show();
                    SaveData();


            }
        });



        RelativeBronze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RBronzeDetail.getVisibility() == View.GONE){

                    RBronzeDetail.setVisibility(View.VISIBLE);

                }else {


                    RBronzeDetail.setVisibility(View.GONE);
                }

            }
        });



        RelativeSilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RSilverDetail.getVisibility() == View.GONE){

                    RSilverDetail.setVisibility(View.VISIBLE);

                }else {


                    RSilverDetail.setVisibility(View.GONE);
                }

            }
        });



        RelativeGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RGoldDetail.getVisibility() == View.GONE){

                    RGoldDetail.setVisibility(View.VISIBLE);

                }else {


                    RGoldDetail.setVisibility(View.GONE);
                }

            }
        });



        RelativePlatinum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RPlatinumDetail.getVisibility() == View.GONE){

                    RPlatinumDetail.setVisibility(View.VISIBLE);

                }else {


                    RPlatinumDetail.setVisibility(View.GONE);
                }

            }
        });



        RelativeDiamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RDiamondDetail.getVisibility() == View.GONE){

                    RDiamondDetail.setVisibility(View.VISIBLE);

                }else {


                    RDiamondDetail.setVisibility(View.GONE);
                }

            }
        });



        GetData();


    }

    public void GetData(){


        FirstName.setText(MyProfile.Fname);
        LastName.setText(MyProfile.Lname);

        Glide.with(getApplicationContext()).load(MyProfile.url).into(HeadImg);

        //////

        PkgTname.setText(MyProfile.PkgTname.getText().toString());
        PkgTprice.setText(MyProfile.PkgTprice.getText().toString());
        PkgTdays.setText(MyProfile.PkgTdays.getText().toString());
        PkgTdescription.setText(MyProfile.PkgTdescription.getText().toString());

        PkgTname2.setText(MyProfile.PkgTname2.getText().toString());
        PkgTprice2.setText(MyProfile.PkgTprice2.getText().toString());
        PkgTdays2.setText(MyProfile.PkgTdays2.getText().toString());
        PkgTdescription2.setText(MyProfile.PkgTdescription2.getText().toString());

        PkgTname3.setText(MyProfile.PkgTname3.getText().toString());
        PkgTprice3.setText(MyProfile.PkgTprice3.getText().toString());
        PkgTdays3.setText(MyProfile.PkgTdays3.getText().toString());
        PkgTdescription3.setText(MyProfile.PkgTdescription3.getText().toString());

        PkgTname4.setText(MyProfile.PkgTname4.getText().toString());
        PkgTprice4.setText(MyProfile.PkgTprice4.getText().toString());
        PkgTdays4.setText(MyProfile.PkgTdays4.getText().toString());
        PkgTdescription4.setText(MyProfile.PkgTdescription4.getText().toString());

        PkgTname5.setText(MyProfile.PkgTname5.getText().toString());
        PkgTprice5.setText(MyProfile.PkgTprice5.getText().toString());
        PkgTdays5.setText(MyProfile.PkgTdays5.getText().toString());
        PkgTdescription5.setText(MyProfile.PkgTdescription5.getText().toString());





    }

    public void SaveData(){

        // Personal Info Save

        databaseReference.child("Users").child("Photographer")
                .child(firebaseAuth.getCurrentUser().getUid()).child("first_Name")
                .setValue(FirstName.getText().toString());

        databaseReference.child("Users").child("Photographer")
                .child(firebaseAuth.getCurrentUser().getUid()).child("last_Name")
                .setValue(LastName.getText().toString());


        if (flag == true){

            databaseReference.child("Users").child("Photographer")
                    .child(firebaseAuth.getCurrentUser().getUid()).child("profile_Img")
                    .setValue(profile_Img);


        }





        // Packages 1 Info Save

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package1").child("package_Name")
                .setValue(PkgTname.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package1").child("package_Price")
                .setValue(PkgTprice.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package1").child("services_Days")
                .setValue(PkgTdays.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package1").child("package_Description")
                .setValue(PkgTdescription.getText().toString());


        // Packages 2 Info Save

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package2").child("package_Name")
                .setValue(PkgTname2.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package2").child("package_Price")
                .setValue(PkgTprice2.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package2").child("services_Days")
                .setValue(PkgTdays2.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package2").child("package_Description")
                .setValue(PkgTdescription2.getText().toString());


        // Packages 3 Info Save

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package3").child("package_Name")
                .setValue(PkgTname3.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package3").child("package_Price")
                .setValue(PkgTprice3.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package3").child("services_Days")
                .setValue(PkgTprice3.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package3").child("package_Description")
                .setValue(PkgTdescription3.getText().toString());


        // Packages 4 Info Save

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package4").child("package_Name")
                .setValue(PkgTname4.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package4").child("package_Price")
                .setValue(PkgTprice4.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package4").child("services_Days")
                .setValue(PkgTdays4.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package4").child("package_Description")
                .setValue(PkgTdescription4.getText().toString());


        // Packages 5 Info Save

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package5").child("package_Name")
                .setValue(PkgTname5.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package5").child("package_Price")
                .setValue(PkgTprice5.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package5").child("services_Days")
                .setValue(PkgTdays5.getText().toString());

        databaseReference.child("Services").child(firebaseAuth.getCurrentUser().getUid())
                .child(MyProfile.key).child("Package5").child("package_Description")
                .setValue(PkgTdescription5.getText().toString());




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                progressDialog.dismiss();
                Intent intent = new Intent(EditProfile.this , MyProfile.class);
                startActivity(intent);
            }
        }, 5000);




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
                HeadImg.setImageBitmap(bitmap);
                uploadfile();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }


    private void uploadfile(){


        if (filepath != null){
            StorageReference riversRef = storageReference.child("images/"+firebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
            /// MAIN FOLDER MAIN JA K UID KA FOLDER HOGA THEN IMAGE HOGI:
            riversRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downUrl = taskSnapshot.getDownloadUrl();
                            // Log.d("downUrl" , downUrl.toString());
                            profile_Img = downUrl.toString();
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

            Toast.makeText(EditProfile.this , " This error from upload file" , Toast.LENGTH_SHORT).show();

        }


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditProfile.this , MyProfile.class);
        startActivity(intent);
    }


}
