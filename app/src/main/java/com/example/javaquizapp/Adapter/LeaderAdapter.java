package com.example.javaquizapp.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.javaquizapp.Domain.UserModel;
import com.example.javaquizapp.databinding.ViewholderLeadersBinding;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.ViewHolder> {

    private ViewholderLeadersBinding binding;

    @NonNull
    @Override
    public LeaderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ViewholderLeadersBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewholderLeadersBinding binding = holder.binding;
        binding.titleTxt.setText(differ.getCurrentList().get(position).getName());

        int drawableResourceId = binding.getRoot().getResources().getIdentifier(
                differ.getCurrentList().get(position).getPic(),
                "drawable", binding.getRoot().getContext().getPackageName()
        );

        Glide.with(binding.getRoot().getContext())
                .load(drawableResourceId)
                .into(binding.pic);

        binding.rowTxt.setText(String.valueOf(position + 4));
        binding.scoreTxt.setText(String.valueOf(differ.getCurrentList().get(position).getScore()));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderLeadersBinding binding;

        public ViewHolder(ViewholderLeadersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private final DiffUtil.ItemCallback<UserModel> differCallback = new DiffUtil.ItemCallback<UserModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    public final AsyncListDiffer<UserModel> differ = new AsyncListDiffer<>(this, differCallback);
}