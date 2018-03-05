package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.example.kashif.bookphotographer.Activities.Adapter.ImageViewExtend;
import com.example.kashif.bookphotographer.Activities.CustomerFlow.Photographer_Profile;
import com.example.kashif.bookphotographer.Activities.CustomerFlow.ShowPhotographer;
import com.example.kashif.bookphotographer.Activities.ModelClass.GalleryClass;
import com.example.kashif.bookphotographer.Activities.UserAuth.LoginActivity;
import com.example.kashif.bookphotographer.Activities.ModelClass.BookReservation;
import com.example.kashif.bookphotographer.Activities.ModelClass.PackageClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyProfile extends AppCompatActivity implements  View.OnClickListener{

    TextView FirstName , LastName , Gender , Location , HeadName , Contact;
    public static  String Fname , Lname , Gend , contact ,  Loc , url , ID ,  PID , key;
    ProgressDialog progressDialog;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ImageView ImageDrawer;
    String names[];
    public static String ImageLargeView;
    ImageView image1,image2,image3,image4,image5,image6;
    String imgUrl1 , imgUrl2 , imgUrl3 , imgUrl4 , imgUrl5 ,imgUrl6;


    FirebaseAuth firebaseAuth;

    TextView Emp_Name , EditProfile;

    ImageView proImg;

   public static TextView PkgTname , PkgTprice ,   PkgTdays , PkgTdescription;
   public static TextView PkgTname2 , PkgTprice2 , PkgTdays2 , PkgTdescription2;
   public static TextView PkgTname3 , PkgTprice3 , PkgTdays3 , PkgTdescription3;
   public static TextView PkgTname4 , PkgTprice4 , PkgTdays4 , PkgTdescription4;
   public static TextView PkgTname5 , PkgTprice5 , PkgTdays5 , PkgTdescription5;

    String PkgName, PkgPrice , PkgDays, PkgDescription;

    ImageView HeadImage;
    DatabaseReference databaseReference;

  public static   int reqCounter;

    RelativeLayout RelativeBronze, RelativeSilver, RelativeGold, RelativePlatinum, RelativeDiamond;
    RelativeLayout RBronzeDetail, RSilverDetail, RGoldDetail, RPlatinumDetail, RDiamondDetail;

    public static ArrayList<String> Order , Photographer , EventDate, EventVenue, Pckg , id , status , resId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        image1 = (ImageView) findViewById(R.id.Image1);
        image2 = (ImageView) findViewById(R.id.Image2);
        image3 = (ImageView) findViewById(R.id.Image3);
        image4 = (ImageView) findViewById(R.id.Image4);
        image5 = (ImageView) findViewById(R.id.Image5);
        image6 = (ImageView) findViewById(R.id.Image6);



        ImageDrawer = (ImageView) findViewById(R.id.ImageDrawer);


        Order = new ArrayList<String>();
        Photographer = new ArrayList<String>();
        EventDate = new ArrayList<String>();
        EventVenue = new ArrayList<String>();
        Pckg = new ArrayList<String>();
        id = new ArrayList<String>();
        status = new ArrayList<String>();
        resId = new ArrayList<String>();



        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        PkgTname = (TextView) findViewById(R.id.PgkTname1);
        PkgTdays = (TextView) findViewById(R.id.Services1);
        PkgTprice = (TextView) findViewById(R.id.PkgTprice1);
        PkgTdescription  = (TextView) findViewById(R.id.PkgTdes1);

        PkgTname2 = (TextView) findViewById(R.id.PkgTname2);
        PkgTprice2 = (TextView) findViewById(R.id.PkgTprice2);
        PkgTdays2 = (TextView) findViewById(R.id.Services2);
        PkgTdescription2  = (TextView) findViewById(R.id.PkgTdes2);


        PkgTname3 = (TextView) findViewById(R.id.PkgTname3);
        PkgTprice3 = (TextView) findViewById(R.id.PkgTprice3);
        PkgTdays3 = (TextView) findViewById(R.id.Services3);
        PkgTdescription3  = (TextView) findViewById(R.id.PkgTdes3);

        PkgTname4 = (TextView) findViewById(R.id.PkgTname4);
        PkgTprice4 = (TextView) findViewById(R.id.PkgTprice4);
        PkgTdays4 = (TextView) findViewById(R.id.Services4);
        PkgTdescription4  = (TextView) findViewById(R.id.PkgTdes4);


        PkgTname5 = (TextView) findViewById(R.id.PkgTname5);
        PkgTprice5 = (TextView) findViewById(R.id.PkgTprice5);
        PkgTdays5 = (TextView) findViewById(R.id.Services5);
        PkgTdescription5  = (TextView) findViewById(R.id.PkgTdes5);



        FirstName = (TextView) findViewById(R.id.PhotographerFname);
        LastName = (TextView) findViewById(R.id.PhotographerLname);
        Gender = (TextView) findViewById(R.id.PhotographerGender);
        Location = (TextView) findViewById(R.id.PhotographerLocation);
        HeadName = (TextView) findViewById(R.id.HeadName);
        HeadImage = (ImageView) findViewById(R.id.HeadImg);
        Contact = (TextView) findViewById(R.id.contact);


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
        EditProfile = (TextView) header.findViewById(R.id.view_emp_email);


        mDrawerList.addHeaderView(header, null, false);


        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MyProfile.this  , EditProfile.class);
                startActivity(intent);
                MyProfile.this.finish();
            }
        });

        ImageDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mDrawerLayout.isDrawerOpen(Gravity.LEFT))
                {
                    mDrawerLayout.closeDrawer(mDrawerList);


                }
                else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);

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

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageViewExtend.ImageUrl = imgUrl1;
                Intent intent = new Intent(MyProfile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });


        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageViewExtend.ImageUrl = imgUrl2;
                Intent intent = new Intent(MyProfile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageViewExtend.ImageUrl = imgUrl3;
                Intent intent = new Intent(MyProfile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageViewExtend.ImageUrl = imgUrl4;
                Intent intent = new Intent(MyProfile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageViewExtend.ImageUrl = imgUrl5;
                Intent intent = new Intent(MyProfile.this , ImageViewExtend.class);
                startActivity(intent);

            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageViewExtend.ImageUrl = imgUrl6;
                Intent intent = new Intent(MyProfile.this , ImageViewExtend.class);
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
        getPendingRequest();


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
                        contact = userModel.getContact_No();


                        getLocation();


                        String FirstLast = Fname + " " + Lname;
                        HeadName.setText(FirstLast);
                        Emp_Name.setText(FirstLast);
                        FirstName.setText(Fname);
                        LastName.setText(Lname);
                        Gender.setText(Gend);
                        Contact.setText(contact);

                        Glide.with(getApplicationContext()).load(url).into(HeadImage);
                        Glide.with(getApplicationContext()).load(url).into(proImg);




                    }else {

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
                        }else {


                            Loc = "Nill";
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
                        }else {


                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                            PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();

                            PkgTname.setText(PkgName);
                            PkgTprice.setText(PkgPrice);
                            PkgTdays.setText(PkgDays);
                            PkgTdescription.setText(PkgDescription);


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


        databaseReference.child("Services").child(LoginActivity.uid).addValueEventListener(new ValueEventListener() {
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




                        } else {

                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                            PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();


                            PkgTname2.setText(PkgName);
                            PkgTprice2.setText(PkgPrice);
                            PkgTdays2.setText(PkgDays);
                            PkgTdescription2.setText(PkgDescription);



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


        databaseReference.child("Services").child(LoginActivity.uid).addValueEventListener(new ValueEventListener() {
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




                        }else {

                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                            PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();

                            PkgTname3.setText(PkgName);
                            PkgTprice3.setText(PkgPrice);
                            PkgTdays3.setText(PkgDays);
                            PkgTdescription3.setText(PkgDescription);




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


        databaseReference.child("Services").child(LoginActivity.uid).addValueEventListener(new ValueEventListener() {
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




                        }else {


                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                            PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();

                            PkgTname4.setText(PkgName);
                            PkgTprice4.setText(PkgPrice);
                            PkgTdays4.setText(PkgDays);
                            PkgTdescription4.setText(PkgDescription);



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


        databaseReference.child("Services").child(LoginActivity.uid).addValueEventListener(new ValueEventListener() {
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




                        }else {


                            PkgName = packageClass.getPackage_Name();
                            PkgPrice = packageClass.getPackage_Price();
                            PkgDays = packageClass.getServices_Days();
                            PkgDescription = packageClass.getPackage_Description();

                            PkgTname5.setText(PkgName);
                            PkgTprice5.setText(PkgPrice);
                            PkgTdays5.setText(PkgDays);
                            PkgTdescription5.setText(PkgDescription);




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


    public void getPendingRequest(){


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                databaseReference.child("ReservationDetail").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        reqCounter = 0;

                        if (dataSnapshot.exists()) {

                            reqCounter = 0;

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                                    BookReservation bookReservation = snapshot1.getValue(BookReservation.class);

                                    if (bookReservation.getPhotographer_ID().equals(firebaseAuth.getCurrentUser().getUid())) {

                                        if (bookReservation.getReservation_Status().equals("Pending")) {

                                            reqCounter++;


                                        }

                                    }


                                }


                            }


                        }else {


                        }

                        int mNotificationId = 001;

                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(MyProfile.this)
                                        .setSmallIcon(R.drawable.logo)
                                        .setContentTitle("Book Photographer")
                                        .setContentText("You have " + String.valueOf(reqCounter) + " Pending Request")
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setPriority(Notification.PRIORITY_MAX)
                                        .setAutoCancel(true);


                        PendingIntent contentIntent = PendingIntent.getActivity(MyProfile.this, 0,
                                new Intent(MyProfile.this, photographerBookingManage.class), PendingIntent.FLAG_UPDATE_CURRENT);


                        mBuilder.setContentIntent(contentIntent);


                        // Gets an instance of the NotificationManager service
                        NotificationManager mNotifyMgr =
                                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


                        if (reqCounter != 0){

                            mNotifyMgr.notify(mNotificationId, mBuilder.build());

                        }

                        // Builds the notification and issues it.




                    }






                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        }, 5000);




    }


    public  void getImages(){


        databaseReference.child("Gallery").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
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

                    Toast.makeText(MyProfile.this, "Not Found Images", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}