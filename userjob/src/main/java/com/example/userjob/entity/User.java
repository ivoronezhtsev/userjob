package com.example.userjob.entity;

import com.example.userjob.dto.UserJobInfo;
import com.example.userjob.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "birthday")
    private LocalDate birthDay;
    private String gender;
    private Integer age;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    @Column(name = "is_blocked")
    private Boolean isBlocked; //todo boolean
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<UserJobInfo> userJobInfoList;

    public static User fromDto(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFamilyName(dto.getFamilyName());
        user.setMiddleName(dto.getMiddleName());
        user.setFirstName(dto.getFirstName());
        user.setBirthDay(dto.getBirthday());
        user.setGender(dto.getGender().toString());
        user.setAge(dto.getAge());
        user.setDescription(dto.getDescription());
        user.setUpdated(dto.getUpdated());
        user.setCreated(dto.getCreated());
        user.setIsBlocked(dto.getIsBlocked());
        return user;
    }
}