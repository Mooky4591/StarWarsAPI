package com.example.starwarsapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<RecyclerItem> viewModelArrayList;
    private OnCharacterListener myoncharacterlistener;

    public MyAdapter(ArrayList<RecyclerItem> viewModelArrayList, OnCharacterListener onCharacterListener){
        this.viewModelArrayList = viewModelArrayList;
        this.myoncharacterlistener = onCharacterListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name, mass, height, birthyear;


        OnCharacterListener onCharacterListener;


        public ViewHolder(@NonNull View itemView, OnCharacterListener myoncharacterlistener) {
            super(itemView);
            name = itemView.findViewById(R.id.nametextview);
            mass = itemView.findViewById(R.id.masstextview);
            height = itemView.findViewById(R.id.heighttextview);
            birthyear = itemView.findViewById(R.id.birthyeartextview);
            this.onCharacterListener = myoncharacterlistener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCharacterListener.onCharacterClick(getAdapterPosition());

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, myoncharacterlistener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerItem currentitem = viewModelArrayList.get(position);
        holder.name.setText(currentitem.getName());
        holder.height.setText(currentitem.getHeight());
        holder.mass.setText(currentitem.getMass());
        holder.birthyear.setText(currentitem.getBirthYear());
    }

    @Override
    public int getItemCount() {
        return viewModelArrayList.size();
    }

    public interface OnCharacterListener{
        void onCharacterClick(int position);
    }

}
