package com.example.alwaqr;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class clothesAdapter extends RecyclerView.Adapter<clothesAdapter.ClothingViewHOlder>{
    private List<item> clothingList;

    public clothesAdapter(List<item> clothingList) {
        this.clothingList = clothingList;
    }

    @NonNull
    @Override
    public ClothingViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clothes_item , parent , false);
        return new ClothingViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothingViewHOlder holder, int position) {
item i =clothingList.get(position);
        Uri uri=Uri.EMPTY.parse(i.getImage()+"");
        holder.store.setText(clothingList.get(position).getCode());
        holder.mImageView.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return clothingList.size();
    }

    public class ClothingViewHOlder extends RecyclerView.ViewHolder {
        private TextView store;
        private ImageView mImageView;
        public ClothingViewHOlder(@NonNull View itemView) {
            super(itemView);

            store = itemView.findViewById(R.id.clothing_store_tv);
            mImageView = itemView.findViewById(R.id.clothing_image);
        }
    }
}
