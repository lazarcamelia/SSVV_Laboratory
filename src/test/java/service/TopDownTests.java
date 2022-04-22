package service;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TopDownTests {
    @Test
    public void tc_top_down_addStudent() {
        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository studentRepository = new StudentXMLRepository(studentValidator, "src/main/resources/studenti.xml");

        Service service = new Service(studentRepository, null, null);

        try {
            service.deleteStudent("1000");
        } catch (Exception ex) {
            // do nothing
        }
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 934)==1);
    }

    @Test
    public void tc_top_down_addStudentAndAddTema() {
        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository studentRepository = new StudentXMLRepository(studentValidator, "src/main/resources/studenti.xml");


        Validator<Tema> temaValidator = new TemaValidator();
        TemaXMLRepository temaRepository = new TemaXMLRepository(temaValidator, "src/main/resources/teme.xml");

        Service service = new Service(studentRepository, temaRepository, null);

        try {
            service.deleteStudent("1000");
        } catch (Exception ex) {
            // do nothing
        }
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 934)==1);

        try {
            service.deleteTema("1");
        } catch (Exception ex) {}
        assertEquals(1, service.saveTema("1", "tema1", 8, 6));
    }

    @Test
    public void tc_top_down_addStudentAndAddTemaAndAddAssignment() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository studentRepository = new StudentXMLRepository(studentValidator, "src/main/resources/studenti.xml");
        TemaXMLRepository temaRepository = new TemaXMLRepository(temaValidator, "src/main/resources/teme.xml");
        NotaXMLRepository notaRepository = new NotaXMLRepository(notaValidator, "src/main/resources/note.xml");


        Service service = new Service(studentRepository, temaRepository, notaRepository);

        try {
            service.deleteStudent("1000");
        } catch (Exception ex) {
            // do nothing
        }
        assertTrue(service.saveStudent("1000", "Popescu Georgeta", 934)==1);

        try {
            service.deleteTema("1");
        } catch (Exception ex) {}
        assertEquals(1, service.saveTema("1", "tema1", 8, 6));

        try {
            // delete tema
            notaRepository.delete(new Pair<>("1000", "1"));
        } catch (Exception ex) {
            // do nothing
        }

        assertEquals(1, service.saveNota("1000", "1", 9, 7, "super"));

    }
}
