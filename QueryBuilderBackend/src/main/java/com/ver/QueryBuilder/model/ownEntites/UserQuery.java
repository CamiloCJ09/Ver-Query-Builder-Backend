package com.ver.QueryBuilder.model.ownEntites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String queryname;

    private String comment;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "userQuery")
    private List<Comment> comments;

    @Column(length = 100000)
    private String query;

    private String countryCode;

    private String seriesCode;

    private int year;

    private String value;

}
