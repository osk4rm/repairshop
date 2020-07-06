package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.ui.Component;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.domain.service.Service;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class ServiceEditor extends AbstractForm<Service> implements I18nAware {

  private TextArea description = new TextArea(i18n("description"));
  private DateTimeField registrationTime = new DateTimeField(i18n("registrationTime"));

  public ServiceEditor() {
    super(Service.class);
  }

  @Override
  protected Component createContent() {

    setSaveCaption(i18n("save"));
    setModalWindowTitle(i18n("title"));

    return new MVerticalLayout(
        new MHorizontalLayout(
            new MVerticalLayout(description, registrationTime),
            new MVerticalLayout())
            .withFullWidth(),
        getToolbar())
        .withStyleName("with-small-frame");
  }

  @Override
  public Window openInModalPopup() {
    Window w = super.openInModalPopup();
    w.setHeight("95%");
    w.setWidth("70%");
    return w;
  }

}
