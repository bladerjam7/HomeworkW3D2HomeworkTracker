package com.americanairlines.homeworkw3d2homeworktracker.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.americanairlines.homeworkw3d2homeworktracker.model.data.Homework;

@Database(version = 3, entities = {Homework.class})
public abstract class HomeworkDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "homework.db";
    public abstract HomeworkDao homeworkDao();

}
