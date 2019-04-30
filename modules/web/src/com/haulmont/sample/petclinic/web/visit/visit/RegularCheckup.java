package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitType;


@UiController("petclinic_Visit.regularCheckup")
@UiDescriptor("regular-checkup.xml")
@EditedEntityContainer("visitDc")
@LoadDataBeforeShow
public class RegularCheckup extends StandardEditor<Visit> {

    @Subscribe
    protected void initRegularCheckupVisit(InitEntityEvent<Visit> event) {
        Visit visit = event.getEntity();
        visit.setType(VisitType.REGULAR_CHECKUP);
        visit.setPaid(true);
    }

}