package sen3004.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sen3004.project.dao.ProjectprofessorRepository;
import sen3004.project.model.Professor;


@Service
@Transactional
public class ProjectService {
    
      @Autowired
      private ProjectprofessorRepository projectprofessorRepository;

      public Professor findProfessorById(long id){
        return projectprofessorRepository.findById(id).get();
      }

      public List<Professor> findProfessorByName(String name){
        return projectprofessorRepository.findByFirstName(name);
      }

      public List<Professor> ProfessorRatingList(){
        return projectprofessorRepository.findAll();
      }

      public void saveProfessorRating(Professor professor){
        projectprofessorRepository.save(professor);
      }

      public void deleteProfessor(long id){
        projectprofessorRepository.deleteById(id);
      }

}
