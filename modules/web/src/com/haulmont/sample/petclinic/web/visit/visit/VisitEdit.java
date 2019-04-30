package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitType;

import javax.inject.Inject;


@UiController("petclinic_Visit.edit")
@UiDescriptor("visit-edit.xml")
@EditedEntityContainer("visitDc")
@LoadDataBeforeShow
@PrimaryEditorScreen(Visit.class)
public class VisitEdit extends StandardEditor<Visit> {

    @Subscribe(id = "visitDc", target = Target.DATA_CONTAINER)
    protected void onVisitDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Visit> event) {

        if (visitTypeChanged(event)) {
            updateHasToBePayedUpfrontValue(event);
        }
    }

    private boolean visitTypeChanged(InstanceContainer.ItemPropertyChangeEvent<Visit> event) {
        return event.getProperty().equals("type");
    }

    private void updateHasToBePayedUpfrontValue(InstanceContainer.ItemPropertyChangeEvent<Visit> event) {
        VisitType selectedVisitType = (VisitType) event.getValue();

        if (selectedVisitType != null) {
            event.getItem().setPaid(selectedVisitType.isToBePayedUpfront());
        }
    }

}