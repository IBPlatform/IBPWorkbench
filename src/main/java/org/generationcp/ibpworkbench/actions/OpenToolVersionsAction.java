package org.generationcp.ibpworkbench.actions;

import org.generationcp.commons.exceptions.InternationalizableException;
import org.generationcp.commons.vaadin.util.MessageNotifier;
import org.generationcp.ibpworkbench.comp.ToolVersionsPanel;
import org.generationcp.ibpworkbench.comp.window.IContentWindow;
import org.generationcp.ibpworkbench.navigation.NavManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.Window;

public class OpenToolVersionsAction implements ClickListener, ActionListener {
    private static final long serialVersionUID = 1L;
    
    private static final Logger LOG = LoggerFactory.getLogger(OpenToolVersionsAction.class);
    
    @Override
    public void buttonClick(ClickEvent event) {
        doAction(event.getComponent().getWindow(), null, true);
    }
    
    @Override
    public void doAction(Event event) {
        NavManager.breadCrumbClick(this, event);
    }
    
    @Override
    public void doAction(Window window, String uriFragment, boolean isLinkAccessed) {
        IContentWindow w = (IContentWindow) window;
        
        try {
            ToolVersionsPanel toolVersionsPanel = new ToolVersionsPanel();
            toolVersionsPanel.setWidth("420px");
            
            w.showContent(toolVersionsPanel);
            
            NavManager.navigateApp(window, "/ToolVersions", isLinkAccessed);
        } catch (Exception e) {
            LOG.error("Exception", e);
            if(e.getCause() instanceof InternationalizableException) {
                InternationalizableException i = (InternationalizableException) e.getCause();
                MessageNotifier.showError(window, i.getCaption(), i.getDescription());
            }
            return;
        }
    }
}