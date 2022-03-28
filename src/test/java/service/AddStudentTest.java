package service;

import domain.Student;
import org.junit.Test;
import repository.StudentXMLRepository;
import validation.StudentValidator;
import validation.Validator;

import static java.sql.Types.NULL;
import static org.junit.Assert.*;


public class AddStudentTest {
    private Validator<Student> studentValidator = new StudentValidator();
    private StudentXMLRepository repository = new StudentXMLRepository(studentValidator, "src/main/resources/studenti_test.xml");
    private Service service = new Service(repository, null, null);


    @Test
    public void tc_1_addStudent_whenValidInput_ShouldAddStudent() {
        try {
            service.deleteStudent("1000");
        } catch (Exception ex) {
            // do nothing
        }
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 934)==1);
    }

    @Test
    public void tc_2_addStudent_whenIdNull_ShouldThrowEx() {
        assertTrue(service.saveStudent(null, "Popescu Georgeta", 934)==0);
    }

    @Test
    public void tc_3_addStudent_whenIdNegative() {
        assertTrue(service.saveStudent("-1000", "Popescu Georgeta", 934)==0);
    }

    @Test
    public void tc_4_addStudent_whenNameNull() {
        assertTrue(service.saveStudent("1000", null, 934)==0);
    }

    @Test
    public void tc_5_addStudent_whenGroupIdTooSmall() {
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 100)==0);
    }

    @Test
    public void tc_6_addStudent_whenGroupIdIsTooBig() {
        assertTrue( service.saveStudent("1000", "Popescu Georgeta", 1000)==0);
    }

    @Test
    public void tc_7_addStudent_whenIdIsDuplicated() {
        try {
            service.deleteStudent("1000");
        } catch (Exception ex) {
            // do nothing
        }

        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 924)==1);
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 923)==0);
    }

    @Test
    public void tc_8_addStudent_whenNameIsEmpty() {
        assertTrue(service.saveStudent("1000", "", 923)==0);
    }

    @Test
    public void tc_9_addStudent_whenGroupIdIsNull() {
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", NULL)==0);
    }

    @Test
    public  void tc_11_addStudent_IdIs1(){
        try {
            service.deleteStudent("1");
        } catch (Exception ex) {
            // do nothing
        }

        assertTrue(service.saveStudent("1", "Popescu Georgeta", 120)==1);
    }

    @Test
    public void tc_13_addStudent_GroupIs109(){
        try {
            service.deleteStudent("1000");
        } catch (Exception ex) {
            // do nothing
        }
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 109) == 0);
    }

    @Test
    public void tc_10_addStudent_IdIs1() {
        try {
            service.deleteStudent("0");
        } catch (Exception ex) {
            // do nothing
        }
        assertTrue(service.saveStudent("0", "Popescu Georgeta", 120)==1);
    }

    @Test
    public void tc_12_addStudent_addIsNegative() {
        assertTrue(service.saveStudent("-1", "Popescu Georgeta", 120)==0);
    }

    @Test
    public void tc_14_addStudent_groupIdIdIs110() {
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 110)==0);
    }

    @Test
    public void tc_16_addStudent_groupIdIdIs937() {
        try {
            service.deleteStudent("1000");
        } catch (Exception ex) {
            // do nothing
        }
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 937)==1);
    }

    @Test
    public void tc_18_addStudent_groupIdIdIs939() {
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 939)==0);
    }

    @Test
    public void tc_20_addStudent_idIsNegativeAndGroupIdIdIs10() {
        assertTrue(service.saveStudent("-1", "Popescu Georgeta", 109)==0);
    }

    @Test
    public void tc_15_addStudent_groupIdIs111() {
        try {
            service.deleteStudent("1000");
        } catch (Exception ex) {
            // do nothing
        }
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 111)==1);
    }

    @Test
    public void tc_17_addStudent_groupIdIs938() {
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 938)==0);
    }

    @Test
    public void tc_19_addStudent_idIs1001AndGroupIdIs932() {
        try {
            service.deleteStudent("1001");
        } catch (Exception ex) {
            // do nothing
        }
        assertTrue(service.saveStudent("1001", "Popescu Georgeta", 932)==1);
    }

}
