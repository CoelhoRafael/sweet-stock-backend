package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.request.ProviderRequest;
import com.stock.sweet.sweetstockapi.dto.response.ProviderResponse;
import com.stock.sweet.sweetstockapi.mapper.ProviderMapper;
import com.stock.sweet.sweetstockapi.service.ProviderService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class ProviderController {

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private HeadersUtils headersUtils;

    @PostMapping
    public ResponseEntity createProvider(@RequestBody ProviderRequest body, @RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var companyId = headersUtils.getCompanyIdFromToken(headers);
        try {
            providerMapper.convertModelToResponse(
                    providerService.createProvider(providerMapper.convertRequestToModel(body, companyId))
            );
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProviderResponse> getAllProviders() {
        return providerMapper.convertModelListToResponseList(
                providerService.getAllProviders()
        );
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProviderResponse getProviderByUuid(@PathVariable String uuid) throws Exception {
        return providerMapper.convertModelToResponse(
                providerService.findProviderByUuid(uuid)
        );
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProviderResponse updateProvider(@PathVariable String uuid, @RequestBody ProviderRequest body) throws Exception {
        return providerMapper.convertModelToResponse(
                providerService.updateProvider(uuid, body)
        );
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProviderResponse deleteProvider(@PathVariable String uuid) throws Exception {
        return providerMapper.convertModelToResponse(
                providerService.deleteProvider(uuid)
        );
    }
}
