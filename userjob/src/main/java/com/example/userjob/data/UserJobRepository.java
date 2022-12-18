package com.example.userjob.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJobRepository extends JpaRepository<UserJobInfo, Long> {
}
