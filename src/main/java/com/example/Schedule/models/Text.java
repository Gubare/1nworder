package com.example.Schedule.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Texts")
@Data

public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(length = 2550, name = "text")
    private String text;
    @Column(length = 2550, name = "translate")
    private String translate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public Text() {
    }

    public Text(String text, String translate) {
        this.text = text;
        this.translate = translate;
    }
}
