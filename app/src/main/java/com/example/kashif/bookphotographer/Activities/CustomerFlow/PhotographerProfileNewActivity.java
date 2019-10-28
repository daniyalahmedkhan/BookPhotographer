package com.example.kashif.bookphotographer.Activities.CustomerFlow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kashif.bookphotographer.Activities.Adapter.PackagesAdapter;
import com.example.kashif.bookphotographer.Activities.ModelClass.PackageClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PhotographerProfileNewActivity extends AppCompatActivity {


    DatabaseReference databaseReference;
    private TextView txtFname;
    private TextView txtLname;
    private TextView txtLocation;
    private TextView txtGender;
    private TextView txtContact;
    private CircleImageView headImage;
    private TextView txtHeadName;
    private ListView lisView;
    private PackagesAdapter adapter;
    ArrayList<PackageClass> arrPackages=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_profile_new);

        initViews();

        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");

        getData();
        getAllPackages();

    }

    private void initViews() {
        txtFname = findViewById(R.id.txtFname);
        txtLname = findViewById(R.id.txtLname);
        txtLocation = findViewById(R.id.txtLocation);
        txtGender = findViewById(R.id.txtGender);
        txtContact = findViewById(R.id.txtContact);
        headImage = findViewById(R.id.headImg);
        txtHeadName = findViewById(R.id.txtHeadName);
        lisView = findViewById(R.id.lisView);

        adapter=new PackagesAdapter(getApplicationContext(),new ArrayList<PackageClass>());
        lisView.setAdapter(adapter);


    }


    public void getData(){

        if (databaseReference.child("Users").child("Photographer").child(ShowPhotographer.CurrntID) != null){

            databaseReference.child("Users").child("Photographer").child(ShowPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    UserModel userModel = dataSnapshot.getValue(UserModel.class);

                    if (userModel != null){


                        bindData(userModel);


                    }else {

                        Toast.makeText(PhotographerProfileNewActivity.this , "EMPTY" , Toast.LENGTH_LONG).show();
                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }




    }

    private void getAllPackages()
    {


//        PackageClass model=new PackageClass();
//        model.setPackage_Name("Basic Package");
//        model.setPackage_Price("100$");
//        model.setServices_Days("2");
//        model.setPackage_Description("Basic purpose of this use case is to get authorized credentials of the users (photographer, client) to use all the available features of the application according to their privileges");
//        databaseReference.child("Services").child(ShowPhotographer.CurrntID).push().setValue(model);
        databaseReference.child("Services").child(ShowPhotographer.CurrntID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    PackageClass model = snapshot.getValue(PackageClass.class);

                    arrPackages.add(model);


                }

                if(arrPackages.size()>0) {
                    adapter.addItems(arrPackages);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void bindData(UserModel userModel) {
        String FirstLast = userModel.getFirst_Name() + " " + userModel.getLast_Name();
        txtHeadName.setText(FirstLast);
        txtFname.setText("First Name : "+userModel.getFirst_Name());
        txtLname.setText("Last Name : "+userModel.getLast_Name());
        txtGender.setText("Gender : "+userModel.getGender());
        txtLocation.setText("Location : "+userModel.getCity_Des());
        txtContact.setText("Contact : "+userModel.getContact_No());
        Glide.with(getApplicationContext()).load(userModel.getProfile_Img()).into(headImage);
    }
}
