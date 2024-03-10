package com.example.quizapp;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    int score = 0;
    TextView questionTextView;
    TextView totalQuestionTextView;
    Button Answer_A, Answer_B, Answer_C, Answer_D;
    Button SUBMIT;
    int totalQuestions = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.Question);
        Answer_A = findViewById(R.id.Answer_A);
        Answer_B = findViewById(R.id.Answer_B);
        Answer_C = findViewById(R.id.Answer_C);
        Answer_D = findViewById(R.id.Answer_D);
        SUBMIT = findViewById(R.id.SUBMIT);

        Answer_A.setOnClickListener(this);
        Answer_B.setOnClickListener(this);
        Answer_C.setOnClickListener(this);
        Answer_D.setOnClickListener(this);
        SUBMIT.setOnClickListener(this);

        totalQuestionTextView.setText("total Question : " +totalQuestions);

        loadNewQuestion();
    }
    private void loadNewQuestion(){
        if (currentQuestionIndex==totalQuestions){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        Answer_A.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        Answer_B.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        Answer_C.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        Answer_D.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        selectedAnswer = "";
    }
    private void finishQuiz(){
        String passStatus;
    if (score >= totalQuestions * 0.6){
        passStatus = "Passed";
    }
    else {
        passStatus = " Failed";
    }
    new AlertDialog.Builder(this)
            .setTitle(passStatus)
            .setMessage("Your score is "+score+ " Out of " +totalQuestions)
            .setPositiveButton("Restart" , ((dialog,i)->restartQuiz() ))
            .setCancelable(false)
            .show();
    }
    private void restartQuiz(){
        score = 0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view){

        Answer_A.setBackgroundColor(Color.WHITE);
        Answer_B.setBackgroundColor(Color.WHITE);
        Answer_C.setBackgroundColor(Color.WHITE);
        Answer_D.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;

        if(clickedButton.getId() == R.id.SUBMIT){
            if(!selectedAnswer.isEmpty()){
                if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                    score++;
                }else{
                    clickedButton.setBackgroundColor(Color.MAGENTA);
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }else{

            }
        }else{
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }
    }

}
