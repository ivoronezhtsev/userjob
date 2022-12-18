package com.example.userjob;

import com.example.userjob.data.*;
import com.example.userjob.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static com.example.userjob.dto.CompanyDto.fromEntity;

@Service
public class UserJobService {
    private UserRepository userRepository;
    private UserJobRepository userJobRepository;
    private CompanyRepository companyRepository;

    public UserJobService(@Autowired UserRepository userRepository,
                          @Autowired UserJobRepository userJobRepository,
                          @Autowired CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.userJobRepository = userJobRepository;
        this.companyRepository = companyRepository;
    }

    public void create(UserDto userDto) {
        // Валидация
        if (userDto.getUserJobInfoDto() != null // todo Если getUserId == null ?
                && userRepository.findById(userDto.getUserJobInfoDto().getUserId()).isPresent()
                || userRepository.findById(userDto.getUserJobInfoDto().getIdCompany()).isPresent()) {
            throw new AllreadyPresentException();
        }
        userRepository.save(fromDto(userDto));
        userJobRepository.save(UserJobInfo.fromDto(userDto.getUserJobInfoDto()));
        companyRepository.save(Company.fromDto(userDto.getCompanyDto()));
    }

    public UserJobInfoRequestResponse get(UserJobInfoRequestResponse query) {
        UserJobInfoRequestResponse response = new UserJobInfoRequestResponse();
        if (query.getUserDto() != null && query.getUserDto().getId() != null) {
            userRepository.findById(query.getUserDto().getId()).ifPresent(user ->
                    userJobRepository.findById(user.getId()).ifPresent(userJobInfo -> { //todo У пользователя мб несколько работ и только одна действующая findById
                        if (userJobInfo.getIsActivity()) {
                            companyRepository.findById(userJobInfo.getIdCompany()).ifPresent(company -> {
                                response.setUserDto(UserDto.fromEntity(user));
                                response.setCompanyDto(fromEntity(company));
                            });
                        }
                    }));
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
            if(!UserDto.fromEntity(user).equals(userJobInfoRequestResponse.getUserDto())) {
                user.setUpdated(LocalDateTime.now());
                userRepository.save(user);
                response.setUserDto(UserDto.fromEntity(user));
            }
        }, NotFoundException::new);
        userJobRepository.findById(userJobInfoRequestResponse.getUserJobInfoDto().getId()).ifPresentOrElse(userJobInfo -> {
            if(!UserJobInfoDto.fromEntity(userJobInfo).equals(userJobInfoRequestResponse.getUserJobInfoDto())) {
                userJobInfo.setUpdated(LocalDateTime.now());
                userJobRepository.save(userJobInfo);
                response.setUserJobInfoDto(UserJobInfoDto.fromEntity(userJobInfo));
            }
        }, NotFoundException::new);
        companyRepository.findById(userJobInfoRequestResponse.getCompanyDto().getId()).ifPresentOrElse(company -> {
            company.setUpdated(LocalDateTime.now());
            companyRepository.save(company);
            response.setCompanyDto(fromEntity(company));
        }, NotFoundException::new);
        //todo Не уверен что нужны dto если и нужны то использовать DIP
        return response;
    }




    private static User fromDto(UserDto dto) {
        User user = new User();
        user.setId(dto.getUserJobInfoDto().getUserId());
        user.setFamilyName(dto.getFamilyName());
        user.setMiddleName(dto.getMiddleName());
        user.setFirstName(dto.getFirstName());
        return user;
    }
}
