package com.example.footballm.repo.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@Table (name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer commission;
    @Column(nullable = false)
    private Long money;
}
