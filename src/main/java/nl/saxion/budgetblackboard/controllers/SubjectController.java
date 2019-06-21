package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.Course;
import nl.saxion.budgetblackboard.models.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/courses/{courseID}/subjects")
public class SubjectController {
	private DataProvider data = DataProvider.getInstance();

	@GetMapping(path = "")
	public String getSubjects(Model model, @PathVariable int courseID, HttpSession session) {
		if (session.getAttribute("email") != null) {

			Course currentCourse = this.data.findCourseByID(courseID);
			model.addAttribute("course", currentCourse);
			model.addAttribute("subjects", currentCourse.getSubjects());
			return "subject/indexSubject";
		}
		return "redirect:/login";
	}

	@GetMapping(path = "/add")
	public String add(@PathVariable int courseID, Model model, HttpSession session) {
		if (session.getAttribute("email") != null) {
			Course currentCourse = this.data.findCourseByID(courseID);
			model.addAttribute("course", currentCourse);
			return "subject/addSubject";
		}
		return "redirect:/login";
	}


	@PostMapping(path = "/add")
	public String addSubject(Subject subject, Model model, @PathVariable int courseID) {
		Course currentCourse = this.data.findCourseByID(courseID);
		currentCourse.addSubject(subject);
		model.addAttribute("subjects", currentCourse.getSubjects());
		return "redirect:/courses/" + courseID + "/subjects";
	}

	@GetMapping(path = "/edit/{subjectID}")
	public String edit(@PathVariable int subjectID, Model model, @PathVariable int courseID, HttpSession session) {
		if (session.getAttribute("email") != null) {
			Course currentCourse = this.data.findCourseByID(courseID);
			Subject subject = this.data.findSubjectByID(courseID, subjectID);
			model.addAttribute("subject", subject);
			model.addAttribute("course", currentCourse);
			return "subject/editSubject";
		}
		return "redirect:/login";
	}

	@PostMapping(path = "/edit/{subjectID}")
	public String editSubject(Subject editedSubject, @PathVariable int subjectID, Model model, @PathVariable int courseID) {
		Subject uneditedSubject = this.data.findSubjectByID(courseID, subjectID);
		Course currentCourse = this.data.findCourseByID(courseID);
		currentCourse.setSubjects(this.data.updateSubject(currentCourse, uneditedSubject, editedSubject));
		model.addAttribute("subjects", currentCourse.getSubjects());
		return "redirect:/courses/" + courseID + "/subjects";
	}

	@GetMapping(path = "/delete/{subjectID}")
	public String deleteSubject(@PathVariable int subjectID, Model model, @PathVariable int courseID, HttpSession session) {
		if (session.getAttribute("email") != null) {
			Course currentCourse = this.data.findCourseByID(courseID);
			currentCourse.setSubjects(this.data.deleteSubjectInCourse(courseID, subjectID));
			model.addAttribute("subjects", currentCourse.getSubjects());
			return "redirect:/courses/" + courseID + "/subjects";
		}
		return "redirect:/login";
	}
}