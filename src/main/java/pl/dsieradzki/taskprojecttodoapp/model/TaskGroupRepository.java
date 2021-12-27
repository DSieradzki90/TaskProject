package pl.dsieradzki.taskprojecttodoapp.model;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskGroupRepository {

    List<TaskGroup> findAll();
    Optional<TaskGroup> findById(Integer id);
    TaskGroup save (TaskGroup taskGroup);
    boolean existsByDoneIsFalseAndProjectId(Integer projectId);
}
