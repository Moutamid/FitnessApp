package com.example.fitnessapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.Model.Recipes;
import com.example.fitnessapp.Model.VegeItems;
import com.example.fitnessapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VegItemListAdapter extends RecyclerView.Adapter<VegItemListAdapter.TipsListViewHolder> {

    private Context mContext;
    private ArrayList<VegeItems> tipsList;

    public VegItemListAdapter(Context mContext, ArrayList<VegeItems> tipsList) {
        this.mContext = mContext;
        this.tipsList = tipsList;
    }

    @NonNull
    @Override
    public TipsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.veg_sub_items,parent,false);
        return new TipsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsListViewHolder holder, int position) {
        VegeItems model = tipsList.get(position);
        holder.textView.setText(model.getName());
        Picasso.with(mContext)
                .load(model.getImage())
                .placeholder(R.drawable.logo)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }

    public class TipsListViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public TipsListViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
