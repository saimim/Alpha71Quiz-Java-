package com.example.javaquizapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.javaquizapp.Adapter.QuestionAdapter;
import com.example.javaquizapp.Domain.QuestionModel;
import com.example.javaquizapp.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements QuestionAdapter.Score {
    private TextView questionTxt, questionNumberTxt;
    private ImageView pic, backBtn, leftArrow, rightArrow;
    private LinearProgressIndicator progressBar;
    private RecyclerView questionList;

    private int position = 0;
    private List<QuestionModel> receivedList = new ArrayList<>();
    private int allScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.grey));

        receivedList = getIntent().getParcelableArrayListExtra("list");
        if (receivedList == null) {
            receivedList = new ArrayList<>();
        }

        initView();
        loadQuestion();

        backBtn.setOnClickListener(view -> finish());

        rightArrow.setOnClickListener(view -> {
            if (progressBar.getProgress() == 10) {
                Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
                intent.putExtra("Score", allScore);
                startActivity(intent);
                finish();
                return;
            }
            position++;
            progressBar.setProgress(progressBar.getProgress() + 1);
            questionNumberTxt.setText("Question " + progressBar.getProgress() + "/10");
            loadQuestion();
        });

        leftArrow.setOnClickListener(view -> {
            if (progressBar.getProgress() == 1) {
                return;
            }
            position--;
            progressBar.setProgress(progressBar.getProgress() - 1);
            questionNumberTxt.setText("Question " + progressBar.getProgress() + "/10");
            loadQuestion();
        });
    }

    private void initView() {
        questionTxt = findViewById(R.id.questionTxt);
        questionNumberTxt = findViewById(R.id.questionNumberTxt);
        pic = findViewById(R.id.pic);
        backBtn = findViewById(R.id.backBtn);
        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);
        progressBar = findViewById(R.id.progressBar);
        questionList = findViewById(R.id.questionList);
    }

    private void loadQuestion() {
        questionTxt.setText(receivedList.get(position).getQuestion());

        int drawableResourceId = getResources().getIdentifier(
                receivedList.get(position).getPicPath(),
                "drawable", getPackageName()
        );

        Glide.with(this)
                .load(drawableResourceId)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)))
                .into(pic);

        loadAnswers();
    }

    private void loadAnswers() {
        List<String> answers = new ArrayList<>();
        answers.add(receivedList.get(position).getAnswer1());
        answers.add(receivedList.get(position).getAnswer2());
        answers.add(receivedList.get(position).getAnswer3());
        answers.add(receivedList.get(position).getAnswer4());

        if (receivedList.get(position).getClickedAnswer() != null) {
            answers.add(receivedList.get(position).getClickedAnswer());
        }

        QuestionAdapter questionAdapter = new QuestionAdapter(
                receivedList.get(position).getCorrectAnswer(), answers, this
        );

        questionList.setLayoutManager(new LinearLayoutManager(this));
        questionList.setAdapter(questionAdapter);
    }

    @Override
    public void amount(int number, String clickedAnswer) {
        allScore += number;
        receivedList.get(position).setClickedAnswer(clickedAnswer);
    }
}
