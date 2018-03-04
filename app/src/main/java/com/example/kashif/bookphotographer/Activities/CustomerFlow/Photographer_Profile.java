package com.example.kashif.bookphotographer.Activities.CustomerFlow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kashif.bookphotographer.Activities.Adapter.ImageViewExtend;
import com.example.kashif.bookphotographer.Activities.ModelClass.PackageClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.GalleryClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.Activities.PhotographerFlow.MyProfile;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Photographer_Profile extends AppCompatActivity {

    TextView FirstName , LastName , Gender , Location , HeadName , Contact;
    public static String Fname , Lname , Gend , Loc , url , contact;
    ProgressDialog progressDialog;
    ImageView image1,image2,image3,image4,image5,image6;
    public static String ImageLargeView;
    String imgUrl1 , imgUrl2 , imgUrl3 , imgUrl4 , imgUrl5 ,imgUrl6;
    Button BtnRes;

    public  static ArrayList<String> arrayList;

    TextView PkgTname , PkgTprice ,     PkgTdays , PkgTdescription;
    TextView PkgTname2 , PkgTprice2 , PkgTdays2 , PkgTdescription2;
    TextView PkgTname3 , PkgTprice3 , PkgTdays3 , PkgTdescription3;
    TextView PkgTname4 , PkgTprice4 , PkgTdays4 , PkgTdescription4;
    TextView PkgTname5 , PkgTprice5 , PkgTdays5 , PkgTdescription5;

    String PkgName, PkgPrice , PkgDays, PkgDescription  ,  key;


    ImageView HeadImage;
    DatabaseReference databaseReference;

    RelativeLayout RelativeBronze, RelativeSilver, RelativeGold, RelativePlatinum, RelativeDiamond;
    RelativeLayout RBronzeDetail, RSilverDetail, RGoldDetail, RPlatinumDetail, RDiamondDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer__profile);

        arrayList = new ArrayList<String>();

        image1 = (ImageView) findViewById(R.id.Image1);
        image2 = (ImageView) findViewById(R.id.Image2);
        image3 = (ImageView) findViewById(R.id.Image3);
        image4 = (ImageView) findViewById(R.id.Image4);
        image5 = (ImageView) findViewById(R.id.Image5);
        image6 = (ImageView) findViewById(R.id.Image6);

        BtnRes = (Button) findViewById(R.id.BtnRes);


        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        PkgTname = (TextView) findViewById(R.id.PgkTname1);
        PkgTprice = (TextView) findViewById(R.id.PkgTprice1);
        PkgTdays = (TextView) findViewById(R.id.Services1);
        PkgTdescription  = (TextView) findViewById(R.id.PkgTdes1);

        PkgTname2 = (TextView) findViewById(R.id.PkgTname2);
        PkgTprice2 = (TextView) findViewById(R.id.PkgTprice2);
        PkgTdays = (TextView) findViewById(R.id.Services2);
        PkgTdescription2  = (TextView) findViewById(R.id.PkgTdes2);


        PkgTname3 = (TextView) findViewById(R.id.PkgTname3);
        PkgTprice3 = (TextView) findViewById(R.id.PkgTprice3);
        PkgTdays = (TextView) findViewById(R.id.Services3);
        PkgTdescription3  = (TextView) findViewById(R.id.PkgTdes3);

        PkgTname4 = (TextView) findViewById(R.id.PkgTname4);
        PkgTprice4 = (TextView) findViewById(R.id.PkgTprice4);
        PkgTdays = (TextView) findViewById(R.id.Services4);
        PkgTdescription4  = (TextView) findViewById(R.id.PkgTdes4);


        PkgTname5 = (TextView) findViewById(R.id.PkgTname5);
        PkgTprice5 = (TextView) findViewById(R.id.PkgTprice5);
        PkgTdays = (TextView) findViewById(R.id.Services5);
        PkgTdescription5  = (TextView) findViewById(R.id.PkgTdes5);



        FirstName = (TextView) findViewById(R.id.PhotographerFname);
        LastName = (TextView) findViewById(R.id.PhotographerLname);
        Gender = (TextView) findViewById(R.id.PhotographerGender);
        Location = (TextView) findViewById(R.id.PhotographerLocation);
        HeadName = (TextView) findViewById(R.id.HeadName);
        HeadImage = (ImageView) findViewById(R.id.HeadImg);
        Contact = (TextView) findViewById(R.id.Contact);


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


        BtnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Photographer_Profile.this , BookingActivity.class);
                startActivity(intent);

            }
        });



        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageLargeView = imgUrl1;
                Intent intent = new Intent(Photographer_Profile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });


        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageLargeView = imgUrl2;
                Intent intent = new Intent(Photographer_Profile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageLargeView = imgUrl3;
                Intent intent = new Intent(Photographer_Profile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageLargeView = imgUrl4;
                Intent intent = new Intent(Photographer_Profile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageLargeView = imgUrl5;
                Intent intent = new Intent(Photographer_Profile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageLargeView = imgUrl6;
                Intent intent = new Intent(Photographer_Profile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });



        getData();
        getPkg1();
        getPkg2();
        getPkg3();
        getPkg4();
        getPkg5();
        getImages();


    }


    public void getData(){

        if (databaseReference.child("Users").child("Photographer").child(ShowPhotographer.CurrntID) != null){

            databaseReference.child("Users").child("Photographer").child(ShowPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    UserModel userModel = dataSnapshot.getValue(UserModel.class);

                    if (userModel != null){


                        Fname = userModel.getFirst_Name();
                        Lname = userModel.getLast_Name();
                        Gend = userModel.getGender();
                        Loc = userModel.getCity_Des();
                        url = userModel.getProfile_Img();
                        contact = userModel.getContact_No();

                        String FirstLast = Fname + " " + Lname;
                        HeadName.setText(FirstLast);
                        FirstName.setText(Fname);
                        LastName.setText(Lname);
                        Gender.setText(Gend);
                        Location.setText(Loc);
                        Contact.setText(contact);
                        Glide.with(getApplicationContext()).load(url).into(HeadImage);



                    }else {

                        Toast.makeText(Photographer_Profile.this , "EMPTY" , Toast.LENGTH_LONG).show();
                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }



    }


    public  void getPkg1(){


        databaseReference.child("Services").child(ShowPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {


                    key = snapshot.getKey().toString();

                    if (dataSnapshot.child(key).child("Package1").exists()) {

                        PackageClass packageClass = dataSnapshot.child(key).child("Package1").getValue(PackageClass.class);


                        if (!(packageClass.getPackage_Name().equals("unk") || packageClass.getPackage_Description().equals("unk") || packageClass.getServices_Days().equals("unk")
                                || packageClass.getPackage_Price().equals("unk"))) {


                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                            PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();

                            PkgTname.setText(PkgName);
                            PkgTprice.setText(PkgPrice);
                            PkgTdays.setText(PkgDays);
                            PkgTdescription.setText(PkgDescription);
                            arrayList.add(PkgName);
                        }else {


                            RelativeBronze.setVisibility(View.GONE);


                        }

                    }else{

                        RelativeBronze.setVisibility(View.GONE);

                    }

                }




            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getPkg2(){


        databaseReference.child("Services").child(ShowPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()){


                    key = snapshot1.getKey().toString();


                    if (dataSnapshot.child(key).child("Package2").exists()){


                        PackageClass packageClass = dataSnapshot.child(key).child("Package2").getValue(PackageClass.class);

                        if (!(packageClass.getPackage_Name().equals("unk") || packageClass.getPackage_Description().equals("unk") || packageClass.getServices_Days().equals("unk")
                                || packageClass.getPackage_Price().equals("unk"))){



                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                           PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();

                            PkgTname2.setText(PkgName);
                            PkgTprice2.setText(PkgPrice);
                            PkgTdays2.setText(PkgDays);
                            PkgTdescription2.setText(PkgDescription);
                            arrayList.add(PkgName);




                        } else {


                            RelativeSilver.setVisibility(View.GONE);

                        }

                    }

                    else {

                        RelativeSilver.setVisibility(View.GONE);
                    }

                }


            }






            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getPkg3(){


        databaseReference.child("Services").child(ShowPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    key = snapshot.getKey().toString();



                    if (dataSnapshot.child(key).child("Package3").exists()){
                        PackageClass packageClass = dataSnapshot.child(key).child("Package3").getValue(PackageClass.class);

                        if (!(packageClass.getPackage_Name().equals("unk") || packageClass.getPackage_Description().equals("unk") || packageClass.getServices_Days().equals("unk")
                                || packageClass.getPackage_Price().equals("unk"))){



                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                            PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();

                            PkgTname3.setText(PkgName);
                            PkgTprice3.setText(PkgPrice);
                            PkgTdays3.setText(PkgDays);
                            PkgTdescription3.setText(PkgDescription);
                            arrayList.add(PkgName);



                        }else {


                            RelativeGold.setVisibility(View.GONE);

                        }






                    }else{

                        RelativeGold.setVisibility(View.GONE);


                    }

                }





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getPkg4(){


        databaseReference.child("Services").child(ShowPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    key = snapshot.getKey().toString();

                    if (dataSnapshot.child(key).child("Package4").exists()){




                        PackageClass packageClass = dataSnapshot.child(key).child("Package4").getValue(PackageClass.class);

                        if (!(packageClass.getPackage_Name().equals("unk") || packageClass.getPackage_Description().equals("unk") || packageClass.getServices_Days().equals("unk")
                                || packageClass.getPackage_Price().equals("unk"))){



                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                            PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();

                            PkgTname4.setText(PkgName);
                            PkgTprice4.setText(PkgPrice);
                            PkgTdays4.setText(PkgDays);
                            PkgTdescription4.setText(PkgDescription);
                            arrayList.add(PkgName);



                        }else {


                            RelativePlatinum.setVisibility(View.GONE);

                        }



                    }else {


                        RelativePlatinum.setVisibility(View.GONE);

                    }

                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getPkg5(){


        databaseReference.child("Services").child(ShowPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot snapshot : dataSnapshot.getChildren()){


                    key = snapshot.getKey().toString();

                    if (dataSnapshot.child(key).child("Package5").exists()){


                        PackageClass packageClass = dataSnapshot.child(key).child("Package5").getValue(PackageClass.class);

                        if (!(packageClass.getPackage_Name().equals("unk") || packageClass.getPackage_Description().equals("unk") || packageClass.getServices_Days().equals("unk")
                                || packageClass.getPackage_Price().equals("unk"))){



                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                            PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();

                            PkgTname5.setText(PkgName);
                            PkgTprice5.setText(PkgPrice);
                            PkgTdays5.setText(PkgDays);
                            PkgTdescription5.setText(PkgDescription);
                            arrayList.add(PkgName);




                        }else {


                            RelativeDiamond.setVisibility(View.GONE);

                        }








                    }else {

                        RelativeDiamond.setVisibility(View.GONE);

                    }

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getImages(){


        databaseReference.child("Gallery").child(ShowPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){



                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){





                        GalleryClass galleryClass = snapshot.getValue(GalleryClass.class);

                        imgUrl1 = galleryClass.getImage1();
                        imgUrl2 = galleryClass.getImage2();
                        imgUrl3 = galleryClass.getImage3();
                        imgUrl4 = galleryClass.getImage4();
                        imgUrl5 = galleryClass.getImage5();
                        imgUrl6 = galleryClass.getImage6();



                    }

                    Glide.with(getApplicationContext()).load(imgUrl1).into(image1);
                    Glide.with(getApplicationContext()).load(imgUrl2).into(image2);
                    Glide.with(getApplicationContext()).load(imgUrl3).into(image3);
                    Glide.with(getApplicationContext()).load(imgUrl4).into(image4);
                    Glide.with(getApplicationContext()).load(imgUrl5).into(image5);
                    Glide.with(getApplicationContext()).load(imgUrl6).into(image6);





                }else {

                    Toast.makeText(Photographer_Profile.this, "Not Found Images", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }



}
