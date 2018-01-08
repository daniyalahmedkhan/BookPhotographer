package com.example.kashif.bookphotographer.Activities;

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
import com.example.kashif.bookphotographer.Activities.ModelClass.PkgClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.Activities.PhotographerFlow.MyProfile;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Photographer_Profile extends AppCompatActivity {

    TextView FirstName , LastName , Gender , Location , HeadName;
    String Fname , Lname , Gend , Loc , url;
    ProgressDialog progressDialog;

    TextView PkgTname , PkgTprice , PkgTdays , PkgTdescription;
    TextView PkgTname2 , PkgTprice2 , PkgTdays2 , PkgTdescription2;
    TextView PkgTname3 , PkgTprice3 , PkgTdays3 , PkgTdescription3;
    TextView PkgTname4 , PkgTprice4 , PkgTdays4 , PkgTdescription4;
    TextView PkgTname5 , PkgTprice5 , PkgTdays5 , PkgTdescription5;

    String PkgName, PkgPrice , PkgDays, PkgDescription;


    ImageView HeadImage;
    DatabaseReference databaseReference;

    RelativeLayout RelativeBronze, RelativeSilver, RelativeGold, RelativePlatinum, RelativeDiamond;
    RelativeLayout RBronzeDetail, RSilverDetail, RGoldDetail, RPlatinumDetail, RDiamondDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer__profile);



        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        PkgTname = (TextView) findViewById(R.id.PgkTname1);
        PkgTprice = (TextView) findViewById(R.id.PkgTprice1);
        PkgTdescription  = (TextView) findViewById(R.id.PkgTdes1);

        PkgTname2 = (TextView) findViewById(R.id.PkgTname2);
        PkgTprice2 = (TextView) findViewById(R.id.PkgTprice2);
        PkgTdescription2  = (TextView) findViewById(R.id.PkgTdes2);


        PkgTname3 = (TextView) findViewById(R.id.PkgTname3);
        PkgTprice3 = (TextView) findViewById(R.id.PkgTprice3);
        PkgTdescription3  = (TextView) findViewById(R.id.PkgTdes3);

        PkgTname4 = (TextView) findViewById(R.id.PkgTname4);
        PkgTprice4 = (TextView) findViewById(R.id.PkgTprice4);
        PkgTdescription4  = (TextView) findViewById(R.id.PkgTdes4);


        PkgTname5 = (TextView) findViewById(R.id.PkgTname5);
        PkgTprice5 = (TextView) findViewById(R.id.PkgTprice5);
        PkgTdescription5  = (TextView) findViewById(R.id.PkgTdes5);



        FirstName = (TextView) findViewById(R.id.PhotographerFname);
        LastName = (TextView) findViewById(R.id.PhotographerLname);
        Gender = (TextView) findViewById(R.id.PhotographerGender);
        Location = (TextView) findViewById(R.id.PhotographerLocation);
        HeadName = (TextView) findViewById(R.id.HeadName);
        HeadImage = (ImageView) findViewById(R.id.HeadImg);


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

        getData();
        getPkg1();
        getPkg2();
        getPkg3();
        getPkg4();
        getPkg5();



    }


    public void getData(){

        if (databaseReference.child("users").child(SearchPhotographer.CurrntID) != null){

            databaseReference.child("users").child(SearchPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    UserModel userModel = dataSnapshot.getValue(UserModel.class);

                    if (userModel != null){


                        Fname = userModel.getFirstname();
                        Lname = userModel.getLastname();
                        Gend = userModel.getGender();
                        Loc = userModel.getCity();
                        url = userModel.getImageUrl();

                        String FirstLast = Fname + " " + Lname;
                        HeadName.setText(FirstLast);
                        FirstName.setText(Fname);
                        LastName.setText(Lname);
                        Gender.setText(Gend);
                        Location.setText(Loc);
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


        databaseReference.child("packages").child(SearchPhotographer.CurrntID).child("pkg1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                PkgClass pkgClass = dataSnapshot.getValue(PkgClass.class);

                if (!(pkgClass.getName().equals("unk") || pkgClass.getDescription().equals("unk") || pkgClass.getDays().equals("unk")
                        || pkgClass.getPrice().equals("unk"))){



                    PkgName = pkgClass.getName();
                    PkgPrice = pkgClass.getPrice();
                    PkgDescription = pkgClass.getDescription();

                    PkgTname.setText(PkgName);
                    PkgTprice.setText(PkgPrice);
                    PkgTdescription.setText(PkgDescription);




                }else {


                    RelativeBronze.setVisibility(View.GONE);

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getPkg2(){


        databaseReference.child("packages").child(SearchPhotographer.CurrntID).child("pkg2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                PkgClass pkgClass = dataSnapshot.getValue(PkgClass.class);

                if (!(pkgClass.getName().equals("unk") || pkgClass.getDescription().equals("unk") || pkgClass.getDays().equals("unk")
                        || pkgClass.getPrice().equals("unk"))){



                    PkgName = pkgClass.getName();
                    PkgPrice = pkgClass.getPrice();
                    PkgDescription = pkgClass.getDescription();

                    PkgTname2.setText(PkgName);
                    PkgTprice2.setText(PkgPrice);
                    PkgTdescription2.setText(PkgDescription);




                }else {


                    RelativeSilver.setVisibility(View.GONE);

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getPkg3(){


        databaseReference.child("packages").child(SearchPhotographer.CurrntID).child("pkg3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                PkgClass pkgClass = dataSnapshot.getValue(PkgClass.class);

                if (!(pkgClass.getName().equals("unk") || pkgClass.getDescription().equals("unk") || pkgClass.getDays().equals("unk")
                        || pkgClass.getPrice().equals("unk"))){



                    PkgName = pkgClass.getName();
                    PkgPrice = pkgClass.getPrice();
                    PkgDescription = pkgClass.getDescription();

                    PkgTname3.setText(PkgName);
                    PkgTprice3.setText(PkgPrice);
                    PkgTdescription3.setText(PkgDescription);




                }else {


                    RelativeGold.setVisibility(View.GONE);

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getPkg4(){


        databaseReference.child("packages").child(SearchPhotographer.CurrntID).child("pkg4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                PkgClass pkgClass = dataSnapshot.getValue(PkgClass.class);

                if (!(pkgClass.getName().equals("unk") || pkgClass.getDescription().equals("unk") || pkgClass.getDays().equals("unk")
                        || pkgClass.getPrice().equals("unk"))){



                    PkgName = pkgClass.getName();
                    PkgPrice = pkgClass.getPrice();
                    PkgDescription = pkgClass.getDescription();

                    PkgTname4.setText(PkgName);
                    PkgTprice4.setText(PkgPrice);
                    PkgTdescription4.setText(PkgDescription);




                }else {


                    RelativePlatinum.setVisibility(View.GONE);

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getPkg5(){


        databaseReference.child("packages").child(SearchPhotographer.CurrntID).child("pkg5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                PkgClass pkgClass = dataSnapshot.getValue(PkgClass.class);

                if (!(pkgClass.getName().equals("unk") || pkgClass.getDescription().equals("unk") || pkgClass.getDays().equals("unk")
                        || pkgClass.getPrice().equals("unk"))){



                    PkgName = pkgClass.getName();
                    PkgPrice = pkgClass.getPrice();
                    PkgDescription = pkgClass.getDescription();

                    PkgTname3.setText(PkgName);
                    PkgTprice3.setText(PkgPrice);
                    PkgTdescription3.setText(PkgDescription);




                }else {


                    RelativeDiamond.setVisibility(View.GONE);

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


}
