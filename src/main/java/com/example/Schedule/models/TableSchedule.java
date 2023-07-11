package com.example.Schedule.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "libraryTableSchedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "tableSchedule")
    private List<Subject> subject = new ArrayList<>();


}

