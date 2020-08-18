package com.wsiiz.repairshop.servicing.ui.service;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.domain.service.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;


public class MaintenanceCard implements I18nAware {

    Service service;
    ServiceService serviceService;
    TaskRepository repository;
    AbstractForm<Task> editor;
    MGrid<Task> tasks;
    MButton addNewTask;

    public MaintenanceCard(Service service, UI ui, ServiceService serviceService, TaskRepository repository) {

        this.service = service;
        this.serviceService = serviceService;
        this.repository = repository;
        this.editor = new TaskEditor(serviceService);
        this.addNewTask = addNewTask();
        this.tasks = createTasksGrid();
        loadEntities();

        Window window = new Window(i18n("tasksWindow"));
        window.setWidth("50%");
        window.center();

        window.setContent(new MVerticalLayout(infoText(), addNewTask, tasks));


        editor.setSavedHandler(entity -> {
            repository.save(entity);
            editor.closePopup();
            loadEntities();
        });

        ui.addWindow(window);
    }

    protected RichText infoText() {

        RichText info = new RichText();

        info.setRichText(("Karta serwisowa pojazdu: ") + serviceService.getVehicleData(service.getVehicleId()));

        return info;
    }

    protected MButton addNewTask() {

        MButton addTaskButton = new MButton(i18n("addNewTask")).withIcon(VaadinIcons.PLUS);

        addTaskButton.addClickListener(event -> {
            Task entity = new Task();
            entity.setStatus(Status.OPENED);
            entity.setService(service);

            editor.setEntity(entity);
            editor.focusFirst();
            editor.openInModalPopup();
        });

        return addTaskButton;
    }

    protected MGrid<Task> createTasksGrid() {

        MGrid<Task> tasks = new MGrid<>();

        tasks.addColumn(e -> e.getDescription()).setCaption(i18n("taskDescription"));
        tasks.addColumn(e -> e.getResponsiblePerson()).setCaption(i18n("employee"));
        tasks.addColumn(e -> e.getStatus()).setCaption(i18n("status"));
        tasks.addComponentColumn(entity -> new MHorizontalLayout(
                entity.getStatus().equals(Status.OPENED) ?
                        new MButton(VaadinIcons.CHECK, e -> {
                            entity.setStatus(Status.COMPLETED);
                            repository.save(entity);
                            loadEntities();
                        }).withStyleName("no-padding").withStyleName(ValoTheme.BUTTON_BORDERLESS) : new Label(""),

                new MButton(VaadinIcons.EDIT, e -> {
                    editInPopup(entity);
                }).withStyleName(ValoTheme.BUTTON_BORDERLESS).withStyleName("no-padding")
        ))
                .setCaption(i18n("actions"))
                .setWidth(120);

        tasks.setStyleGenerator(e -> e.getStatus() == Status.COMPLETED ? "statusCompleted" : "statusOpened");
        tasks.setWidthFull();
        tasks.setStyleName("tasksTable");

        return tasks;
    }

    protected void editInPopup(Task entity) {
        editor.setEntity(entity);
        editor.openInModalPopup();
    }

    protected void loadEntities() {
        tasks.setRows(serviceService.getTasksByServiceId(service));
    }
}




