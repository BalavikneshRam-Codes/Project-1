package com.busoft.project1.repo;

import com.busoft.project1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUserEmail(String userName);
    User findByUserEmailAndStatus(String userName,String status);
}
