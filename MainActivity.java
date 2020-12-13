package com.example.json_example;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {


    ArrayList<String> color = new ArrayList<>();
    ArrayList<String> category = new ArrayList<>();
    ArrayList<String> hex = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONArray named colors
            JSONArray userArray = obj.getJSONArray("colors");
            // implement a loop to get colors list data
            for (int i = 0; i < userArray.length(); i++) {
                // create a JSONObject for fetching single color data
                JSONObject userDetail = userArray.getJSONObject(i);
                // fetch color name and category and store it in arraylist
                color.add(userDetail.getString("color"));
                category.add(userDetail.getString("category"));
                // create an object for getting code data from JSONObject
                JSONObject contact = userDetail.getJSONObject("code");
                // fetch hex value and store it in arraylist
                hex.add(contact.getString("hex"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        Custom_adapter customAdapter = new Custom_adapter(MainActivity.this, color, category, hex);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("example.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}