package org.generationcp.ibpworkbench.actions;

import org.generationcp.ibpworkbench.comp.CreateNewProjectPanel;
import org.generationcp.ibpworkbench.comp.window.IContentWindow;

import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public class OpenNewProjectAction implements ClickListener, MouseEvents.ClickListener {
    private static final long serialVersionUID = 1L;

    @Override
    public void buttonClick(ClickEvent event) {
        doAction(event.getComponent());
    }

    @Override
    public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
        doAction(event.getComponent());
    }

    protected void doAction(Component component) {
        IContentWindow window = (IContentWindow) component.getWindow();
        
        CreateNewProjectPanel newProjectPanel = new CreateNewProjectPanel();
        newProjectPanel.setWidth("480px");
        window.showContent(newProjectPanel);
        
        newProjectPanel.getSaveButton().addListener(new SaveNewProjectAction(newProjectPanel.getForm()));
    }
}
