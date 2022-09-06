package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnessapp.Model.Recipes;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

public class RecipesDetails extends AppCompatActivity {

    TextView item_description;
    ImageView item_img;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private Recipes model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_details);
        item_description = (TextView)findViewById(R.id.description);
        item_img = (ImageView)findViewById(R.id.img_item);
        model = (Recipes) getIntent().getSerializableExtra("details");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(model.getName());

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        item_description.setText(model.getDescription());
        Picasso.with(this)
                .load(model.getImage())
                .into(item_img);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}