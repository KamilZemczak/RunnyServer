package kamilzemczak.runny;

import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.PostService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostService_createPostTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private final PostService testedService = new PostService();

    @Mock
    Post post;

    @Mock
    User author;

    @After
    public void clean() {
        reset(post, author);
    }

    @Test
    public void correctData_createNew() {
        final String content = RandomStringUtils.randomAlphabetic(6);
        final Post result = testedService.create(author, content);
        assertEquals(content, result.getContents());
        assertEquals(author, result.getAuthor());
    }

    @Test
    public void contentNotFound_throwException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Treść nie może być pusta.");
        testedService.create(author, null);
    }
}
