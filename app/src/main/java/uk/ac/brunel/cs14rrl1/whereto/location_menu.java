package uk.ac.brunel.cs14rrl1.whereto;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class location_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_menu);

        ImageView factsButton = (ImageView) findViewById(R.id.factsbutton);
        ImageView quizButton = (ImageView) findViewById(R.id.quizbutton);

        factsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), facts.class);
                startActivity(i);
            }

        });

        quizButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), quiz.class);
                startActivity(i);
            }

        });


    }


}
