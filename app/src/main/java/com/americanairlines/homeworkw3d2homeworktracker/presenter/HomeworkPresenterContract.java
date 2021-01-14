package com.americanairlines.homeworkw3d2homeworktracker.presenter;

import android.content.Context;

import com.americanairlines.homeworkw3d2homeworktracker.model.data.Homework;

import java.util.List;

public interface HomeworkPresenterContract {

    interface HomeworkPresenter{
        void insertHomework(Homework homework);
        void deleteHomework(Homework homework);
        void updateHomework(Homework homework);
        void getWeek(int week);
        void getDay(int day);
        void getAllHomework();
    }

    interface HomeworkView{
        void displayHomework(List<Homework> homeworkList);
        void displaySuccess(String message);
        void displayError(String message);
        Context getContext();
    }
}
