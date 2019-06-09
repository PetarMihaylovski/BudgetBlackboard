package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.users.Person;
import nl.saxion.budgetblackboard.users.Student;
import nl.saxion.budgetblackboard.users.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class FormController {
    @Autowired
    DataProvider dataProvider;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayLogin(Model model){
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegister(Model model){
        return "register";
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String displayCourses(Model model){
        return "courses";
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public String displaySubjects(Model model){
        return "subjects";
    }

    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public String displayTopics(Model model){
        return "topics";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String processLogin(@RequestParam String email, @RequestParam String password){

        for (Person person : dataProvider.getUsers()){
            if (person.getEmail().equals(email) && !email.equals("")){
                if (person.getPassword().equals(password) && !password.equals("")){
                    return "redirect:/courses";
                }
            }
        }
        return "login";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegister(@RequestParam String account,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String password2){
        if (account.equals("student")){
            if (password.equals(password2)) {
                dataProvider.addUser(new Student(email, password));
                return "redirect:";
            }
        }
        else {
            if (password.equals(password2)) {
                dataProvider.addUser(new Teacher(email, password));
                return "redirect:";
            }
        }
        return "redirect:/register";
    }




}
