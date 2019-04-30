package com.haulmont.sample.petclinic.core;

import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component(VisitNumberGenerator.NAME)
public class VisitNumberGenerator {
    static final String NAME = "petclinic_VisitNumberGenerator";

    @Inject
    protected UniqueNumbersAPI uniqueNumbersAPI;

    @Inject
    private TransactionalDataManager transactionalDataManager;

    @EventListener
    private void beforeCommit(EntityChangedEvent<Visit, UUID> event) {

        if (visitNumberNeedsToBeCalculated(event)) {

            Visit visit = transactionalDataManager.load(event.getEntityId()).one();

            visit.setVisitNumber(generateVisitNumber(visit));

            transactionalDataManager.save(visit);
        }
    }

    private boolean visitNumberNeedsToBeCalculated(EntityChangedEvent<Visit, UUID> event) {
        return event.getType().equals(EntityChangedEvent.Type.CREATED);
    }

    private String generateVisitNumber(Visit entity) {
        int visitType = entity.getType().getCode();
        int visitYear = localDate(entity.getVisitDate()).getYear();
        long nextVisitNumber = uniqueNumbersAPI.getNextNumber(String.format("VISIT_%d_%d", visitYear, visitType));

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