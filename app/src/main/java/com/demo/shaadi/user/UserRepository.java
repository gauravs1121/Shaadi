package com.demo.shaadi.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.shaadi.R;
import com.demo.shaadi.database.AppDatabase;
import com.demo.shaadi.database.dao.AppDao;
import com.demo.shaadi.database.dao.InfoDao;
import com.demo.shaadi.database.entity.InfoEntity;
import com.demo.shaadi.holder.InfoHolder;
import com.demo.shaadi.holder.ResultHolder;
import com.demo.shaadi.holder.UserListHolder;
import com.demo.shaadi.network.APICall;
import com.demo.shaadi.network.RetrofitConfig;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    Context context;
    APICall apiCall;

    public UserRepository(Context context) {
        this.context = context;
        apiCall = RetrofitConfig.getClient(context).create(APICall.class);
    }


    public LiveData<UserListHolder> getUserData() {

        final MutableLiveData<UserListHolder> data = new MutableLiveData<>();
        apiCall.getUsers("10").enqueue(new Callback<UserListHolder>() {
            @Override
            public void onResponse(Call<UserListHolder> call, Response<UserListHolder> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserListHolder holder = response.body();
                    InfoHolder info = holder.getInfo();

                    InfoEntity infoEntity = new InfoEntity(info.getSeed(), info.getResults(), info.getPage(), info.getVersion());
                    new insertInfoAsync(AppDatabase.getDatabase(context).infoDao()).execute(infoEntity);
                    new insertLocAsync(AppDatabase.getDatabase(context).appDao()).execute(holder.getResults());

                    data.setValue(holder);
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserListHolder> call, Throwable t) {
                data.setValue(null);
                Toast.makeText(context, context.getResources().getString(R.string.e_fail_data), Toast.LENGTH_SHORT).show();
            }
        });

        return data;
    }

    public LiveData<List<ResultHolder>> getUserDataFromDB() {
        try {
            return new getAllUserData().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateHolder(ResultHolder holder) {
        new updateAsync().execute(holder);
    }


    private static class insertInfoAsync extends AsyncTask<InfoEntity, Void, Void> {
        private InfoDao infoDao;

        public insertInfoAsync(InfoDao infoDao) {
            this.infoDao = infoDao;
        }


        @Override
        protected Void doInBackground(InfoEntity... infoEntities) {
            infoDao.deleteAll();
            infoDao.insertInfo(infoEntities[0]);
            Log.e("TAG", new Gson().toJson(infoDao.getAll()));
            return null;
        }
    }

    private static class insertLocAsync extends AsyncTask<List<ResultHolder>, Void, Void> {
        private AppDao appDao;

        public insertLocAsync(AppDao appDao) {
            this.appDao = appDao;
        }


        @SafeVarargs
        @Override
        protected final Void doInBackground(List<ResultHolder>... resultHolders) {
            appDao.deleteAll();
            appDao.insertLocRecord(resultHolders[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class getAllUserData extends AsyncTask<Void, Void, LiveData<List<ResultHolder>>> {
        @Override
        protected LiveData<List<ResultHolder>> doInBackground(Void... url) {
            return AppDatabase.getDatabase(context).appDao().getAll();
        }
    }

    private class updateAsync extends AsyncTask<ResultHolder, Void, Void> {


        @Override
        protected Void doInBackground(ResultHolder... resultHolders) {
            AppDatabase.getDatabase(context).appDao().UpdateColumnById(resultHolders[0].getStatus(),
                    resultHolders[0].get_rId());
            return null;
        }
    }
}
