package com.alurachallenge.forohub.domain.answer;

import com.alurachallenge.forohub.domain.user.User;
import com.alurachallenge.forohub.domain.userProfile.UserProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerData(
        @NotNull Long id,
        @NotBlank String message,
        @NotNull String creationDate,
        Boolean solution,
        @NotBlank String author,
        @NotNull UserProfile authorProfile,
        @NotNull String topicTitle
) {
}
