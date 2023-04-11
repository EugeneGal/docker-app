package org.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findByName(String name);

}
