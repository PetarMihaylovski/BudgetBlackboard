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
		Subject currentSubject = this.data.findSubjectByID(courseID ,subjectID);
		model.addAttribute("topics", currentSubject.getTopics());
		model.addAttribute("course", this.data.findCourseByID(courseID));
		model.addAttribute("subject", currentSubject);
		return "topic/indexTopic";
	}

	@GetMapping(path = "/add")
	public String add(@PathVariable int courseID, @PathVariable int subjectID, Model model){
		model.addAttribute("course", this.data.findCourseByID(courseID));
		model.addAttribute("subject", this.data.findSubjectByID(courseID, subjectID));
		return "topic/addTopic";
	}

	@PostMapping(path = "/add")
	public String addTopic(@PathVariable int courseID, @PathVariable int subjectID, Model model, Topic topic){
		Subject currentSubject = this.data.findSubjectByID(courseID, subjectID);
		currentSubject.addTopic(topic);
		model.addAttribute("topics", currentSubject.getTopics());
		return "redirect:/courses/"  + courseID + "/subjects/" + subjectID;
	}

	@GetMapping(path = "/edit/{topicID}")
	public String edit(@PathVariable int courseID, @PathVariable int subjectID,
					   @PathVariable int topicID, Model model){
		model.addAttribute("course", this.data.findCourseByID(courseID));
		model.addAttribute("subject", this.data.findSubjectByID(courseID,subjectID));
		model.addAttribute("topic", this.data.findTopicByID(courseID, subjectID, topicID));
		return "topic/editTopic";
	}

	@PostMapping(path = "edit/{topicID}")
	public String editCourse(@PathVariable int courseID, @PathVariable int subjectID,
							 @PathVariable int topicID, Model model, Topic editedTopic){
		Topic uneditedTopic = this.data.findTopicByID(courseID, subjectID, topicID);
		Subject currentSubject = this.data.findSubjectByID(courseID, subjectID);
		currentSubject.setTopics(this.data.updateTopic(courseID, subjectID, uneditedTopic, editedTopic));
		model.addAttribute("topics", currentSubject.getTopics());
		return "redirect:/courses/" + courseID + "/subjects/" + subjectID;
	}

	@GetMapping(path = "delete/{topicID}")
	public String deleteTopic(@PathVariable int courseID, @PathVariable int subjectID,
							  @PathVariable int topicID, Model model){
		Subject currentSubject= this.data.findSubjectByID(courseID, subjectID);
		currentSubject.setTopics(this.data.deleteTopic(courseID, subjectID, topicID));
		model.addAttribute("topics", currentSubject.getTopics());
		return "redirect:/courses/" + courseID + "/subjects/" + subjectID;
	}
}
