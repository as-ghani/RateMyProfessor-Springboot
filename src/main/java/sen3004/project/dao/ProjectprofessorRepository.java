package sen3004.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sen3004.project.model.Professor;


public interface ProjectprofessorRepository extends JpaRepository<Professor,Long> {
    
    public List<Professor> findByFirstName(String firstName);

}
