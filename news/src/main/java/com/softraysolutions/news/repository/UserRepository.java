package com.softraysolutions.news.repository;

import com.softraysolutions.news.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
