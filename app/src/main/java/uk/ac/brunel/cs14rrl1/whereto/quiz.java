package uk.ac.brunel.cs14rrl1.whereto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class quiz extends AppCompatActivity {

    int score = 0;
    private int currentQuestion;
    private String [] questions;
    private String [] answers;
    private Button answerButton;
    private ImageButton nextQuestion;
    private ImageButton prevQuestion;
    private TextView questionView;
    private TextView answerView;
    private EditText answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();
    }

    public void init() {
        questions = new String[]{"In what year was the current St Paul's Cathedral built?",
                "In what year was the cathedral previously burnt down by the great fire of London?",
                "Which architect was responsible for the construction of the cathedral?"};

        answers = new String[]{"June 21, 1675", "1666", "Sir Christopher Wren"};

        currentQuestion = 0;

        answerButton = (Button) findViewById(R.id.answercheck);
        questionView = (TextView) findViewById(R.id.questionview);
        answerView = (TextView) findViewById(R.id.answerview);
        answerText = (EditText) findViewById(R.id.answerbar);
        nextQuestion = (ImageButton) findViewById(R.id.nextbutton);
        prevQuestion = (ImageButton) findViewById(R.id.prevbutton);

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextQuestion();
            }
        });
        prevQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPrevQuestion();
            }
        });
    }

    public void showNextQuestion()
    {

        currentQuestion++;
        if (currentQuestion == questions.length) {
            currentQuestion = 0;
        }

        questionView.setText(questions[currentQuestion]);
        answerView.setText("");
        answerText.setText("");
    }

    public void showPrevQuestion()
    {

        if (currentQuestion == 0)
        {
            Toast.makeText(getApplicationContext(), "This is the first question",
                    Toast.LENGTH_SHORT).show();
        }

        else
        {
            currentQuestion--;
        }

        questionView.setText(questions[currentQuestion]);
        answerView.setText("");
        answerText.setText("");
    }

    public boolean isCorrect(String answer)
    {
        return (answer.equalsIgnoreCase(answers[currentQuestion]));
    }

    public void checkAnswer()
    {
        String answer = answerText.getText().toString();
        if (isCorrect(answer))
            answerView.setText("Correct!");
        else
            answerView.setText("Sorry, the correct answer is " + answers[currentQuestion]);
    }




}

