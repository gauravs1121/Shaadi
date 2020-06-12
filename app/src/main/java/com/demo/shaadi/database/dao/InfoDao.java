package com.demo.shaadi.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.demo.shaadi.database.entity.InfoEntity;
import com.demo.shaadi.holder.InfoHolder;

@Dao
public interface InfoDao {
    @Insert
    void insertInfo(InfoEntity holder);

    @Query("Select * from info_table")
    InfoEntity getAll();

    @Query("DELETE FROM info_table")
    void deleteAll();
}
