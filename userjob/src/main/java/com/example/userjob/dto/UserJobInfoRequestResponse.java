package com.example.userjob.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJobInfoRequestResponse {
    private UserDto userDto;
    private UserJobInfoDto userJobInfoDto;
    private CompanyDto companyDto;
}
