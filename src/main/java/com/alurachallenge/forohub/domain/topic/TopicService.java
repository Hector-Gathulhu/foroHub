package com.alurachallenge.forohub.domain.topic;


import com.alurachallenge.forohub.domain.course.Course;
import com.alurachallenge.forohub.domain.course.CourseRepository;
import com.alurachallenge.forohub.domain.user.User;
import com.alurachallenge.forohub.domain.user.UserRepositosy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepositosy userRepository;

    @Transactional
    public Topic registTopic(RegistTopicData registTopicData) {
        Long courseId = registTopicData.courseId();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("El curso de ID " + courseId + " no existe."));

        Long authorId = registTopicData.authorId();
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("El author de ID " + authorId + " no existe."));
        System.out.println("Autor encontrado: " + author);


        List<Topic> topics = topicRepository.findAll();
        for (Topic topic : topics) {
            if (topic.getTitle().equalsIgnoreCase(registTopicData.title())
                    && topic.getMessage().equalsIgnoreCase(registTopicData.message())) {
                System.out.println("Ya existe un tópico con ese título y message.");
                return null;
            }
        }

        Topic newTopico = new Topic(registTopicData, author, course);
        Topic topicoSaved = topicRepository.save(newTopico);
        System.out.println("Tópico guardado: " + topicoSaved);

        return topicoSaved;
    }
}