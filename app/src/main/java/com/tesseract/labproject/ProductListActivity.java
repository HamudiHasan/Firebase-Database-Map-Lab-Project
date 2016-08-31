package com.tesseract.labproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewPostActivity.class));
            }
        });


        db = FirebaseDatabase.getInstance();
        ref = db.getReference("products");


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("userList", dataSnapshot.getValue().toString());
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
