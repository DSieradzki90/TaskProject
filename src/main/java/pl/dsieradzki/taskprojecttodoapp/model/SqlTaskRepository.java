package pl.dsieradzki.taskprojecttodoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlTaskRepository extends TaskRepository, JpaRepository<Task,Integer> {

    @Override
    @Query(nativeQuery = true,value = "SELECT count(*) > 0 from tasks where id=:id")
    boolean existsById(@Param("id")Integer id);
}
