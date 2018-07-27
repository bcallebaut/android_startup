package be.belgiplast.dashboard.coach;

public enum TaskStates {
    BACKLOG,
    ONGOING,
    FINISHED,
    CANCELLED;

    public boolean canPromote(){
        return this.equals(BACKLOG) || this.equals(ONGOING);
    }

    public TaskStates promote(){
        switch(this){
            case BACKLOG:
                return ONGOING;
            case ONGOING:
                return FINISHED;
            default:
                return this;
        }
    }
}
