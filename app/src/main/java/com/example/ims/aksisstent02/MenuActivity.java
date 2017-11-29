package com.example.ims.aksisstent02;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    public String searchQuery;
    private String searchResult;
    private Boolean searchState;

    TextView begrussung;
    Button enter;
    EditText suche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        begrussung = (TextView) findViewById(R.id.viewBegrussung);
        enter = (Button) findViewById(R.id.btnEnter);
        suche = (EditText) findViewById(R.id.editSuche);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search Alpha = new Search();
                searchQuery = suche.getText().toString(); //Get Query
                searchResult = Alpha.doSearch(searchQuery);
                if (searchResult != null) {
                    begrussung.setText(searchResult);

                    startActivity(new Intent(MenuActivity.this, DataActivity.class));
                    DataActivity Beta = new DataActivity();
                    Beta.setTeacher(searchResult);
                } else {
                    begrussung.setText("Kein Suchresultat gefunden");
                }


            }
        });
    }
}




