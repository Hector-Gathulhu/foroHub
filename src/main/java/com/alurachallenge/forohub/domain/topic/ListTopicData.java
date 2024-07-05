package com.alurachallenge.forohub.domain.topic;

import com.alurachallenge.forohub.domain.course.Course;
import com.alurachallenge.forohub.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ListTopicData(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        LocalDateTime creationDate,
        @NotNull
        Boolean status,
        @NotNull
        User author,
        @NotNull
        Course course) {

    public ListTopicData(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getAuthor(), topic.getCourse());

    }


}
