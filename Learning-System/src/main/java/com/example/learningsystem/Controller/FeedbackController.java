package com.example.learningsystem.Controller;

import com.example.learningsystem.API.ApiResponse;
import com.example.learningsystem.Model.Feedback;
import com.example.learningsystem.Service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;


    @GetMapping("/get")
    public ResponseEntity<?> getFeedback() {

        return ResponseEntity.status(200).body(feedbackService.getFeedbacks());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFeedback(@RequestBody @Valid Feedback feedback, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        feedbackService.addFeedback(feedback);
        return ResponseEntity.status(200).body(new ApiResponse("The Feedback Is Added Successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFeedback(@PathVariable String id, @RequestBody @Valid Feedback feedback, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = feedbackService.updateFeedback(id, feedback);

        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("The Feedback Is Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Feedback IS Not Found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable String id) {
        boolean isDeleted = feedbackService.deleteFeedback(id);

        if (isDeleted){
                return ResponseEntity.status(200).body(new ApiResponse("The Feedback Is Deleted Successfully"));
            }
            return ResponseEntity.status(400).body(new ApiResponse("The Feedback IS Not Found"));
    }




    @GetMapping("/get/course/{id}")
    public ResponseEntity<?> getByCourse(@PathVariable String id) {

        if (! feedbackService.getByCourseId(id).isEmpty()){
            return ResponseEntity.status(200).body(feedbackService.getByCourseId(id));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Feedback IS Not Found"));
    }



    @GetMapping("/get/minRating/{rating}")
    public ResponseEntity<?> getByMinRating(@PathVariable int rating) {

        if (!feedbackService.getByMinRating(rating).isEmpty()){

            return ResponseEntity.status(200).body(feedbackService.getByMinRating(rating));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The Feedback IS Not Found"));
    }


    @GetMapping("/get/average/{id}")
    public ResponseEntity<?> getAverage(@PathVariable String id) {
        if (feedbackService.getAverageForCourse(id) != 0){

            return ResponseEntity.status(200).body(feedbackService.getAverageForCourse(id));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The Feedback IS Not Found"));
    }



    @GetMapping("/get/reviewer/{reviewerName}")
    public ResponseEntity<?> getByReviewer(@PathVariable String reviewerName) {

        if (!feedbackService.getByReviewer(reviewerName).isEmpty()){

            return ResponseEntity.status(200).body(feedbackService.getAverageForCourse(reviewerName));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The Feedback IS Not Found"));
    }
}
