package com.codeAlpha.mindsnap.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class results extends AppCompatActivity {
    private ProgressBar resultProgressBar;
    private int max;
    private int score;
    private int correct;
    private int wrong;
    private TextView crct, wrng, scr, prg;
    private ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        max = getIntent().getIntExtra("MAX",10);
        score = getIntent().getIntExtra("SCORE", 0);
        correct = score;
        wrong = max - correct;
        back=findViewById(R.id.back_result);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastMaker t=new ToastMaker();
                t.showToast(getApplicationContext(),"cleared results");
                finish();
            }
        });

        resultProgressBar = findViewById(R.id.result_progress);
        resultProgressBar.setMax(max);
        resultProgressBar.setProgress(score);

        crct = findViewById(R.id.txt_correct);
        wrng = findViewById(R.id.txt_wrong);
        scr = findViewById(R.id.txt_total);
        prg = findViewById(R.id.progress_text);

        float pint = ((float) score / max) * 100;

        prg.setText((int) pint + "%");
        crct.setText(String.valueOf(correct));
        wrng.setText(String.valueOf(wrong));
        scr.setText(String.valueOf(score));
    }
}
