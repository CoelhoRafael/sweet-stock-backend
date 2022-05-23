package com.stock.sweet.sweetstockapi.repository;

import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String Password);

    Optional<User> findByEmail(String username);

    List<User> findAllByLevelAccessAndCompany(String accessLevel, Company company);

    List<User> findAllByCompany_Uuid(String uuid);

    User findUserByUuid(String uuid);

    User deleteUserByCompanyUuidAndId(String uuid, Integer id);

    @Query("select u from User u inner join Company c where c.uuid = ?1 and u.aproved = false")
    List<User> findAllEmployeeAproved(String uuid);

    @Query("select u from User u inner join Company c where c.uuid = ?1")
    List<User> xxx(String uuid);


    List<User> findAllByAprovedIsTrueAndLevelAccessAndCompanyUuid(String levelAccess, String uuid);

    List<User> findAllByAprovedIsFalseAndLevelAccessAndCompanyUuid(String levelAccess, String uuid);

    @Transactional
    @Modifying
    @Query("update User u set u.aproved = true, u.levelAccess = 'EMPLOYEE' where u.uuid in ?1")
    void toApproveEmployees(List<String> listUuidsToAprove);

    @Transactional
    @Modifying
    @Query("update User u set u.picture = ?1 where u.email = ?2")
    void updatePicture(String picture, String userEmail);

}
