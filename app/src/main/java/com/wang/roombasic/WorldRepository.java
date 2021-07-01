package com.wang.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

/**
 * Created by wang on 2021/3/20 0020.
 **/
public class WorldRepository {
    private LiveData<List<World>> allWorlds;
    private WorldDao worldDao;
    public WorldRepository(Context context) {
        WorldDatabase worldDatabase=WorldDatabase.getInstance(context.getApplicationContext());
        worldDao=worldDatabase.worldDao();
        allWorlds=worldDao.getAllWorlds();
    }


    public void insertWorlds(World... worlds){
        new InsertAsyncTask(worldDao).execute(worlds);
    }

    public void updateWorld(World world){
        new UpdateAsyncTask(worldDao).execute(world);

    }

    public void  deleteWorld(World world){
        new DeleteAsyncTask(worldDao).execute(world);
    }
    public void deleteAllWorld(){
        new DeleteAllAsyncTask(worldDao).execute();

    }

    public LiveData<List<World>> getAllWorlds(){
        return allWorlds;
    }

    public Integer  getMaxId() throws ExecutionException, InterruptedException {
        return   new GetMaxId(worldDao).execute().get();
    }




    static  class InsertAsyncTask extends AsyncTask<World,Void,Void> {
        private  WorldDao worldDao;

        public InsertAsyncTask(WorldDao worldDao) {
            this.worldDao= worldDao;
        }

        @Override
        protected Void doInBackground(World... worlds) {
            worldDao.insertWorld(worlds);
            return  null;
        }
    }
    static  class UpdateAsyncTask extends AsyncTask<World,Void,Void> {
        private  WorldDao worldDao;

        public UpdateAsyncTask(WorldDao worldDao) {
            this.worldDao= worldDao;
        }

        @Override
        protected Void doInBackground(World... worlds) {
            worldDao.updateWorld(worlds);
            return  null;
        }
    }
    static  class DeleteAsyncTask extends AsyncTask<World,Void,Void> {
        private  WorldDao worldDao;

        public DeleteAsyncTask(WorldDao worldDao) {
            this.worldDao= worldDao;
        }

        @Override
        protected Void doInBackground(World... worlds) {
            worldDao.deleteWorld(worlds);
            return  null;
        }
    }
    static  class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void> {
        private  WorldDao worldDao;

        public DeleteAllAsyncTask(WorldDao worldDao) {
            this.worldDao= worldDao;
        }

        @Override
        protected Void doInBackground(Void... Void) {
            worldDao.deleteAll();
            return  null;
        }
    }
    static  class GetMaxId extends AsyncTask<Void,Void,Integer> {
        private  WorldDao worldDao;

        public GetMaxId(WorldDao worldDao) {
            this.worldDao= worldDao;
        }

        @Override
        protected Integer doInBackground(Void... Void) {
            return   worldDao.getMaxId();
        }
    }

}
