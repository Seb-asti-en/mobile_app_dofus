package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.project.appclasses.Personnage;
import com.example.project.database_dofusm.DofusMDBHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MyPerso extends AppCompatActivity {

    private ListView mListView;
    private List<String> personnages;
    Intent myIntent;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int theme = prefs.getInt("theme", 0);

        if(theme == 1){
            setTheme(R.style.DarkAppTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_my_perso);
        myIntent = new Intent(this, Display_Perso.class);

        mListView = (ListView) findViewById(R.id.listview2);

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"
        personnages = findPerso();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyPerso.this,
                android.R.layout.simple_list_item_1, personnages);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String h = (String) parent.getItemAtPosition(position);
                String s2 = h.substring(0, h.indexOf("."));
                // get the id from the String and open a new activity displaying the character
                myIntent.putExtra("id_perso", s2);
                startActivity(myIntent);

            }
        });

    }

    public List<String> findPerso(){
        DofusMDBHandler dbHandler = new DofusMDBHandler(this);
        return dbHandler.getPersName();
    }
}
