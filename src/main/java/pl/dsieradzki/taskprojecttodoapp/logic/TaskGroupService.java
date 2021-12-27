package pl.dsieradzki.taskprojecttodoapp.logic;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pl.dsieradzki.taskprojecttodoapp.model.TaskGroup;
import pl.dsieradzki.taskprojecttodoapp.model.TaskGroupRepository;
import pl.dsieradzki.taskprojecttodoapp.model.TaskRepository;
import pl.dsieradzki.taskprojecttodoapp.model.projection.GroupReadModel;
import pl.dsieradzki.taskprojecttodoapp.model.projection.GroupWriteModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequestScope
public class TaskGroupService {

    private TaskGroupRepository taskGroupRepository;
    private TaskRepository taskRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository, TaskRepository taskRepository) {
        this.taskGroupRepository = taskGroupRepository;
        this.taskRepository = taskRepository;
    }

    public GroupReadModel createGroup(GroupWriteModel groupWriteModel){
        TaskGroup result = taskGroupRepository.save(groupWriteModel.toGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll(){
       return  taskGroupRepository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId){
      if(taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)){
          throw new IllegalStateException("Group has undone tasks. Done all all tasks first");
      }
        TaskGroup result = taskGroupRepository.findById(groupId)
              .orElseThrow(()-> new IllegalArgumentException("TaskGroup with given id not found"));
      result.setDone(!result.isDone());
    }
}

