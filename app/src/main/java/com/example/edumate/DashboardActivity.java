package com.example.edumate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    TextView welcomeText;
    Button askAiBtn;
    DatabaseHelper dbHelper;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dbHelper = new DatabaseHelper(this);
        currentUser = getIntent().getStringExtra("username");

        welcomeText = findViewById(R.id.tvWelcome);
        askAiBtn = findViewById(R.id.btnAskAI);

        String userName = dbHelper.getUserName(currentUser);
        welcomeText.setText("Welcome, " + userName);

        askAiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, AskAIActivity.class);
                startActivity(i);
            }
        });

        Button questionsBtn = findViewById(R.id.btnQuestions);
        questionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, QuestionsActivity.class);
                startActivity(i);
            }
        });
    }
}
