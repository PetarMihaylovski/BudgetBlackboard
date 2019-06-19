package nl.saxion.budgetblackboard.dataProvider;

import nl.saxion.budgetblackboard.models.Course;
import nl.saxion.budgetblackboard.models.Subject;
import nl.saxion.budgetblackboard.models.Topic;
import nl.saxion.budgetblackboard.users.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public class DataProvider {
	private static DataProvider dataProvider;
	private ArrayList<Person> users;
	private ArrayList<Course> courses;

	private DataProvider() {
		this.users = new ArrayList<>();
		this.courses = new ArrayList<>();
		init();
	}

	public static DataProvider getInstance() {
		if (dataProvider == null) {
			dataProvider = new DataProvider();
		}
		return dataProvider;
	}

	private void init() {
		Course ict = new Course("ICT", 5, 48, 4);
		Course ta = new Course("Tourism management", 2, 51, 4);
		this.courses.add(ict);
		this.courses.add(ta);
		Subject databases = new Subject("Databases", 4, 4, 8);
		ict.addSubject(databases);
		databases.addTopic(new Topic("Introduction", 1, 1));
		databases.addTopic(new Topic("Aggregation", 2, 2));
		Subject networkServices = new Subject("Network services", 3, 3, 8);
		ict.addSubject(networkServices);
		networkServices.addTopic(new Topic("Spring Boot 1", 2, 1));
		networkServices.addTopic(new Topic("Spring Boot 2", 1, 2));
		Subject testing = new Subject("Testing", 4, 4, 8);
		ict.addSubject(testing);
		testing.addTopic(new Topic("Getting requirements: part 1", 4, 1));
		testing.addTopic(new Topic("Getting requirements: part 2", 4, 2));
		Subject management = new Subject("Management", 3, 3, 8);
		ta.addSubject(management);
		management.addTopic(new Topic("What is management", 5, 1));
		management.addTopic(new Topic("Managing people", 2, 2));
		Subject marketing = new Subject("Marketing", 2, 5, 8);
		ta.addSubject(marketing);
		marketing.addTopic(new Topic("What is marketing", 3, 1));
		marketing.addTopic(new Topic("Stocks", 2, 2));
	}

	public ArrayList<Course> getCourses() {
		return new ArrayList<>(this.courses);
	}

	public void addCourse(Course course) {
		if (!this.courses.contains(course)) {
			this.courses.add(course);
		}
	}

	public Course findCourseByID(int ID) {
		for (Course course : this.courses) {
			if (course.getID() == ID) {
				return course;
			}
		}
		return null;
	}

	public Subject findSubjectByID(int courseID, int subjectID) {
		Course course = findCourseByID(courseID);
		for (Subject subject : course.getSubjects()) {
			if (subject.getID() == subjectID) {
				return subject;
			}
		}
		return null;
	}

	public ArrayList<Subject> updateSubject(Course course, Subject uneditedSubject, Subject editedSubject) {
		ArrayList<Subject> subjectsInCourse = course.getSubjects();
		int index = subjectsInCourse.indexOf(uneditedSubject);
		subjectsInCourse.set(index, editedSubject);
		return subjectsInCourse;
	}

	public void deleteCourse(int courseID) {
		Course course = findCourseByID(courseID);
		this.courses.remove(course);
	}

	public ArrayList<Subject> deleteSubjectInCourse(int courseID, int subjectID) {
		Course currentCourse = findCourseByID(courseID);
		ArrayList<Subject> subjects = currentCourse.getSubjects();
		Subject deletedSubject = findSubjectByID(courseID, subjectID);
		subjects.remove(deletedSubject);
		return subjects;
	}

	public void updateCourse(Course editedCourse, int ID) {
		this.courses.set(ID, editedCourse);
	}

	public Topic findTopicByID(int courseID, int subjectID, int topicID) {
		Subject subject = findSubjectByID(courseID, subjectID);
		for (Topic topic : subject.getTopics()) {
			if (topic.getID() == topicID) {
				return topic;
			}
		}
		return null;
	}

	public ArrayList<Topic> updateTopic(int courseID, int subjectID, Topic uneditedTopic, Topic editedTopic) {
		Subject currentSubject = findSubjectByID(courseID, subjectID);
		ArrayList<Topic> topics = currentSubject.getTopics();
		int index = currentSubject.getTopics().indexOf(uneditedTopic);
		topics.set(index, editedTopic);
		return topics;
	}

	public ArrayList<Topic> deleteTopic(int courseID, int subjectID, int topicID) {
		Subject currentSubject = findSubjectByID(courseID, subjectID);
		Topic currentTopic = findTopicByID(courseID, subjectID, topicID);
		ArrayList<Topic> topics = currentSubject.getTopics();
		topics.remove(currentTopic);
		return topics;
	}
}
