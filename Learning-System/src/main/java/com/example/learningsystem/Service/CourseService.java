package com.example.learningsystem.Service;

import com.example.learningsystem.Model.Course;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CourseService {


    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getCourses() {
        return courses;

    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean updateCourse(String id, Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {

                courses.set(i, course);
                return true;
            }

        }
        return false;
    }

    public boolean deleteCourse(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {

                courses.remove(i);
                return true;
            }

        }
        return false;
    }

    public boolean publishCourse(String id) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                c.setPublished(true);

                return true;
            }
        }

        return false;
    }


    public ArrayList<Course> getCoursesByCategory(String category) {
        ArrayList<Course> cate = new ArrayList<>();
        for (Course c : courses) {
            if (c.getCategory().equalsIgnoreCase(category)) {
                cate.add(c);
            }
        }
        return cate;
    }

    public ArrayList<Course> getOldCourses(int years) {
        ArrayList<Course> old = new ArrayList<>();
        for (Course c : courses) {

            if (c.getStartDate().isBefore(LocalDate.now().minusYears(years))) {
                old.add(c);
            }

        }
        return old;
    }


    public int countByCategory(String category) {
        int count = 0;
        for (Course c : courses) {
            if (c.getCategory().equalsIgnoreCase(category)){
                count++;
            }
        }
        return count;
    }
}
