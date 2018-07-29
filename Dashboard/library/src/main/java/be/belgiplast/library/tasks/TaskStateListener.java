package be.belgiplast.library.tasks;

public interface TaskStateListener {
    void taskStateChanged(TaskStates oldState,TaskStates newState);
}
