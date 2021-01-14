package com.americanairlines.homeworkw3d2homeworktracker.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.americanairlines.homeworkw3d2homeworktracker.R;
import com.americanairlines.homeworkw3d2homeworktracker.model.data.Homework;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder> {

    private List<Homework> homeworkList;
    private Context context;
    private List<String> selectedList;
    private Button btn;

    private Selected selected;

    public interface Selected{
        void getSelected(List<Homework> homeworkList, List<String> selectList);
    }

    public HomeworkAdapter(Context context, List<Homework> homeworkList, Selected selected) {
        this.context = context;
        this.homeworkList = homeworkList;
        this.selected = selected;
        this.btn = btn;

        selectedList = new ArrayList<>();
    }

    public void updateHomeworkList(List<Homework> homeworkList){
        this.homeworkList = homeworkList;
        notifyDataSetChanged();
    }

    public void updateCompletedHomework(List<Homework> homeworkList){
        this.homeworkList = homeworkList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeworkViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homework, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        holder.tvName.setText(homeworkList.get(position).getHomeworkTitle());
        holder.tvSubject.setText(homeworkList.get(position).getSubject());
        holder.tvWeek.setText(String.valueOf(homeworkList.get(position).getWeek()));
        holder.tvDay.setText(String.valueOf(homeworkList.get(position).getDay()));

        Log.d("TAG_Y", "onBindViewHolder: isCompleted -> " + homeworkList.get(position).isCompleted());
        if(homeworkList.get(position).isSelected()){
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.light_green));
        } else {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        holder.cardView.setOnClickListener(v-> {
            if(homeworkList.get(position).isSelected()){
                holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.white));
                homeworkList.get(position).setSelected(false);
            } else {
                holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.light_green));
                homeworkList.get(position).setSelected(true);
            }
            selected.getSelected(homeworkList, selectedList);
        });

    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }

    class HomeworkViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_homework_name)
        TextView tvName;

        @BindView(R.id.tv_subject)
        TextView tvSubject;

        @BindView(R.id.tv_week)
        TextView tvWeek;

        @BindView(R.id.tv_day)
        TextView tvDay;

        @BindView(R.id.item_card_view)
        ConstraintLayout cardView;

        public HomeworkViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
