package com.example.android.googlemaps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;
import static android.view.View.Y;

/**
 * Created by HARDIK SHARMA on 6/12/2017.
 */


public class ListClass extends AppCompatActivity {
    ListView listView ;


        // Defined Array values to show in ListView
        String[] web = { "Hardik Sharma \n Designation",
                "Shipra Sharma \n Designation ",
                "Sunil Sharma\n Designation",
                "Kshitija Sharma\n Designation",
                "Luxit Kapoor\n Designation",
                "Shubhjot Singh\n Designation",
                "Kunal Duggal\n Designation",
                "Anand Prakash Sharma\n Designation",
                "Anjali Sharma\n Designation",
                "Anoop Sharma\n Designation",
                "Ankur Sharma\n Designation",
                "Anoushka Sharma\n Designation",
                "Anil Sharma\n Designation"
        };

        Integer[] imageId = {
                R.drawable.image_1,
                R.drawable.woman_1,
                R.drawable.image_1,
                R.drawable.woman_1,
                R.drawable.image_1,
                R.drawable.image_1,
                R.drawable.image_1,
                R.drawable.image_1,
                R.drawable.woman_1,
                R.drawable.image_1,
                R.drawable.image_1,
                R.drawable.woman_1,
                R.drawable.image_1
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        CustomList adapter = new
                CustomList(ListClass.this, web, imageId);
        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(ListClass.this, GridViewClass.class);
                startActivity(appInfo);
            }
        });
    }
}