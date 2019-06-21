package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/courses")
public class CourseController {
	private DataProvider data = DataProvider.getInstance();

	@GetMapping(path = "")
	public String getCourses(Model model, HttpSession session) {
		if (session.getAttribute("email") != null) {
			model.addAttribute("courses", this.data.getCourses());
			return "course/indexCourse";
		}
		return "redirect:/login";
	}

	@GetMapping(path = "/add")
	public String add(HttpSession session) {
		if (session.getAttribute("email") != null) {
			return "course/addCourse";
		}
		return "redirect:/login";
	}

	@PostMapping(path = "/add")
	public String addCourse(Course course, Model model) {
		this.data.addCourse(course);
		model.addAttribute("courses", this.data.getCourses());
		return "redirect:/courses";
	}

	@GetMapping(path = "/edit/{id}")
	public String edit(@PathVariable("id") int ID, Model model, HttpSession session) {
		if (session.getAttribute("email") != null) {
			Course course = this.data.findCourseByID(ID);
			model.addAttribute("course", course);
			return "course/editCourse";
		}
		return "redirect:/login";
	}

	@PostMapping(path = "/edit/{id}")
	public String editCourse(Course course, @PathVariable int id, Model model) {
		this.data.updateCourse(course, id);
		model.addAttribute("courses", this.data.getCourses());
		return "redirect:/courses";
	}

	@GetMapping(path = "/delete/{ID}")
	public String deleteCourse(@PathVariable int ID, HttpSession session) {
		if (session.getAttribute("email") != null) {
			this.data.deleteCourse(ID);
			return "redirect:/courses";
		}
		return "redirect:/login";
	}
}