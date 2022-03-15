package service;

import domain.Student;
import org.junit.Test;
import repository.StudentXMLRepository;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.Assert.assertTrue;


public class AddStudentTest {
    private Validator<Student> studentValidator = new StudentValidator();
    private StudentXMLRepository repository = new StudentXMLRepository(studentValidator, "src/main/resources/studenti_test.xml");
    private Service service = new Service(repository, null, null);


    @Test
    public void tc_1_addStudent_whenValidInput_ShouldAddStudent() {
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 934)==1);
    }

    @Test
    public void tc_2_addStudent_whenIdNull_ShouldThrowEx() {
        assertTrue(service.saveStudent(null, "Popescu Georgeta", 934)==0);
    }
}