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

package org.generationcp.ibpworkbench.model.formfieldfactory;

import java.util.ArrayList;
import java.util.List;

import org.generationcp.commons.exceptions.InternationalizableException;
import org.generationcp.commons.util.StringUtil;
import org.generationcp.ibpworkbench.Message;
import org.generationcp.middleware.exceptions.QueryException;
import org.generationcp.middleware.manager.ManagerFactory;
import org.generationcp.middleware.manager.api.ManagerFactoryProvider;
import org.generationcp.middleware.manager.api.UserDataManager;
import org.generationcp.middleware.manager.api.WorkbenchDataManager;
import org.generationcp.middleware.pojos.Location;
import org.generationcp.middleware.pojos.Method;
import org.generationcp.middleware.pojos.Person;
import org.generationcp.middleware.pojos.User;
import org.generationcp.middleware.pojos.workbench.CropType;
import org.generationcp.middleware.pojos.workbench.WorkflowTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

@Configurable
public class ProjectFormFieldFactory extends DefaultFieldFactory{

    private static final Logger LOG = LoggerFactory.getLogger(ProjectFormFieldFactory.class);
    private static final long serialVersionUID = 1L;

    @Autowired
    private WorkbenchDataManager workbenchDataManager;
    
    @Autowired
    private ManagerFactoryProvider managerFactoryProvider;
    
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SELECT = "select";

    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {
        Field field = super.createField(item, propertyId, uiContext);

        if ("projectName".equals(propertyId)) {
            TextField tf = (TextField) field;
            tf.setRequired(true);
            tf.setRequiredError("Please enter a Project Name.");
            tf.addValidator(new StringLengthValidator("Project Name must be 3-255 characters", 3, 255, false));
        } else if ("targetDueDate".equals(propertyId)) {
            field.setRequired(true);
            field.setRequiredError("Please enter a Target Due Date.");
        } else if ("template".equals(propertyId)) {
            BeanContainer<Long, WorkflowTemplate> templateContainer = new BeanContainer<Long, WorkflowTemplate>(WorkflowTemplate.class);
            templateContainer.setBeanIdProperty("templateId");

            // TODO: Verify the try-catch flow
            try {
                List<WorkflowTemplate> templateList = workbenchDataManager.getWorkflowTemplates();

                for (WorkflowTemplate template : templateList) {
                    templateContainer.addBean(template);
                }

                ComboBox comboBox = new ComboBox("Workflow Template");
                comboBox.setContainerDataSource(templateContainer);
                comboBox.setItemCaptionPropertyId("name");
                comboBox.setRequired(true);
                comboBox.setRequiredError("Please enter a Workflow Template.");

                return comboBox;
            } catch (QueryException e) {
                LOG.error("Error encountered while getting workflow templates", e);
                throw new InternationalizableException(e, Message.DATABASE_ERROR, 
                        Message.CONTACT_ADMIN_ERROR_DESC);
            }
        } else if ("cropType".equals(propertyId)) {
            List<CropType> cropTypes = null;
            try {
                cropTypes = workbenchDataManager.getInstalledCentralCrops();
            }
            catch (QueryException e) {
                LOG.error("Error encountered while getting installed central crops", e);
                throw new InternationalizableException(e, Message.DATABASE_ERROR, 
                        Message.CONTACT_ADMIN_ERROR_DESC);
            }
            
            BeanItemContainer<CropType> beanItemContainer = new BeanItemContainer<CropType>(CropType.class);
            for (CropType cropType : cropTypes) {
                beanItemContainer.addBean(cropType);
            }

            ComboBox comboBox = new ComboBox("Crop");
            comboBox.setContainerDataSource(beanItemContainer);
            comboBox.setItemCaptionPropertyId("cropName");
            comboBox.setRequired(true);
            comboBox.setRequiredError("Please select a Crop.");
            comboBox.setImmediate(true);

            return comboBox;

        } else if ("tblLocation".equals(propertyId)) {

            Table table = new Table("Location");
            IndexedContainer locationContainer = null;
            try {
                locationContainer = getLocationContainer();
            } catch (QueryException e) {
                LOG.error("QueryException", e);
                throw new InternationalizableException(e, 
                        Message.DATABASE_ERROR, Message.CONTACT_ADMIN_ERROR_DESC);
            }
            table.setContainerDataSource(locationContainer);
            table.setVisibleColumns(new String[] { SELECT, NAME });
            table.setColumnHeaders(new String[] { "Select", "Location" });
            table.setColumnWidth(SELECT, 50);
            table.setColumnWidth(NAME, 300);
            table.setHeight("200px");
            table.setWidth("300px");

            return table;

        } else if ("tblMethods".equals(propertyId)) {

            Table table = new Table("Breeding Methods");
            IndexedContainer methodContainer = null;
            try {
                methodContainer = getMethodContainer();
            } catch (QueryException e) {
                LOG.error("QueryException", e);
                throw new InternationalizableException(e, 
                        Message.DATABASE_ERROR, Message.CONTACT_ADMIN_ERROR_DESC);
            }
            table.setContainerDataSource(methodContainer);
            table.setVisibleColumns(new String[] { SELECT, NAME });
            table.setColumnHeaders(new String[] { "Select", "Method Name" });
            table.setColumnWidth(SELECT, 50);
            table.setColumnWidth(NAME, 250);
            table.setHeight("200px");
            table.setWidth("300px");

            return table;
        }
        else if ("members".equals(propertyId)) {
            TwinColSelect select = new TwinColSelect("Project Members");
            select.setLeftColumnCaption("Available Users");
            select.setRightColumnCaption("Selected Project Members");
            select.setRows(10);
            select.setWidth("400px");
            select.setMultiSelect(true);
            select.setNullSelectionAllowed(true);
            
            CropType cropType = (CropType) item.getItemProperty("cropType").getValue();
            if (cropType != null) {
                Container container = createUsersContainer(cropType);
                select.setContainerDataSource(container);
                
                for (Object itemId : container.getItemIds()) {
                    User user = (User) itemId;
                    select.setItemCaption(itemId, user.getPerson().getDisplayName());
                }
            }
            
            return select;
        }
        return field;
    }

    public IndexedContainer getLocationContainer() throws QueryException {

        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(SELECT, CheckBox.class, "");
        container.addContainerProperty(ID, Integer.class, 0);
        container.addContainerProperty(NAME, String.class, "");

        // TODO: Get a ManagerFactory from the ManagerFactoryProvider
        // Check ManagerFactoryProvider for methods that you can use.
        ManagerFactory managerFactory = managerFactoryProvider.getManagerFactoryForProject(null);
        int locCount = managerFactory.getGermplasmDataManager().countAllLocations();
        List<Location> locationList = managerFactory.getGermplasmDataManager().getAllLocations(0, locCount);

        for (Location l : locationList) {
            addLocationItem(container, l.getLocid(), l.getLname());
        }
        return container;
    }

    private void addLocationItem(Container container, int locid, String lname) {
        Object itemId = container.addItem();
        Item item = container.getItem(itemId);
        item.getItemProperty(SELECT).setValue(new CheckBox(null, false));
        item.getItemProperty(ID).setValue(locid);
        item.getItemProperty(NAME).setValue(lname);
    }

    public IndexedContainer getMethodContainer() throws QueryException {

        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(SELECT, CheckBox.class, "");
        container.addContainerProperty(ID, Integer.class, 0);
        container.addContainerProperty(NAME, String.class, "");

        // TODO: Get a ManagerFactory from the ManagerFactoryProvider
        // Check ManagerFactoryProvider for methods that you can use.
        ManagerFactory managerFactory = managerFactoryProvider.getManagerFactoryForProject(null);
        List<Method> methodList = managerFactory.getGermplasmDataManager().getAllMethods();
        for (Method method : methodList) {
            addMethodItem(container, method.getMid(), method.getMname());
        }

        return container;
    }

    private void addMethodItem(Container container, int mid, String mname) {
        Object itemId = container.addItem();
        Item item = container.getItem(itemId);
        item.getItemProperty(SELECT).setValue(new CheckBox(null, false));
        item.getItemProperty(ID).setValue(mid);
        item.getItemProperty(NAME).setValue(mname);
    }

    private Container createUsersContainer(CropType cropType) {
        ManagerFactory managerFactory = managerFactoryProvider.getManagerFactoryForCropType(cropType);
        UserDataManager userDataManager = managerFactory.getUserDataManager();
        
        List<User> validUserList = new ArrayList<User>();
        
        // TODO: This can be improved once we implement proper User-Person mapping
        List<User> userList = userDataManager.getAllUsers();
        for (User user : userList) {
            Person person = userDataManager.getPersonById(user.getPersonid());
            user.setPerson(person);
            
            if (person != null) {
                validUserList.add(user);
            }
        }
        
        BeanItemContainer<User> beanItemContainer = new BeanItemContainer<User>(User.class);
        for (User user : validUserList) {
            beanItemContainer.addBean(user);
        }
        
        return beanItemContainer;
    }
}
