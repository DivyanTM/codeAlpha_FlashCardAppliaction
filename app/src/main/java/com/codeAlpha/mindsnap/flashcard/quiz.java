package com.codeAlpha.mindsnap.flashcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class quiz extends AppCompatActivity {
    private ImageView back;
    private CardView answerCard;
    private int score = 0;
    private int max = 10;
    private int currentQuestionIndex = 0;
    private ArrayList<FlashCard> quizCards;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button showAnswer = findViewById(R.id.btn_show_answer);
        answerCard = findViewById(R.id.answerCardView);
        back = findViewById(R.id.quiz_back);
        answerCard.setAlpha(0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastMaker t=new ToastMaker();
                t.showToast(getApplicationContext(),"Aborted quiz");
                finish();
            }
        });
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerCard.setAlpha(1.0f);
            }
        });

        quizCards = new ArrayList<>(FlashCardAdapter.flashCardList);
        Collections.shuffle(quizCards);
        displayNextQuestion();
        if(quizCards.size()<10){
            max=quizCards.size();
        }
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < max) {
            FlashCard currentCard = quizCards.get(currentQuestionIndex);
            String question = currentCard.getQuestion();
            String answer = currentCard.getAnswer();
            TextView quizQuestion = findViewById(R.id.quiz_q);
            quizQuestion.setText(question);
            TextView quizAnswer = findViewById(R.id.quiz_ans);
            quizAnswer.setText(answer);
            answerCard.setAlpha(0);

            Button iki = findViewById(R.id.btn_iki);
            iki.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answerCard.getAlpha() == 0) {
                        ToastMaker t=new ToastMaker();
                        t.showToast(getApplicationContext(),"View answer first");
                    } else {
                        score++;
                        currentQuestionIndex++;
                        displayNextQuestion();
                    }
                }
            });

            Button idk = findViewById(R.id.btn_idk);
            idk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentQuestionIndex++;
                    displayNextQuestion();
                }
            });
        } else {
            Intent resultIntent = new Intent(quiz.this, results.class);
            resultIntent.putExtra("MAX",max);
            resultIntent.putExtra("SCORE", score);
            startActivity(resultIntent);
            finish();
        }
    }
}
