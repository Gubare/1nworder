package com.example.Schedule.models;

import com.example.Schedule.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "Subject")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "timeStart")
    private LocalDateTime timeStart;
    @Column(name = "timeEnd")
    private LocalDateTime timeEnd;
    @Column(name = "data")
    private Date data;
    @Column(name = "additional_subject")
    private boolean additional_subject;
    @Column(name = "auditorium")
    private Long auditorium;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "subject")
    private List<Comment> comment = new ArrayList<>();
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "subject")
    private List<Note> note = new ArrayList<>();
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "subject")
    private List<Notification> notification = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private TableSchedule tableSchedule;
}