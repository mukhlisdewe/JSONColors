package com.example.json_example;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Custom_adapter extends RecyclerView.Adapter<Custom_adapter.MyViewHolder>
{
    ArrayList<String> color;
    ArrayList<String> category;
    ArrayList<String> hex;
    Context context;



    public Custom_adapter(Context context, ArrayList<String>color, ArrayList<String> category, ArrayList<String> hex) {
        this.context=context;
        this.color=color;
        this.category=category;
        this.hex=hex;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;

    }

    @Override
    public void onBindViewHolder(Custom_adapter.MyViewHolder holder, final int position) {
        // set the data in items
        holder.color.setText(color.get(position));
        holder.category.setText(category.get(position));
        holder.hex.setText(hex.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with color name on item click
                Toast.makeText(context, color.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return color.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView color, category, hex;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            color = (TextView) itemView.findViewById(R.id.color);
            category = (TextView) itemView.findViewById(R.id.category);
            hex = (TextView) itemView.findViewById(R.id.hex);

        }
    }

}
