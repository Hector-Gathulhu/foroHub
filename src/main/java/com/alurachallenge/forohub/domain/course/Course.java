package com.alurachallenge.forohub.domain.course;


import com.alurachallenge.forohub.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "courseId")
    private List<Topic> topics;
    private CourseCategory category;
}
