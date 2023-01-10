package com.projet.spring.jpa.h2.model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    public Tutorial getTutorial() {
        return this.tutorial;
    }

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }

    @ManyToOne
    private Tutorial tutorial;

    public Student() {
        this.tutorial = null;
    }

    public Student(String name) {
        this.name = name;
        this.tutorial = null;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", name=" + name + "]";
    }

}