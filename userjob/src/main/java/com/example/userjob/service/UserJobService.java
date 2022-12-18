package com.example.userjob.service;

import com.example.userjob.exception.AlreadyPresentException;
import com.example.userjob.repository.*;
import com.example.userjob.dto.*;
import com.example.userjob.entity.Company;
import com.example.userjob.entity.User;
import com.example.userjob.entity.UserJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static com.example.userjob.dto.CompanyDto.fromEntity;

@Service
public class UserJobService {
    private final UserRepository userRepository;
    private final UserJobRepository userJobRepository;
    private final CompanyRepository companyRepository;

    public UserJobService(@Autowired UserRepository userRepository,
                          @Autowired UserJobRepository userJobRepository,
                          @Autowired CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.userJobRepository = userJobRepository;
        this.companyRepository = companyRepository;
    }

    public void create(UserDto userDto) {
        if (userDto.getUserJobInfoDto() != null
                && userDto.getUserJobInfoDto().getUserId() != null
                && userRepository.findById(userDto.getUserJobInfoDto().getUserId()).isPresent()
                || (userDto.getUserJobInfoDto().getIdCompany() != null
                && userRepository.findById(userDto.getUserJobInfoDto().getIdCompany()).isPresent())) {
            throw new AlreadyPresentException();
        }
        User user = userRepository.save(User.fromDto(userDto));
        UserJobInfo userJobInfo = UserJobInfo.fromDto(userDto.getUserJobInfoDto());
        userJobInfo.setUserId(user.getId());
        Company company = companyRepository.save(Company.fromDto(userDto.getCompanyDto()));
        userJobInfo.setCompany(company);
        userJobRepository.save(userJobInfo);
    }

    public UserJobInfoRequestResponse get(UserJobInfoRequestResponse query) {
        UserJobInfoRequestResponse response = new UserJobInfoRequestResponse();
        if (query.getUserDto() != null && query.getUserDto().getId() != null) {
            userRepository.findById(query.getUserDto().getId()).ifPresentOrElse(user -> {
                user.getUserJobInfoList().stream().filter(UserJobInfo::getIsActivity).findFirst()
                        .ifPresentOrElse(userJobInfo -> {
                            response.setUserDto(UserDto.fromEntity(user));
                            response.setCompanyDto(fromEntity(userJobInfo.getCompany()));
                        }, NoSuchElementException::new);
            }, NoSuchElementException::new);

        } else if (query.getCompanyDto() != null && query.getCompanyDto().getId() != null) {
            companyRepository.findById(query.getCompanyDto().getId()).ifPresent(company -> response.setCompanyDto(fromEntity(company)));
        }
        //todo Пользователь не найден
        return response;
    }

    public UserJobInfoRequestResponse update(UserJobInfoRequestResponse userJobInfoRequestResponse) {
        UserJobInfoRequestResponse response = new UserJobInfoRequestResponse(
                userJobInfoRequestResponse.getUserDto(),
                userJobInfoRequestResponse.getUserJobInfoDto(),
                userJobInfoRequestResponse.getCompanyDto());
        userRepository.findById(userJobInfoRequestResponse.getUserDto().getId()).ifPresentOrElse(user -> {
            if (!UserDto.fromEntity(user).equals(userJobInfoRequestResponse.getUserDto())) {
                user.setUpdated(LocalDateTime.now());
                userRepository.save(user);
                response.setUserDto(UserDto.fromEntity(user));
            }
        }, NoSuchElementException::new);
        userJobRepository.findById(userJobInfoRequestResponse.getUserJobInfoDto().getId()).ifPresentOrElse(userJobInfo -> {
            if (!UserJobInfoDto.fromEntity(userJobInfo).equals(userJobInfoRequestResponse.getUserJobInfoDto())) {
                userJobInfo.setUpdated(LocalDateTime.now());
                userJobRepository.save(userJobInfo);
                response.setUserJobInfoDto(UserJobInfoDto.fromEntity(userJobInfo));
            }
        }, NoSuchElementException::new);
        companyRepository.findById(userJobInfoRequestResponse.getCompanyDto().getId()).ifPresentOrElse(company -> {
            company.setUpdated(LocalDateTime.now());
            companyRepository.save(company);
            response.setCompanyDto(fromEntity(company));
        }, NoSuchElementException::new);
        return response;
    }
}
