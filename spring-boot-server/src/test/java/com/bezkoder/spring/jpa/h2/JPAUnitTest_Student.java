package com.bezkoder.spring.jpa.h2;

import com.bezkoder.spring.jpa.h2.model.Student;
import com.bezkoder.spring.jpa.h2.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@SpringBootTest
public class JPAUnitTest_Student {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    StudentRepository repository;

    @Test
    public void should_find_no_students_if_repository_is_empty() {
        Iterable students = repository.findAll();

        assertThat(students).isEmpty();
    }

    @Test
    public void should_store_a_student() {
        Student student = repository.save(new Student("Tut title"));

        assertThat(student).hasFieldOrPropertyWithValue("name", "Tut title");
    }

    @Test
    public void should_find_all_students() {
        Student tut1 = new Student("Tut#1");
        entityManager.persist(tut1);

        Student tut2 = new Student("Tut#2");
        entityManager.persist(tut2);

        Student tut3 = new Student("Tut#3");
        entityManager.persist(tut3);

        Iterable students = repository.findAll();

        assertThat(students).hasSize(3).contains(tut1, tut2, tut3);
    }

    @Test
    public void should_find_student_by_id() {
        Student tut1 = new Student("Tut#1");
        entityManager.persist(tut1);

        Student tut2 = new Student("Tut#2");
        entityManager.persist(tut2);

        Student foundStudent = repository.findById(tut2.getId()).get();

        assertThat(foundStudent).isEqualTo(tut2);
    }

    @Test
    public void should_find_students_by_name_containing_string() {
        Student tut1 = new Student("Spring Boot Tut#1");
        entityManager.persist(tut1);

        Student tut2 = new Student("Java Tut#2");
        entityManager.persist(tut2);

        Student tut3 = new Student("Spring Data JPA Tut#3");
        entityManager.persist(tut3);

        Iterable students = repository.findByNameContaining("ring");

        assertThat(students).hasSize(2).contains(tut1, tut3);
    }

    @Test
    public void should_update_student_by_id() {
        Student tut1 = new Student("Tut#1");
        entityManager.persist(tut1);

        Student tut2 = new Student("Tut#2");
        entityManager.persist(tut2);

        Student updatedTut = new Student("updated Tut#2");

        Student tut = repository.findById(tut2.getId()).get();
        tut.setName(updatedTut.getName());
        repository.save(tut);

        Student checkTut = repository.findById(tut2.getId()).get();

        assertThat(checkTut.getId()).isEqualTo(tut2.getId());
        assertThat(checkTut.getName()).isEqualTo(updatedTut.getName());
    }

    @Test
    public void should_delete_student_by_id() {
        Student tut1 = new Student("Tut#1");
        entityManager.persist(tut1);

        Student tut2 = new Student("Tut#2");
        entityManager.persist(tut2);

        Student tut3 = new Student("Tut#3");
        entityManager.persist(tut3);

        repository.deleteById(tut2.getId());

        Iterable students = repository.findAll();

        assertThat(students).hasSize(2).contains(tut1, tut3);
    }

    @Test
    public void should_delete_all_students() {
        entityManager.persist(new Student("Tut#1"));
        entityManager.persist(new Student("Tut#2"));

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }
}