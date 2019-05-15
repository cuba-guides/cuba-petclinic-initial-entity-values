package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component(VisitNumberGenerator.NAME)
public class VisitNumberGenerator {

    static final String NAME = "petclinic_VisitNumberGenerator";

    @Inject
    UniqueNumbersService uniqueNumbersService;


    public String generateVisitNumber(Visit entity) {
        int visitType = entity.getType().getCode();
        int visitYear = localDate(entity.getVisitDate()).getYear();
        long nextVisitNumber = uniqueNumbersService.getNextNumber(String.format("VISIT_%d_%d", visitYear, visitType));

        return String.format("%4d%02d%06d", visitYear, visitType, nextVisitNumber);
    }

    private LocalDate localDate(Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return LocalDate.from(date.toInstant().atZone(ZoneId.systemDefault()));
        }
    }


}
