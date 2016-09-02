package com.tesseract.labproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tesseract.labproject.R;
import com.tesseract.labproject.adaptar.Adapter;
import com.tesseract.labproject.javaClass.Product;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;
    Adapter adapter;
    ArrayList<Product>products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        products=new ArrayList<Product>();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("products");



        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAndSetData();
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewPostActivity.class));
            }
        });
        findViewById(R.id.map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });


        getAndSetData();


    }
    void getAndSetData()
    {



        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Product product1 = child.getValue(Product.class);
                    products.add(product1);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter=new Adapter(products,this);
        RecyclerView rv=(RecyclerView)findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }
}
