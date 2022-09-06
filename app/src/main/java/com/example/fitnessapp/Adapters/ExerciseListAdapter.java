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

import com.example.fitnessapp.ExerciseDetails;
import com.example.fitnessapp.Model.Exercise;
import com.example.fitnessapp.R;

import java.util.ArrayList;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder> {

    private Context mContext;
    private ArrayList<Exercise> exerciseArrayList;

    public ExerciseListAdapter(Context mContext, ArrayList<Exercise> exerciseArrayList) {
        this.mContext = mContext;
        this.exerciseArrayList = exerciseArrayList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_layout,parent,false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        final Exercise model = exerciseArrayList.get(position);
        holder.textView.setText(model.getTitle());
        holder.imageView.setImageResource(model.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ExerciseDetails.class);
                intent.putExtra("title",model.getTitle());
                intent.putExtra("descp",model.getDescription());
                intent.putExtra("logo",model.getImage());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image);
            textView = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
