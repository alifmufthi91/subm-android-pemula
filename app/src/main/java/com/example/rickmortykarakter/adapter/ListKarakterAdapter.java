package com.example.rickmortykarakter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rickmortykarakter.pages.DetailActivity;
import com.example.rickmortykarakter.R;
import com.example.rickmortykarakter.model.Karakter;

import java.util.ArrayList;

public class ListKarakterAdapter extends RecyclerView.Adapter<ListKarakterAdapter.ListViewHolder> {

    private ArrayList<Karakter> listKarakter;
    private Context mContext;
    public ListKarakterAdapter(ArrayList<Karakter> list){
        this.listKarakter = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Karakter karakter = listKarakter.get(position);

        Glide.with(holder.itemView.getContext())
                .load(karakter.getGambar())
                .apply(new RequestOptions().override(300, 300))
                .into(holder.imgPhoto);
        holder.karNama.setText(karakter.getNama());
        holder.karStatus.setText(String.format("Status : %s", karakter.getStatus()));
        mContext = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("karakter",karakter);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKarakter.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView karNama,karStatus;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.list_item_photo);
            karNama = itemView.findViewById(R.id.list_item_name);
            karStatus = itemView.findViewById(R.id.list_item_status);

        }
    }
}
