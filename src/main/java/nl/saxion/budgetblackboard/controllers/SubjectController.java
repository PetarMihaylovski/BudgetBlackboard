package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.Course;
import nl.saxion.budgetblackboard.models.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/courses/{courseID}/subjects")
public class SubjectController {
	private DataProvider data = DataProvider.getInstance();

	@GetMapping(path = "")
	public String getSubjects(Model model, @PathVariable int courseID) {
		Course currentCourse = this.data.findCourseByID(courseID);
		model.addAttribute("course", currentCourse);
		model.addAttribute("subjects", currentCourse.getSubjects());
		return "indexSubject";
	}

	@GetMapping(path = "/add")
	public String add() {
		return "addSubject";
	}

	@PostMapping(path = "/add")
	public String addSubject(Subject subject, Model model, @PathVariable int courseID) {
		Course currentCourse = this.data.findCourseByID(courseID);
		currentCourse.addSubject(subject);
		model.addAttribute("subjects", currentCourse.getSubjects());
		return "redirect:/courses/subjects/" + currentCourse.getID();
	}

	@GetMapping(path = "/edit/{subjectID}")
	public String edit(@PathVariable int subjectID, Model model, @PathVariable int courseID) {
		Subject subject = this.data.getSubjectByID(courseID, subjectID);
		model.addAttribute("subject", subject);
		return "editSubject";
	}

	@PostMapping(path = "/edit/{subjectID}")
	public String editSubject(Subject editedSubject, @PathVariable int subjectID, Model model, @PathVariable int courseID) {
		Subject uneditedSubject = this.data.getSubjectByID(courseID, subjectID);
		Course currentCourse = this.data.findCourseByID(courseID);
		currentCourse.setSubjects(this.data.updateSubject(currentCourse, uneditedSubject, editedSubject));
		model.addAttribute("subjects", currentCourse.getSubjects());
		return "redirect:/courses/subjects/" + currentCourse.getID();
	}

	@GetMapping(path = "/delete/{subjectID}")
	public String deleteSubject(@PathVariable int subjectID, Model model, @PathVariable int courseID) {
		Course currentCourse = this.data.findCourseByID(courseID);
		currentCourse.setSubjects(this.data.deleteSubjectInCourse(courseID, subjectID));
		model.addAttribute("subjects", currentCourse.getSubjects());
		return "redirect:/courses/subjects/" + currentCourse.getID();
	}
}