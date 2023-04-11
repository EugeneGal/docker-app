package org.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.StudentDto;
import org.example.repository.Student;
import org.example.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AppController {

    private final StudentRepository studentRepository;

    @PostMapping("save")
    public void saveData(@RequestBody StudentDto request) {
        log.info("Request is received: {}", request);

        Student student = new Student(request.getName(), request.getSurname(), request.getAge());

        Student savedModel = studentRepository.save(student);

        log.info("Student model was saved into MongoDB: {}", savedModel);
    }

    @GetMapping("getAll")
    public List<StudentDto> getAll() {
        log.info("Get all request is received");

        List<Student> students = studentRepository.findAll();

        return students.stream()
            .map(student -> new StudentDto(student.getName(), student.getSurname(), student.getAge()))
            .collect(Collectors.toList());
    }

    @GetMapping("get/{name}")
    public List<StudentDto> getByNamePathVar(@PathVariable String name) {
        log.info("Get by name 'path variable' request is received: name={}", name);

        List<Student> students = studentRepository.findByName(name);

        return students.stream()
            .map(student -> new StudentDto(student.getName(), student.getSurname(), student.getAge()))
            .collect(Collectors.toList());
    }

    @GetMapping("get")
    public List<StudentDto> getByNameRequestParam(@RequestParam String name) {
        log.info("Get by name 'request param' request is received: name={}", name);

        List<Student> students = studentRepository.findByName(name);

        return students.stream()
            .map(student -> new StudentDto(student.getName(), student.getSurname(), student.getAge()))
            .collect(Collectors.toList());
    }

}
