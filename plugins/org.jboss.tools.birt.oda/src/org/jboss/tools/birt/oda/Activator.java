/*******************************************************************************
 * Copyright (c) 2007-2014 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 ******************************************************************************/
package org.jboss.tools.birt.oda;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.hibernate.SessionFactory;
import org.jboss.tools.hibernate.spi.ISessionFactory;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author snjeza
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.jboss.tools.birt.oda"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static Class classForName(String name, Class caller) throws ClassNotFoundException {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if ( contextClassLoader != null ) {
				return contextClassLoader.loadClass( name );
			}
		}
		catch ( Throwable e ) {
		}
		return Class.forName( name, true, caller.getClassLoader() );
	}
	
	public static void log(Throwable e) {
		IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, e
				.getLocalizedMessage(), e);
		plugin.getLog().log(status);
	}
	
	public static SessionFactory getSessionFactory(ISessionFactory isf) {
		if (isf != null) {
			Class<?> clazz = isf.getClass();
			try {
				Method method = clazz.getDeclaredMethod(
						"getTraget", new Class[0]); //$NON-NLS-1$
				method.setAccessible(true);
				Object object = method.invoke(isf, new Object[0]);
				if (object instanceof SessionFactory) {
					return (SessionFactory) object;
				}
			} catch (NoSuchMethodException e) {
				Activator.log(e);
			} catch (IllegalAccessException e) {
				Activator.log(e);
			} catch (IllegalArgumentException e) {
				Activator.log(e);
			} catch (InvocationTargetException e) {
				Activator.log(e);
			}
		}
		return null;
	}
}
