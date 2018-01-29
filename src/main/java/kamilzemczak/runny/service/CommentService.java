package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kamilzemczak.runny.dao.CommentRepository;
import kamilzemczak.runny.model.Comment;
import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.User;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()) {
            comments.add(comment);
        }
        return comments;
    }

    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment create(User author, String contents, Post postToModify, List<Comment> comments) {
        Comment comment = new Comment();
        if (author == null) {
            throw new NullPointerException("Brak użytkownika.");
        }
        comment.setAuthor(author);
        comment.setContents(contents);
        comment.setPost(postToModify);
        comments.add(comment);
        return comment;
    }

    public void modifyPost(Post postToModify, List<Comment> comments, Comment comment) {
        if (postToModify.getComments() == null) {
            postToModify.setComments(comments);
        } else {
            postToModify.getComments().add(comment);
        }
    }

    public void find(List<Comment> allComments, Integer postId, List<Comment> commentsToSend) {
        for (Comment comment : allComments) {
            if (comment.getAuthor().getFriends() != null) {
                comment.getAuthor().getFriends().clear();
                comment.getPost().getAuthor().getFriends().clear();
            }
            if (comment.getPost().getId().equals(postId)) {
                commentsToSend.add(comment);
            }
            comment.getPost().getComments().clear();
        }
    }

    public List<Integer> getCommentSize(List<Post> postsToSend) {
        List<Integer> commentNumber = new ArrayList<>();
        for (Post posts : postsToSend) {
            if (posts.getComments() != null) {
                commentNumber.add(posts.getComments().size());
            }
        }
        return commentNumber;
    }

    public void deleteCommentWithPost(List<Comment> comments, Integer postId) {
        for (Comment comment : comments) {
            if (comment.getPost().getId().equals(postId)) {
                commentRepository.delete(comment.getId());
            }
        }
    }

    /*public PostLike setLikeToPost(Post post, User currentUser) {
        List<PostLike> likes = new ArrayList<>();
        PostLike like = new PostLike();
        like.setPost(post);
        like.setUser(currentUser);
        likes.add(like);
        post.setLikes(likes);
        return like;
    }*/
}
