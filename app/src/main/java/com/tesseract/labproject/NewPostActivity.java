package com.tesseract.labproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tesseract.labproject.R;
import com.tesseract.labproject.activity.LoginActivity;
import com.tesseract.labproject.javaClass.Product;

public class NewPostActivity extends AppCompatActivity {

    EditText title,des,status;
    FirebaseDatabase db;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        title=(EditText) findViewById(R.id.etTitle);
        des=(EditText) findViewById(R.id.etDescription);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("products");
        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product product=new Product();
                product.setTitle(title.getText().toString());
                product.setDes(des.getText().toString());
                product.setUsername(LoginActivity.username);
                product.setStatus("Not Taken");
                product.setLat("46");
                product.setLon("546");


                ref.push().setValue(product);
            }
        });
    }
}
