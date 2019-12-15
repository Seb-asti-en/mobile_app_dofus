package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.project.appclasses.Personnage;
import com.example.project.database_dofusm.DofusMDBHandler;

public class DisplayObj extends AppCompatActivity {

    private TextView dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_obj);

        Intent inte = getIntent();
        String _id = inte.getStringExtra("id_obj");
        dis = (TextView) findViewById(R.id.textView5);
        dis.setText(showPerso(Integer.parseInt(_id)));
    }

    public String showPerso(int id){
        DofusMDBHandler dbHandler = new DofusMDBHandler(this);

        // Objectif o = dbHandler.findObjHandler(Integer.toString(id));
        String o = "";


        refreshScreen();
        String obj = o.toString();
        return obj;
    }

    public void refreshScreen(){
        dis.setText("");
    }
}