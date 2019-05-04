package com.haulmont.sample.petclinic.entity.visit;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.sample.petclinic.entity.pet.Pet;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamePattern("%s (%s)|pet,visitDate")
@Table(name = "PETCLINIC_VISIT")
@Entity(name = "petclinic_Visit")
@PublishEntityChangedEvents
public class Visit extends StandardEntity {
    private static final long serialVersionUID = 6351202390461847589L;

    @Column(name = "VISIT_NUMBER")
    protected String visitNumber;

    @Column(name = "TYPE_")
    protected String type;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "VISIT_DATE", nullable = false)
    protected Date visitDate;

    @Column(name = "DESCRIPTION", length = 4000)
    protected String description;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PET_ID")
    protected Pet pet;

    @Column(name = "PAID")
    protected Boolean paid = false;

    public VisitType getType() {
        return type == null ? null : VisitType.fromId(type);
    }

    public void setType(VisitType type) {
        this.type = type == null ? null : type.getId();
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }


    @PostConstruct
    private void initVisitDate() {
        if (visitDate == null) {
            setVisitDate(today());
        }
    }

    private Date today() {
        TimeSource timeSource = AppBeans.get(TimeSource.class);
        return timeSource.currentTimestamp();
    }
}