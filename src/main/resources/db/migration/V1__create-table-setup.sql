CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message VARCHAR(100) NOT NULL,
    creation_date DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    answer VARCHAR(200) NOT NULL,

    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    answer_id BIGINT ,

    PRIMARY KEY(id)
);

CREATE TABLE answers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    message VARCHAR(100) NOT NULL,
    topic_id BIGINT NOT NULL,
    creation_date DATETIME,
    author_id BIGINT NOT NULL,
    solution VARCHAR(200),

    PRIMARY KEY(id)
);

CREATE TABLE users(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,

    profile_id BIGINT NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE user_profiles(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE courses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),

    PRIMARY KEY(id)
);

-- Foreign Key Constraints
ALTER TABLE topics
ADD CONSTRAINT fk_topics_author_id FOREIGN KEY(author_id) REFERENCES users(id),
ADD CONSTRAINT fk_topics_course_id FOREIGN KEY(course_id) REFERENCES courses(id),
ADD CONSTRAINT fk_topics_answer_id FOREIGN KEY(answer_id) REFERENCES answers(id);

ALTER TABLE answers
ADD CONSTRAINT fk_answers_topic_id FOREIGN KEY(topic_id) REFERENCES topics(id),
ADD CONSTRAINT fk_answers_author_id FOREIGN KEY(author_id) REFERENCES users(id);

ALTER TABLE users
ADD CONSTRAINT fk_users_profile_id FOREIGN KEY(profile_id) REFERENCES user_profiles(id);
