package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.fitnessapp.Adapters.ExerciseListAdapter;
import com.example.fitnessapp.Adapters.ViewPagerAdapter;
import com.example.fitnessapp.Model.Exercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ExcerciseScreen extends AppCompatActivity {

    private ImageView back;
    RecyclerView recyclerView;
    private ExerciseListAdapter adapter;
    private ArrayList<Exercise> exerciseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_screen);
        back = (ImageView)findViewById(R.id.back);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(ExcerciseScreen.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        exerciseArrayList = new ArrayList<>();
        setItems();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    //Store Exercises in a list
    private void setItems() {
        exerciseArrayList.add(new Exercise(R.drawable.lunges,"Lunges",
                "1. Start by standing with your feet shoulder-width apart and arms down at your sides.\n" +
                        "2. Take a step forward with your right leg and bend your right knee as you do so, stopping when your thigh is parallel to the ground. Ensure that your right knee doesn’t extend past your right foot.\n" +
                        "3. Push up off your right foot and return to the starting position. Repeat with your left leg. This is one rep.\n" +
                        "4. Complete 3 sets of 10 reps."));
        exerciseArrayList.add(new Exercise(R.drawable.pushups,"Pushups",
                "1. Start in a plank position. Your core should be tight, shoulders pulled down and back, and your neck neutral.\n" +
                        "2. Bend your elbows and begin to lower your body down to the floor. When your chest grazes it, extend your elbows and return to the start. Focus on keeping your elbows close to your body during the movement.\n" +
                        "3. Complete 3 sets of as many reps as possible."));
        exerciseArrayList.add(new Exercise(R.drawable.squats,"Squats",
                "1. Start by standing straight, with your feet slightly wider than shoulder-width apart, and your arms at your sides.\n" +
                        "2. Brace your core and, keeping your chest and chin up, push your hips back and bend your knees as if you’re going to sit in a chair.\n" +
                        "3. Ensuring your knees don’t bow inward or outward, drop down until your thighs are parallel to the ground, bringing your arms out in front of you in a comfortable position. Pause for 1 second, then extend your legs and return to the starting position.\n" +
                        "4. Complete 3 sets of 20 reps."));
        exerciseArrayList.add(new Exercise(R.drawable.dumbell,"Dumbbell rows",
                "1. Start with a dumbbell in each hand. We recommend no more than 10 pounds for beginners.\n" +
                        "2. Bend forward at the waist, so your back is at a 45-degree angle to the ground. Be certain not to arch your back. Let your arms hang straight down. Ensure your neck is in line with your back and your core is engaged.\n" +
                        "3. Starting with your right arm, bend your elbow and pull the weight straight up toward your chest, making sure to engage your lat and stopping just below your chest.\n" +
                        "4. Return to the starting position and repeat with the left arm. This is one rep. Repeat 10 times for 3 sets."));

        adapter = new ExerciseListAdapter(ExcerciseScreen.this,exerciseArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
