package com.client.api.rasmooplus.repositoy.jpa;

import com.client.api.rasmooplus.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
