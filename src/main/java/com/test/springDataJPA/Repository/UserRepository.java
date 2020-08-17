package com.test.springDataJPA.Repository;

import com.test.springDataJPA.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUserLike(String user);
}
