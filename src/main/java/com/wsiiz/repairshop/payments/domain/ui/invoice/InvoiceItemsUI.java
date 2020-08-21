package com.wsiiz.repairshop.payments.domain.ui.invoice;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.wsiiz.repairshop.foundation.ui.dialog.ConfirmDialog;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.payments.domain.invoice.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;


public class InvoiceItemsUI implements I18nAware {

    Invoice invoice;
    InvoiceService service;
    InvoiceItemsRepository repository;
    AbstractForm<InvoiceItems> editor;
    MGrid<InvoiceItems> items;
    MButton addNewItem;

    public InvoiceItemsUI(Invoice invoice, UI ui, InvoiceService service, InvoiceItemsRepository repository) {

        this.invoice = invoice;
        this.service = service;
        this.repository = repository;
        this.addNewItem = addNewItem();
        this.items = createItemsGrid();
        loadEntities();

        Window window = new Window(i18n("itemsWindow"));
        window.setWidth("50%");
        window.center();

        window.setContent(new MVerticalLayout(infoText(), addNewItem, items));

        ui.addWindow(window);
    }

    protected RichText infoText() {

        RichText info = new RichText();
        info.setRichText(("Szczegóły faktury FV " + invoice.getId()));

        return info;
    }

    protected MButton addNewItem() {

        MButton addTaskButton = new MButton(i18n("addNewItem")).withIcon(VaadinIcons.PLUS);

        addTaskButton.addClickListener(event -> {
            InvoiceItems entity = new InvoiceItems();

            editor.setEntity(entity);
            editor.focusFirst();
            editor.openInModalPopup();
        });

        return addTaskButton;
    }

    protected MGrid<InvoiceItems> createItemsGrid() {

        MGrid<InvoiceItems> items = new MGrid<>();

        items.addColumn(e -> e.getItem()).setCaption(i18n("item"));
        items.addColumn(e -> e.getPrice()).setCaption(i18n("price"));

        items.addComponentColumn(entity -> new MHorizontalLayout(

                new MButton(VaadinIcons.EDIT, e -> {
                    editInPopup(entity);
                }).withStyleName(ValoTheme.BUTTON_BORDERLESS).withStyleName("no-padding")
        ))
                .setCaption(i18n("actions"))
                .setWidth(120);

        items.setWidthFull();

        return items;
    }

    protected void editInPopup(InvoiceItems entity) {
        editor.setEntity(entity);
        editor.openInModalPopup();
    }

    protected void loadEntities() {
        items.setRows(service.getInvoiceItems(invoice)); //not sure
    }
}




