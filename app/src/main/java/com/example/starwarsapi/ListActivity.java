package com.example.starwarsapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements MyAdapter.OnCharacterListener {

    private RecyclerView myrecyclerview;
    private RecyclerView.Adapter myadapter;
    private RecyclerView.LayoutManager mylayoutmanager;
    static RequestQueue listqueue;
    static final private String url = "https://swapi.dev/api/people/";

    static ArrayList<RecyclerItem> list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();
        listqueue = Volley.newRequestQueue(this);

        myrecyclerview = findViewById(R.id.characterlist);
        myadapter = new MyAdapter(list, this);

        parseJsonData();
    }

    public void parseJsonData(){
    JsonObjectRequest request = new JsonObjectRequest(
            Request.Method.GET,
            url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonarray = response.getJSONArray("results");
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);

                            String name = jsonobject.getString("name");
                            String height = jsonobject.getString("height");
                            String mass = jsonobject.getString("mass");
                            String eyecolor = jsonobject.getString("eye_color");
                            String birthyear = jsonobject.getString("birth_year");

                            list.add(new RecyclerItem(name, "Height: " + height, "Mass: " + mass, "Birth Year: " + birthyear, eyecolor));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    myrecyclerview.setAdapter(myadapter);
                    myrecyclerview.setHasFixedSize(true);
                    mylayoutmanager = new LinearLayoutManager(getApplicationContext());
                    myrecyclerview.setLayoutManager(mylayoutmanager);

                }

            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });

        listqueue.add(request);

    }


    @Override
    public void onCharacterClick(int position) {
        String color = list.get(position).getEyecolor();
        Toast.makeText(getApplicationContext(), "Eye color: " + color, Toast.LENGTH_SHORT).show();
    }

}