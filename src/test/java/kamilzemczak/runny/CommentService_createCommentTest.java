package kamilzemczak.runny;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.model.Comment;
import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.CommentService;

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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentService_createCommentTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private final CommentService testedService = new CommentService();

    @Mock
    Comment comment;

    @Mock
    User author;

    @Mock
    Post post;

    @Spy
    private List<Comment> comments = new ArrayList<>();

    @After
    public void clean() {
        reset(comment, author, post, comments);
    }

    @Test
    public void correctData_createNew() {
        final String content = RandomStringUtils.randomAlphabetic(6);
        final Comment result = testedService.create(author, content, post, comments);
        assertEquals(content, result.getContents());
        assertEquals(author, result.getAuthor());
    }

    @Test
    public void authorNotFound_throwException() {
        final String content = RandomStringUtils.randomAlphabetic(6);
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Brak użytkownika.");
        testedService.create(null, content, post, comments);
    }
}
