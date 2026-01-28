package com.example.edumate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AskAIActivity extends AppCompatActivity {

    EditText questionInput;
    Button askBtn;
    TextView questionDisplay, answerDisplay;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_ai);

        dbHelper = new DatabaseHelper(this);

        questionInput = findViewById(R.id.etQuestion);
        askBtn = findViewById(R.id.btnAsk);
        Button clearBtn = findViewById(R.id.btnClear);
        questionDisplay = findViewById(R.id.tvQuestionDisplay);
        answerDisplay = findViewById(R.id.tvAnswer);

        askBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = questionInput.getText().toString().trim();
                if(!question.isEmpty()) {
                    questionDisplay.setText(question);
                    String answer = dbHelper.findAnswer(question);
                    answerDisplay.setText(answer);
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionInput.setText("");
                questionDisplay.setText("");
                answerDisplay.setText("");
            }
        });
    }
}
