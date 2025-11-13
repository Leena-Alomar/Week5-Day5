package com.example.learningsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Feedback {
    @NotEmpty(message = "The ID Cannot Be Empty")
    private String id;
    @NotEmpty(message = "The Reviewer Name Cannot Be Empty")
    @Size(min = 3, message = "The Reviewer Name Must Be A Min Length of 3")
    private String reviewerName;
    @Email(message = "Please Enter The Email With Right Format : name@gmail.com")
    private String email;
    @NotNull(message = "The Rating Cannot Be null")
    @Min(value = 0, message = "Rating must be between 0 and 5")
    @Max(value = 5, message = "Rating must be between 0 and 5")
    private Integer rating;
    @NotEmpty(message = "The Comment Cannot Be Empty")
    @Size(min = 10, message = "The Comment Must Be A Min Length of 10")
    private String comment;
    private String CourseName;
}
