package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project.ui.login.LoginActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void loadNews(View view){

        Intent newsMenu = new Intent(MainMenu.this, NewsMenu.class);
        startActivity(newsMenu);

    }

    public void logUser(View view){

        Intent loginActivity = new Intent(MainMenu.this, LoginActivity.class);
        startActivity(loginActivity);

    }
    public void CreatePerso(View view){

        Intent loginActivity = new Intent(MainMenu.this, CreatePerso.class);
        startActivity(loginActivity);

    }

    public void myPerso(View view){

        Intent loginActivity = new Intent(MainMenu.this, MyPerso.class);
        startActivity(loginActivity);

    }
}
