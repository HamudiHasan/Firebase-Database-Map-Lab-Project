package com.tesseract.labproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tesseract.labproject.R;
import com.tesseract.labproject.activity.LoginActivity;
import com.tesseract.labproject.adaptar.Adapter;
import com.tesseract.labproject.javaClass.Product;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    TextView tvtitle,tvdes;
    FirebaseDatabase db;
    DatabaseReference ref;
    String title,des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("products");
        tvtitle=(TextView) findViewById(R.id.title);
        tvdes=(TextView) findViewById(R.id.description);

        title=Adapter.title;
        tvtitle.setText(Adapter.title);
        tvdes.setText(Adapter.des);




        findViewById(R.id.status).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Log.d("location", dataSnapshot.getValue().toString());
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Product pr = child.getValue(Product.class);


                            if(pr.getTitle().equals(title)){
                                pr.setStatus("Taken");
                                child.getRef().setValue(pr);
                                Toast.makeText(getApplicationContext(),"Sucessfully Updated",Toast.LENGTH_SHORT).show();
                            }






                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        //child.getRef().setValue(new Location(33.0000,44.0000));

    }
}
