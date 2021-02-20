package com.example.ezhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

public class HomeFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container, false);

        //initialize the cards
        CardView heartCard = view.findViewById(R.id.heartCard);
        CardView tempCard = view.findViewById(R.id.tempCard);
        CardView ecgCard = view.findViewById(R.id.ecgCard);
        CardView oxyCard = view.findViewById(R.id.oxyCardView);
        CardView respirationCard = view.findViewById(R.id.respirationCard);
        CardView bloodPressureCard = view.findViewById(R.id.bloodpressureCard);



        //***** NAVIGATION TO OTHER ACTIVITIES ******//

        //navigate to realtime heartbeat activity
        heartCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),heartBeatActivity.class);
                startActivity(intent);
            }
        });

        //navigate to realtime temp activity
        tempCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),temperatureActivity.class);
                startActivity(intent);
            }
        });

        //navigate to realtime ecg activity
        ecgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ecgActivity.class);
                startActivity(intent);
            }
        });
        //navigate to realtime oxySaturation activity
        oxyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), oxySaturationActivity.class);
                startActivity(intent);
            }
        });
        //navigate to realtime respiration activity
        respirationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),respiration.class);
                startActivity(intent);
            }
        });
        //navigate to realtime bloodPressure activity
        bloodPressureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),bloodpressure.class);
                startActivity(intent);
            }
        });


        return view;

    }


}
