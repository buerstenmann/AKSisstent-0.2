package com.example.ims.aksisstent02;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    public String searchQuery;
    private String searchresult;


    TextView begrussung = (TextView) findViewById(R.id.viewBegrussung);
    Button enter = (Button) findViewById(R.id.btnEnter);
    EditText suche = (EditText) findViewById(R.id.editSuche);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search Alpha = new Search();
                searchQuery = suche.getText().toString(); //Get Query
                searchresult = Alpha.openFile(searchQuery);
                begrussung.setText(searchresult);
            }
        });
    }
}




