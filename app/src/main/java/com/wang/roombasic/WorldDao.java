package com.wang.roombasic;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by wang on 2021/3/11 0011.
 **/
@Dao
interface WorldDao {
    @Insert
    void insertWorld(World... worlds);
    @Update
    void updateWorld(World... worlds);

    @Delete
    void deleteWorld(World... worlds);

    @Query("DELETE FROM WORLD")
    void deleteAll();

    @Query("SELECT max(id) FROM WORLD")
    int getMaxId();


    @Query("SELECT * FROM WORLD")
    LiveData<List<World>> getAllWorlds();
}
