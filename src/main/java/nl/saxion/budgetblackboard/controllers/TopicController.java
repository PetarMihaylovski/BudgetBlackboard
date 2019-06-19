package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.Subject;
import nl.saxion.budgetblackboard.models.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/courses/{courseID}/subjects/{subjectID}")
public class TopicController {
	private DataProvider data = DataProvider.getInstance();

	@GetMapping(path = "")
	public String getTopics(@PathVariable int courseID, @PathVariable int subjectID,  Model model){
		Subject currentSubject = this.data.getSubjectByID(courseID ,subjectID);
		model.addAttribute("topics", currentSubject.getTopics());
		model.addAttribute("course", this.data.findCourseByID(courseID));
		model.addAttribute("subject", currentSubject);
		return "topic/indexTopic";
	}

	@GetMapping(path = "/add")
	public String add(@PathVariable int courseID, @PathVariable int subjectID, Model model){
		model.addAttribute("course", this.data.findCourseByID(courseID));
		model.addAttribute("subject", this.data.getSubjectByID(courseID, subjectID));
		return "topic/addTopic";
	}

	@PostMapping(path = "/add")
	public String addTopic(@PathVariable int courseID, @PathVariable int subjectID, Model model, Topic topic){
		Subject currentSubject = this.data.getSubjectByID(courseID, subjectID);
		currentSubject.addTopic(topic);
		model.addAttribute("topics", currentSubject.getTopics());
		return "redirect:/courses/"  + courseID + "/subjects/" + subjectID;
	}
}
