package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.answer.AnswerData;
import com.alurachallenge.forohub.domain.topic.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicService topicService;


    // ADD TOPIC
    @PostMapping
    @Transactional
    public ResponseEntity newTopic(@RequestBody @Valid RegistTopicData data, UriComponentsBuilder uriBuilder) {

        Topic topic = topicService.registTopic(data);

        if (topic != null) {
            RegistTopicData answerTopicData = new RegistTopicData(
                    topic.getTitle(),
                    topic.getMessage(),
                    topic.getAuthor().getId(),
                    topic.getCourse().getId()
            );

            URI url = uriBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();

            return ResponseEntity.created(url).body(answerTopicData);

        } else {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un tópico con ese título y message");
        }
    }

    //LIST TOPICS
    @GetMapping
    public ResponseEntity<Page<ListTopicData>> listTopic(
            @PageableDefault(size = 10, sort = "creationDate", direction = Sort.Direction.ASC) Pageable pagination) {

        Page<Topic> topicos = topicRepository.findByStatusTrue(pagination);
        Page<ListTopicData> listTopicData = topicos.map(ListTopicData::new);
        return ResponseEntity.ok().body(listTopicData);
    }

    //TOPIC DETAILS

    @GetMapping("/detail/{id}")
    public ResponseEntity topicdetails(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            List<AnswerData> answerData = topic.getAnswer().stream().map(an
                            -> new AnswerData(
                            an.getId(),
                            an.getMessage(),
                            an.getCreationDate().format(formatter),
                            an.getSolution(),
                            an.getAuthor().getName(),
                            an.getAuthor().getProfile(),
                            an.getTopic().getTitle()
                    )
            ).collect(Collectors.toList());

            AnswerTopicData topicData = new AnswerTopicData(
                    topic.getId(),
                    topic.getTitle(),
                    topic.getMessage(),
                    topic.getCreationDate().format(formatter),
                    topic.getAuthor().getName(),
                    topic.getCourse().getName(),
                    answerData
            );
            return ResponseEntity.ok(topicData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún tópico con el ID dado.");
        }
    }

    //UPDATE TOPIC
    @PutMapping("/update")
    @Transactional
    public ResponseEntity updateTopic(@RequestBody @Valid RegistTopicData.UpdateTopicData updateTopicData) {
        Optional<Topic> optionalTopic = topicRepository.findById(updateTopicData.id());

        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topic.updateData(updateTopicData);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            List<AnswerData> answerData = topic.getAnswer().stream().map(ans
                            -> new AnswerData(
                            ans.getId(),
                            ans.getMessage(),
                            ans.getCreationDate().format(formatter),
                            ans.getSolution(),
                            ans.getAuthor().getName(),
                            ans.getAuthor().getProfile(),
                            ans.getTopic().getTitle()
                    )
            ).collect(Collectors.toList());
            AnswerTopicData topicData = new AnswerTopicData(
                    topic.getId(),
                    topic.getTitle(),
                    topic.getMessage(),
                    topic.getCreationDate().format(formatter),
                    topic.getAuthor().getName(),
                    topic.getCourse().getName(),
                    answerData
            );
            return ResponseEntity.ok(topicData);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se encontró un topico con el ID");
        }
    }

    //DELETE TOPIC
    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity deleteTopico(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topicRepository.deleteById(topic.getId());
            return ResponseEntity.ok().body("El topic ha sido eliminado");
        }

        return ResponseEntity.noContent().build();
    }
}
