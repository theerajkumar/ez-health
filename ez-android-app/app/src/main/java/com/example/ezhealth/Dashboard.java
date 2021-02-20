package com.example.ezhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {
    private static final String PRIMARY_CHANNEL_ID="primary_notification";
    private NotificationManager mnotifymnager;
    private static final int NOTIFICATIONID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        createNotifcationChannel();
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //set Home Fragment as Default Startup Screen
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
    }

   final private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.mngRec:
                            selectedFragment = new ManageFragment();
                            break;
                        case R.id.prof:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.action_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.chatbot:
                            selectedFragment = new ChatbotFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();

                    return true;
                }
            };

    public void chatbot(View view) {
        Intent i=new Intent(this,chatbotmainactivity.class);
        startActivity(i);
    }

    public void logout(View view) {
        sendNotification();
    }
    public void sendNotification(){
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mnotifymnager.notify(NOTIFICATIONID, notifyBuilder.build());
    }
    public void createNotifcationChannel(){
        mnotifymnager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notifychnl=new NotificationChannel(PRIMARY_CHANNEL_ID,"theeraj",NotificationManager.IMPORTANCE_HIGH);
            notifychnl.enableLights(true);
            notifychnl.setLightColor(Color.RED);
            notifychnl.enableVibration(true);
            notifychnl.setDescription("Notification from theeraj");
            mnotifymnager.createNotificationChannel(notifychnl);
        }
    }
    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATIONID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Todays Health status!")
                .setContentText("Your heart rate is in  abnormal state please take care of it")
                .setSmallIcon(R.drawable.ic_health_care).setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
        return notifyBuilder;
    }
}