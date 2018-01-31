package com.example.kashif.bookphotographer.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.example.kashif.bookphotographer.Activities.Adapter.CustomDrawerUser;
import com.example.kashif.bookphotographer.Activities.ModelClass.BookReservation;
import com.example.kashif.bookphotographer.Activities.ModelClass.PkgClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements  View.OnClickListener{

    EditText editText;
    String ESearch;
    public static ArrayList<String> name , loc , imgrl, id;
    TextView search;
    DatabaseReference databaseReference;
    ImageView ImageDrawer;
    String names[];
    FirebaseAuth firebaseAuth;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    int couter =1 ;
    EditText Name , Location , Price;
    String GetName , GetLocation , GetPrice;
    public static  ArrayList<String> Order , Photographer , EventDate, EventVenue, Pckg;
    public static ArrayList<String> Pname , Ploc, Pimgurl , Pid;



//    public static   String[] gridViewString ;
//
//    public static   String[] gridViewString2 ;
//
//
//
//    public static   String[] gridViewImageId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        name = new ArrayList<String>();
        loc = new ArrayList<String>();
        imgrl = new ArrayList<String>();
        id = new ArrayList<String>();


        Pname = new ArrayList<String>();
        Ploc = new ArrayList<String>();
        Pimgurl = new ArrayList<String>();
        Pid = new ArrayList<String>();



        Order = new ArrayList<String>();
        Photographer = new ArrayList<String>();
        EventDate = new ArrayList<String>();
        EventVenue = new ArrayList<String>();
        Pckg = new ArrayList<String>();



        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.bck5);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        search = (TextView) findViewById(R.id.search);
        editText = (EditText) findViewById(R.id.EditTxt);
        ESearch = null;
        ImageDrawer = (ImageView) findViewById(R.id.ImageDrawer);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name.clear();
                loc.clear();
                imgrl.clear();
                id.clear();

                Search();
            }
        });


        //// Side Drawer///


        names = new String[]{"My Request" , "Logout"};
        int img[] = {R.mipmap.logout_sidemenu_icon , R.mipmap.logout_sidemenu_icon };
        //mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.Left_Drawer);

        mDrawerList.setFitsSystemWindows(true);

        mDrawerList.setAdapter(new CustomDrawerUser(this, img, names));
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.drawer_header, mDrawerList, false);


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




       // getReqData();

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


                    i = new Intent(HomeActivity.this, UserBookingManage.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                break;

            case 2:

                FirebaseAuth.getInstance().signOut();
                i = new Intent(HomeActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;


             default:
                 break;

        }


    }

    public void getReqData(){

        databaseReference.child("bookres").child("foruser").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                        BookReservation bookReservation = snapshot.getValue(BookReservation.class);

//                        Order.add(String.valueOf(couter));
//                        Photographer.add(bookReservation.getPhotographername());
//                        EventDate.add(bookReservation.getOcc());
//                        EventVenue.add(bookReservation.getVen());
//                        Pckg.add(bookReservation.getPkg());
//                        couter++;





                    }



//






                }else {


                    Toast.makeText(HomeActivity.this , "NO RESERVATION FOUND" , Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void Search(){



        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.search_photographer_prompt, null);
        builder.setView(view);


        Name = (EditText) view.findViewById(R.id.EditName);
        Location = (EditText) view.findViewById(R.id.EditLocation);
        Price = (EditText) view.findViewById(R.id.EditPrice);


        final AlertDialog b = builder.create();
        b.show();


        Button buttonSearch = (Button) view.findViewById(R.id.OK);
        Button buttonCancel = (Button) view.findViewById(R.id.CANCEL);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             GetName = Name.getText().toString().trim();
             GetLocation = Location.getText().toString().trim();
             GetPrice = Price.getText().toString();

             Name.setText("");
             Location.setText("");
             Price.setText("");



            if ((GetName.isEmpty() && GetLocation.isEmpty()
                    && GetPrice.isEmpty())){


                Name.setError("Please Enter before search");
                Location.setError("Please Enter before search");
                Price.setError("Please Enter before search");

            }else if(((!GetName.isEmpty()) && GetLocation.isEmpty()
                    && GetPrice.isEmpty())){

                NameSearch();


            }else if(((GetName.isEmpty()) && (!GetLocation.isEmpty())
                    && GetPrice.isEmpty())){

                LocationSearch();


            }else if(((GetName.isEmpty()) && GetLocation.isEmpty()
                    && (!GetPrice.isEmpty()))){

                PriceSearch();

            }else if(((!GetName.isEmpty()) && (!GetLocation.isEmpty())
                    && GetPrice.isEmpty())){

                NameLocationSearch();



            }else if(((!GetName.isEmpty()) && GetLocation.isEmpty()
                    && (!GetPrice.isEmpty()))){


                NamePriceSearch();

            }else if((GetName.isEmpty() && (!GetLocation.isEmpty())
                    && (!GetPrice.isEmpty()))){


                LocationPriceSearch();

            }else if(!((GetName.isEmpty() && GetLocation.isEmpty()
                    && GetPrice.isEmpty()))){

                NameLocationPriceSearch();

            }else {

                Toast.makeText(HomeActivity.this , "What to Search" , Toast.LENGTH_SHORT).show();


            }



                b.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b.dismiss();

            }
        });



    }


    public void NameSearch(){

        databaseReference.child("users").orderByChild("firstname").equalTo(GetName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){


                    name.clear();
                    loc.clear();
                    imgrl.clear();
                    id.clear();


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                        UserModel userModel = snapshot.getValue(UserModel.class);




                        name.add(userModel.getFirstname());
                        id.add(userModel.getId());
                        loc.add(userModel.getCity());
                        imgrl.add(userModel.getImageUrl()); }


                     SearchPhotographer searchPhotographer = new SearchPhotographer(name.toArray(new String[name.size()]) , loc.toArray(new String[loc.size()]) , imgrl.toArray(new String[imgrl.size()]) , id.toArray(new String[id.size()]) );

//             img = String.valueOf(Glide.with(getApplicationContext()).load(imgrl));

                  //  Toast.makeText(HomeActivity.this , "name"+ name , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, SearchPhotographer.class);// New activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }else {


                    Toast.makeText(HomeActivity.this , "Not Found " , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void LocationSearch(){


        databaseReference.child("users").orderByChild("city").equalTo(GetLocation).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    name.clear();
                    loc.clear();
                    imgrl.clear();
                    id.clear();


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                        UserModel userModel = snapshot.getValue(UserModel.class);





                        name.add(userModel.getFirstname());
                        id.add(userModel.getId());
                        loc.add(userModel.getCity());
                        imgrl.add(userModel.getImageUrl()); }

//             img = String.valueOf(Glide.with(getApplicationContext()).load(imgrl));


                    SearchPhotographer searchPhotographer = new SearchPhotographer(name.toArray(new String[name.size()]) , loc.toArray(new String[loc.size()]) , imgrl.toArray(new String[imgrl.size()]) , id.toArray(new String[id.size()]) );

                    Intent intent = new Intent(HomeActivity.this, SearchPhotographer.class);// New activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();


                }else {


                    Toast.makeText(HomeActivity.this , "Not Found " , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void PriceSearch(){


        databaseReference.child("users").orderByChild("type").equalTo("photographer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){


                    name.clear();
                    loc.clear();
                    imgrl.clear();
                    id.clear();



                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                     UserModel   userModel   = snapshot.getValue(UserModel.class);


                        name.add(userModel.getFirstname());
                        id.add(userModel.getId());
                        loc.add(userModel.getCity());
                        imgrl.add(userModel.getImageUrl());

                    }
        for (int i=0; i<id.size(); i++){


            final int finalI = i;
            databaseReference.child("packages").child(id.get(i)).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {

                           for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                               PkgClass pkgClass = snapshot.getValue(PkgClass.class);

                               if (GetPrice.equals(pkgClass.getPrice())){

                                    Pname.add(name.get(finalI));
                                    Ploc.add(loc.get(finalI));
                                    Pimgurl.add(imgrl.get(finalI));
                                    Pid.add(id.get(finalI));

                             //      Toast.makeText(HomeActivity.this, "" + id.get(finalI) , Toast.LENGTH_SHORT).show();


                               }
                           }

                                   SearchPhotographer searchPhotographer = new SearchPhotographer(Pname.toArray(new String[Pname.size()]) , Ploc.toArray(new String[Ploc.size()]) , Pimgurl.toArray(new String[Pimgurl.size()]) , Pid.toArray(new String[Pid.size()]) );

                                   Intent intent = new Intent(HomeActivity.this, SearchPhotographer.class);// New activity
                                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                   startActivity(intent);
                                   finish();






                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });
        }


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    public void NameLocationSearch(){

        databaseReference.child("users").orderByChild("firstname").equalTo(GetName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){


                    name.clear();
                    loc.clear();
                    imgrl.clear();
                    id.clear();


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                        UserModel userModel = snapshot.getValue(UserModel.class);

                        if (GetLocation.equals(userModel.getCity())){
                            name.add(userModel.getFirstname());
                            id.add(userModel.getId());
                            loc.add(userModel.getCity());
                            imgrl.add(userModel.getImageUrl());


                        }


                    }

                    if (!name.isEmpty()){

                        SearchPhotographer searchPhotographer = new SearchPhotographer(name.toArray(new String[name.size()]) , loc.toArray(new String[loc.size()]) , imgrl.toArray(new String[imgrl.size()]) , id.toArray(new String[id.size()]) );
                        Intent intent = new Intent(HomeActivity.this, SearchPhotographer.class);// New activity
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }else {


                        Toast.makeText(HomeActivity.this , "Not Found " , Toast.LENGTH_SHORT).show();


                    }




                }else {


                    Toast.makeText(HomeActivity.this , "Not Found " , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    public void NamePriceSearch(){

        databaseReference.child("users").orderByChild("firstname").equalTo(GetName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){


                    name.clear();
                    loc.clear();
                    imgrl.clear();
                    id.clear();


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                        final UserModel userModel = snapshot.getValue(UserModel.class);


                        String Pid = userModel.getId();



                        databaseReference.child("packages").child(Pid.toString()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {



                                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()){


                                    PkgClass pkgClass = snapshot1.getValue(PkgClass.class);

                                    if (GetPrice.equals(pkgClass.getPrice())){

                                        name.add(userModel.getFirstname());
                                        id.add(userModel.getId());
                                        loc.add(userModel.getCity());
                                        imgrl.add(userModel.getImageUrl());


                                    }else if (!name.isEmpty()){


                                        SearchPhotographer searchPhotographer = new SearchPhotographer(name.toArray(new String[name.size()]) , loc.toArray(new String[loc.size()]) , imgrl.toArray(new String[imgrl.size()]) , id.toArray(new String[id.size()]) );


                                        Intent intent = new Intent(HomeActivity.this, SearchPhotographer.class);// New activity
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }


                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        }




                }else {


                    Toast.makeText(HomeActivity.this , "Not Found " , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void LocationPriceSearch(){



        databaseReference.child("users").orderByChild("city").equalTo(GetLocation).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){


                    name.clear();
                    loc.clear();
                    imgrl.clear();
                    id.clear();


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                        final UserModel userModel = snapshot.getValue(UserModel.class);


                        String Pid = userModel.getId();



                        databaseReference.child("packages").child(Pid.toString()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {



                                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()){


                                    PkgClass pkgClass = snapshot1.getValue(PkgClass.class);

                                    if (GetPrice.equals(pkgClass.getPrice())){

                                        name.add(userModel.getFirstname());
                                        id.add(userModel.getId());
                                        loc.add(userModel.getCity());
                                        imgrl.add(userModel.getImageUrl());


                                    }else if (!name.isEmpty()){


                                        SearchPhotographer searchPhotographer = new SearchPhotographer(name.toArray(new String[name.size()]) , loc.toArray(new String[loc.size()]) , imgrl.toArray(new String[imgrl.size()]) , id.toArray(new String[id.size()]) );


                                        Intent intent = new Intent(HomeActivity.this, SearchPhotographer.class);// New activity
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }


                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }




                }else {


                    Toast.makeText(HomeActivity.this , "Not Found " , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void NameLocationPriceSearch(){


        Toast.makeText(HomeActivity.this , "NameLocationPrice Search" , Toast.LENGTH_SHORT).show();

    }
}
