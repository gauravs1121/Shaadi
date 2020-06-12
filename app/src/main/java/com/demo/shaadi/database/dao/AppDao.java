package com.demo.shaadi.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.demo.shaadi.holder.ResultHolder;

import java.util.List;

@Dao
public interface AppDao {
    @Insert
    void insertLocRecord(List<ResultHolder> resultHolder);

    @Query("Select * from result_table")
    LiveData<List<ResultHolder>> getAll();

    @Query("DELETE FROM result_table")
    void deleteAll();

    @Query("UPDATE result_table SET status=:text WHERE _rId=:id")
    void UpdateColumnById(String text, int id);
}
