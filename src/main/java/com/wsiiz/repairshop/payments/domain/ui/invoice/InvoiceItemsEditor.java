package com.wsiiz.repairshop.payments.domain.ui.invoice;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.payments.domain.invoice.Invoice;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceItems;
import com.wsiiz.repairshop.payments.domain.invoice.InvoiceService;
import com.wsiiz.repairshop.servicing.domain.service.Employee;
import com.wsiiz.repairshop.servicing.domain.service.ServiceService;
import com.wsiiz.repairshop.servicing.domain.service.Task;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class InvoiceItemsEditor extends AbstractForm<InvoiceItems> implements I18nAware {

    InvoiceService service;
    TextArea item = new TextArea(i18n("item"));
    IntegerField price = new IntegerField(i18n("price"));

    public InvoiceItemsEditor(InvoiceService service) {
      super(InvoiceItems.class);
      this.service = service;
    }

    @Override
    protected Component createContent() {

        setSaveCaption(i18n("save"));
        setModalWindowTitle(i18n("title"));


        return new MVerticalLayout(
                new MHorizontalLayout(
                        new MVerticalLayout(item, price))
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
