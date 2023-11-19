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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    @OneToMany(mappedBy = "customer")
    private List<UserQuery> userQueries;

    @OneToMany(mappedBy = "customer")
    private List<Comment> comments;

}
