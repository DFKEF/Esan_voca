package com.gunho0406.voca;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> englist = new ArrayList<>();
    private ArrayList<String> korlist = new ArrayList<>();
    private Activity activity;
    private Context context;

    public RecyclerAdapter(MainActivity activity, ArrayList<String> englist, ArrayList<String> korlist) {
        this.activity = activity;
        this.englist = englist;
        this.korlist = korlist;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView english;
        private TextView korean;

        public ViewHolder(final View itemView) {
            super(itemView);
            english = (TextView) itemView.findViewById(R.id.eng);
            korean = (TextView) itemView.findViewById(R.id.kor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        CustomDialog customDialog = new CustomDialog(v.getContext());
                        String korean = korlist.get(pos);
                        String english = englist.get(pos);
                        customDialog.callFunction(english, korean);
                    }
                }
            });
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
