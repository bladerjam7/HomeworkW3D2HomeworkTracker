package com.americanairlines.homeworkw3d2homeworktracker.presenter;

import androidx.room.Room;

import com.americanairlines.homeworkw3d2homeworktracker.model.data.Homework;
import com.americanairlines.homeworkw3d2homeworktracker.model.db.HomeworkDatabase;

import static com.americanairlines.homeworkw3d2homeworktracker.presenter.HomeworkPresenterContract.*;

public class HomeworkPresenter implements HomeworkPresenterContract.HomeworkPresenter {

    private HomeworkView homeworkView;
    private HomeworkDatabase homeworkDatabase;

    public HomeworkPresenter(HomeworkView view) {
        this.homeworkView = view;
        homeworkDatabase = Room.databaseBuilder(
                view.getContext(),
                HomeworkDatabase.class,
                HomeworkDatabase.DATABASE_NAME
        ).build();
    }

    @Override
    public void insertHomework(Homework homework) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                homeworkDatabase.homeworkDao().insertHomework(homework);
                /*homeworkView.displaySuccess("Homework Inserted");*/
                homeworkView.displayHomework(homeworkDatabase.homeworkDao().getAllHomework());
            }
        }.start();
    }

    @Override
    public void deleteHomework(Homework homework) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                homeworkDatabase.homeworkDao().deleteHomework(homework);
                /*homeworkView.displaySuccess("Homework Deleted");*/
                homeworkView.displayHomework(homeworkDatabase.homeworkDao().getAllHomework());
            }
        }.start();

    }

    @Override
    public void updateHomework(Homework homework) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                homeworkDatabase.homeworkDao().updateHomework(homework);
                /*homeworkView.displaySuccess("Homework Updated");*/
                //homeworkView.displayHomework(homeworkDatabase.homeworkDao().getAllHomework());
            }
        }.start();

    }

    @Override
    public void getWeek(int week) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                homeworkView.displayHomework(homeworkDatabase.homeworkDao().getFromWeek(week));
            }
        }.start();
    }

    @Override
    public void getDay(int day) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                homeworkView.displayHomework(homeworkDatabase.homeworkDao().getFromDay(day));
            }
        }.start();
    }

    @Override
    public void getAllHomework() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                homeworkView.displayHomework(homeworkDatabase.homeworkDao().getAllHomework());
            }
        }.start();
    }
}
