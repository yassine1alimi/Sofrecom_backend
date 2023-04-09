package com.sofrecom.stage.services.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sofrecom.stage.models.Comment;
import com.sofrecom.stage.repository.forum.CommentRepository;
import com.sofrecom.stage.repository.forum.EvaluatePostRepository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EvaluatePostRepository evaluatePostRepository;

    @Override
    public Comment saveMultiPartComment(MultipartFile file) {
        Comment c = new Comment();
        System.out.println("file is " + file.getContentType());
        try {
            c.setCommentContentType(file.getContentType());
            c.setCommentContent(file.getBytes());
            return commentRepository.save(c);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Comment retrieveCommentById(String commentId) {
        Comment retrievedComment = commentRepository.findById(commentId).orElse(null);
        return retrievedComment;
    }

    @Override
    @Transactional
    public void removeCommentById(String commentId) {
        Comment comment = retrieveCommentById(commentId);
        comment.setLocked(true);
        commentRepository.save(comment);
        evaluatePostRepository.lockEvaluatePost(commentId);

    }

    @Override
    public Comment updateComment(String commentId, Comment comment) {
        Comment retrievedComment = retrieveCommentById(commentId);
        retrievedComment.setCommentContent(comment.getCommentContent());
        return commentRepository.save(retrievedComment);
    }

    @Override
    public Comment updateCommentFile(String commentId, MultipartFile file) {
        Comment c = retrieveCommentById(commentId);
        try{
            c.setCommentContentType(file.getContentType());
            c.setCommentContent(file.getBytes());
        }
        catch (Exception e){
            System.out.println(e);
        }
        return commentRepository.save(c);
    }
    @Override
    public Comment saveTextComment(Comment comment){
        if(comment ==null){
            throw new NullPointerException("we cannot save null ! ");
        }
        Comment c = new Comment();
        c.setCommentContent(comment.getCommentContent());
        System.out.println(comment.getCommentContentType());
        c.setCommentContentType("text/plain");


       return commentRepository.save(c);
    }
}
