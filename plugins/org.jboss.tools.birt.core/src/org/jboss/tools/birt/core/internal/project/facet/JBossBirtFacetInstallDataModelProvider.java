/*******************************************************************************
 * Copyright (c) 2007-2014 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 ******************************************************************************/
package org.jboss.tools.birt.core.internal.project.facet;

import org.eclipse.birt.integration.wtp.ui.internal.wizards.BirtWizardUtil;
import org.eclipse.birt.integration.wtp.ui.project.facet.BirtFacetInstallDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.jboss.tools.birt.core.BirtCoreActivator;
/**
 * 
 * @author snjeza
 *
 */
public class JBossBirtFacetInstallDataModelProvider extends
		BirtFacetInstallDataModelProvider {

	@Override
	public Object create( )
	{
		IDataModel dataModel = (IDataModel) super.create( );
		dataModel.setProperty( FACET_ID, BirtCoreActivator.JBOSS_BIRT__FACET_ID );
		dataModel.setProperty( BIRT_CONFIG, BirtWizardUtil.initWebapp( null ) );
		// TODO: define all the birt properties as nested data models
		return dataModel;
	}
}
