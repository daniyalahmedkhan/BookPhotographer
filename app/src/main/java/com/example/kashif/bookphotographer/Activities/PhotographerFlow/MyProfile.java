package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kashif.bookphotographer.Activities.Adapter.CustomDrawerUser;
import com.example.kashif.bookphotographer.Activities.HomeActivity;
import com.example.kashif.bookphotographer.Activities.LoginActivity;
import com.example.kashif.bookphotographer.Activities.ModelClass.BookReservation;
import com.example.kashif.bookphotographer.Activities.ModelClass.PkgClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.Activities.UserBookingManage;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyProfile extends AppCompatActivity implements  View.OnClickListener{

    TextView FirstName , LastName , Gender , Location , HeadName;
    String Fname , Lname , Gend , Loc , url , ID ,  PID , key;
    ProgressDialog progressDialog;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ImageView ImageDrawer;
    String names[];

    int couter = 1;

    FirebaseAuth firebaseAuth;

    TextView Emp_Name;
    ImageView proImg;

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

    public static ArrayList<String> Order , Photographer , EventDate, EventVenue, Pckg , id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);



        ImageDrawer = (ImageView) findViewById(R.id.ImageDrawer);


        Order = new ArrayList<String>();
        Photographer = new ArrayList<String>();
        EventDate = new ArrayList<String>();
        EventVenue = new ArrayList<String>();
        Pckg = new ArrayList<String>();
        id = new ArrayList<String>();



        firebaseAuth = FirebaseAuth.getInstance();

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



        //// Side Drawer///


        names = new String[]{"My Request" , "Logout"};
        int img[] = {R.mipmap.camera , R.mipmap.logout_sidemenu_icon};
        //mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.Left_Drawer);

        mDrawerList.setFitsSystemWindows(true);

        mDrawerList.setAdapter(new CustomDrawerUser(this, img, names));
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.drawer_header, mDrawerList, false);


        Emp_Name = (TextView) header.findViewById(R.id.Emp_Name);
        proImg = (ImageView) header.findViewById(R.id.proImg);


        mDrawerList.addHeaderView(header, null, false);

        ImageDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mDrawerLayout.isDrawerOpen(Gravity.LEFT))
                {
                    mDrawerLayout.closeDrawer(mDrawerList);
                    // getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    // getSupportActionBar().setCustomView(R.layout.menu_title);
                    // getSupportActionBar().show();


                }
                else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    //getSupportActionBar().hide();
                    // requestWindowFeature(Window.FEATURE_NO_TITLE);
                }

            }
        });

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        //////// Side Drawer End //////






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


//            getReqData();
        getData();
   getPkg1();
        getPkg2();
//        getPkg3();
//        getPkg4();
//        getPkg5();


    }


    public void getData(){

        if (databaseReference.child("Users").child("Photographer").child(LoginActivity.uid) != null){

            databaseReference.child("Users").child("Photographer").child(LoginActivity.uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    final UserModel userModel = dataSnapshot.getValue(UserModel.class);

                    if (userModel != null){


                        Fname = userModel.getFirst_Name();
                        Lname = userModel.getLast_Name();
                        Gend = userModel.getGender();
                        url = userModel.getProfile_Img();
                        ID = userModel.getPhotographer_ID();


                        getLocation();


                        String FirstLast = Fname + " " + Lname;
                        HeadName.setText(FirstLast);
                        Emp_Name.setText(FirstLast);
                        FirstName.setText(Fname);
                        LastName.setText(Lname);
                        Gender.setText(Gend);

                        Glide.with(getApplicationContext()).load(url).into(HeadImage);
                        Glide.with(getApplicationContext()).load(url).into(proImg);




                    }else {

                        Toast.makeText(MyProfile.this , "EMPTY" , Toast.LENGTH_LONG).show();
                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }



    }

    public void  getLocation(){


        databaseReference.child("Users").child("Photographer").child("City").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                 for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                        UserModel userModel1 = snapshot.getValue(UserModel.class);

                       PID  = userModel1.getPhotographer_ID();


                        if (ID.equals(PID)) {

                            Loc = userModel1.getCity_Des();
                            Location.setText(Loc);
                            Toast.makeText(MyProfile.this , "Proof" + Loc, Toast.LENGTH_SHORT).show();
                        }else {

                            Toast.makeText(MyProfile.this , "Not Proof" , Toast.LENGTH_SHORT).show();

                            Loc = "buubububu";
                        }




                 }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    public  void getPkg1(){


        databaseReference.child("Services").child(LoginActivity.uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot: dataSnapshot.getChildren()){



                    key = snapshot.getKey().toString();

                PkgClass pkgClass = dataSnapshot.child(key).child("Package1").getValue(PkgClass.class);


                PkgName = pkgClass.getPackage_Name();
                PkgPrice = pkgClass.getPackage_Price();
                PkgDescription = pkgClass.getPackage_Description();

                PkgTname.setText(PkgName);
                PkgTprice.setText(PkgPrice);
                PkgTdescription.setText(PkgDescription);


                  }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void getPkg2(){


        databaseReference.child("Services").child(LoginActivity.uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                 for (DataSnapshot snapshot1 : dataSnapshot.getChildren()){


                     key = snapshot1.getKey().toString();


                     PkgClass pkgClass = dataSnapshot.child(key).child("Package2").getValue(PkgClass.class);

                        if (!(pkgClass.getPackage_Name().equals("unk") || pkgClass.getPackage_Description().equals("unk") || pkgClass.getServices_Days().equals("unk")
                                || pkgClass.getPackage_Price().equals("unk"))){



                            PkgName = pkgClass.getPackage_Name();
                            PkgPrice = pkgClass.getPackage_Price();
                            PkgDescription = pkgClass.getPackage_Description();

                            PkgTname2.setText(PkgName);
                            PkgTprice2.setText(PkgPrice);
                            PkgTdescription2.setText(PkgDescription);




                        }else {


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


        databaseReference.child("Services").child(LoginActivity.uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    key = snapshot.getKey().toString();


                    PkgClass pkgClass = dataSnapshot.child(key).child("Package3").getValue(PkgClass.class);

                        if (!(pkgClass.getPackage_Name().equals("unk") || pkgClass.getPackage_Description().equals("unk") || pkgClass.getServices_Days().equals("unk")
                                || pkgClass.getPackage_Price().equals("unk"))){



                            PkgName = pkgClass.getPackage_Name();
                            PkgPrice = pkgClass.getPackage_Price();
                            PkgDescription = pkgClass.getPackage_Description();

                            PkgTname3.setText(PkgName);
                            PkgTprice3.setText(PkgPrice);
                            PkgTdescription3.setText(PkgDescription);




                        }else {


                            RelativeGold.setVisibility(View.GONE);

                        }








                }





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

//    public  void getPkg4(){
//
//
//        databaseReference.child("packages").child(LoginActivity.uid).child("pkg4").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//
//                PkgClass pkgClass = dataSnapshot.getValue(PkgClass.class);
//
//                if (!(pkgClass.getName().equals("unk") || pkgClass.getDescription().equals("unk") || pkgClass.getDays().equals("unk")
//                        || pkgClass.getPrice().equals("unk"))){
//
//
//
//                    PkgName = pkgClass.getName();
//                    PkgPrice = pkgClass.getPrice();
//                    PkgDescription = pkgClass.getDescription();
//
//                    PkgTname4.setText(PkgName);
//                    PkgTprice4.setText(PkgPrice);
//                    PkgTdescription4.setText(PkgDescription);
//
//
//
//
//                }else {
//
//
//                    RelativePlatinum.setVisibility(View.GONE);
//
//                }
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//    }
//
//    public  void getPkg5(){
//
//
//        databaseReference.child("packages").child(LoginActivity.uid).child("pkg5").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//
//                PkgClass pkgClass = dataSnapshot.getValue(PkgClass.class);
//
//                if (!(pkgClass.getName().equals("unk") || pkgClass.getDescription().equals("unk") || pkgClass.getDays().equals("unk")
//                        || pkgClass.getPrice().equals("unk"))){
//
//
//
//                    PkgName = pkgClass.getName();
//                    PkgPrice = pkgClass.getPrice();
//                    PkgDescription = pkgClass.getDescription();
//
//                    PkgTname3.setText(PkgName);
//                    PkgTprice3.setText(PkgPrice);
//                    PkgTdescription3.setText(PkgDescription);
//
//
//
//
//                }else {
//
//
//                    RelativeDiamond.setVisibility(View.GONE);
//
//                }
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//    }


    @Override
    public void onClick(View view) {

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    public  void  selectItem(int pos){

        Intent i;

        switch (pos){

            case 1:

                i = new Intent(MyProfile.this, photographerBookingManage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
                break;


            case 2:

                FirebaseAuth.getInstance().signOut();
                i = new Intent(MyProfile.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;



            default:
                break;

        }


    }
    public void getReqData(){

        databaseReference.child("bookres").child("forphotographer").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                        BookReservation bookReservation = snapshot.getValue(BookReservation.class);

                        Order.add(String.valueOf(couter));


//                        id.add(bookReservation.getId());
//                        Photographer.add(bookReservation.getPhotographername());
//                        EventDate.add(bookReservation.getOcc());
//                        EventVenue.add(bookReservation.getVen());
//                        Pckg.add(bookReservation.getPkg());
//                        couter++;





                    }



//






                }else {


                    Toast.makeText(MyProfile.this , "NO REQUEST FOUND" , Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
