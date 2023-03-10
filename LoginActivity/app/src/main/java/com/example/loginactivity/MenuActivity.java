package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MenuActivity extends AppCompatActivity {

    public static User user = new User();
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        User userInfo = (User) bundle.getSerializable("userInfo");
        user.setUser(userInfo);

        TextView t = findViewById(R.id.MenuTitle);
        t.setText(user.getInfo());

//        ImageView IVPrincipalMenu = (ImageView) findViewById(R.id.IVPrincipalMenu);
//        IVPrincipalMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setQuiz(0);
//            }
//        });

        //Mise en place de la liste de Quiz
        this.gridView = findViewById(R.id.ListQuiz);
        ArrayList<TypeQuiz> arrayQuiz = new ArrayList<>();
        setListTypeQuiz(arrayQuiz);
        AdapterTypeQuiz arrayQuizAdapter = new AdapterTypeQuiz(MenuActivity.this, R.layout.quiz_item, arrayQuiz);
        gridView.setAdapter(arrayQuizAdapter);
    }


    private void setQuiz(int typeQuiz) {
        Intent i = new Intent(this, QuizActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInfo", user);
        bundle.putInt("typeQuiz", typeQuiz);
        i.putExtras(bundle);
        startActivity(i);
    }

    public static User getUser() {
        return user;
    }

    private void setListTypeQuiz(ArrayList<TypeQuiz> arrayQuiz) {
        ArrayList<String> ListQuiz = new ArrayList<String>();
        Collections.addAll(ListQuiz,
                "Al??atoire", "Animal", "Technologie", "G??ographie", "France", "D??veloppement", "Histoire", "Jeu-Vid??o");

        for (int i = 0; i < ListQuiz.size(); i++) {
            arrayQuiz.add(new TypeQuiz(ListQuiz.get(i), i));
        }

    }
}