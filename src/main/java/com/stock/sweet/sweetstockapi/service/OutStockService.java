package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.OutStock;
import com.stock.sweet.sweetstockapi.repository.OutStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutStockService {
    @Autowired
    private OutStockRepository outStockRepository;

    public List<OutStock> getOutStockCurrentMonth(String idCompany) {
        return outStockRepository.findAllByIdCompany(idCompany)
                .stream()
                .filter(outStock -> outStock.getDate().withDayOfMonth(1).equals(LocalDate.now().withDayOfMonth(1)))
                .collect(Collectors.toList());
    }

    public List<OutStock> getAllOutStockNonExpiredProduct(String idCompany) {
        return outStockRepository.findAllByIdCompany(idCompany)
                .stream()
                .filter(outStock -> !outStock.getIsExpiredProduct())
                .collect(Collectors.toList());
    }

    public Integer allItemsSold(String idCompany){
        Date data = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        Calendar d = Calendar.getInstance();
        d.setTime(data);
        d.add(Calendar.MONTH, 0);
        d.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));


        LocalDate date1 = LocalDate.parse(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
        LocalDate date2 = LocalDate.parse(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));

        return outStockRepository.allItensSold(idCompany, date1, date2);


    }

}
