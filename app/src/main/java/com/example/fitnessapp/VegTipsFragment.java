package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.Adapters.TipsListAdapter;
import com.example.fitnessapp.Model.Recipes;

import java.util.ArrayList;

public class VegTipsFragment extends Fragment {

    LinearLayout veg_layout,fruit_layout,nuts_layout,tea_layout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.veg_tips_fragment,container,false);
        veg_layout = (LinearLayout) root.findViewById(R.id.veg);
        fruit_layout = (LinearLayout) root.findViewById(R.id.fruits);
        nuts_layout = (LinearLayout) root.findViewById(R.id.nuts);

        veg_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),VegetableSubItems.class);
                intent.putExtra("title","Vegetables");
                startActivity(intent);

            }
        });
        fruit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),VegetableSubItems.class);
                intent.putExtra("title","Fruits");
                startActivity(intent);
            }
        });

        nuts_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),VegetableSubItems.class);
                intent.putExtra("title","Nuts And Seed");
                startActivity(intent);
            }
        });

      /*  tea_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),VegetableSubItems.class);
                intent.putExtra("title","Herbal Tea");
                startActivity(intent);
            }
        });*/

        return root;
    }
}
