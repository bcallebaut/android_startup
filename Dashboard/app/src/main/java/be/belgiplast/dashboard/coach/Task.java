package be.belgiplast.dashboard.coach;

import java.util.Date;

public interface Task {
    String getName();
    String getDescription();
    Date getStartDate();
    Date getFinishDate();
    TaskStates getState();
    void promote();
    boolean canPromote();
}
