package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.ProviderRequest;
import com.stock.sweet.sweetstockapi.dto.request.UserRequest;
import com.stock.sweet.sweetstockapi.model.*;
import com.stock.sweet.sweetstockapi.model.enums.LevelAccess;
import com.stock.sweet.sweetstockapi.repository.CompanyRepository;
import com.stock.sweet.sweetstockapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return user.get().getUuid();
        }
        return null;
    }

    public User createUser(User user, String associateCode) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var company = companyRepository.findByAssociateCode(associateCode).orElse(null);
        if (company != null) {
            user.setCompany(Company.builder().id(company.getId()).build());
            var userSaved = userRepository.save(user);
            emailService.sendEmail(
                    new Email(
                            "sweet-stock",
                            "abnerlucasdarochasantos@gmail.com",
                            company.getEmail(),
                            String.format("Funcionário %s está aguardando aprovação para acessar o sistema.", user.getName()),
                            String.format("Acesse o sistema para aprovar ou desaprovar a entrada de %s.", user.getName()),
                            LocalDateTime.now()
                    )
            );
        }
        return null;
    }


    public List<User>getUserByUuid(String uuid){
        List<User> users = userRepository.findAllByCompany_Uuid(uuid);

        return users;
    }

    public User deleteUserByUuidAndId(String uuid, Integer id){
       return userRepository.deleteUserByCompanyUuidAndId(uuid, id);
    }
    public User getUserByUuidAndId(String uuid, Integer id){
        return userRepository.findUserByCompany_UuidAndId(uuid, id);
    }

    public User updateUser(String uuid, Integer id, UserRequest userRequest) throws Exception {
        User userToUpdate = getUserByUuidAndId(uuid, id);

        if (userToUpdate == null) {
            return null;
        }
        userToUpdate.setName(userRequest.getName());
        userToUpdate.setEmail(userRequest.getEmail());
        userToUpdate.setPassword(userRequest.getPassword());
        userToUpdate.setLevelAccess(String.valueOf(userRequest.getLevelAccess()));
        userToUpdate.setTelephoneNumber(userRequest.getTelephoneNumber());

      userRepository.save(userToUpdate);

        return userToUpdate;
    }


    public List<User> getAllWaitingApproval(String uuidCompany) {
        var company = companyRepository.findByUuid(uuidCompany).orElse(null);

        if (company != null) {
            return userRepository.findAllByLevelAccessAndCompany(LevelAccess.EMPLOYEE_NOT_VERIFIED.name(), company);
        }
        return new ArrayList<>();
    }
}