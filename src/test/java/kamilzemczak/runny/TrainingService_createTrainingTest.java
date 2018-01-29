package kamilzemczak.runny;

import java.util.Random;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;

import kamilzemczak.runny.service.TrainingService;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import static org.junit.Assert.assertNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TrainingService_createTrainingTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private final TrainingService testedService = new TrainingService();

    @Mock
    Training training;

    @Mock
    User author;

    @After
    public void clean() {
        reset(training, author);
    }

    @Test
    public void correctMaleData_createNew() {
        final Integer distance = (int) (Math.random()*100);
        final String hours = RandomStringUtils.randomAlphabetic(6);
        final String mins = RandomStringUtils.randomAlphabetic(6);
        final Integer duration = (int) (Math.random()*100);
        final Integer iCalories = (int) (Math.random()*100);
        final String notes = RandomStringUtils.randomAlphabetic(6);
        final String name = RandomStringUtils.randomAlphabetic(6);
        final String surname = RandomStringUtils.randomAlphabetic(6);
        final String content = name + " " + surname + " " + "przebiegł" + " " + distance + "km" + " " + "w" + " " + hours + "g" + ":" + mins + "m.";
        when(author.getGender()).thenReturn("M");
        when(author.getName()).thenReturn(name);
        when(author.getSurname()).thenReturn(surname);
        final Training result = testedService.create(author, distance, hours, mins, duration, iCalories, notes);
        assertEquals(distance, result.getDistance());
        assertEquals(duration, result.getDuration());
        assertEquals(iCalories, result.getCalories());
        assertEquals(notes, result.getNotes());
        assertEquals(content, result.getContents());
    }
    
    
    @Test
    public void correctFemaleData_createNew() {
        final Integer distance = (int) (Math.random()*100);
        final String hours = RandomStringUtils.randomAlphabetic(6);
        final String mins = RandomStringUtils.randomAlphabetic(6);
        final Integer duration = (int) (Math.random()*100);
        final Integer iCalories = (int) (Math.random()*100);
        final String notes = RandomStringUtils.randomAlphabetic(6);
        final String name = RandomStringUtils.randomAlphabetic(6);
        final String surname = RandomStringUtils.randomAlphabetic(6);
        final String content = name + " " + surname + " " + "przebiegła" + " " + distance + "km" + " " + "w" + " " + hours + "g" + ":" + mins + "m.";
        when(author.getGender()).thenReturn("F");
        when(author.getName()).thenReturn(name);
        when(author.getSurname()).thenReturn(surname);
        final Training result = testedService.create(author, distance, hours, mins, duration, iCalories, notes);
        assertEquals(distance, result.getDistance());
        assertEquals(duration, result.getDuration());
        assertEquals(iCalories, result.getCalories());
        assertEquals(notes, result.getNotes());
        assertEquals(content, result.getContents());
    }

    @Test
    public void notesNull_createNew() {
        final Integer distance = (int) (Math.random()*100);
        final String hours = RandomStringUtils.randomAlphabetic(6);
        final String mins = RandomStringUtils.randomAlphabetic(6);
        final Integer duration = (int) (Math.random()*100);
        final Integer iCalories = (int) (Math.random()*100);
        final String name = RandomStringUtils.randomAlphabetic(6);
        final String surname = RandomStringUtils.randomAlphabetic(6);
        final String content = name + " " + surname + " " + "przebiegł" + " " + distance + "km" + " " + "w" + " " + hours + "g" + ":" + mins + "m.";
        when(author.getGender()).thenReturn("M");
        when(author.getName()).thenReturn(name);
        when(author.getSurname()).thenReturn(surname);
        final Training result = testedService.create(author, distance, hours, mins, duration, iCalories, null);
        assertEquals(distance, result.getDistance());
        assertEquals(duration, result.getDuration());
        assertEquals(iCalories, result.getCalories());
        assertEquals(content, result.getContents());
        assertNull(result.getNotes());
    }
}
