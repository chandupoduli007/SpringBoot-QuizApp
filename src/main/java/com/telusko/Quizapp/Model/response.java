package com.telusko.Quizapp.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class response {
    private int id;
    private List <response> responses;
}
