package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/courses")
public class CourseController {
	private DataProvider data = DataProvider.getInstance();
	private int lastEditedCourse;

	@GetMapping(path = "")
	public String getCourses(Model model, HttpSession session, HttpServletResponse response,
							 @CookieValue(value ="lastEditedCourse" , defaultValue = "")String courseID) {
		if (session.getAttribute("email") != null) {
			if (lastEditedCourse > -1){
				courseID = lastEditedCourse + "";
				try{
					Course lastEditedCourse = this.data.findCourseByID(Integer.parseInt(courseID));
					Cookie cookie = new Cookie("lastEditedCourse", lastEditedCourse.getID()+"");
					response.addCookie(cookie);
					model.addAttribute("lastEditedCourse", lastEditedCourse.getName());
				}
				catch (NullPointerException npe){
					model.addAttribute("deleted", null);
				}
			}
			else {
				model.addAttribute("noEditedCourse", null);
			}
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
			lastEditedCourse = course.getID();
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