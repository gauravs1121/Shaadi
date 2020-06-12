package com.demo.shaadi.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.demo.shaadi.database.dao.AppDao;
import com.demo.shaadi.database.dao.InfoDao;
import com.demo.shaadi.database.entity.InfoEntity;
import com.demo.shaadi.holder.LocationHolder;
import com.demo.shaadi.holder.ResultHolder;

@Database(entities ={ InfoEntity.class, ResultHolder.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE; //singleton
    public abstract InfoDao infoDao();
    public abstract AppDao appDao();
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "shaadi_db")
                            .fallbackToDestructiveMigration() // it will delete database with all tables in case of no migration
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
