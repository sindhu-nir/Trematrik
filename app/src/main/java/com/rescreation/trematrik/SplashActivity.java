package com.rescreation.trematrik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {


    Handler handler;
    ProgressBar bar;
    Context mContext;
    private LottieAnimationView splashAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        mContext = SplashActivity.this;
        splashAnimation = findViewById(R.id.splashAnimation);
        splashAnimation.setVisibility(View.VISIBLE);
        bar = (ProgressBar) findViewById(R.id.progress1);

        if (isNetworkConnected()) {

            splash();

        } else {
            ShowAlert("No Internet Connection", "Connect to the internet \n and try again");
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }






    public static int CheckValue(String a,String[] cache){

        for(int k=0;k<cache.length;k++){
            if(cache[k]==null){

            }
            else if(cache[k].equals(a)){

                return k;
            }
        }
        return -1;
    }

    public static void PrintArry(String[] cacheLase){
        for(int k=0;k<cacheLase.length;k++){
            if(k==4){
                System.out.print(cacheLase[k]+"");
            }
            else{
                System.out.print(cacheLase[k]+"-");
            }
        }
    }









    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(mContext.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    private void splash() {
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }


    private void doWork() {
        for (int progress = 0; progress < 100; progress += 10) {
            try {
                Thread.sleep(500);
                bar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
                //Timber.e(e.getMessage());
            }
        }
    }

    private void startApp() {

        if (isNetworkConnected()) {

//            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            // ShowAlert("ERROR","No Internet Connection");
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_LONG).show();
            // System.exit(1);
//            Intent intent = new Intent(SplashScreen.this, OfflineActivity.class);
//            startActivity(intent);
        }


    }

    public void ShowAlert(String title, String msg) {


        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SplashActivity.this);

        dlgAlert.setMessage(msg);
        dlgAlert.setTitle(title);
        dlgAlert.setIcon(R.mipmap.trematrik_icon);
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // call your code here
                System.exit(1);
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }


}
