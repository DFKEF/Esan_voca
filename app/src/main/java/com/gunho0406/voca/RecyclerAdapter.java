package com.gunho0406.voca;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> englist = new ArrayList<>();
    private ArrayList<String> korlist = new ArrayList<>();
    private Activity activity;

    public RecyclerAdapter(MainActivity activity, ArrayList<String> englist, ArrayList<String> korlist) {
        this.activity = activity;
        this.englist = englist;
        this.korlist = korlist;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView english;
        private TextView korean;

        public ViewHolder(View itemView) {
            super(itemView);
            english = (TextView) itemView.findViewById(R.id.eng);
            korean = (TextView) itemView.findViewById(R.id.kor);

        }

    }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder( ViewHolder viewHolder, final int pos) {
            viewHolder.english.setText(String.valueOf(englist.get(pos)));
            viewHolder.korean.setText((String.valueOf(korlist.get(pos))));
        }

        @Override
        public int getItemCount() {
            return englist.size();
        }



}
