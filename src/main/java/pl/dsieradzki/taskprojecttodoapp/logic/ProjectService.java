package pl.dsieradzki.taskprojecttodoapp.logic;

import org.springframework.stereotype.Service;
import pl.dsieradzki.taskprojecttodoapp.TaskConfigurationProperties;
import pl.dsieradzki.taskprojecttodoapp.model.*;
import pl.dsieradzki.taskprojecttodoapp.model.projection.GroupReadModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private TaskGroupRepository taskGroupRepository;
    private TaskConfigurationProperties taskConfigurationProperties;

    public ProjectService(ProjectRepository projectRepository, TaskGroupRepository taskGroupRepository, TaskConfigurationProperties taskConfigurationProperties) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskConfigurationProperties = taskConfigurationProperties;
    }

    public List<Project> readAll(){
        return projectRepository.findAll();
    }

    public Project save(Project toSave){
        return projectRepository.save(toSave);
    }

    public GroupReadModel createGroup(LocalDateTime deadline,int projectId){
        if(taskConfigurationProperties.isAllowMultipleTasksFromTemplate() && taskGroupRepository.existsByDoneIsFalseAndProjectId(projectId)){
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        TaskGroup result = projectRepository.findById(projectId)
                .map(project -> {
                    var targetGroup = new TaskGroup();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(project.getSteps().stream()
                            .map(step ->
                                new Task(step.getDescription(),
                                        deadline.plusDays(step.getDaysToDeadline())))
                                    .collect(Collectors.toSet())
                            );
                     return targetGroup;
                }).orElseThrow(()->new IllegalArgumentException ("Project with given id not found"));
return new GroupReadModel(result);
    }
}
