package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicInteger;

public class QuizActivity extends AppCompatActivity {

    boolean ResponseGive = false;
    int timeLeft = 0;
    Handler handler = new Handler();
    ProgressBar progressBar;
    TextView progressTXT;
    AtomicInteger progress = new AtomicInteger();
    User user;
    int idDistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressTXT = (TextView) findViewById(R.id.progressTXT);
        mToastRunnable.run();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.user = (User) bundle.getSerializable("userInfo");
        this.idDistrict = (int) bundle.getInt("typeQuiz");
        TextView nomJoueurTV = (TextView) findViewById(R.id.nomJoueurTV);
        nomJoueurTV.setText(user.getuserName());
        TextView districtTV = (TextView) findViewById(R.id.districtTV);
        districtTV.setText("District " + idDistrict);
        System.out.println("------------------" + user.getuserName());

        ImageView imgAvatarJoueur = (ImageView) findViewById(R.id.imgAvatarJoueur);
//        imgAvatarJoueur.setImageResource(R.drawable.avatar_joueur);
        ImageView imgAvatarAdversaire = (ImageView) findViewById(R.id.imgAvatarAdversaire);
//        imgAvatarAdversaire.setImageResource(R.drawable.avatar_bot);
    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            if (progressBar.getProgress() > 0) {
//                Toast.makeText(QuizActivity.this, "Progress BAR : "+progressBar.getProgress(), Toast.LENGTH_SHORT).show();
                progress.getAndIncrement();
                timeLeft = 100 - (int) progress.get();
                progressBar.setProgress(timeLeft);
                progressTXT.setText(String.valueOf((float) timeLeft / 10));
                handler.postDelayed(this, 100);
            }
            if (progressBar.getProgress() == 1) {
                Toast.makeText(QuizActivity.this, "PERDU", Toast.LENGTH_SHORT).show();
            }

        }
    };
}