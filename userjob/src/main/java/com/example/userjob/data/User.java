package com.example.userjob.data;

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
}