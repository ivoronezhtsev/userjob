package com.example.userjob.dto;

import com.example.userjob.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String familyName;
    private String middleName;
    private String firstName;
    private LocalDate birthday;
    private Gender gender;
    private Integer age;
    private String description;
    private Boolean isBlocked;
    private LocalDateTime created;
    private LocalDateTime updated;
    private CompanyDto companyDto;
    private UserJobInfoDto userJobInfoDto;

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFamilyName(user.getFamilyName());
        dto.setMiddleName(user.getMiddleName());
        dto.setFirstName(user.getFirstName());
        dto.setBirthday(user.getBirthDay());
        dto.setGender(Gender.fromString(user.getGender()));
        dto.setAge(user.getAge());
        dto.setDescription(user.getDescription());
        dto.setCreated(user.getCreated());
        dto.setUpdated(user.getUpdated());
        dto.setIsBlocked(user.getIsBlocked());
        return dto;
    }
}
