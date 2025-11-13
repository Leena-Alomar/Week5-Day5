package com.example.learningsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "The ID Cannot Be Empty")
    private String id;
    @NotEmpty(message = "The Title Cannot Be Empty")
    @Size(min = 3, max = 100, message = "The Title Must Be Min Length of 3 And Max Length Of 100")
    private String title;
    @NotEmpty(message = "The Category Cannot Be Empty")
    @Size(min = 3, message = "Category must be at min length of 3")
    private String category;
    @NotNull(message = "The Start Date Cannot Be Null")
    @PastOrPresent(message = "Start date must be in the past or present")
    @JsonFormat
    private LocalDate startDate;
    @AssertFalse(message = "The Published Must Be Set To False")
    private boolean published;
}
