package com.jubydull.pt.projecttask.repository;

import com.jubydull.pt.projecttask.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String username);

}