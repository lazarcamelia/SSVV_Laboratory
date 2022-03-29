package service;

import domain.Tema;
import org.junit.Test;
import repository.TemaXMLRepository;
import validation.TemaValidator;
import validation.ValidationException;
import validation.Validator;

import static org.junit.Assert.*;

public class AddAssignmentTest {
    private Validator<Tema> assignmentValidator = new TemaValidator();
    private TemaXMLRepository repository = new TemaXMLRepository(assignmentValidator, "src/main/resources/teme_test.xml");
    private Service service = new Service(null, repository, null);


    @Test
    public void tc_01_saveTema_tryBlockRepo_happyFlow() {
        try {
            service.deleteTema("1");
        } catch (Exception ex) {}
        assertEquals(1, service.saveTema("1", "tema1", 8, 6));
    }

    @Test
    public void tc_02_saveTema_tryBlockRepo_catchBlockForInvalidId() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            service.saveTema("", "tema1", 8, 6);
        });

        String expectedMessage = "ID invalid!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void tc_03_saveTema_tryBlockRepo_catchBlockInvalidDescription() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            service.saveTema("1", "", 8, 6);
        });

        String expectedMessage = "Descriere invalida!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void tc_04_saveTema_tryBlockRepo_catchBlockForDeadlineBeforeStartLine() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            service.saveTema("1", "tema1", 6, 7);
        });

        String expectedMessage = "Deadline invalid!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void tc_05_saveTema_tryBlockRepo_catchBlockForNegativeStartline() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            service.saveTema("1", "tema1", 6, 0);
        });

        String expectedMessage = "Data de primire invalida!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void tc_06_saveTema_tryBlockRepo_catchBlockForNegativeStartline() {
        assertEquals(0, service.saveTema("2", "tema1", 8, 6));
    }
}
