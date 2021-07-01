package com.wang.roombasic;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by wang on 2021/3/25 0025.
 **/
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<World> worlds=new ArrayList<>();
    private  boolean switchCard;

    public void setWorlds(List<World> worlds) {
        this.worlds = worlds;
    }

    public MyAdapter(boolean switchCard) {
        this.switchCard = switchCard;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view;
        if (switchCard){
            view= inflater.inflate(R.layout.cell_card,parent,false);
        }else{
            view= inflater.inflate(R.layout.cell_normal,parent,false);
        }
        final MyViewHolder holder=new MyViewHolder(view);
      // holder.getAdapterPosition()
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = holder.textViewName.getText().toString();
                Uri uri=Uri.parse("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd="+name);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
       World world= worlds.get(position);
        holder.textViewNumber.setText(String.valueOf(world.getId()));
        holder.textViewName.setText(world.getName());
        holder.textViewAge.setText(String.valueOf(world.getAge()));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               String name = holder.textViewName.getText().toString();
//                Uri uri=Uri.parse("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd="+name);
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setData(uri);
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return worlds.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber,textViewName,textViewAge;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber=  itemView.findViewById(R.id.textViewNumber);
            textViewName=  itemView.findViewById(R.id.textViewName);
            textViewAge=  itemView.findViewById(R.id.textViewAge);
        }
    }
}
