package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button AuthButton = (Button) findViewById(R.id.AuthButton);
        AuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuncAuth(v);
            }
        });
    }

    public void FuncAuth(View v) {

        EditText nameTXT = (EditText) findViewById(R.id.identifiantWrite);
        EditText pwdTXT = (EditText) findViewById(R.id.passwordWrite);

        String nameValue = nameTXT.getText().toString().trim();
        String pwdValue = pwdTXT.getText().toString().trim();
        String msgToast = null;

        if (!TextUtils.isEmpty(nameValue) && !TextUtils.isEmpty(pwdValue)) {
            msgToast = "Authentification Réussi !";
            setSecondActivity(nameValue, pwdValue);
        } else {
            msgToast = "Tous les champs doivent être complétés !";
        }
        Toast authToast = Toast.makeText(v.getContext(), msgToast, Toast.LENGTH_SHORT);
        authToast.show();
        if (TextUtils.isEmpty(nameValue)) {
            nameTXT.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        } else {
            nameTXT.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        }
        if (TextUtils.isEmpty(pwdValue)) {
            pwdTXT.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        } else {
            pwdTXT.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        }
    }

    private void setSecondActivity(String nameValue, String pwdValue) {
        User user = new User(nameValue, pwdValue);
        Intent i = new Intent(this, MenuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInfo", user);
        i.putExtras(bundle);
        startActivity(i);
    }
}