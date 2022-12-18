package com.example.userjob.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyService extends JpaRepository<Company, Long> {
}
