package com.demo.shaadi.user;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.shaadi.BR;
import com.demo.shaadi.R;
import com.demo.shaadi.database.AppDatabase;
import com.demo.shaadi.databinding.UserCardBinding;
import com.demo.shaadi.holder.ResultHolder;

import java.util.List;

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.ViewHolder> implements UserClickListener {
    List<ResultHolder> resultHolders;
    Context context;
    UserRepository repository;

    public UserDetailAdapter(Context context, List<ResultHolder> resultHolderList) {
        this.context = context;
        this.resultHolders = resultHolderList;
        repository = new UserRepository(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.user_card, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(resultHolders.get(holder.getAdapterPosition()));
        holder.userCardBinding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return resultHolders == null ? 0 : resultHolders.size();
    }

    @Override
    public void accept(ResultHolder holder) {

        holder.setStatus("Accept");
        repository.updateHolder(holder);
     }

    @Override
    public void decline(ResultHolder holder) {

        holder.setStatus("Decline");
        repository.updateHolder(holder);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        UserCardBinding userCardBinding;

        public ViewHolder(@NonNull UserCardBinding userCardBinding) {
            super(userCardBinding.getRoot());
            this.userCardBinding = userCardBinding;

        }

        public void bind(ResultHolder resultHolder) {
            userCardBinding.setVariable(BR.userData, resultHolder);
            userCardBinding.executePendingBindings();

        }
    }
}
