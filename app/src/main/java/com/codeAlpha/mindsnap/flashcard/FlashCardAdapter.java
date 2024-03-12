package com.codeAlpha.mindsnap.flashcard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlashCardAdapter extends RecyclerView.Adapter<FlashCardAdapter.ViewHolder> {
    public static List<FlashCard> flashCardList;

    public FlashCardAdapter(List<FlashCard> flashCardList) {
        FlashCardAdapter.flashCardList = flashCardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FlashCard flashCard = flashCardList.get(position);
        holder.questionTextView.setText(flashCard.getQuestion());
        holder.answerTextView.setText(flashCard.getAnswer());
    }

    @Override
    public int getItemCount() {
        return flashCardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;
        TextView answerTextView;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.list_item_q);
            answerTextView = itemView.findViewById(R.id.list_item_ans);
        }
    }
}
