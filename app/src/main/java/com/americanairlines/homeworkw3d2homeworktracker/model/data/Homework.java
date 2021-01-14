package com.americanairlines.homeworkw3d2homeworktracker.model.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "homework_table")
public class Homework {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "homework_id")
    private int homeworkId;

    @ColumnInfo(name = "homework_title")
    private String homeworkTitle;

    @ColumnInfo(name = "homework_subject")
    private String subject;

    @ColumnInfo(name = "homework_week")
    private int week;

    @ColumnInfo(name = "homework_day")
    private int day;

    @ColumnInfo(name = "homework_complete")
    private boolean completed;

    private boolean isSelected;


    public Homework(int homeworkId, String homeworkTitle, String subject, int week, int day, boolean completed) {
        this.homeworkId = homeworkId;
        this.homeworkTitle = homeworkTitle;
        this.week = week;
        this.day = day;
        this.subject = subject;
        completed = false;
        isSelected = false;
    }

    @Ignore
    public Homework(String homeworkTitle,  String subject, int week, int day) {
        this.homeworkTitle = homeworkTitle;
        this.week = week;
        this.day = day;
        this.subject = subject;
        completed = false;
        isSelected = false;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public int getWeek() {
        return week;
    }

    public int getDay() {
        return day;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
