/*******************************************************************************
 * Copyright (c) 2007-2014 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 ******************************************************************************/
package org.jboss.tools.birt.core.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Platform;
import org.jboss.tools.test.util.TestProjectProvider;
import org.jboss.tools.tests.AbstractPluginsLoadTest;
import org.osgi.framework.Bundle;

public class BirtCoreTestSuite extends TestCase {

	public static Test suite ()
	{
		TestSuite suite = new TestSuite(BirtCoreTestSuite.class.getName());
		suite.addTestSuite(BirtPluginsLoadTest.class);
		return suite;
	}
	
	
	static public class BirtPluginsLoadTest extends AbstractPluginsLoadTest {
		
		public BirtPluginsLoadTest() {}
		
		public void testOrgJbossToolsBirtCoreResolvedAndActivated() {
			assertPluginResolved(Platform.getBundle("org.jboss.tools.birt.core"));
		}
	}
}
