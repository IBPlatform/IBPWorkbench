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

package org.generationcp.ibpworkbench.comp;

import org.generationcp.commons.vaadin.spring.InternationalizableComponent;
import org.generationcp.commons.vaadin.spring.SimpleResourceBundleMessageSource;
import org.generationcp.ibpworkbench.Message;
import org.generationcp.ibpworkbench.actions.LaunchWorkbenchToolAction;
import org.generationcp.ibpworkbench.actions.LaunchWorkbenchToolAction.ToolEnum;
import org.generationcp.middleware.pojos.workbench.Project;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@Configurable
public class ManagerWorkflowDiagram extends VerticalLayout implements InitializingBean, InternationalizableComponent {

    private static final long serialVersionUID = 1L;

    //this is in pixels and used for layouting
    private static final int WORKFLOW_STEP_HEIGHT = 130;
    private static final int WORKFLOW_STEP_WIDTH = 270;
    private static final int EXTRA_SPACE_BETWEEN_COMPONENTS = 10;
    
    private Project project;

    private Label dashboardTitle;

    private Label administrationTitle;
    private Label configurationTitle;
    private Label projectPlanningTitle;
    private Label breedingManagementTitle;
    private Label analysisPipelineTitle;
    private Label decisionSupportTitle;

    //links for tools
    private Button browseGermplasmButton;
    private Button browseStudiesButton;
    private Button browseGermplasmListsButton;
    private Button gdmsButton;
    private Button breedingViewButton;
    private Button fieldbookButton;
    private Button optimasButton;
    private Button nurseryManagerButton;
    
    @Autowired
    private SimpleResourceBundleMessageSource messageSource;

    public ManagerWorkflowDiagram(Project project) {
        this.project = project;
    }
    
    @Override
    public void afterPropertiesSet() {
        assemble();
    }

    protected void initializeComponents() {
        // dashboard title
        dashboardTitle = new Label();
        dashboardTitle.setStyleName("gcp-content-title");

        administrationTitle = new Label("Administration");
        administrationTitle.setStyleName("gcp-section-title-large");
        administrationTitle.setSizeUndefined();

        configurationTitle = new Label("Configuration");
        configurationTitle.setStyleName("gcp-section-title-large");
        configurationTitle.setSizeUndefined();

        projectPlanningTitle = new Label("Project Planning");
        projectPlanningTitle.setStyleName("gcp-section-title-large");
        projectPlanningTitle.setSizeUndefined();
        
        breedingManagementTitle = new Label("Breeding Management");
        breedingManagementTitle.setStyleName("gcp-section-title-large");
        breedingManagementTitle.setSizeUndefined();

        analysisPipelineTitle = new Label("Analysis Pipeline");
        analysisPipelineTitle.setStyleName("gcp-section-title-large");
        analysisPipelineTitle.setSizeUndefined();

        decisionSupportTitle = new Label("Decision Support");
        decisionSupportTitle.setStyleName("gcp-section-title-large");
        decisionSupportTitle.setSizeUndefined();
        
        browseGermplasmButton = new Button("Browse Germplasm Information");
        browseGermplasmButton.setStyleName(BaseTheme.BUTTON_LINK);
        browseGermplasmButton.setSizeUndefined();
        browseGermplasmButton.setDescription("Click to launch Germplasm Browser");

        browseStudiesButton = new Button("Browse Studies and Datasets");
        browseStudiesButton.setStyleName(BaseTheme.BUTTON_LINK);
        browseStudiesButton.setSizeUndefined();
        browseStudiesButton.setDescription("Click to launch Study Browser");

        browseGermplasmListsButton = new Button("Browser Germplasm Lists");
        browseGermplasmListsButton.setStyleName(BaseTheme.BUTTON_LINK);
        browseGermplasmListsButton.setSizeUndefined();
        browseGermplasmListsButton.setDescription("Click to launch Germplasm List Browser");
        
        breedingViewButton = new Button("Breeding View");
        breedingViewButton.setStyleName(BaseTheme.BUTTON_LINK);
        breedingViewButton.setSizeUndefined();
        breedingViewButton.setDescription("Click to launch Breeding View");

        gdmsButton = new Button("Manage Genotyping Data");
        gdmsButton.setStyleName(BaseTheme.BUTTON_LINK);
        gdmsButton.setSizeUndefined();
        gdmsButton.setDescription("Click to launch GDMS");
        
        fieldbookButton = new Button("Fieldbook");
        fieldbookButton.setStyleName(BaseTheme.BUTTON_LINK);
        fieldbookButton.setSizeUndefined();
        fieldbookButton.setDescription("Click to launch Fieldbook");
        
        optimasButton = new Button("OptiMAS");
        optimasButton.setStyleName(BaseTheme.BUTTON_LINK);
        optimasButton.setSizeUndefined();
        optimasButton.setDescription("Click to launch OptiMAS");
        
        nurseryManagerButton = new Button("Nursery Manager");
        nurseryManagerButton.setStyleName(BaseTheme.BUTTON_LINK);
        nurseryManagerButton.setSizeUndefined();
        nurseryManagerButton.setDescription("Click to launch Nursery Manager");
    }

    protected void initializeLayout() {
        setSpacing(true);
        setMargin(true);
        setWidth("1000px");

        dashboardTitle.setSizeUndefined();
        addComponent(dashboardTitle);

        Component workFlowArea = layoutWorkflowArea();
        workFlowArea.setSizeUndefined();
        addComponent(workFlowArea);

    }

    protected Component layoutWorkflowArea() {
        Panel panel = new Panel();

        AbsoluteLayout layout = new AbsoluteLayout();
        layout.setMargin(true);
        layout.setWidth("850px");
        layout.setHeight("300px");
        
        String extraSpace = EXTRA_SPACE_BETWEEN_COMPONENTS + "px";
        int top = 10;
        String topInPixels = "";
        
        //the steps on the first column
        Component populationManagementArea = layoutAdministration();
        layout.addComponent(populationManagementArea, "top:" + extraSpace + "; left:" + extraSpace);
        
        top = top + WORKFLOW_STEP_HEIGHT + EXTRA_SPACE_BETWEEN_COMPONENTS;
        topInPixels = top + "px";
        Component fieldTrialArea = layoutConfiguration();
        layout.addComponent(fieldTrialArea, "top:" + topInPixels  + "; left:" + extraSpace);
        
        //the steps on the second column   
        top = EXTRA_SPACE_BETWEEN_COMPONENTS; 
        topInPixels = top + "px";
        int left = EXTRA_SPACE_BETWEEN_COMPONENTS + WORKFLOW_STEP_WIDTH + EXTRA_SPACE_BETWEEN_COMPONENTS;
        String leftInPixels = left + "px";
        
        Component projectPlanningArea = layoutProjectPlanning();
        layout.addComponent(projectPlanningArea, "top:" + topInPixels + "; left:" + leftInPixels);
        
        top = top + WORKFLOW_STEP_HEIGHT + EXTRA_SPACE_BETWEEN_COMPONENTS;
        topInPixels = top + "px";
        Component genotypingArea = layoutBreedingManagement();
        layout.addComponent(genotypingArea, "top:" + topInPixels  + "; left:" + leftInPixels);
        
        //the steps on the third column
        top = EXTRA_SPACE_BETWEEN_COMPONENTS; 
        topInPixels = top + "px";
        left = EXTRA_SPACE_BETWEEN_COMPONENTS + WORKFLOW_STEP_WIDTH + EXTRA_SPACE_BETWEEN_COMPONENTS
            + WORKFLOW_STEP_WIDTH + EXTRA_SPACE_BETWEEN_COMPONENTS;
        leftInPixels = left + "px";
        
        Component progenySelectionArea = layoutAnalysisPipeline();
        layout.addComponent(progenySelectionArea, "top:" + topInPixels  + "; left:" + leftInPixels);

        top = top + WORKFLOW_STEP_HEIGHT + EXTRA_SPACE_BETWEEN_COMPONENTS;
        topInPixels = top + "px";
        Component projectCompletionArea = layoutDecisionSupport();
        layout.addComponent(projectCompletionArea, "top:" + topInPixels  + "; left:" + leftInPixels);

        panel.setContent(layout);
        return panel;
    }

    protected Component layoutProjectPlanning() {
        VerticalLayout layout = new VerticalLayout();
        configureWorkflowStepLayout(layout);
        
        layout.addComponent(projectPlanningTitle);
        layout.setComponentAlignment(projectPlanningTitle, Alignment.TOP_CENTER);
        layout.setExpandRatio(projectPlanningTitle, 0);

        Label emptyLabel = new Label(" ");
        emptyLabel.setWidth("100%");
        emptyLabel.setHeight("20px");
        layout.addComponent(emptyLabel);
        layout.setExpandRatio(emptyLabel, 100);
        
        layout.addComponent(browseGermplasmButton);
        browseGermplasmButton.setHeight("20px");
        layout.setComponentAlignment(browseGermplasmButton, Alignment.TOP_CENTER);
        layout.setExpandRatio(browseGermplasmButton, 0);

        layout.addComponent(browseStudiesButton);
        browseStudiesButton.setHeight("20px");
        layout.setComponentAlignment(browseStudiesButton, Alignment.TOP_CENTER);
        layout.setExpandRatio(browseStudiesButton, 0);
        
        layout.addComponent(browseGermplasmListsButton);
        layout.setComponentAlignment(browseGermplasmListsButton, Alignment.TOP_CENTER);
        layout.setExpandRatio(browseGermplasmListsButton, 0);

        /*
        layout.addComponent(gdmsButton);
        layout.setComponentAlignment(gdmsButton, Alignment.TOP_CENTER);
        layout.setExpandRatio(gdmsButton, 0);
        */
        
        return layout;
    }

    protected Component layoutAdministration() {
        VerticalLayout layout = new VerticalLayout();
        configureWorkflowStepLayout(layout);

        layout.addComponent(administrationTitle);
        layout.setComponentAlignment(administrationTitle, Alignment.TOP_CENTER);
        layout.setExpandRatio(administrationTitle, 0);

        Label emptyLabel = new Label(" ");
        emptyLabel.setWidth("100%");
        emptyLabel.setHeight("20px");
        layout.addComponent(emptyLabel);
        layout.setExpandRatio(emptyLabel, 100);

        return layout;
    }

    protected Component layoutConfiguration() {
        VerticalLayout layout = new VerticalLayout();
        configureWorkflowStepLayout(layout);

        layout.addComponent(configurationTitle);
        layout.setComponentAlignment(configurationTitle, Alignment.TOP_CENTER);
        layout.setExpandRatio(configurationTitle, 0);

        Label emptyLabel = new Label(" ");
        emptyLabel.setWidth("100%");
        emptyLabel.setHeight("20px");
        layout.addComponent(emptyLabel);
        layout.setExpandRatio(emptyLabel, 100);

        return layout;
    }

    protected Component layoutBreedingManagement() {
        VerticalLayout layout = new VerticalLayout();
        configureWorkflowStepLayout(layout);

        layout.addComponent(breedingManagementTitle);
        layout.setComponentAlignment(breedingManagementTitle, Alignment.TOP_CENTER);
        layout.setExpandRatio(breedingManagementTitle, 0);

        Label emptyLabel = new Label(" ");
        emptyLabel.setWidth("100%");
        emptyLabel.setHeight("20px");
        layout.addComponent(emptyLabel);
        layout.setExpandRatio(emptyLabel, 100);

        layout.addComponent(fieldbookButton);
        fieldbookButton.setHeight("20px");
        layout.setComponentAlignment(fieldbookButton, Alignment.TOP_CENTER);
        layout.setExpandRatio(fieldbookButton, 0);
        
        layout.addComponent(nurseryManagerButton);
        layout.setComponentAlignment(nurseryManagerButton, Alignment.TOP_CENTER);
        layout.setExpandRatio(nurseryManagerButton, 0);
        
        return layout;
    }

    protected Component layoutAnalysisPipeline() {
        VerticalLayout layout = new VerticalLayout();
        configureWorkflowStepLayout(layout);

        layout.addComponent(analysisPipelineTitle);
        layout.setComponentAlignment(analysisPipelineTitle, Alignment.TOP_CENTER);
        layout.setExpandRatio(analysisPipelineTitle, 0);

        Label emptyLabel = new Label(" ");
        emptyLabel.setWidth("100%");
        emptyLabel.setHeight("20px");
        layout.addComponent(emptyLabel);
        layout.setExpandRatio(emptyLabel, 100);

        layout.addComponent(breedingViewButton);
        layout.setComponentAlignment(breedingViewButton, Alignment.TOP_CENTER);
        layout.setExpandRatio(breedingViewButton, 0);

        return layout;
    }

    protected Component layoutDecisionSupport() {
        VerticalLayout layout = new VerticalLayout();
        configureWorkflowStepLayout(layout);

        layout.addComponent(decisionSupportTitle);
        layout.setComponentAlignment(decisionSupportTitle, Alignment.TOP_CENTER);
        layout.setExpandRatio(decisionSupportTitle, 0);

        Label emptyLabel = new Label(" ");
        emptyLabel.setWidth("100%");
        emptyLabel.setHeight("20px");
        layout.addComponent(emptyLabel);
        layout.setExpandRatio(emptyLabel, 100);

        layout.addComponent(optimasButton);
        layout.setComponentAlignment(optimasButton, Alignment.TOP_CENTER);
        layout.setExpandRatio(optimasButton, 0);
        
        return layout;
    }

    protected Component createPanel(String caption, String... buttonCaptions) {
        VerticalLayout layout = new VerticalLayout();
        configureWorkflowStepLayout(layout);

        Label titleLabel = new Label(caption);
        titleLabel.setStyleName("gcp-section-title");
        titleLabel.setSizeUndefined();

        layout.addComponent(titleLabel);
        layout.setComponentAlignment(titleLabel, Alignment.TOP_CENTER);
        layout.setExpandRatio(titleLabel, 0);

        Label emptyLabel = new Label(" ");
        emptyLabel.setWidth("100%");
        emptyLabel.setHeight("20px");
        layout.addComponent(emptyLabel);
        layout.setExpandRatio(emptyLabel, 100);

        for (String buttonCaption : buttonCaptions) {
            Button button = new Button(buttonCaption);
            button.setStyleName(BaseTheme.BUTTON_LINK);

            layout.addComponent(button);
            layout.setComponentAlignment(button, Alignment.TOP_CENTER);
            layout.setExpandRatio(button, 0);
        }

        return layout;
    }

    protected void configureWorkflowStepLayout(VerticalLayout layout) {
        layout.setWidth(WORKFLOW_STEP_WIDTH + "px");
        layout.setHeight(WORKFLOW_STEP_HEIGHT + "px");
        layout.setStyleName("gcp-mars-workflow-step");
        layout.setMargin(true, true, true, true);
    }
    
    protected void initializeActions() {
        browseGermplasmButton.addListener(new LaunchWorkbenchToolAction(ToolEnum.GERMPLASM_BROWSER));
        browseStudiesButton.addListener(new LaunchWorkbenchToolAction(ToolEnum.STUDY_BROWSER));
        browseGermplasmListsButton.addListener(new LaunchWorkbenchToolAction(ToolEnum.GERMPLASM_LIST_BROWSER));
        gdmsButton.addListener(new LaunchWorkbenchToolAction(ToolEnum.GDMS));
        breedingViewButton.addListener(new LaunchWorkbenchToolAction(ToolEnum.BREEDING_VIEW));
        fieldbookButton.addListener(new LaunchWorkbenchToolAction(ToolEnum.FIELDBOOK));
        optimasButton.addListener(new LaunchWorkbenchToolAction(ToolEnum.OPTIMAS));
        nurseryManagerButton.addListener(new LaunchWorkbenchToolAction(ToolEnum.BREEDING_MANAGER));
    }

    protected void assemble() {
        initializeComponents();
        initializeLayout();
        initializeActions();
    }
    
    @Override
    public void attach() {
        super.attach();        
        updateLabels();
    }
    
    @Override
    public void updateLabels() {
        messageSource.setValue(dashboardTitle, Message.project_title, project.getProjectName());
    }
}