package com.wsiiz.repairshop.foundation.ui.dialog;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class ConfirmDialog implements I18nAware {


    public ConfirmDialog(String question, Runnable action, UI ui) {

        Window window = new Window(i18n("confirmation"));
        Button yesButton = new Button(i18n("yes"));
        Button noButton = new Button(i18n("no"));

        window.setContent(new MVerticalLayout(
                new Label(question),
                new MHorizontalLayout(noButton, yesButton)));

        noButton.addClickListener(event -> window.close());
        yesButton.addClickListener(event -> {
            action.run();
            window.close();
        });

        window.center();
        window.setWidth("30%");
        ui.addWindow(window);
    }
}
