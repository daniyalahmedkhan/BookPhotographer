package com.example.kashif.bookphotographer.Activities.UserAuth;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.CustomerFlow.HomeActivity;
import com.example.kashif.bookphotographer.Activities.PhotographerFlow.MyProfile;
import com.example.kashif.bookphotographer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    TextView signup ;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    EditText LoginEmail , LoginPass;
   public static String email , pass , uid , type;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    Button Signin;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.bck5);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        progressDialog = new ProgressDialog(this);

        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("allusers");
        signup = (TextView) findViewById(R.id.Signup);

        LoginEmail = (EditText) findViewById(R.id.LoginEmail);
        LoginPass = (EditText) findViewById(R.id.LoginPass);
        Signin = (Button) findViewById(R.id.Signin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
                startActivity(intent);
            }
        });




        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if((LoginEmail.getText().toString().isEmpty() )){

                    LoginEmail.setError("Enter Email");
                }else if(LoginPass.getText().toString().isEmpty()){

                    LoginPass.setError("Enter Password");
                }else {

                  email = LoginEmail.getText().toString().trim();
                  pass = LoginPass.getText().toString().trim();

               firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {



                   }
               }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                   @Override
                   public void onSuccess(AuthResult authResult) {

                       uid = firebaseAuth.getCurrentUser().getUid();
                       progressDialog.setMessage("Please Wait");
                       progressDialog.show();
                       CheckUser();

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {


                       Toast.makeText(LoginActivity.this , "Invalid" , Toast.LENGTH_LONG).show();
                   }
               });

                }

            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
           // Intent i = new Intent(LoginActivity.this, MainActivity.class);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //startActivity(i);
            uid = firebaseAuth.getCurrentUser().getUid();
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
            CheckUser();


        } else {
            // User is signed out
           // Log.d(TAG, "onAuthStateChanged:signed_out");
        }

    }

    public void CheckUser(){


        firebaseDatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.child("Photographer").child(firebaseAuth.getCurrentUser().getUid()).exists()){

                    firebaseDatabase.child("Users").child("Photographer").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {



                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this , MyProfile.class);
                            startActivity(intent);



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });




                    }else if (dataSnapshot.child("Customer").child(firebaseAuth.getCurrentUser().getUid()).exists()){


                    firebaseDatabase.child("Users").child("Customer").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {




                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                            startActivity(intent);


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                }else {

                    Toast.makeText(LoginActivity.this, "Not Found", Toast.LENGTH_SHORT).show();

                }



            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
