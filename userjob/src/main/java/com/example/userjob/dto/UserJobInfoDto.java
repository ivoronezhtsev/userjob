package com.example.userjob.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserJobInfoDto {
    private Long id;
    private Long userId;
    private Long idCompany;
    private String description;
    private Boolean isActivity;
    private LocalDateTime created;
    private LocalDateTime updated;

    public static UserJobInfoDto fromEntity(UserJobInfo userJobInfo) {
        UserJobInfoDto dto = new UserJobInfoDto();
        dto.setId(userJobInfo.getId());
        dto.setIdCompany(userJobInfo.getIdCompany());
        dto.setIsActivity(userJobInfo.getIsActivity());
        dto.setDescription(userJobInfo.getDescription());
        dto.setUpdated(userJobInfo.getUpdated());
        dto.setUpdated(userJobInfo.getCreated());
        return dto;
    }
}
