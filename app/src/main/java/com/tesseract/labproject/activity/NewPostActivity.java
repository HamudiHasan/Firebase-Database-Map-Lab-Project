package com.tesseract.labproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tesseract.labproject.R;
import com.tesseract.labproject.javaClass.GPSTracker;
import com.tesseract.labproject.javaClass.Product;

public class NewPostActivity extends AppCompatActivity {

    EditText title,des,status;
    FirebaseDatabase db;
    DatabaseReference ref;
    GPSTracker gps;
    Double latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        title=(EditText) findViewById(R.id.etTitle);
        des=(EditText) findViewById(R.id.etDescription);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("products");

        gps = new GPSTracker(NewPostActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product product=new Product();
                product.setTitle(title.getText().toString());
                product.setDes(des.getText().toString());
                product.setUsername(LoginActivity.username);
                product.setStatus("Not Taken");
                product.setLat(latitude.toString());
                product.setLon(longitude.toString());


                ref.push().setValue(product);
                Toast.makeText(getApplicationContext(),"Sucessfully Added Please refresh the list",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
