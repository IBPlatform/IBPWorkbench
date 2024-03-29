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
package org.generationcp.ibpworkbench.actions;

import org.generationcp.ibpworkbench.comp.ibtools.breedingview.select.SelectDetailsForBreedingViewWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * 
 * @author Jeffrey Morales
 * 
 */
@Configurable
public class CancelDetailsAsInputForBreedingViewAction implements ClickListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(CancelDetailsAsInputForBreedingViewAction.class);
    private static final long serialVersionUID = 1L;
    
    private SelectDetailsForBreedingViewWindow window;

    public CancelDetailsAsInputForBreedingViewAction(SelectDetailsForBreedingViewWindow window) {
        this.window = window;
    }
    
    @Override
    public void buttonClick(ClickEvent event) {
            
        window.getParent().removeWindow(window);
    
    }
        
}
