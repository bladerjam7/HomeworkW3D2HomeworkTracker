package com.americanairlines.homeworkw3d2homeworktracker.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.americanairlines.homeworkw3d2homeworktracker.R;
import com.americanairlines.homeworkw3d2homeworktracker.model.data.Homework;
import com.americanairlines.homeworkw3d2homeworktracker.view.adapter.HomeworkAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements HomeworkAdapter.Selected {

    @BindView(R.id.rv_homework_list)
    public RecyclerView rvHomeworkList;

    @BindView(R.id.btn_complete)
    public Button btnComplete;

    private MainActivity mainActivity;
    private HomeworkAdapter adapter;

    private UpdateList updateList;

    public interface UpdateList{
        void updateComplete(List<Homework> homeworkList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        Activity activity = getActivity();
        if(activity instanceof MainActivity){
            mainActivity = (MainActivity) activity;
        }

        updateList = mainActivity.setUpdateList();

        Log.d("TAG_X", "ListFragment check " );
        adapter = new HomeworkAdapter(getContext(), new ArrayList<>(), this);
        rvHomeworkList.setAdapter(adapter);

    }

    public void updateHomework(List<Homework> homeworkList){
        adapter.updateHomeworkList(homeworkList);
    }

    @Override
    public void getSelected(List<Homework> homeworkList, List<String> selectedPositions) {
        if(selectedPositions.size() > 0){
            Log.d("TAG_X", "getSelected: SelectPosition size -> " + selectedPositions.size());
            btnComplete.setVisibility(View.VISIBLE);
            mainActivity.setFabVisibilityGone();
        } else {
            btnComplete.setVisibility(View.GONE);
            mainActivity.setFabVisibilityVisible();
        }

        btnComplete.setOnClickListener(v -> {
            // Update complete on list
            for (int i = 0; i < selectedPositions.size(); i++){
                homeworkList.get(Integer.parseInt(selectedPositions.get(i))).setCompleted(true);
            }
            //adapter.updateCompletedHomework(homeworkList);
            updateList.updateComplete(homeworkList);
            btnComplete.setVisibility(View.GONE);
            mainActivity.setFabVisibilityVisible();
        });
    }
}