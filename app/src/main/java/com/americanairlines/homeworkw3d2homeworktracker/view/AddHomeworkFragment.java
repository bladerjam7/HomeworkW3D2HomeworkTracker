package com.americanairlines.homeworkw3d2homeworktracker.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.americanairlines.homeworkw3d2homeworktracker.R;
import com.americanairlines.homeworkw3d2homeworktracker.model.data.Homework;
import com.americanairlines.homeworkw3d2homeworktracker.view.adapter.HomeworkAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class AddHomeworkFragment extends Fragment {

    @BindView(R.id.et_home_name)
    EditText etName;

    @BindView(R.id.et_subject)
    EditText etSubject;

    @BindView(R.id.spin_week)
    public Spinner spinWeek;

    @BindView(R.id.spin_day)
    public Spinner spinDay;

    private AddHomeworkInterface addHomeworkInterface;

    String [] weeks = {"1","2","3","4"};
    String [] days = {"1","2","3","4","5"};


    public interface AddHomeworkInterface {
        void addHomework(Homework homework);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_homework, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Log.d("TAG_X", "onViewCreated: " + getResources().getIntArray(R.array.spin_day_array));

        ArrayAdapter spinWeekAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, weeks);
        spinWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter spinDayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, days);
        spinWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinWeek.setAdapter(spinWeekAdapter);
        spinDay.setAdapter(spinDayAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addHomeworkInterface = (AddHomeworkInterface) context;
    }

    @OnClick(R.id.btn_submit)
    public void insertHomework(){
        String title = etName.getText().toString().trim();
        String subject = etSubject.getText().toString().trim();
        int week = spinWeek.getSelectedItemPosition() + 1;
        int day = spinDay.getSelectedItemPosition() + 1;

        if (title.isEmpty()){
            Toast.makeText(getContext(), "Title missing!", Toast.LENGTH_SHORT).show();
        } else if (subject.isEmpty()){
            Toast.makeText(getContext(), "Subject missing!", Toast.LENGTH_SHORT).show();
        } else {
            addHomeworkInterface.addHomework(new Homework(title,subject, week, day));
            onDestroy();
        }


    }
}