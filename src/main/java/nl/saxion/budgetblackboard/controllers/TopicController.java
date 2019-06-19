package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/courses/{courseID}/subjects/{subjectID}")
public class TopicController {
	private DataProvider data = DataProvider.getInstance();

	@GetMapping(path = "")
	public String getTopics(@PathVariable int subjectID, @PathVariable int courseID, Model model){
		Subject currentSubject = this.data.getSubjectByID(courseID ,subjectID);
		model.addAttribute("topics", currentSubject.getTopics());
		model.addAttribute("course", this.data.findCourseByID(courseID));
		model.addAttribute("subject", currentSubject);
		return "topic/indexTopic";
	}
}
