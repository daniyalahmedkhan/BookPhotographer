package com.example.kashif.bookphotographer.Activities.PhotographerFlow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kashif.bookphotographer.Activities.ModelClass.PackageClass;
import com.example.kashif.bookphotographer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PhotographerPackages extends AppCompatActivity {

    Button btnNEXT;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    FirebaseDatabase firebase;

    String id , id2 , id3 , id4, id5 , pName , price, days,  description , type;
    EditText editprice1 , editsp1 , editdes1;
    EditText editprice2 , editsp2 , editdes2;
    EditText editprice3 , editsp3 , editdes3;
    EditText editprice4 , editsp4 , editdes4;
    EditText editprice5 , editsp5 , editdes5;

    EditText pkg1 , pkg2 , pkg3 , pkg4 , pkg5;
    public  static  String Service_ID , Category_ID , Photographer_ID;
    //String pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_packages);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("allusers");


        editprice1 = (EditText) findViewById(R.id.EditPrice1);
        editsp1 = (EditText) findViewById(R.id.EditSp1);
        editdes1 = (EditText) findViewById(R.id.EditDes1);


        editprice2 = (EditText) findViewById(R.id.EditPrice2);
        editsp2 = (EditText) findViewById(R.id.EditSp2);
        editdes2 = (EditText) findViewById(R.id.EditDes2);


        editprice3 = (EditText) findViewById(R.id.EditPrice3);
        editsp3 = (EditText) findViewById(R.id.EditSp3);
        editdes3 = (EditText) findViewById(R.id.EditDes3);


        editprice4 = (EditText) findViewById(R.id.EditPrice4);
        editsp4 = (EditText) findViewById(R.id.EditSp4);
        editdes4 = (EditText) findViewById(R.id.EditDes4);


        editprice5 = (EditText) findViewById(R.id.EditPrice5);
        editsp5 = (EditText) findViewById(R.id.EditSp5);
        editdes5 = (EditText) findViewById(R.id.EditDes5);


        pkg1 = (EditText) findViewById(R.id.pkg1);
        pkg2 = (EditText) findViewById(R.id.pkg2);
        pkg3 = (EditText) findViewById(R.id.pkg3);
        pkg4 = (EditText) findViewById(R.id.pkg4);
        pkg5 = (EditText) findViewById(R.id.pkg5);



        btnNEXT = (Button) findViewById(R.id.NEXT);

        btnNEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pckg1();


            }
        });



    }

    public  void Pckg1(){

        Service_ID = firebaseDatabase.push().getKey();
        Category_ID = firebaseDatabase.push().getKey();
        id =  firebaseDatabase.push().getKey();



        if (!pkg1.getText().toString().isEmpty()){

            pName  = pkg1.getText().toString().trim();

        }else {

            pName = "unk";
        }


        if (!((editprice1.getText().toString().isEmpty()) && (editsp1.getText().toString().isEmpty())
                && (editdes1.getText().toString().isEmpty())) ){


            price = editprice1.getText().toString().trim();
            days = editsp1.getText().toString().trim();
            description = editdes1.getText().toString().trim();
            type = "pkg1";

        }else {


            price = "unk" ;
            days = "unk";
            description = "unk";
            type = "pkg1";

        }


        PackageClass packageClass = new PackageClass(id, Category_ID  , pName , price , days , description , type);

        firebaseDatabase.child("Services").child(ProfileManage.uid).child(Service_ID).child("Package1").setValue(packageClass, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                if (databaseReference.equals(databaseError)){



                }else {

                Pckg2();


                }
            }
        });

    }
    public  void Pckg2(){


        if (!pkg2.getText().toString().isEmpty()){

            pName  = pkg2.getText().toString().trim();

        }else {

            pName = "unk";
        }


        if (!((editprice2.getText().toString().isEmpty()) && (editsp2.getText().toString().isEmpty())
                && (editdes2.getText().toString().isEmpty())) ){


            price = editprice2.getText().toString().trim();
            days = editsp2.getText().toString().trim();
            description = editdes2.getText().toString().trim();
            type = "pkg2";

        }else {


            price = "unk" ;
            days = "unk";
            description = "unk";
            type = "pkg2";

        }

        id2 =  firebaseDatabase.push().getKey();

        PackageClass packageClass = new PackageClass(Category_ID  ,id2 , pName , price , days , description , type);

        firebaseDatabase.child("Services").child(ProfileManage.uid).child(Service_ID).child("Package2").setValue(packageClass, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                if (databaseReference.equals(databaseError)){



                }else {

//
                Pckg3();


                }
            }
        });






    }
    public  void Pckg3(){


        if (!pkg3.getText().toString().isEmpty()){

            pName  = pkg3.getText().toString().trim();

        }else {

            pName = "unk";
        }


        if (!((editprice3.getText().toString().isEmpty()) && (editsp3.getText().toString().isEmpty())
                && (editdes3.getText().toString().isEmpty())) ){


            price = editprice3.getText().toString().trim();
            days = editsp3.getText().toString().trim();
            description = editdes3.getText().toString().trim();
            type = "pkg3";

        }else {


            price = "unk" ;
            days = "unk";
            description = "unk";
            type = "pkg3";

        }

        id3 =  firebaseDatabase.push().getKey();

        PackageClass packageClass = new PackageClass(Category_ID ,id3 , pName , price , days , description , type);

        firebaseDatabase.child("Services").child(ProfileManage.uid).child(Service_ID).child("Package3").setValue(packageClass, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                if (databaseReference.equals(databaseError)){



                }else {

//
            Pckg4();


                }
            }
        });






    }
    public  void Pckg4(){


        if (!pkg4.getText().toString().isEmpty()){

            pName  = pkg4.getText().toString().trim();

        }else {

            pName = "unk";
        }


        if (!((editprice4.getText().toString().isEmpty()) && (editsp4.getText().toString().isEmpty())
                && (editdes4.getText().toString().isEmpty())) ){


            price = editprice4.getText().toString().trim();
            days = editsp4.getText().toString().trim();
            description = editdes4.getText().toString().trim();
            type = "pkg4";

        }else {


            price = "unk" ;
            days = "unk";
            description = "unk";
            type = "pkg4";

        }

        id4 =  firebaseDatabase.push().getKey();

        PackageClass packageClass = new PackageClass(Category_ID , id4 , pName , price , days , description , type);

        firebaseDatabase.child("Services").child(ProfileManage.uid).child(Service_ID).child("Package4").setValue(packageClass, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                if (databaseReference.equals(databaseError)){



                }else {

//
                Pckg5();


                }
            }
        });






    }
    public  void Pckg5(){

        if (!pkg5.getText().toString().isEmpty()){

            pName  = pkg5.getText().toString().trim();

        }else {

            pName = "unk";
        }


        if (!((editprice5.getText().toString().isEmpty()) && (editsp5.getText().toString().isEmpty())
                && (editdes5.getText().toString().isEmpty())) ){


            price = editprice5.getText().toString().trim();
            days = editsp5.getText().toString().trim();
            description = editdes5.getText().toString().trim();
            type = "pkg5";

        }else {


            price = "unk" ;
            days = "unk";
            description = "unk";
            type = "pkg5";

        }

        id5 =  firebaseDatabase.push().getKey();

        PackageClass packageClass = new PackageClass(Category_ID , id5 , pName , price , days , description , type);

        firebaseDatabase.child("Services").child(ProfileManage.uid).child(Service_ID).child("Package5").setValue(packageClass, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {


                if (databaseReference.equals(databaseError)){



                }else {

//

                    Intent intent = new Intent(PhotographerPackages.this , SampleImages.class);
                    startActivity(intent);
                }
            }
        });







    }

}
