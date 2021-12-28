package pl.dsieradzki.taskprojecttodoapp.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dsieradzki.taskprojecttodoapp.model.Task;
import pl.dsieradzki.taskprojecttodoapp.model.TaskRepository;

@Repository
public interface SqlTaskRepository extends TaskRepository, JpaRepository<Task,Integer> {

    @Override
    @Query(nativeQuery = true,value = "SELECT count(*) > 0 from tasks where id=:id")
    boolean existsById(@Param("id")Integer id);

    @Override
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);
}
