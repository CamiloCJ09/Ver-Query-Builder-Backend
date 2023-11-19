package com.ver.QueryBuilder.repository;

import com.ver.QueryBuilder.model.ownEntites.UserQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserQueryRepository extends JpaRepository<UserQuery, UUID> {


}
