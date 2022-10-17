package com.stock.sweet.sweetstockapi.dto.response.app.home;

import com.stock.sweet.sweetstockapi.dto.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeResponse {
    private List<CategoryResponse> categories;
    private List<CompanyHomeResponse> companies;
}
