package com.example.javaquizapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.example.javaquizapp.Adapter.LeaderAdapter;
import com.example.javaquizapp.Domain.UserModel;
import com.example.javaquizapp.R;
import com.example.javaquizapp.databinding.ActivityLeaderBinding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class LeaderActivity extends AppCompatActivity {
    private ActivityLeaderBinding binding;
    private final LeaderAdapter leaderAdapter = new LeaderAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = LeaderActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(LeaderActivity.this, R.color.grey));

        binding.scoreTop1Text.setText(String.valueOf(loadData().get(0).getScore()));
        binding.scoreTop2Text.setText(String.valueOf(loadData().get(1).getScore()));
        binding.scoreTop3Text.setText(String.valueOf(loadData().get(2).getScore()));
        binding.titleTop1Txt.setText(loadData().get(0).getName());
        binding.titleTop2Txt.setText(loadData().get(1).getName());
        binding.titleTop3Txt.setText(loadData().get(2).getName());

        int drawableResourceId1 = binding.getRoot().getResources().getIdentifier(
                loadData().get(0).getPic(), "drawable", binding.getRoot().getContext().getPackageName()
        );

        Glide.with(binding.getRoot().getContext())
                .load(drawableResourceId1)
                .into(binding.pic1);

        int drawableResourceId2 = binding.getRoot().getResources().getIdentifier(
                loadData().get(1).getPic(), "drawable", binding.getRoot().getContext().getPackageName()
        );

        Glide.with(binding.getRoot().getContext())
                .load(drawableResourceId2)
                .into(binding.pic2);

        int drawableResourceId3 = binding.getRoot().getResources().getIdentifier(
                loadData().get(2).getPic(), "drawable", binding.getRoot().getContext().getPackageName()
        );

        Glide.with(binding.getRoot().getContext())
                .load(drawableResourceId3)
                .into(binding.pic3);

        // Correct usage of setItemSelected method
        binding.bottomMenu.setItemSelected(R.id.Board, true);

        binding.bottomMenu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                if (id == R.id.Home) {
                    startActivity(new Intent(LeaderActivity.this, MainActivity.class));
                }
            }
        });

        List<UserModel> list = loadData();
        list.remove(0);
        list.remove(0);  // Index 1 becomes 0 after the first removal
        list.remove(0);  // Index 2 becomes 0 after the second removal

        leaderAdapter.differ.submitList(list);

        binding.leaderView.setLayoutManager(new LinearLayoutManager(LeaderActivity.this));
        binding.leaderView.setAdapter(leaderAdapter);
    }

    private List<UserModel> loadData() {
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(1, "Mim", "person1", 4850));
        users.add(new UserModel(2, "Karim", "person2", 4560));
        users.add(new UserModel(3, "Aslam", "person3", 3873));
        users.add(new UserModel(4, "Johan", "person4", 3250));
        users.add(new UserModel(5, "Sanjida", "person5", 3015));
        users.add(new UserModel(6, "Malek", "person6", 2970));
        users.add(new UserModel(7, "Ashik", "person7", 2870));
        users.add(new UserModel(8, "Akter", "person8", 2670));
        users.add(new UserModel(9, "Jannatun", "person9", 3880));
        return users;
    }
}
