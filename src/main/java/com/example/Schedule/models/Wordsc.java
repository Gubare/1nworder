package com.example.Schedule.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Words3")
@Data

public class Wordsc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "word")
    private String word;
    @Column(name = "transcription")
    private String transcription;
    @Column(name = "translate")
    private String translate;
    @Column(name = "translate2")
    private String translate2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getTranslate2() {
        return translate2;
    }

    public void setTranslate2(String translate2) {
        this.translate2 = translate2;
    }

    public Wordsc() {
    }

    public Wordsc(String word, String transcription, String translate, String translate2) {
        this.word = word;
        this.transcription = transcription;
        this.translate = translate;
        this.translate2 = translate2;
    }

}
