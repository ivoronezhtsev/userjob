package com.example.userjob.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
    private LocalDate created;
    private LocalDate updated;
    @Column(name = "is_blocked")
    private Boolean isBlocked; //todo boolean
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<UserJobInfo> userJobInfoList;
}