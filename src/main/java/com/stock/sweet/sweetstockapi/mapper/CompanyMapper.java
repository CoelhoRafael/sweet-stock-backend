package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.CompanyRequest;
import com.stock.sweet.sweetstockapi.dto.request.HoursCompanyRequest;
import com.stock.sweet.sweetstockapi.dto.response.CompanyResponse;
import com.stock.sweet.sweetstockapi.dto.response.HoursCompanyResponse;
import com.stock.sweet.sweetstockapi.dto.response.address.AddressAppResponse;
import com.stock.sweet.sweetstockapi.model.Address;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.HoursCompany;
import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.model.enums.LevelAccess;
import com.stock.sweet.sweetstockapi.service.CompanyService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    @Autowired
   private CompanyService companyService;

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
                .picture(companyRequest.getPicture())
                .activated(companyRequest.isActivated())
                .hoursCompany(
                        HoursCompany.builder()
                                .horaAbertura(companyRequest.getHoursCompany().getHoraAbertura())
                                .horaFechar(companyRequest.getHoursCompany().getHoraFechar())
                                .sunday(companyRequest.getHoursCompany().getSunday())
                                .monday(companyRequest.getHoursCompany().getMonday())
                                .tuesday(companyRequest.getHoursCompany().getTuesday())
                                .wednesday(companyRequest.getHoursCompany().getWednesday())
                                .thursday(companyRequest.getHoursCompany().getThursday())
                                .friday(companyRequest.getHoursCompany().getFriday())
                                .saturday(companyRequest.getHoursCompany().getSaturday())
                                .build()
                )
                .isOpen(null)

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
                .aproved(true)
                .picture(companyRequest.getPicture())
                .activated(true)
                .build();
    }

    public CompanyResponse convertModelToResponse(Company company) throws Exception {
        return new CompanyResponse(
                company.getUuid(),
                company.getName(),
                company.getFantansyName(),
                company.getCeo(),
                company.getCpf(),
                company.getCnpj(),
                company.getEmail(),
                company.getTelephoneNumber(),
                company.getPicture(),
                new AddressAppResponse(
                        company.getAddress().getUuid(),
                        company.getAddress().getStreet(),
                        company.getAddress().getNeighborhood(),
                        company.getAddress().getNumber()
                ),
                company.isActivated(),
                companyService.findCompanyIsOpen(company.getUuid())


        );
    }

    public List<CompanyResponse> convertModelListToResponseList(List<Company> company) {
        return company.stream().map(c -> {
            try {
                return new CompanyResponse(
                        c.getUuid(),
                        c.getName(),
                        c.getFantansyName(),
                        c.getCeo(),
                        c.getCpf(),
                        c.getCnpj(),
                        c.getEmail(),
                        c.getTelephoneNumber(),
                        c.getPicture(),
                        new AddressAppResponse(
                                c.getAddress().getUuid(),
                                c.getAddress().getStreet(),
                                c.getAddress().getNeighborhood(),
                                c.getAddress().getNumber()
                        ),
                        c.isActivated(),
                        companyService.findCompanyIsOpen(c.getUuid())

                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

}
