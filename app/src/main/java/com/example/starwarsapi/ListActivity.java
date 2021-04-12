package com.example.starwarsapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements MyAdapter.OnCharacterListener {

    Button generatelistbutton;
    private RecyclerView myrecyclerview;
    private RecyclerView.Adapter myadapter;
    private RecyclerView.LayoutManager mylayoutmanager;

    ArrayList<RecyclerItem> list = new ArrayList<>();


    final private String url = "https://swapi.dev/api/people/?format=wookiee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();

        generatelistbutton = findViewById(R.id.displaylist);

        myadapter = new MyAdapter(list, this);
        mylayoutmanager = new LinearLayoutManager(getApplicationContext());
        myrecyclerview = findViewById(R.id.characterlist);
        myrecyclerview.setHasFixedSize(true);
        myrecyclerview.setLayoutManager(mylayoutmanager);
        myrecyclerview.setAdapter(myadapter);


        list.add(new RecyclerItem("Han Solo",
                "Height: 6ft",
                "Weight: 210lbs",
                "Birth Year: 1960",
                "Eye color: Blue"));
        list.add(new RecyclerItem("Luke Skywalker",
                "Height: 5ft 11in",
                "Weight: 190lbs",
                "Birth Year: 1963",
                "Eye color: Green"));
        list.add(new RecyclerItem("C3P0",
                "Height: 6ft",
                "Weight: 450lbs",
                "Birth Year: 1940",
                "Eye color: Yellow"));
        list.add(new RecyclerItem("Darth Maul",
                "Height: 6ft 2in",
                "Weight: 205lbs",
                "Birth Year: 1942",
                "Eye color: Red"));
    }

    @Override
    public void onCharacterClick(int position) {
        String color = list.get(position).getEyecolor();
        Toast.makeText(getApplicationContext(), color, Toast.LENGTH_SHORT).show();
    }
}