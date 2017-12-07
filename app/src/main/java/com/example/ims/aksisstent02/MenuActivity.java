package com.example.ims.aksisstent02;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    public String searchQuery;
    private String searchResult;
    private Boolean searchState;

    EditText suche;
    Button enter;
    Button noten;
    Button stupla;
    Button prufung;
    TextView begrussung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        begrussung = (TextView) findViewById(R.id.viewBegrussung);
        suche = (EditText) findViewById(R.id.editSuche);
        enter = (Button) findViewById(R.id.btnEnter);
        noten = (Button) findViewById(R.id.btnNoten);
        stupla = (Button) findViewById(R.id.btnStupla);
        prufung = (Button) findViewById(R.id.btnTests);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search Alpha = new Search();
                searchQuery = suche.getText().toString(); //Get Query
                searchResult = Alpha.doSearch(searchQuery);
                if (searchResult != null) {
                    startActivity(new Intent(MenuActivity.this, DataActivity.class));
                    DataActivity Beta = new DataActivity();
                    Beta.setTeacher(searchResult);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Kein Suchresultat gefunden", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        noten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, NotenActivity.class));
            }
        });

        stupla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, StuplaActivity.class));
            }
        });
        prufung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, PrufungActivity.class));
            }
        });
    }
}




