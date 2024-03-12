package com.codeAlpha.mindsnap.flashcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastMaker {
    String toastText;
    public void showToast(Context context,String str){
        LayoutInflater li=LayoutInflater.from(context);
        View v=li.inflate(R.layout.toast,null);
        TextView tv=v.findViewById(R.id.toastText);
        tv.setText(str);
        Toast toast=new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(v);
        toast.show();
    }
}
