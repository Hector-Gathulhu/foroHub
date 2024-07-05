package com.alurachallenge.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistTopicData(
        @NotBlank
        String title,
        @NotBlank
        @Size(min = 0, max = 300)
        String message,
        @NotNull
        Long authorId,
        @NotNull
        Long courseId
) {

        public static record UpdateTopicData (
                @NotNull Long id,
                String title,
                String message,
                Long courseId
        ){
        }


}
