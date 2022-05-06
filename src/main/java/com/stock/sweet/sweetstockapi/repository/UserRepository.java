package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String Password);

    Optional<User> findByEmail(String username);

    List<User> findAllByLevelAccessAndCompany(String accessLevel, Company company);
    List<User> findAllByCompany_Uuid(String uuid);
    User findUserByCompany_UuidAndId(String uuid,Integer id );
    User deleteUserByCompanyUuidAndId(String uuid,Integer id);

}
