package com.project.preferences.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "favorite")
@Data
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String favoriteDish;

    @Column(nullable = false)
    private String favoriteColorHex;

    @Column(nullable = false)
    private String favoriteSong;

    @Column(nullable = false)
    private LocalDate favoriteDate;

    @Column(nullable = false)
    private Integer favoriteNumber;


}