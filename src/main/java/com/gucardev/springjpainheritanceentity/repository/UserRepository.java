package com.gucardev.springjpainheritanceentity.repository;

import com.gucardev.springjpainheritanceentity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
