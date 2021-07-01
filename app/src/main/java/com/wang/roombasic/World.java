package com.wang.roombasic;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by wang on 2021/3/11 0011.
 **/
@Entity
public class World {
    @PrimaryKey(autoGenerate = true)
    private int  id;

    private String name;

    @ColumnInfo(name = "release_age")
    private int age;

    @ColumnInfo(name = "is_use")
    private boolean isUse;



@Ignore
    public World(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public World(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
@Ignore
    public World(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }
}
