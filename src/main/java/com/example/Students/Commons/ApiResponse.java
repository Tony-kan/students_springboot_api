package com.example.Students.Commons;

import lombok.*;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse{
    private boolean success;
    private String message;
}
