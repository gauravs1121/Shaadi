package com.demo.shaadi.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.demo.shaadi.R;
import com.demo.shaadi.databinding.ActivityUserListBinding;
import com.demo.shaadi.holder.ResultHolder;
import com.demo.shaadi.network.APICall;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    ActivityUserListBinding userListBinding;
    UserDetailAdapter adapter;
    List<ResultHolder> resultHolderList = new ArrayList<>();
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userListBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);
        viewModel = new UserViewModel(this);
        userListBinding.setViewModel(viewModel);
        userListBinding.executePendingBindings();
        adapter = new UserDetailAdapter(this, resultHolderList);
        userListBinding.setUserAdapter(adapter);
        viewModel.getUserList().observe(this, userListHolder -> {
            if (userListHolder != null) {
                resultHolderList = userListHolder.getResults();
                adapter = new UserDetailAdapter(this, resultHolderList);
                userListBinding.setUserAdapter(adapter);
            }
        });


        viewModel.getUserFromDB().observe(this, resultHolders -> {
            resultHolderList.addAll(resultHolders);
            adapter = new UserDetailAdapter(this, resultHolderList);
            userListBinding.setUserAdapter(adapter);
        });
    }


}


