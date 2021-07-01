package com.wang.roombasic;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


/**
 * Created by wang on 2021/3/20 0020.
 **/
public class WorldViewModel extends AndroidViewModel {
    private WorldRepository worldRepository;
    public WorldViewModel(@NonNull Application application) {
        super(application);
        worldRepository=new WorldRepository(application);
    }


    public void insertWorlds(World... worlds){
        worldRepository.insertWorlds(worlds);
    }

    public void updateWorld(World world){
        worldRepository.updateWorld(world);

    }

    public void  deleteWorld(World world){
        worldRepository.deleteWorld(world);
    }
    public void deleteAllWorld(){
        worldRepository.deleteAllWorld();
    }

    public LiveData<List<World>> getAllWorlds(){
      return    worldRepository.getAllWorlds();
    }

    public Integer  getMaxId() throws ExecutionException, InterruptedException {
       return worldRepository.getMaxId();
    }



}
