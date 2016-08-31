package com.tesseract.labproject.adaptar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tesseract.labproject.DetailsActivity;
import com.tesseract.labproject.ProductListActivity;
import com.tesseract.labproject.R;
import com.tesseract.labproject.javaClass.Product;

import java.util.ArrayList;

/**
 * Created by BlackFlag on 8/31/2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.Holder>  {
    ArrayList<Product>products;
    Context context;
    public static  String title,des;

    public Adapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        Holder ch=new Holder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        holder.title.setText(products.get(position).getTitle());
        holder.des.setText(products.get(position).getDes());
        holder.status.setText(products.get(position).getStatus());
        holder.linlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
               des=products.get(position).getDes();
                title=products.get(position).getTitle();
                Toast.makeText(context,products.get(position).getDes(),Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class Holder extends RecyclerView.ViewHolder{

        TextView title,des,status;
        LinearLayout linlay;
        public Holder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.title);
            des=(TextView) itemView.findViewById(R.id.description);
            status=(TextView) itemView.findViewById(R.id.status);
            linlay= (LinearLayout) itemView.findViewById(R.id.linlay);
        }
    }
}
