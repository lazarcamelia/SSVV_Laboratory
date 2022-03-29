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
    public void tc_01_saveTema_tryBlockRepo_catchBlockForInvalidId() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            service.saveTema("", "tema1", 8, 6);
        });

        String expectedMessage = "ID invalid!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
