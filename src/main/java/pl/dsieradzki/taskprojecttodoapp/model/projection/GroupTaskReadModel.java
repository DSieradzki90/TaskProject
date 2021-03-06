package pl.dsieradzki.taskprojecttodoapp.model.projection;

import pl.dsieradzki.taskprojecttodoapp.model.Task;

public class GroupTaskReadModel {

    private boolean done;
    private String description;

    public GroupTaskReadModel(Task task) {
        description=task.getDescription();
        done= task.isDone();
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
