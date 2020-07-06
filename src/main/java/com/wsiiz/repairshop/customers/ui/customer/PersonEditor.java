package com.wsiiz.repairshop.customers.ui.customer;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.wsiiz.repairshop.customers.domain.customer.Person;
import com.wsiiz.repairshop.customers.domain.customer.Sex;
import com.wsiiz.repairshop.foundation.ui.i18n.BaseEditor;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.time.LocalDate;

public class PersonEditor extends BaseEditor<Person> {

    private static final int PESEL_LENGTH = 11;

    private VerticalLayout container;
    private TextField name;
    private TextField surname;
    private DateField birthDate;
    private TextField pesel;
    private ComboBox<Sex> sex;

    public PersonEditor() {
        super(Person.class);
    }

    @Override
    protected Component createContent() {

        Design.read("PersonEditor.html", this);

        setSaveCaption(i18n("save"));
        setModalWindowTitle(i18n("title"));

        sex.setItems(Sex.values());
        sex.setItemCaptionGenerator(e -> i18n(Sex.class, e.name()));

        getBinder().forField(pesel)
                .withValidator(this::peselValidator)
                .bind("pesel");

        pesel.addValueChangeListener(
                event -> {
                    String pesel = event.getValue();
                    if (!peselValidator(pesel, null).isError()) {
                        birthDate.setValue(computeDateFromPesel(event.getValue()));
                        sex.setValue(Integer.parseInt(pesel.substring(9, 10)) % 2 == 0 ? Sex.FEMALE : Sex.MALE);
                    }
                }
        );

        setCaptions();
        container.addComponent(getToolbar());

        return getCompositionRoot();
    }

    private ValidationResult peselValidator(String value, ValueContext valueContext) {
        {
            if (value == null) {
                return ValidationResult.ok();
            }

            if (value.length() != PESEL_LENGTH) {
                return ValidationResult.error(i18n("badPeselLength"));
            }

            if (!StringUtils.isNumeric(value)) {
                return ValidationResult.error(i18n("badPeselLength"));
            }

            if (computeDateFromPesel(value) == null) {
                return ValidationResult.error(i18n("badPeselDate"));
            }

            //TODO DALSZA WALIDACJA NUMERU PESEL
            return ValidationResult.ok();
        }
    }

    private LocalDate computeDateFromPesel(String pesel) {
        int month = Integer.parseInt(pesel.substring(2, 4));
        int finalMonth = month > 20 ? month - 20 : month;
        int year = Integer.parseInt(pesel.substring(0, 2));
        int finalYear = month > 20 ? year + 2000 : year + 1900;
        try {
            return LocalDate
                    .of(finalYear, finalMonth, Integer.parseInt(pesel.substring(4, 6)));
        } catch (Exception ex) {
            return null;
        }
    }


    @Override
    public Window openInModalPopup() {
        Window w = super.openInModalPopup();
        w.setHeight("95%");
        w.setWidth("70%");
        return w;
    }

}
