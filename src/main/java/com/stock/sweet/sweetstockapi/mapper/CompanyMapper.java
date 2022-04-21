package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.CompanyRequest;
import com.stock.sweet.sweetstockapi.dto.response.CompanyResponse;
import com.stock.sweet.sweetstockapi.model.Address;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.model.enums.LevelAccess;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    public Company convertRequestToModel(CompanyRequest companyRequest) {
        return Company
                .builder()
                .uuid(UUID.randomUUID().toString())
                .name(companyRequest.getName())
                .fantansyName(companyRequest.getFantasyName())
                .ceo(companyRequest.getCeo())
                .cnpj(companyRequest.getCnpj())
                .cpf(companyRequest.getCpf())
                .email(companyRequest.getEmail())
                .telephoneNumber(companyRequest.getTelephoneNumber())
                .associateCode(RandomStringUtils.randomAlphanumeric(20))
                .address(
                        Address.builder()
                                .uuid(UUID.randomUUID().toString())
                                .street(companyRequest.getAddressRequest().getStreet())
                                .number(companyRequest.getAddressRequest().getNumber())
                                .city(companyRequest.getAddressRequest().getCity())
                                .complement(companyRequest.getAddressRequest().getComplement())
                                .neighborhood(companyRequest.getAddressRequest().getNeighborhood())
                                .state(companyRequest.getAddressRequest().getState())
                                .build()
                )
                .build();
    }

    public User convertRequestToUserModel(CompanyRequest companyRequest, Integer companyId) {
        return User
                .builder()
                .uuid(UUID.randomUUID().toString())
                .name(companyRequest.getCeo())
                .email(companyRequest.getEmail())
                .levelAccess(LevelAccess.ADMINISTRATOR.name())
                .telephoneNumber(companyRequest.getTelephoneNumber())
                .password(companyRequest.getPassword())
                .company(Company.builder().id(companyId).build())
                .build();
    }

    public CompanyResponse convertModelToResponse(Company company) {
        return new CompanyResponse(
                company.getUuid(),
                company.getName(),
                company.getFantansyName(),
                company.getCeo(),
                company.getCpf(),
                company.getCnpj(),
                company.getEmail(),
                company.getTelephoneNumber()

        );
    }

    public List<CompanyResponse> convertModelListToResponseList(List<Company> company) {
        return company.stream().map(c -> {
            return new CompanyResponse(
                    c.getUuid(),
                    c.getName(),
                    c.getFantansyName(),
                    c.getCeo(),
                    c.getCpf(),
                    c.getCnpj(),
                    c.getEmail(),
                    c.getTelephoneNumber()

            );
        }).collect(Collectors.toList());
    }

}
