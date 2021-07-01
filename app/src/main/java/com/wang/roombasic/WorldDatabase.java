package com.wang.roombasic;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by wang on 2021/3/11 0011.
 **/
@Database(entities = {World.class}, version = 2,exportSchema = false)
public abstract class WorldDatabase extends RoomDatabase {
    private static  WorldDatabase instance;
    public WorldDatabase() {
    }

    public  static WorldDatabase getInstance(Context context){
        if (instance == null){
            instance=Room
                    .databaseBuilder(context.getApplicationContext(), WorldDatabase.class, "my_world")
                    .addMigrations(MIG_1_TO_2)
                    .allowMainThreadQueries().build();
        }
        return  instance;
    }

    public    abstract WorldDao worldDao();

    private  static Migration MIG_1_TO_2 =new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE world ADD COLUMN is_use INTEGER NOT NULL DEFAULT 1 ");
        }
    };

}
