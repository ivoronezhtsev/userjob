package com.example.userjob.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate created;
    private LocalDate updated;
    private Boolean isActivity;
    @OneToMany
    @JoinColumn(name = "id_company")
    private List<UserJobInfo> userJobInfoList;
}
