package com.example.userjob.entity;

import com.example.userjob.dto.UserJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJobRepository extends JpaRepository<UserJobInfo, Long> {
}
