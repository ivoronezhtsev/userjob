package com.example.userjob.dto;

import com.example.userjob.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_job_info")
@Getter
@Setter
@NoArgsConstructor
public class UserJobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "id_company", insertable = false, updatable = false)
    private Long idCompany;
    private String description;
    @Column(name = "is_activity")
    private Boolean isActivity;
    private LocalDateTime created;
    private LocalDateTime updated;
    @ManyToOne
    @JoinColumn(name = "id_company", referencedColumnName = "id")
    private Company company;

    public static UserJobInfo fromDto(UserJobInfoDto dto) {
        UserJobInfo userJobInfo = new UserJobInfo();
        userJobInfo.setUserId(dto.getUserId());
        userJobInfo.setIdCompany(dto.getIdCompany());
        userJobInfo.setIsActivity(dto.getIsActivity());
        userJobInfo.setCreated(LocalDateTime.now());
        return userJobInfo;
    }

}
