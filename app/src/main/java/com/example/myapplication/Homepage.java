package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.MyAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Homepage extends AppCompatActivity {

    //initialize recyclerView here
    RecyclerView recyclerView;

   String s1[],s2[];
   int images[]= { R.mipmap.adena,R.mipmap.adena,R.mipmap.adena,R.mipmap.adena,R.mipmap.adena,R.mipmap.adena,R.mipmap.adena,R.mipmap.adena,R.mipmap.adena,R.mipmap.adena
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        //initialize button here(selectedItem)
        Button button = findViewById(R.id.selectedItem);
        //find recyclerViewID
        recyclerView = findViewById(R.id.recyclerView);
        //get strings resources from custom dataset(arrays in values/styles.xml)
        s1 = getResources().getStringArray(R.array.items);
        s2 = getResources().getStringArray(R.array.description);
        //final String[] status = {findViewById(R.id.selectedStatus).toString()};

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//ERROR WHEN THIS IS ACTIVED
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent to next screen here
                startActivity(new Intent(Homepage.this,cart.class));
            }
        });
    }


}
