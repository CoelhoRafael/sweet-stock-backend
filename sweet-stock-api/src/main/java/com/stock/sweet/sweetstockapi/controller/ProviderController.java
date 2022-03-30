package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.ProviderRequest;
import com.stock.sweet.sweetstockapi.dto.response.ProviderResponse;
import com.stock.sweet.sweetstockapi.mapper.ProviderMapper;
import com.stock.sweet.sweetstockapi.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private ProviderService providerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderResponse createProvider(@RequestBody ProviderRequest body) {
        return providerMapper.convertModelToResponse(
                providerService.createProvider(providerMapper.convertRequestToModel(body))
        );
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
