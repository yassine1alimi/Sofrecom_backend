package com.sofrecom.stage.services.forum;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import com.sofrecom.stage.models.Comment;
import com.sofrecom.stage.models.Emoji;
import com.sofrecom.stage.models.EvaluatePost;
import com.sofrecom.stage.models.EvaluatePostKey;
import com.sofrecom.stage.models.Post;
import com.sofrecom.stage.models.Rating;
import com.sofrecom.stage.repository.IUtilidateurRepo;
import com.sofrecom.stage.repository.forum.EvaluatePostRepository;
import com.sofrecom.stage.repository.forum.PostRepository;
import com.sofrecom.stage.utils.utils.BadWordChecker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.stream.Collectors;
import springfox.documentation.spring.web.json.Json;

@Service
public class EvaluatePostService implements IEvaluatePostService {

    @Autowired
    EvaluatePostRepository evaluatePostRepository;

    @Autowired
    ICommentService commentService;

    @Autowired
    IUtilidateurRepo userRepository;
    @Autowired
    IPostService postService;

    @Autowired
    private PostRepository postRepository;


    @Override
    @Transactional
    public EvaluatePost addEvaluatePostFile(MultipartFile file,Long postId,Long userId){
        if(file == null){
            throw new NullPointerException("We can not save null !");
        }
        if(checkPostAvailable(postId)){
            return null;
        }
        Comment comment = commentService.saveMultiPartComment(file);
        EvaluatePost evaluatePost = new EvaluatePost();
        evaluatePost.setComment(comment);
        evaluatePost.setPostEvaluator(userRepository.findById((long) userId).orElse(null));
        System.out.println(postService.retrievePostById(postId));
        evaluatePost.setEvaluatedPost(postService.retrievePostById(postId));
        EvaluatePostKey evaluatePostKey = new EvaluatePostKey();
        evaluatePostKey.setPostId(postId);
        evaluatePostKey.setUserId(userId);
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate= DateFor.format(date);
        evaluatePostKey.setInteractionDate(stringDate);
        evaluatePost.setEvaluatePostKey(evaluatePostKey);
        return evaluatePostRepository.save(evaluatePost);
    }

    @Override
    @Transactional
    public EvaluatePost addEvaluatePostText(Comment comment, Long postId, Long userId) {
        if(checkPostAvailable(postId)){
            return null;
        }

        BadWordChecker badWordChecker = new BadWordChecker();
        badWordChecker.parseFile();


        try{
            String word = new String(comment.getCommentContent());
            System.out.println("word is :  " + word);
            if(!(badWordChecker.checkWord(word))){
                System.out.println("Evaluation will not be saved ! ");
                return null;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }


        Comment c = commentService.saveTextComment(comment);
        EvaluatePost evaluatePost = new EvaluatePost();
        evaluatePost.setComment(c);
        evaluatePost.setPostEvaluator(userRepository.findById(userId).orElse(null));
        System.out.println(postService.retrievePostById(postId));
        evaluatePost.setEvaluatedPost(postService.retrievePostById(postId));
        EvaluatePostKey evaluatePostKey = new EvaluatePostKey();
        evaluatePostKey.setPostId(postId);
        evaluatePostKey.setUserId((long) 1);
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate= DateFor.format(date);
        evaluatePostKey.setInteractionDate(stringDate);
        evaluatePost.setEvaluatePostKey(evaluatePostKey);
        System.out.println("DATE FORMAT : "  + new Timestamp(new Date().getTime()));
        System.out.print("**************");
        System.out.print("Evaluation is : " + evaluatePost);
        System.out.print("**************");
        return evaluatePostRepository.save(evaluatePost);
    }

    @Override
    @Transactional
    public EvaluatePost addEvaluationPostRating(EvaluatePost evaluatePost, Long postId, Long userId) {
        //System.out.println(evaluatePost);
        if(checkPostAvailable(postId)){
            return null;
        }
        EvaluatePostKey evaluatePostKey = new EvaluatePostKey();
        evaluatePostKey.setUserId(userId);
        evaluatePostKey.setPostId(postId);
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate= DateFor.format(date);
        evaluatePostKey.setInteractionDate(stringDate);
        EvaluatePost evaluatePost1 = new EvaluatePost();
        evaluatePost1.setEvaluatePostKey(evaluatePostKey);
        evaluatePost1.setRating(evaluatePost.getRating());
        evaluatePost1.setPostEvaluator(userRepository.findById(userId).orElse(null));
        evaluatePost1.setEvaluatedPost(postService.retrievePostById(postId));
        System.out.println("================");

        System.out.print("Evaluation post is  : " + evaluatePost1);

        System.out.println("================");
        return evaluatePostRepository.save(evaluatePost1);
    }

    @Override
    @Transactional
    public EvaluatePost addEvaluationPostEmoji(EvaluatePost evaluatePost, Long postId, Long userId) {
        if(checkPostAvailable(postId)){
            return null;
        }
        EvaluatePostKey evaluatePostKey = new EvaluatePostKey();
        evaluatePostKey.setUserId(userId);
        evaluatePostKey.setPostId(postId);
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate= DateFor.format(date);
        evaluatePostKey.setInteractionDate(stringDate);
        EvaluatePost evaluatePost1 = new EvaluatePost();
        evaluatePost1.setEvaluatePostKey(evaluatePostKey);
        evaluatePost1.setEmoji(evaluatePost.getEmoji());
        if(evaluatePost.getEmoji()==Emoji.LIKE){
            System.out.println("I am Here");
            postService.retrievePostById(postId).setNbLikes(postService.retrievePostById(postId).getNbLikes()+1);
        }
        evaluatePost1.setPostEvaluator(userRepository.findById(userId).orElse(null));
        evaluatePost1.setEvaluatedPost(postService.retrievePostById(postId));
        System.out.println("================");

        System.out.print("Evaluation post is  : " + evaluatePost1);

        System.out.println("================");


        return evaluatePostRepository.save(evaluatePost1);
    }


  public  Set<EvaluatePost> getEvaluationPostByUserId(Long userId){
      return evaluatePostRepository.getAllEvaluationPostByUserId(userId);
   }

    @Override
    public Set<EvaluatePost> getAllEvaluationPostByPostId(Long postId) {
        return  evaluatePostRepository.getAllEvaluationPostByPostId(postId);
    }

    @Override
    public String getPostDetails(Long postId) {
        System.out.println("*********Test ************");
        System.out.println(evaluatePostRepository.getReactionEvaluationPostByPostId(postId));
        System.out.println("********* End Test*********");
        if(!(evaluatePostRepository.getReactionEvaluationPostByPostId(postId).isEmpty())){
            System.out.println("I enter here");
            List<Emoji> emoji =
                    evaluatePostRepository.getReactionEvaluationPostByPostId(postId)
                            .stream()
                            .map(EvaluatePost::getEmoji)
                            .filter(emoji1 -> emoji1!=null)
                            .collect(Collectors.toList());
            Map<Emoji, Long> result =emoji
                    .stream()
                    .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
            List<Rating> ratings =
                    evaluatePostRepository.getReactionEvaluationPostByPostId(postId)
                            .stream()
                            .map(EvaluatePost::getRating)
                            .filter(rating ->rating != null )
                            .collect(Collectors.toList());
            Map<Rating, Long> resultRating =ratings
                    .stream()
                    .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
            System.out.println(resultRating);
            float score = 0;
            int total = 0;
            for (Map.Entry<Rating,Long> entry : resultRating.entrySet()){

                switch (entry.getKey()){
                    case HIGH:
                        score+=5*entry.getValue();
                        total+=entry.getValue();
                        break;
                    case MEDUIM:
                        score+=3*entry.getValue();
                        total+=entry.getValue();
                        break;
                    case LOW:
                        //score+=0;
                        total+=entry.getValue();
                }

            }

            System.out.println(score);
            System.out.println(total);
            System.out.println("ratingScore = "+score/total);
            Post setRatingtoPost = postService.retrievePostById(postId);
            setRatingtoPost.setPostRatingScore(score/total);
            postRepository.save(setRatingtoPost);
            System.out.println(setRatingtoPost);

            Set<EvaluatePost> postDetails = evaluatePostRepository.getCommentEvaluationPostByPostId(postId);
            System.out.println("==============");
            System.out.println(postDetails);
            System.out.println("==============");
            JSONObject jsonObject = new JSONObject();
            try {
				jsonObject.put("emoji",result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            List<Comment> comments = new ArrayList<>();

            for(EvaluatePost post:postDetails){
                System.out.println(post.getComment());
                comments.add(post.getComment());
            }
            try {
				jsonObject.put("comments",comments);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				jsonObject.put("rating",score/total);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				jsonObject.put("postId",postId);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return jsonObject.toString();
        }
        return "";
    }


    boolean checkPostAvailable(Long postId){
      return postService.retrievePostById(postId).isLocked();
   }
}
