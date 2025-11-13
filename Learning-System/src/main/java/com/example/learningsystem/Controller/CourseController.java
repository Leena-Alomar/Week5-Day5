package com.example.learningsystem.Controller;

import com.example.learningsystem.API.ApiResponse;
import com.example.learningsystem.Model.Course;
import com.example.learningsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {


    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("The Course IS added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean updated = courseService.updateCourse(id, course);
        if (updated){
            return ResponseEntity.status(200).body(new ApiResponse("The Course IS updated Successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("The Course Is not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {

        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {

            return ResponseEntity.status(200).body(new ApiResponse("The Course IS Deleted Successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("The Course Is not found"));
    }



    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publishCourse(@PathVariable String id) {
        boolean published = courseService.publishCourse(id);

        if (published){
            return ResponseEntity.status(200).body(new ApiResponse("The Course IS Published Successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("The Course Is not found"));
    }


    @GetMapping("/get/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category) {
        if (!courseService.getCoursesByCategory(category).isEmpty()) {

            return ResponseEntity.status(200).body(courseService.getCoursesByCategory(category));
        }

        return ResponseEntity.status(400).body(new ApiResponse("There Is No courses in this category"));
    }


    @GetMapping("/get/old/{years}")
    public ResponseEntity<?> getOldCourses(@PathVariable int years) {

        if (!courseService.getOldCourses(years).isEmpty()) {

            return ResponseEntity.status(200).body(courseService.getOldCourses(years));
        }

        return ResponseEntity.status(400).body(new ApiResponse("No old courses Found"));
    }



    @GetMapping("/count/category/{category}")
    public ResponseEntity<?> countByCategory(@PathVariable String category) {

        if ( courseService.countByCategory(category)> 0){

            return ResponseEntity.status(200).body(courseService.countByCategory(category));
        }
        return ResponseEntity.status(400).body(new ApiResponse("There is No Courses in this Category"));
    }

}
