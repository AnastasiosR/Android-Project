package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

public class cart extends AppCompatActivity {

    private static final int MATCH_PARENT = 0;
    private static final int WRAP_CONTENT = 9;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        Bundle extras = getIntent().getExtras();
        TextView item1 = findViewById(R.id.item1);
        TextView amountToPay = findViewById(R.id.totalText);
        double[] amount = {1.99, 2.99, 3.99, 4.99, 5.99, 6.99, 7.99, 8.99, 9.99};
        String s1[];
        s1 = getResources().getStringArray(R.array.items);



        if (extras != null) {
            String value = extras.getString("key");
            item1.setText(value);
            //The key argument here must match that used in the other activity
            if (value == s1[0]) {
                amountToPay.setText(s1[0]);
            }

        } else {
            item1.setText("no data received.");
        }


    }
}//end of class
