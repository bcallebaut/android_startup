package be.belgiplast.dashboard;

import android.app.Application;

import be.belgiplast.library.tasks.TaskList;

public class DashboardApplication extends Application {
    private TaskList coach = new TaskList();

    public TaskList getCoach() {
        return coach;
    }
}
