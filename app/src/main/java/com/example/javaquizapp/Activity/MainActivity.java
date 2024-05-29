package com.example.javaquizapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.javaquizapp.Domain.QuestionModel;
import com.example.javaquizapp.R;
import com.example.javaquizapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.grey));

        binding.bottomMenu.setItemSelected(R.id.Home, false);
        binding.bottomMenu.setOnItemSelectedListener(item -> {
            if (item == R.id.Board) {
                startActivity(new Intent(MainActivity.this, LeaderActivity.class));
            }
        });

        binding.singleBtn.setOnClickListener(v -> {
            List<QuestionModel> questions = questionList();
            if (questions != null) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putParcelableArrayListExtra("list", new ArrayList<>(questions));
                startActivity(intent);
            } else {
                Log.e("MainActivity", "questionList() returned null");
            }
        });
    }

    private List<QuestionModel> questionList() {
        List<QuestionModel> question = new ArrayList<>();
        question.add(new QuestionModel(1, "Which planet is the largest place in the solar system?", "Earth", "Mars", "Neptune", "Jupiter", "d", 5, "q_1", "null"));
        question.add(new QuestionModel(2, "Which Country is the largest country in the world by land area?", "Russia", "Canada", "United States", "China", "a", 5, "q_2", "null"));
        question.add(new QuestionModel(3, "Which of the following substances is used as an anti-cancer medication in the medical world?", "Cheese", "Lemon Juice", "Cannabis", "Paspalum", "c", 5, "q_3", "null"));
        question.add(new QuestionModel(4, "Which moon in the Earth's solar system has an atmosphere?", "Luna (Moon)", "Phobos (Mars' moon)", "Venus' moon", "None of the above", "d", 5, "q_4", "null"));
        question.add(new QuestionModel(5, "Which of the following symbols represents the chemical element with the atomic number 6?", "0", "H", "C", "N", "c", 5, "q_5", "null"));
        question.add(new QuestionModel(6, "Who is credited with inventing theater as we know it today?", "Shakespeare", "Arthur Miller", "Ashkouri", "Ancient Greeks", "d", 5, "q_6", "null"));
        question.add(new QuestionModel(7, "Which ocean is the largest ocean in the world?", "Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic ocean", "a", 5, "q_7", "null"));
        question.add(new QuestionModel(9, "In Which continent are the most independent countries located?", "Asia", "Europe", "Africa", "Americas", "c", 5, "q_9", "null"));
        question.add(new QuestionModel(10, "Which ocean has the greatest average depth?", "Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Southern Ocean", "d", 5, "q_10", "null"));
        return question;
    }
}
