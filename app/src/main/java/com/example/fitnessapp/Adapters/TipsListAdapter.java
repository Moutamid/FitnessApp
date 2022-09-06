package com.example.fitnessapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.Model.Recipes;
import com.example.fitnessapp.R;
import com.example.fitnessapp.RecipesDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TipsListAdapter extends RecyclerView.Adapter<TipsListAdapter.TipsListViewHolder> {

    private Context mContext;
    private ArrayList<Recipes> tipsList;

    public TipsListAdapter(Context mContext, ArrayList<Recipes> tipsList) {
        this.mContext = mContext;
        this.tipsList = tipsList;
    }

    @NonNull
    @Override
    public TipsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_layout,parent,false);
        return new TipsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsListViewHolder holder, int position) {
        final Recipes model = tipsList.get(position);
        holder.textView.setText(model.getName());
        Picasso.with(mContext)
                .load(model.getImage())
                .placeholder(R.drawable.logo)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecipesDetails.class);
                intent.putExtra("details",model);
                mContext.startActivity(intent);
            }
        });
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
            textView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
