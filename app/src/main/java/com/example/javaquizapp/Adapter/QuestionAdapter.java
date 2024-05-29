package com.example.javaquizapp.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaquizapp.R;
import com.example.javaquizapp.databinding.ViewholderQuestionBinding;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    public interface Score {
        void amount(int number, String clickedAnswer);
    }

    private final String correctAnswer;
    private final List<String> user;
    private final Score returnScore;
    private final AsyncListDiffer<String> differ;
    private boolean isAnswered = false;

    public QuestionAdapter(String correctAnswer, List<String> user, Score returnScore) {
        this.correctAnswer = correctAnswer;
        this.user = user != null ? user : new ArrayList<>();
        this.returnScore = returnScore;
        this.differ = new AsyncListDiffer<>(this, new DiffUtil.ItemCallback<String>() {
            @Override
            public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.differ.submitList(user);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewholderQuestionBinding binding = ViewholderQuestionBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewholderQuestionBinding binding = holder.binding;
        binding.answerTxt.setText(differ.getCurrentList().get(position));

        holder.itemView.setOnClickListener(v -> {
            if (!isAnswered) {
                isAnswered = true;
                String clickedAnswer = getAnswerLetter(position);
                boolean isCorrect = clickedAnswer.equals(correctAnswer);
                if (isCorrect) {
                    setCorrectAnswerAppearance(binding, holder.itemView.getContext());
                    returnScore.amount(5, clickedAnswer);
                } else {
                    setIncorrectAnswerAppearance(binding, holder.itemView.getContext());
                    returnScore.amount(0, clickedAnswer);
                }
                notifyItemChanged(getCorrectAnswerPosition(correctAnswer));
            }
        });

        if (isAnswered) {
            int currentPos = getCorrectAnswerPosition(correctAnswer);
            if (currentPos == position) {
                setCorrectAnswerAppearance(binding, holder.itemView.getContext());
            } else if (differ.getCurrentList().size() == 5 && getClickedAnswerPosition(differ.getCurrentList().get(4)) == position) {
                setIncorrectAnswerAppearance(binding, holder.itemView.getContext());
            }
        }
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public void submitList(List<String> list) {
        differ.submitList(list);
    }

    private int getCorrectAnswerPosition(String answer) {
        switch (answer) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            default:
                return -1;
        }
    }

    private int getClickedAnswerPosition(String answer) {
        switch (answer) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            default:
                return -1;
        }
    }

    private String getAnswerLetter(int position) {
        switch (position) {
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            default:
                return "";
        }
    }

    private void setCorrectAnswerAppearance(ViewholderQuestionBinding binding, Context context) {
        binding.answerTxt.setBackgroundResource(R.drawable.green_background);
        binding.answerTxt.setTextColor(ContextCompat.getColor(context, R.color.white));
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.tick);
        binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null);
    }

    private void setIncorrectAnswerAppearance(ViewholderQuestionBinding binding, Context context) {
        binding.answerTxt.setBackgroundResource(R.drawable.red_background);
        binding.answerTxt.setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderQuestionBinding binding;

        public ViewHolder(ViewholderQuestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
