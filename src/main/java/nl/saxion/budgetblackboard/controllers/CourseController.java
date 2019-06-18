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
		System.out.println("im here from the delete method " +  this.data.getCourses().size());
		return "course/indexCourse";
	}

	@GetMapping(path = "/add")
	public String add() {
		return "course/addCourse";
	}

	@PostMapping(path = "/add")
	public String addCourse(Course course, Model model) {
		this.data.addCourse(course);
		model.addAttribute("courses", this.data.getCourses());
		return "redirect:/courses";
	}

	@GetMapping(path = "/edit/{id}")
	public String edit(@PathVariable("id") int ID, Model model) {
		Course course = this.data.findCourseByID(ID);
		model.addAttribute("course", course);
		return "course/editCourse";
	}

	@PostMapping(path = "/edit/{id}")
	public String editCourse(Course course, @PathVariable int id, Model model) {
		this.data.updateCourse(course, id);
		model.addAttribute("courses", this.data.getCourses());
		return "redirect:/courses";
	}

	@GetMapping(path = "/view")
	public String viewSubject(@PathVariable int ID) {
		Course course = this.data.findCourseByID(ID);
		return "redirect:/courses/subjects/" + course.getID();
	}

	@GetMapping(path = "/delete/{ID}")
	public String deleteCourse(@PathVariable int ID) {
		this.data.deleteCourse(ID);
		return "redirect:/courses";
	}
}