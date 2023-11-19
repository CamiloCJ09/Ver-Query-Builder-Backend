package com.ver.QueryBuilder.model.ownEntites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String queryname;

    private String comment;

    @ManyToOne
    private Costumer costumer;

    @OneToMany(mappedBy = "userQuery")
    private List<Comment> comments;

    private String query;

}
