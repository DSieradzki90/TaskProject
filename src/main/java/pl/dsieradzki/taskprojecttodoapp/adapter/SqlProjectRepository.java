package pl.dsieradzki.taskprojecttodoapp.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dsieradzki.taskprojecttodoapp.model.Project;
import pl.dsieradzki.taskprojecttodoapp.model.ProjectRepository;

import java.util.List;

@Repository
public interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project,Integer> {

    @Override
    @Query("from Project p join fetch p.steps")
    List<Project> findAll();

}

