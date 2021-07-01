package com.wang.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private Button buttonAdd;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Button buttonDeleteAll;
    private TextView textView;
    private WorldViewModel worldViewModel;
    private RecyclerView recyclerView;
    private MyAdapter myAdapterNormal;
    private MyAdapter myAdapterCard;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //    worldViewModel = new ViewModelProvider(this).get(WorldViewModel.class);
        worldViewModel=new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(WorldViewModel.class);
        recyclerView = findViewById(R.id.recycleView);

        myAdapterNormal=new MyAdapter(false);
        myAdapterCard=new MyAdapter(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapterNormal);

        buttonAdd=findViewById(R.id.buttonadd);
        buttonDelete=findViewById(R.id.buttondelte);
        buttonUpdate=findViewById(R.id.buttonUpdate);
        buttonDeleteAll=findViewById(R.id.buttonDeleteAll);
        textView=findViewById(R.id.textViewName);
        aSwitch = findViewById(R.id.switchCard);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    recyclerView.setAdapter(myAdapterCard);
                }else{
                    recyclerView.setAdapter(myAdapterNormal);
                }
            }
        });


        worldViewModel.getAllWorlds().observe(this, new Observer<List<World>>() {
            @Override
            public void onChanged(List<World> worlds) {
//                StringBuilder text=new StringBuilder();
////                for (World world:worlds){
////                    text.append("id:"+world.getId()+"    name:"+world.getName()+"     age:"+world.getAge()+"\n");
////
////                }
////                textView.setText(text.toString());
                myAdapterCard.setWorlds(worlds);
                myAdapterCard.notifyDataSetChanged();
                myAdapterNormal.setWorlds(worlds);
                myAdapterNormal.notifyDataSetChanged();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                World world1=new World("测试1",20);
                World world2=new World("测试2",25);
                worldViewModel.insertWorlds(world1,world2);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id= null;
                try {
                    id = worldViewModel.getMaxId();
                    if (id !=null){
                        World world=new World(id);
                        worldViewModel.deleteWorld(world);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id= null;
                try {
                    id = worldViewModel.getMaxId();
                    if (id !=null){
                        World world=new World(id);
                        world.setAge(101);
                        world.setName("更新");
                        worldViewModel.updateWorld(world);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                worldViewModel.deleteAllWorld();
            }
        });

    }



}