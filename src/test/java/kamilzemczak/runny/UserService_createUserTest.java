package kamilzemczak.runny;

import kamilzemczak.runny.model.User;

import kamilzemczak.runny.service.UserServiceImpl;
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
public class UserService_createUserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private final UserServiceImpl testedService = new UserServiceImpl();

    @Mock
    User user;

    @After
    public void clean() {
        reset(user);
    }

    @Test
    public void correctData_createNew() {
        final String name = RandomStringUtils.randomAlphabetic(6);
        final String surname = RandomStringUtils.randomAlphabetic(6);
        final String username = RandomStringUtils.randomAlphabetic(6);
        final String email = RandomStringUtils.randomAlphabetic(6);
        final Integer age = (int) (Math.random() * 100);
        final String gender = RandomStringUtils.randomAlphabetic(6);
        final Integer weight = (int) (Math.random() * 100);
        final Integer height = (int) (Math.random() * 100);
        final String city = RandomStringUtils.randomAlphabetic(6);
        final String about = RandomStringUtils.randomAlphabetic(6);
        when(user.getName()).thenReturn(name);
        when(user.getSurname()).thenReturn(surname);
        when(user.getUsername()).thenReturn(username);
        when(user.getEmail()).thenReturn(email);
        when(user.getAge()).thenReturn(age);
        when(user.getGender()).thenReturn(gender);
        when(user.getCity()).thenReturn(city);
        when(user.getAbout()).thenReturn(about);
        when(user.getWeight()).thenReturn(weight);
        when(user.getHeight()).thenReturn(height);
        final User result = testedService.createNew(user);
        assertEquals(name, result.getName());
        assertEquals(surname, result.getSurname());
        assertEquals(username, result.getUsername());
        assertEquals(email, result.getEmail());
        assertEquals(age, result.getAge());
        assertEquals(gender, result.getGender());
        assertEquals(city, result.getCity());
        assertEquals(about, result.getAbout());
        assertEquals(weight, result.getWeight());
        assertEquals(height, result.getHeight());
    }

    @Test
    public void noAboutAndCityData_createNew() {
        final String name = RandomStringUtils.randomAlphabetic(6);
        final String surname = RandomStringUtils.randomAlphabetic(6);
        final String username = RandomStringUtils.randomAlphabetic(6);
        final String email = RandomStringUtils.randomAlphabetic(6);
        Integer age = (int) (Math.random() * 100);
        final String gender = RandomStringUtils.randomAlphabetic(6);
        final Integer weight = (int) (Math.random() * 100);
        final Integer height = (int) (Math.random() * 100);
        when(user.getName()).thenReturn(name);
        when(user.getSurname()).thenReturn(surname);
        when(user.getUsername()).thenReturn(username);
        when(user.getEmail()).thenReturn(email);
        when(user.getAge()).thenReturn(age);
        when(user.getGender()).thenReturn(gender);
        when(user.getWeight()).thenReturn(weight);
        when(user.getHeight()).thenReturn(height);
        final User result = testedService.createNew(user);
        assertEquals(name, result.getName());
        assertEquals(surname, result.getSurname());
        assertEquals(username, result.getUsername());
        assertEquals(email, result.getEmail());
        assertEquals(age, result.getAge());
        assertEquals(gender, result.getGender());
        assertEquals(weight, result.getWeight());
        assertEquals(height, result.getHeight());
        assertNull(result.getAbout());
        assertNull(result.getCity());
    }
}
