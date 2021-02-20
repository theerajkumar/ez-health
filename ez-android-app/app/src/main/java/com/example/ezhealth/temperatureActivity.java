package com.example.ezhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


public class temperatureActivity extends AppCompatActivity {

//    Button updateBut = findViewById(R.id.button);
    final int min = 36;
    final int max = 45;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

//        TextView tv = findViewById(R.id.tempVal);

//        updateBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final int random = new Random().nextInt((max - min) + 1) + min;
////                tv.setText(Integer.toString(random));
//
//            }
//        });




    }

}