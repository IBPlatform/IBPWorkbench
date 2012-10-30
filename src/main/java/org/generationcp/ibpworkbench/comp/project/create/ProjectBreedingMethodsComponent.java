/*******************************************************************************
 * Copyright (c) 2012, All Rights Reserved.
 * 
 * Generation Challenge Programme (GCP)
 * 
 * 
 * This software is licensed for use under the terms of the GNU General Public
 * License (http://bit.ly/8Ztv8M) and the provisions of Part F of the Generation
 * Challenge Programme Amended Consortium Agreement (http://bit.ly/KQX1nL)
 * 
 *******************************************************************************/

package org.generationcp.ibpworkbench.comp.project.create;

import java.util.List;
import java.util.Set;

import org.generationcp.commons.exceptions.InternationalizableException;
import org.generationcp.commons.vaadin.util.MessageNotifier;
import org.generationcp.ibpworkbench.Message;
import org.generationcp.middleware.exceptions.MiddlewareQueryException;
import org.generationcp.middleware.manager.ManagerFactory;
import org.generationcp.middleware.manager.api.ManagerFactoryProvider;
import org.generationcp.middleware.pojos.Method;
import org.generationcp.middleware.pojos.workbench.CropType;
import org.generationcp.middleware.pojos.workbench.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Select;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;

/**
 * The fourth tab (Breeding Methods) in Create Project Accordion Component.
 * 
 * @author Joyce Avestro
 * 
 */
@SuppressWarnings("unchecked")
@Configurable
public class ProjectBreedingMethodsComponent extends VerticalLayout implements InitializingBean{

    private static final Logger LOG = LoggerFactory.getLogger(ProjectBreedingMethodsComponent.class);

    private static final long serialVersionUID = 1L;

    private Button addMethodButton;
    private Button previousButton;
    private Button nextButton;
    private Component buttonArea;

    private GridLayout gridMethodLayout;
    private Select selectMethodType;
    private Select selectMethodGroup;
    private TwinColSelect selectMethods;
    private Button btnFilterMethod;
    private VerticalLayout methodsLayout;

    private CreateProjectPanel createProjectPanel;

    @Autowired
    private ManagerFactoryProvider managerFactoryProvider;

    public ProjectBreedingMethodsComponent(CreateProjectPanel createProjectPanel) {
        this.createProjectPanel = createProjectPanel;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        assemble();
    }

    protected void assemble() {
        initializeComponents();
        initializeValues();
        initializeLayout();
        initializeActions();
    }

    protected void initializeComponents() {
        setSpacing(true);
        setMargin(true);

        addComponent(layoutMethodsArea());

        buttonArea = layoutButtonArea();
        addComponent(buttonArea);
    }

    protected void initializeValues() {
    }

    protected void initializeLayout() {
        setSpacing(true);
        setMargin(true);
        setComponentAlignment(buttonArea, Alignment.TOP_RIGHT);
    }

    protected void initializeActions() {
        //TODO addMethodButton.addListener(new ButtonClickListener());
        previousButton.addListener(new PreviousButtonClickListener());
        nextButton.addListener(new NextButtonClickListener());
    }

    protected Component layoutButtonArea() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);
        buttonLayout.setMargin(true, false, false, false);

        addMethodButton = new Button("Add Breeding Method");
        previousButton = new Button("Previous");
        nextButton = new Button("Next");
        buttonLayout.addComponent(addMethodButton);
        buttonLayout.addComponent(previousButton);
        buttonLayout.addComponent(nextButton);
        return buttonLayout;
    }

    private Component layoutMethodsArea() {

        methodsLayout = new VerticalLayout();
        gridMethodLayout = new GridLayout();
        gridMethodLayout.setRows(3);
        gridMethodLayout.setColumns(4);
        gridMethodLayout.setSpacing(true);

        selectMethodType = new Select();
        selectMethodType.addItem("");
        selectMethodType.addItem("GEN");
        selectMethodType.setItemCaption("GEN", "Generative");
        selectMethodType.addItem("DER");
        selectMethodType.setItemCaption("DER", "Derivative");
        selectMethodType.addItem("MAN");
        selectMethodType.setItemCaption("MAN", "Maintenance");
        selectMethodType.select("GEN");
        selectMethodType.setNullSelectionAllowed(false);

        selectMethodGroup = new Select();
        selectMethodGroup.addItem("");
        selectMethodGroup.addItem("S");
        selectMethodGroup.setItemCaption("S", "Self Fertilizing");
        selectMethodGroup.addItem("O");
        selectMethodGroup.setItemCaption("O", "Cross Pollinating");
        selectMethodGroup.addItem("C");
        selectMethodGroup.setItemCaption("C", "Clonolly Propogating");
        selectMethodGroup.addItem("G");
        selectMethodGroup.setItemCaption("G", "All System");
        selectMethodGroup.select("");
        selectMethodGroup.setNullSelectionAllowed(false);

        btnFilterMethod = new Button("Filter");
        btnFilterMethod.addListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
                selectMethods.removeAllItems();
                CropType cropType = createProjectPanel.getSelectedCropType();
                if (cropType != null) {
                    try {
                        Container container = createMethodsContainer(cropType);
                        selectMethods.setContainerDataSource(container);

                        for (Object itemId : container.getItemIds()) {
                            Method method = (Method) itemId;

                            selectMethods.setItemCaption(itemId, method.getMname());
                        }
                    } catch (MiddlewareQueryException e) {
                        LOG.error("Error encountered while getting central methods", e);
                        throw new InternationalizableException(e, Message.DATABASE_ERROR, Message.CONTACT_ADMIN_ERROR_DESC);
                    }
                }
            }
        });

        gridMethodLayout.addComponent(new Label("Select Methods Type To Filter"), 1, 1);
        gridMethodLayout.addComponent(selectMethodType, 2, 1);
        gridMethodLayout.addComponent(new Label("Select Methods Group To Filter"), 1, 2);
        gridMethodLayout.addComponent(selectMethodGroup, 2, 2);
        gridMethodLayout.addComponent(btnFilterMethod, 3, 1);

        selectMethods = new TwinColSelect("");
        selectMethods.setLeftColumnCaption("Available Methods");
        selectMethods.setRightColumnCaption("Selected Methods");
        selectMethods.setRows(10);
        selectMethods.setWidth("690px");
        selectMethods.setMultiSelect(true);
        selectMethods.setNullSelectionAllowed(true);

        CropType cropType = createProjectPanel.getSelectedCropType();
        if (cropType != null) {
            try {
                Container container = createMethodsContainer(cropType);
                selectMethods.setContainerDataSource(container);

                for (Object itemId : container.getItemIds()) {
                    Method method = (Method) itemId;

                    selectMethods.setItemCaption(itemId, method.getMname());
                }
            } catch (MiddlewareQueryException e) {
                LOG.error("Error encountered while getting central methods", e);
                throw new InternationalizableException(e, Message.DATABASE_ERROR, Message.CONTACT_ADMIN_ERROR_DESC);
            }
        }

        methodsLayout.addComponent(gridMethodLayout);
        methodsLayout.addComponent(selectMethods);

        return methodsLayout;

    }

    private Container createMethodsContainer(CropType cropType) throws MiddlewareQueryException {
        ManagerFactory managerFactory = managerFactoryProvider.getManagerFactoryForCropType(cropType);
        BeanItemContainer<Method> beanItemContainer = new BeanItemContainer<Method>(Method.class);
        if (managerFactory == null) {
            return beanItemContainer;
        }
        String methodType = selectMethodType.getValue().toString();
        String methodGroup = selectMethodGroup.getValue().toString();

        List<Method> methodList = null;
        if (selectMethodType.getValue().toString() != "" && selectMethodGroup.getValue().toString() == "") {
            methodList = managerFactory.getGermplasmDataManager().getMethodsByType(selectMethodType.getValue().toString());
        } else if (selectMethodType.getValue().toString() == "" && selectMethodGroup.getValue().toString() != "") {
            methodList = managerFactory.getGermplasmDataManager().getMethodsByGroup(selectMethodGroup.getValue().toString());
        } else if (selectMethodType.getValue().toString() != "" && selectMethodGroup.getValue().toString() != "") {
            methodList = managerFactory.getGermplasmDataManager().getMethodsByGroupAndType(methodGroup, methodType);
        } else {
            methodList = managerFactory.getGermplasmDataManager().getAllMethods();
        }

        for (Method method : methodList) {
            beanItemContainer.addBean(method);
        }

        return beanItemContainer;
    }

    public TwinColSelect getSelectMethods() {
        return selectMethods;
    }

    private boolean validate() {
        boolean success = true;
        if (selectMethods != null) {
            Set<Method> methods = (Set<Method>) selectMethods.getValue();
            if (methods.size() == 0) {
                MessageNotifier.showWarning(getWindow(), "Warning", "No method selected.");
            }
        } else {
            MessageNotifier.showWarning(getWindow(), "Warning", "No method selected.");
        }
        return success; // returns true. methods not required.

    }

    public boolean validateAndSave() {
        if (validate()) { // save if valid
            Set<Method> methods = (Set<Method>) selectMethods.getValue();
            Project project = createProjectPanel.getProject();
            project.setMethods(methods);
            createProjectPanel.setProject(project);
        }
        return true;     // methods not required, so even if there are no values, this returns true
    }

    private class PreviousButtonClickListener implements ClickListener{

        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
            createProjectPanel.getCreateProjectAccordion().setFocusToTab(CreateProjectAccordion.THIRD_TAB_PROJECT_MEMBERS);
        }
    }

    private class NextButtonClickListener implements ClickListener{

        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
            createProjectPanel.getCreateProjectAccordion().setFocusToTab(CreateProjectAccordion.FIFTH_TAB_LOCATIONS);
        }
    }

}
