package sen3004.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import sen3004.project.model.Professor;
import sen3004.project.service.ProjectService;
import sen3004.project.validator.RateprofessorValidator;

@Controller
public class ProjectController {


    @Autowired
    RateprofessorValidator rpv;
    
    @Autowired
    ProjectService service;
    
    @GetMapping("/ratemyprofessor/new")
    public ModelAndView newRating(){
        ModelAndView mv = new ModelAndView("new-rating");
        mv.addObject("professor",new Professor());

        return mv;
    }

    @GetMapping("/professor/{id}")
    public ModelAndView viewProfessor(@PathVariable long id ){
        ModelAndView mv = new ModelAndView("view-professor");
        Professor professor=service.findProfessorById(id);
        mv.addObject("professor", professor);
        return mv;
    }

    @GetMapping("/list-professor")
    public ModelAndView list(){
        ModelAndView mv=new ModelAndView("professor-list");
        mv.addObject("professor", service.ProfessorRatingList());
        return mv;
    }

    @GetMapping("/ratemyprofessor/{name}")
    public ModelAndView getRating(@PathVariable String name){
        ModelAndView mv=new ModelAndView("get-rating");
        mv.addObject("professor",service.findProfessorByName(name));

        return mv;
    }

    @GetMapping("/professor/delete/{id}")
    public ModelAndView deleteProfessor(@PathVariable long id){
        ModelAndView mv=new ModelAndView("professor-list");
        service.deleteProfessor(id);
        mv.addObject("professor",service.ProfessorRatingList());
        return mv;
    }

    @PostMapping("/ratemyprofessor/add")
    public ModelAndView addRating(@Valid @ModelAttribute Professor professor, BindingResult result){

        ModelAndView mv =new ModelAndView();
        mv.addObject("professor", professor);
        rpv.validate(professor, result);
        if(result.hasErrors()){
           mv.setViewName("new-rating");
        }
        else{
            mv.setViewName("professor-List");
            service.saveProfessorRating(professor);
            mv.addObject("professor", service.ProfessorRatingList());
        }
        return mv;
    }

    

}
