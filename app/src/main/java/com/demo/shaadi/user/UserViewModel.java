package com.demo.shaadi.user;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;

import com.demo.shaadi.holder.ResultHolder;
import com.demo.shaadi.holder.UserListHolder;

import java.util.List;

public class UserViewModel extends BaseObservable {
    Context context;
    UserRepository repository;

    public UserViewModel(Context context) {
        this.context = context;
        repository = new UserRepository(context);
    }

    public LiveData<UserListHolder> getUserList() {
        return repository.getUserData();
    }

    public LiveData<List<ResultHolder>> getUserFromDB() {
        return repository.getUserDataFromDB();
    }
}
