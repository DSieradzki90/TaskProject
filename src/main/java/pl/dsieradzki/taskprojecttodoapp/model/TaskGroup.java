package pl.dsieradzki.taskprojecttodoapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "task_groups")
public class TaskGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Task's groups description can't be blank")
    private String description;
    private boolean done;
    @Embedded
    private Audit audit = new Audit();

    public TaskGroup() {
    }


    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
