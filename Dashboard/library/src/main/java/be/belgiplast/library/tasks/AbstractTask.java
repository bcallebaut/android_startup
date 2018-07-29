package be.belgiplast.library.tasks;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AbstractTask implements Task {
    private TaskStates state;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int size;
    private final Map<String,Object> properties = new HashMap<>();

    public AbstractTask() {
    }

    public AbstractTask(TaskStates state, String name, String description, Date startDate, Date endDate) {

        this.state = state;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public TaskStates getState() {
        return state;
    }

    @Override
    public void promote() {
        if (state == TaskStates.ONGOING) {
            state = TaskStates.FINISHED;
            endDate = new Date();
        }
        if (state == TaskStates.BACKLOG)
            state = TaskStates.ONGOING;
    }

    @Override
    public boolean canPromote() {
        return state == TaskStates.BACKLOG || state == TaskStates.ONGOING;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getFinishDate() {
        return endDate;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        if (state == TaskStates.BACKLOG)
            this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (state == TaskStates.BACKLOG)
            this.size = size;
    }
}
