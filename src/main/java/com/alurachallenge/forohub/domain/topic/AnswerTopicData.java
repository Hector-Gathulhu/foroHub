package com.alurachallenge.forohub.domain.topic;

import com.alurachallenge.forohub.domain.answer.AnswerData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record AnswerTopicData(
        @NotNull Long id,
        @NotBlank String title,
        @NotBlank String message,
        @NotNull String creationDate,
        @NotNull String author,
        @NotNull String category,
        @NotNull List<AnswerData> respuestas
) {
}
