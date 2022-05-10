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

    User deleteUserByCompanyUuidAndId(String uuid,Integer id);

    @Query("select u from User u inner join Company c where c.uuid = ?1 and u.aproved = false")
    List<User> findAllEmployeeAproved(String uuid);

    @Query("select u from User u inner join Company c where c.uuid = ?1")
    List<User> xxx(String uuid);

    @Transactional
    @Modifying
    @Query("update User u set u.aproved = true where (select c.uuid from Company c where c.uuid = ?1) = ?1 and u.uuid in (?2)")
    void toApproveEmployees(String uuid, List<String> employeesToApprove);
}
