package com.jubydull.pt.projecttask.repository;

import com.jubydull.pt.projecttask.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
