package com.codeAlpha.mindsnap.flashcard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    private FlashCardAdapter adapter;
    private List<FlashCard> flashCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView back=findViewById(R.id.app_bar_back);
//        app bar back button onclicklistener

        back.setOnClickListener(v1 -> {
            ToastMaker toastMaker=new ToastMaker();
            toastMaker.showToast(home.this,"activity finished");
            finish();
        });

        RecyclerView content_recyclerView = findViewById(R.id.recyclerview);
        content_recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        flashcard
            flashCards=new ArrayList<>();
            adapter=new FlashCardAdapter(flashCards);
            content_recyclerView.setAdapter(adapter);

           Button createCard=findViewById(R.id.btn_create_card);
           Button takeQuiz=findViewById(R.id.btn_tk_quiz);

           takeQuiz.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(flashCards.size()==0){
                       ToastMaker t=new ToastMaker();
                       t.showToast(home.this,"create flash cards first");
                   }else{
                       Intent quizIntent=new Intent(home.this, quiz.class);
                       startActivity(quizIntent);
                   }
               }
           });
    }
    public void cardCreate(View view){
       Dialog d=new Dialog(home.this);
        d.setContentView(R.layout.custom_dialog);
        d.setCancelable(true);
        if(d.getWindow()!=null){
            d.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        EditText et1=d.findViewById(R.id.dialog_question);
        EditText et2=d.findViewById(R.id.dialog_answer);
        LinearLayout ll=d.findViewById(R.id.linearLayout);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question=et1.getText().toString();
                String answer=et2.getText().toString();
                flashCards.add(new FlashCard(question,answer));
                adapter.notifyDataSetChanged();
                ToastMaker t=new ToastMaker();
                t.showToast(getApplicationContext(),"inserted a card");
                d.cancel();
            }
        });
        d.show();
    }

}