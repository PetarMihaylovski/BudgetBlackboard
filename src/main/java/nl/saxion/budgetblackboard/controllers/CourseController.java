package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
	private final DataProvider data = DataProvider.getInstance();

	@GetMapping(path = "")
	public String getCourses(Model model) {
		model.addAttribute("courses", this.data.getCourses());
		return "indexCourse";
	}

	@GetMapping(path = "/add")
	public String add() {
		return "addCourse";
	}

	@PostMapping(path = "/add")
	public String addCourse(Course course, Model model){
		this.data.addCourse(course);
		model.addAttribute("courses", this.data.getCourses());
		return "indexCourse";
	}

	@GetMapping(path = "/edit/{id}")
	public String edit(@PathVariable("id") int ID, Model model) {
		Course course = this.data.findCourseByID(ID);
		model.addAttribute("course", course);
		return "editCourse";
	}

	@PostMapping(path = "/edit/{id}")
	public String editCourse(Course course, @PathVariable int id, Model model) {
		this.data.updateCourse(course, id);
		model.addAttribute("courses", this.data.getCourses());
		return "indexCourse";
	}

	@GetMapping(path = "/view/{ID}")
	public String view(@PathVariable int ID, Model model){
		Course course = this.data.findCourseByID(ID);
		model.addAttribute("course", course);
		return "";
	}
}