package com.example.myapplication1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.data.HealthItemModel;


public class HealthListAdapter extends
        RecyclerView.Adapter<HealthListAdapter.HealthViewHolder> {

    private String[] listOfItems;
    private HealthItemModel[] healthItemLists;

    public HealthListAdapter(String[] listOfItems) {
        this.listOfItems = listOfItems;
    }
    public HealthListAdapter(HealthItemModel[] items) {
        this.healthItemLists = items;
    }

    @NonNull
    @Override
    public HealthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Define a layout file for individual list item
        View healthItemLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.health_list_item, parent, false);

        HealthViewHolder healthViewHolder = new HealthViewHolder(healthItemLayout);
        return healthViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HealthViewHolder holder, int position) {
        // Set data to the individual list item
        holder.textToShow.setText(healthItemLists[position].getDescription());
        holder.imageView.setImageResource(healthItemLists[position].getImgId());
    }

    @Override
    public int getItemCount() {
        return healthItemLists.length;
    }

    class HealthViewHolder extends RecyclerView.ViewHolder{
        // Define all of your views here
        TextView textToShow;
        ImageView imageView;
        RelativeLayout relativeLayout;

        public HealthViewHolder(View itemView){
            super(itemView);

            textToShow = (TextView) itemView.findViewById(R.id.tv_list_item_text);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
//            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rel)
        }
    }
}
