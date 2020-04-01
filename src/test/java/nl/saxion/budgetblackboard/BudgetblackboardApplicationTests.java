package nl.saxion.budgetblackboard;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BudgetblackboardApplicationTests {

	@Test
	public void testAddCourse(){
		//Arrange
		DataProvider data = DataProvider.getInstance();
		Course course = new Course("ad", 1,1,1);

		//Act
		data.addCourse(course);
		Course courseInTheArray = data.getCourses().get(data.getCourses().size()-1);

		//Assert
		assertSame(course, courseInTheArray, "Message");
	}

}
