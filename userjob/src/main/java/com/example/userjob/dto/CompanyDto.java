package com.example.userjob.dto;

import com.example.userjob.data.Company;
import lombok.Data;

@Data
public class CompanyDto {
    private Long id;
    private String companyName;
    private String description;
    private Boolean isActivity;

    public static CompanyDto fromEntity(Company company) {
        CompanyDto dto = new CompanyDto();
        dto.setId(company.getId());
        dto.setCompanyName(company.getCompanyName());
        dto.setDescription(company.getDescription());
        dto.setIsActivity(company.getIsActivity());
        return dto;
    }
}
