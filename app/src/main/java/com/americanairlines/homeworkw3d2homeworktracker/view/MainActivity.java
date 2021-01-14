package com.americanairlines.homeworkw3d2homeworktracker.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.americanairlines.homeworkw3d2homeworktracker.R;
import com.americanairlines.homeworkw3d2homeworktracker.model.data.Homework;
import com.americanairlines.homeworkw3d2homeworktracker.presenter.HomeworkPresenter;
import com.americanairlines.homeworkw3d2homeworktracker.presenter.HomeworkPresenterContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.americanairlines.homeworkw3d2homeworktracker.presenter.HomeworkPresenterContract.*;
import static com.americanairlines.homeworkw3d2homeworktracker.view.AddHomeworkFragment.*;
import static com.americanairlines.homeworkw3d2homeworktracker.view.ListFragment.*;

public class MainActivity extends AppCompatActivity implements HomeworkView, AddHomeworkInterface, UpdateList {

    private HomeworkPresenter presenter;
    private ListFragment listFragment;
    private AddHomeworkFragment addHomeworkFragment = new AddHomeworkFragment();

    @BindView(R.id.card_view)
    CardView cardView;

    @BindView(R.id.fab_add_new)
    FloatingActionButton fabAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.frag_list);

        presenter = new HomeworkPresenter(this);
        presenter.getAllHomework();
    }

    @OnClick(R.id.fab_add_new)
    public void openAddNew(){
        if (cardView.getVisibility() == View.GONE){
            cardView.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_add_frame, addHomeworkFragment)
                    .addToBackStack(addHomeworkFragment.getTag())
                    .commit();
            fabAddNew.setVisibility(View.GONE);

        } else {
            cardView.setVisibility(View.GONE);
            getSupportFragmentManager().popBackStack();
        }

    }

    public void setFabVisibilityGone(){
        fabAddNew.setVisibility(View.GONE);
    }
    public void setFabVisibilityVisible(){
        fabAddNew.setVisibility(View.VISIBLE);
    }

    public UpdateList setUpdateList(){
        return this;
    }

    @Override
    public void displayHomework(List<Homework> homeworkList) {
        runOnUiThread(()->{
            listFragment.updateHomework(sortHomeworkByWeek(homeworkList));
        });

        Log.d("TAG_X", "displayHomework: " + homeworkList.size());
    }

    private List<Homework> sortHomeworkByWeek(List<Homework> homeworkList) {

        List<Homework> sortedList = new ArrayList<>();


        for(int weeks = 1; weeks <=  4; weeks++) {
            for(int days = 1; days <= 5; days++) {
                for(int i = 0; i < homeworkList.size(); i++) {
                    if(homeworkList.get(i).getWeek() == weeks && homeworkList.get(i).getDay() == days){
                        sortedList.add(homeworkList.get(i));
                    }
                }
            }
        }


        return sortedList;
    }

    @Override
    public void displaySuccess(String message) {

    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void addHomework(Homework homework) {
        presenter.insertHomework(homework);
        fabAddNew.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.GONE);
        getSupportFragmentManager().popBackStack();
        hideKeyboard(this);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fabAddNew.getVisibility() == View.GONE){
            fabAddNew.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateComplete(List<Homework> homeworkList) {
        Log.d("TAG_X", "updateComplete: " + homeworkList.size());
        for(Homework homework: homeworkList){
            Log.d("TAG_X", "updateComplete: " + homework.isCompleted());

            runOnUiThread(()->{
                presenter.updateHomework(homework);
            });

        }
            //presenter.getAllHomework();

    }
}