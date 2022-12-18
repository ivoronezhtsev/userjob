package com.example.userjob.entity;

import com.example.userjob.repository.UserJobInfo;
import com.example.userjob.dto.CompanyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String companyName;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean isActivity;
    @OneToMany
    @JoinColumn(name = "id_company")
    private List<UserJobInfo> userJobInfoList;

    public static Company fromDto(CompanyDto dto) {
        Company company = new Company();
        company.setCompanyName(dto.getCompanyName());
        company.setDescription(dto.getDescription());
        company.setCreated(LocalDateTime.now());
        company.setIsActivity(dto.getIsActivity());
        return company;
    }
}
