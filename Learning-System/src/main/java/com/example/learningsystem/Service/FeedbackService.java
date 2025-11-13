package com.example.learningsystem.Service;

import com.example.learningsystem.Model.Feedback;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FeedbackService {

    ArrayList<Feedback> feedbacks = new ArrayList<>();


    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addFeedback(Feedback f) {
        feedbacks.add(f);
    }

    public boolean updateFeedback(String id, Feedback feedback) {
        for (int i = 0; i < feedbacks.size(); i++) {

            if (feedbacks.get(i).getId().equals(id)) {

                feedbacks.set(i, feedback);

                return true;
            }
        }
        return false;
    }

    public boolean deleteFeedback(String id) {
        for (int i = 0; i < feedbacks.size(); i++) {
            if (feedbacks.get(i).getId().equals(id)) {
                feedbacks.remove(i);
                return true;
            }

        }
        return false;
    }



    public ArrayList<Feedback> getById(String id) {
        ArrayList<Feedback> couresID = new ArrayList<>();
        for (Feedback f : feedbacks) {
            if (f.getId().equals(id)) {

                couresID.add(f);
            }
        }
        return couresID;
    }


    public ArrayList<Feedback> getByMinRating(int rating) {
        ArrayList<Feedback> min = new ArrayList<>();
        for (Feedback f : feedbacks) {

            if (f.getRating() >= rating) {
                min.add(f);

            }
        }
        return min;
    }


    public double getAverageForCourse(String id) {
        double sum = 0;
        for (Feedback f : feedbacks) {
            if (f.getId().equals(id)) {

                sum += f.getRating();

            }
        }
        return sum / feedbacks.size();
    }


    public ArrayList<Feedback> getByReviewer(String reviewerName) {
        ArrayList<Feedback> reviwer = new ArrayList<>();

        for (Feedback f : feedbacks) {
            if (f.getReviewerName().equalsIgnoreCase(reviewerName)){
                reviwer.add(f);
            }
        }
        return reviwer;
    }
}
