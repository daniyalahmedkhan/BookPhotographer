package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.CustomerFlow.Photographer_Profile;
import com.example.kashif.bookphotographer.Activities.ModelClass.AddressClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.LocationClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.CityClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.LocationClass;
import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ProfileManage extends AppCompatActivity {

    Button btnNEXT;
    String Country_ID , City_ID  ;
    Spinner Country, City, Location ,  Gender;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    public static String email, pass, type, uid;
    EditText FirstName, LastName , Contact;
    String FirstN, LastN, Gend, Count, Cit, Addr , imageUrl , Contact_No;
    ImageView ProfileImage;
    private static final int PICK_IMAGE_REQUEST = 234 ;
    private Uri filepath;
    private StorageReference storageReference;
    String LocationID , CityID , CountryID, TodayDate;
    ArrayList<String> CountryList ,CountryListID , CityList , CityListID , LocationList , LocationListID;

    ArrayAdapter<String> adapterCountry;
    ArrayAdapter<String> adapterCity;
    ArrayAdapter<String> adapterLocation;

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



        CountryList = new ArrayList<String>();
        CityList = new ArrayList<String>();
        LocationList = new ArrayList<String>();
        CountryListID = new ArrayList<String>();
        CityListID = new ArrayList<String>();
        LocationListID = new ArrayList<String>();

        CountryList.add("Please Select Country");

        LocationList.add("Please Select Location");
        CountryListID.add("0");

        LocationListID.add("0");




        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        storageReference = FirebaseStorage.getInstance().getReference();
        btnNEXT = (Button) findViewById(R.id.NEXT);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        Gender = (Spinner) findViewById(R.id.Gender);
        Location = (Spinner) findViewById(R.id.AddressName);
        Country = (Spinner) findViewById(R.id.CountryName);
        City = (Spinner) findViewById(R.id.CityName);
        Contact = (EditText) findViewById(R.id.ContactNo);
        ProfileImage = (ImageView) findViewById(R.id.ProfileImage);


        /// Getting Current Date and Time //
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        TodayDate = dateFormat.format(cal.getTime());
        ///

        String[] GenderSpinner = {"Please Select Gender" , "Male" , "Female"};
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , GenderSpinner);
        Gender.setAdapter(adapter);
        
        ////
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
                          Contact.getText().toString().isEmpty()) {


                    Toast.makeText(ProfileManage.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();

                } else {


                    FirstN = FirstName.getText().toString().trim();
                    LastN = LastName.getText().toString().trim();
//                    Gend = Gender.getText().toString().trim();
                    Contact_No = Contact.getText().toString().trim();

                    PhotographerSignUp();

                }




            }
        });


        Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                CityList.clear();
                CityListID.clear();

                Count = CountryList.get(i);
                Country_ID = CountryListID.get(i);

            if (!Country_ID.equals("0")){

                CityList();

            }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                LocationList.clear();
                LocationListID.clear();

                City_ID = CityListID.get(i);
                Cit = CityList.get(i);


                    LocationList();




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Addr = LocationList.get(i);
                LocationID = LocationListID.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    Gend = Gender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        CountryList();
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


//        LocationID =  databaseReference.push().getKey();
//        CityID = databaseReference.push().getKey();
//        CountryID =  databaseReference.push().getKey();

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


        UserModel userModel = new UserModel(uid , LocationID , Addr , City_ID , "NIL");

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


        UserModel userModel = new UserModel(uid , City_ID , Cit , Country_ID);

        databaseReference.child("Users").child("Photographer").child("City").child(City_ID).setValue(userModel, new DatabaseReference.CompletionListener() {
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


        UserModel userModel = new UserModel(uid , Country_ID , Count);

        databaseReference.child("Users").child("Photographer").child("Country").child(Country_ID).setValue(userModel, new DatabaseReference.CompletionListener() {
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

    public void  CountryList(){

    databaseReference.child("Define_Country").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {




            if(dataSnapshot.exists()){

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    AddressClass add = snapshot.getValue(AddressClass.class);

                    CountryList.add(add.getCountry_Name());
                    CountryListID.add(add.getCountry_ID());

                }

                adapterCountry = new ArrayAdapter<String>(ProfileManage.this, android.R.layout.simple_spinner_dropdown_item , CountryList);
                Country.setAdapter(adapterCountry);


            }else{

                Toast.makeText(ProfileManage.this, "No Data", Toast.LENGTH_SHORT).show();

            }



            adapterCountry.notifyDataSetChanged();

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

    }

    public  void CityList(){

        databaseReference.child("Define_Cities").child(Country_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                CityList.add("Please Select City");
                CityListID.add("0");


                if (dataSnapshot.exists()) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    CityClass cityClass = snapshot.getValue(CityClass.class);


                    CityList.add(cityClass.getCity_Name());
                    CityListID.add(cityClass.getCity_ID());


                }

            }else {

                Toast.makeText(ProfileManage.this, "No City Found", Toast.LENGTH_SHORT).show();

            }

                        adapterCity = new ArrayAdapter<String>(ProfileManage.this, android.R.layout.simple_spinner_dropdown_item , CityList);
                            City.setAdapter(adapterCity);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void LocationList(){


        databaseReference.child("Define_Location").child(City_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                        LocationClass locationClass = snapshot.getValue(LocationClass.class);

                        LocationList.add(locationClass.getLocation_Name());
                        LocationListID.add(locationClass.getLocation_ID());


                    }


                }else {


                    Toast.makeText(ProfileManage.this, "No Data Cites", Toast.LENGTH_SHORT).show();

                }

                adapterLocation = new ArrayAdapter<String>(ProfileManage.this, android.R.layout.simple_spinner_dropdown_item , LocationList);
                Location.setAdapter(adapterLocation);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
