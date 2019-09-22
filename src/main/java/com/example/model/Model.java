package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_generator")
    @SequenceGenerator(name = "model_generator", sequenceName = "model_seq")
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(name = "works_fine")
    private String worksFine;

    @Column(updatable = false)
    private String incorrect;

    public Long getId() {
        return id;
    }

    public String getWorksFine() {
        return worksFine;
    }

    public void setWorksFine(String worksFine) {
        this.worksFine = worksFine;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(String incorrect) {
        this.incorrect = incorrect;
    }
}
