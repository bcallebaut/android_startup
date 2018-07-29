package be.belgiplast.library.tasks;

public final class TaskStateEvent {
    private final TaskStates oldState;
    private final TaskStates newState;

    public TaskStateEvent(TaskStates oldState, TaskStates newState) {
        this.oldState = oldState;
        this.newState = newState;
    }

    public final TaskStates getNewState() {
        return newState;
    }

    public final TaskStates getOldState() {

        return oldState;
    }
}
