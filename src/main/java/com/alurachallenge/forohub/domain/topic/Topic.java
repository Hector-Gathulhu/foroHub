package com.alurachallenge.forohub.domain.topic;


import com.alurachallenge.forohub.domain.answer.Answer;
import com.alurachallenge.forohub.domain.course.Course;
import com.alurachallenge.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id")
    private List<Answer> answer;

    public Topic(RegistTopicData registTopicData, User author, Course course) {
        this.title = registTopicData.title();
        this.message = registTopicData.message();
        this.creationDate = LocalDateTime.now();
        this.author = author;
        this.course = course;
        this.status = true;

    }

    public void updateData(RegistTopicData.UpdateTopicData updateTopicData) {
        if (updateTopicData.title() != null) {
            this.title = updateTopicData.title();
        }
        if (updateTopicData.message() != null) {
            this.message = updateTopicData.message();
        }
    }
}
