package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.Course;
import nl.saxion.budgetblackboard.models.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/courses/subjects/")
public class SubjectController {
	private DataProvider data = DataProvider.getInstance();
	private Course course;

	@GetMapping(path = "/{ID}")
	public String getSubjects(@PathVariable int ID, Model model) {
		course = this.data.findCourseByID(ID);
		model.addAttribute("subjects", course.getSubjects());
		return "indexSubject";
	}

	@GetMapping(path = "/add")
	public String add() {
		return "addSubject";
	}

	@PostMapping(path = "/add")
	public String addSubject(Subject subject, Model model) {
		this.course.addSubject(subject);
		model.addAttribute("subjects", this.course.getSubjects());
		return "redirect:/courses/subjects/" + this.course.getID();
	}

	@GetMapping(path = "/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Subject subject = this.data.getSubjectByID(this.course, id);
		model.addAttribute("subject", subject);
		return "editSubject";
	}

	@PostMapping(path = "/edit/{id}")
	public String editSubject(Subject editedSubject, @PathVariable int id, Model model) {
		Subject uneditedSubject = this.data.getSubjectByID(this.course, id);
		ArrayList<Subject> updatedSubjects = this.data.updateSubject(this.course, uneditedSubject, editedSubject, id);
		model.addAttribute("subjects", updatedSubjects);
		return "redirect:/courses/subjects/" + this.course.getID();
	}

	@PostMapping(path = "/delete/{id}")
	public String deleteSubject(@PathVariable int id) {
		Subject subject = this.data.getSubjectByID(this.course, id);
		this.course.getSubjects().remove(subject);
		return "";
	}
}