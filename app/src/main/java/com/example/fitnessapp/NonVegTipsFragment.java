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

public class NonVegTipsFragment extends Fragment {

    LinearLayout chicken_layout,beef_layout,fish_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.non_veg_tips_fragment,container,false);
        chicken_layout = (LinearLayout) root.findViewById(R.id.chicken);
       beef_layout = (LinearLayout) root.findViewById(R.id.beef);
        fish_layout = (LinearLayout) root.findViewById(R.id.fish);

        chicken_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),VegetableSubItems.class);
                intent.putExtra("title","Chicken");
                startActivity(intent);

            }
        });
        beef_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),VegetableSubItems.class);
                intent.putExtra("title","Beef");
                startActivity(intent);
            }
        });

        fish_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),VegetableSubItems.class);
                intent.putExtra("title","Fish");
                startActivity(intent);
            }
        });
        return root;
    }

}
