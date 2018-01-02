package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileManage extends AppCompatActivity {

    Button btnNEXT;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String email , pass, type , uid;
    EditText FirstName, LastName, Gender, Country, City, Address;



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

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        btnNEXT = (Button) findViewById(R.id.NEXT);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        Gender = (EditText) findViewById(R.id.Gender);
        Country = (EditText) findViewById(R.id.CountryName);
        City = (EditText) findViewById(R.id.CityName);



        btnNEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



           if(FirstName.getText().toString().isEmpty() || LastName.getText().toString().isEmpty() ||
                   Gender.getText().toString().isEmpty() || Country.getText().toString().isEmpty()||
                   City.getText().toString().isEmpty()){


               Toast.makeText(ProfileManage.this, "Please Enter all Fields" ,Toast.LENGTH_SHORT).show();

           }else {


                // PhotographerSignUp();
                Intent intent = new Intent(ProfileManage.this , PhotographerPackages.class);
                startActivity(intent);


           }


            }
        });
    }

    public  void PhotographerSignUp(){


        if (!(email.isEmpty() && pass.isEmpty())){


            firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(ProfileManage.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                }
            }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                   if (firebaseAuth.getCurrentUser().getUid() != null){

                       uid =  firebaseAuth.getCurrentUser().getUid();

                     //  UserModel userModel = new UserModel(uid, email, pass , type, FirstName.getText().toString() , );



                   }else {

                       Toast.makeText(ProfileManage.this, "Uid Not Found" , Toast.LENGTH_SHORT).show();

                   }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                   Toast.makeText(ProfileManage.this, "Failed" , Toast.LENGTH_SHORT).show();

                }
            });

        }


    }
}
