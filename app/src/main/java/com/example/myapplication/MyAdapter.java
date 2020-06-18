package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Homepage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {

    String data1[],data2[];
    int images[];
    Context context;
    OnCurrencyListener onCurrencyListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public  class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText1,myText2;
        ImageView myImage;
        Button button;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.titleTextView);
            myText2 = itemView.findViewById(R.id.descTextView);
            myImage = itemView.findViewById(R.id.myImageView);
            button  = itemView.findViewById(R.id.selectedItem);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context ct,String s1[],String s2[], int img[], OnCurrencyListener onCurrencyListener){
        this.context = ct;
        this.data1 = s1;
        this.data2 = s2;
        this.images = img;
        this.onCurrencyListener = onCurrencyListener;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)

    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dosomething
                Log.i("ABC","WE CLICKED THE BUTTON" + " " + position );
                onCurrencyListener.onCurrencyClicked(data1[position]);

            }
        });
    }


    private void goToNextActivity(){
        Intent intent;

    }
    // Return the size of your dataset (invoked by the layout manager)

    public int getItemCount() {
        return data1.length;
    }



} //end of class