package com.toparchy.atom.util;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.picketlink.annotations.PicketLink;

public class Resources {
	@Produces
	@PersistenceContext(unitName = "atom")
	private EntityManager atomEm;

	@PicketLink
	public EntityManager getPicketLinkEntityManager() {
		return atomEm;
	}

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
