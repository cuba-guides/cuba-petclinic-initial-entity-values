package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.visit.Visit;


@UiController("petclinic_Visit.create")
@UiDescriptor("visit-create.xml")
@EditedEntityContainer("visitDc")
@LoadDataBeforeShow
public class VisitCreate extends StandardEditor<Visit> {

}