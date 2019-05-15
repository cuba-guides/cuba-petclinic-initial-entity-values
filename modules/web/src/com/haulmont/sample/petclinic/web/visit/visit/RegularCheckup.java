package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitType;

import javax.inject.Inject;


@UiController("petclinic_Visit.regularCheckup")
@UiDescriptor("regular-checkup.xml")
@EditedEntityContainer("visitDc")
@LoadDataBeforeShow
public class RegularCheckup extends StandardEditor<Visit> {

    @Inject
    private VisitNumberGenerator visitNumberGenerator;

    @Subscribe
    protected void initRegularCheckupVisit(InitEntityEvent<Visit> event) {
        Visit visit = event.getEntity();
        visit.setType(VisitType.REGULAR_CHECKUP);
        visit.setPaid(true);
    }


    @Subscribe(target = Target.DATA_CONTEXT)
    protected void onPreCommit(DataContext.PreCommitEvent event) {
        Visit visit = getEditedEntity();
        visit.setVisitNumber(visitNumberGenerator.generateVisitNumber(visit));
    }

}