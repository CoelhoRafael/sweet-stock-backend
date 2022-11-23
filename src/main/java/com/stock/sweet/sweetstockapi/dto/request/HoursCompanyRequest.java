package com.stock.sweet.sweetstockapi.dto.request;


import lombok.Data;

import javax.persistence.Column;
import java.time.LocalTime;

@Data
public class HoursCompanyRequest {

    private LocalTime horaAbertura;
    private LocalTime horaFechar;
    private Boolean sunday;
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday ;
    private Boolean friday;
    private Boolean saturday;

}
