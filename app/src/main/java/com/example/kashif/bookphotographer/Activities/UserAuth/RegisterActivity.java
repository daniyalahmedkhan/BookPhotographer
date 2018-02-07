package com.example.kashif.bookphotographer.Activities.UserAuth;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.ModelClass.UserModel;
import com.example.kashif.bookphotographer.Activities.PhotographerFlow.ProfileManage;
import com.example.kashif.bookphotographer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    EditText editUser , editPass;
    RadioButton RadioUser , RadioPhoto;
    RadioGroup radioGroup;
    EditText RegisterEmail, RegisterPass , RegisterUserName;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
   String email , pass, username, type , uid , date;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.bck5);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_register);

        btnSignUp = (Button) findViewById(R.id.Signup);
        RadioUser = (RadioButton) findViewById(R.id.RadioUser);
        RadioPhoto = (RadioButton) findViewById(R.id.RadioPhoto);
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        RegisterEmail = (EditText) findViewById(R.id.RegisterEmail);
        RegisterPass = (EditText) findViewById(R.id.RegisterPass);
        RegisterUserName  = (EditText) findViewById(R.id.UserName);
        databaseReference = FirebaseDatabase.getInstance().getReference("allusers");
        firebaseAuth = FirebaseAuth.getInstance();


        /// Getting Current Date and Time //
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        date = dateFormat.format(cal.getTime());
        ///


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((RegisterEmail.getText().toString().isEmpty())){

                    RegisterEmail.setError("Please Enter Email");

                }else if (RegisterPass.getText().toString().isEmpty()){

                    RegisterPass.setError("Please Enter Password");
                }else {

                    email = RegisterEmail.getText().toString().trim();
                    pass = RegisterPass.getText().toString().trim();
                    username = RegisterUserName.getText().toString().trim();
                    if ((email.matches(emailPattern)) && pass.length() > 6){


                        if (radioGroup.getCheckedRadioButtonId() == -1){

                            Toast.makeText(RegisterActivity.this , "No Radio is Check" , Toast.LENGTH_SHORT).show();


                        }else {


                            if (RadioUser.isChecked()){


                                type = "user";
                                UserSignUp();
//                                Toast.makeText(RegisterActivity.this , "Radio User" , Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(RegisterActivity.this , HomeActivity.class);
//                                startActivity(intent);



                            }else {

                                type = "photographer";


                                ProfileManage profileManage = new ProfileManage(email , pass , type);
                                // Toast.makeText(RegisterActivity.this , "Radio Photo" , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this , ProfileManage.class);
                                startActivity(intent);

                            }


                        }




                    }else {

                        Toast.makeText(RegisterActivity.this, "Please check pass and email" , Toast.LENGTH_SHORT).show();

                    }





                }





            }
        });

    }


    public void UserSignUp(){

        firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {


                uid = firebaseAuth.getCurrentUser().getUid();

                UserModel userModel =  new UserModel(username , uid , email , pass , type , date);
                databaseReference.child("Users").child("Customer").child(uid).setValue(userModel, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                        if (databaseReference.equals(databaseError)){


                            Toast.makeText(RegisterActivity.this , "Error in Saving" , Toast.LENGTH_SHORT).show();

                        }else {

                            Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
                            startActivity(intent);


                        }


                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }


}
