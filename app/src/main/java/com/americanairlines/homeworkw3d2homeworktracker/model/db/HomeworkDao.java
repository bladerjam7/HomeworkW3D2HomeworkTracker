package com.americanairlines.homeworkw3d2homeworktracker.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.americanairlines.homeworkw3d2homeworktracker.model.data.Homework;

import java.util.List;

import static com.americanairlines.homeworkw3d2homeworktracker.model.db.HomeworkDatabase.*;

@Dao
public interface HomeworkDao {

    @Insert
    void insertHomework(Homework homework);

    @Delete
    void deleteHomework(Homework homework);

    @Update
    void updateHomework(Homework homework);

    @Query("SELECT * FROM homework_table")
    List<Homework> getAllHomework();

    @Query("SELECT * FROM homework_table WHERE homework_week = :week")
    List<Homework> getFromWeek(int week);

    @Query("SELECT * FROM homework_table WHERE homework_day = :day")
    List<Homework> getFromDay(int day);
}
