/*
 * $Id:  SBaseChangedEvent.java 18:05:03 draeger $
 * $URL: SBaseChangedEvent.java $
 *
 * 
 *==================================================================================
 * Copyright (c) 2009 The jsbml team.
 *
 * This file is part of jsbml, the pure java SBML library. Please visit
 * http://sbml.org for more information about SBML, and http://jsbml.sourceforge.net/
 * to get the latest version of jsbml.
 *
 * jsbml is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * jsbml is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with jsbml.  If not, see <http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html>.
 *
 *===================================================================================
 *
 */
package org.sbml.jsbml;

import java.util.EventObject;

/**
 * This event tells an {@link SBaseChangedListener} which values have been
 * changed in an {@link SBase} and also provides the old and the new value.
 * 
 * @author Andreas Dr&auml;ger
 * @date 2010-11-14
 */
public class SBaseChangedEvent extends EventObject {
	
	/**
	 * Generated serial version identifier
	 */
	private static final long serialVersionUID = 1669574491009205844L;
	
	/*
	 * Property names that can change in the life time of an SBML document.
	 */
	public static final String addCVTerm = "addCVTerm";
	public static final String addExtension="addExtension";
	public static final String addNamespace="addNamespace";
	public static final String notes="notes";
	public static final String setAnnotation="setAnnotation";
	public static final String level = "level";	
	public static final String version="version"; 
	public static final String metaId = "metaId";
	public static final String notesBuffer = "notesBuffer";
	public static final String parentSBMLObject = "parentSBMLObject";
	public static final String sboTerm = "sboTerm";
	public static final String annotation = "annotation";
	public static final String unsetCVTerms = "unsetCVTerms";
	public static final String history = "history";
	public static final String currentList = "currentList";
	public static final String symbol = "symbol";
	public static final String math = "math";
	public static final String name = "name";
	public static final String id = "id";
	public static final String compartmentType = "compartmentType";
	public static final String outside = "outside";
	public static final String spacialDimensions = "spacialDimensions";
	public static final String message = "message";
	public static final String messageBuffer = "messageBuffer";
	public static final String timeUnits = "timeUnits";
	public static final String useValuesFromTriggerTime = "useValuesFromTriggerTime";
	public static final String variable = "variable";
	public static final String units = "units";
	public static final String baseListType = "baseListType";
	public static final String areaUnits = "areaUnits";
	public static final String conversionFactor = "conversionFactor";
	public static final String extentUnits = "extentUnits";
	public static final String lengthUnits = "lengthUnits";
	public static final String substanceUnits = "substanceUnits";
	public static final String volumeUnits = "volumeUnits";
	public static final String value = "value";
	public static final String compartment = "compartment";
	public static final String fast = "fast";
	public static final String reversible = "reversible";
	public static final String species = "species";
	public static final String boundaryCondition = "boundaryCondition";
	public static final String charge = "charge";
	public static final String hasOnlySubstanceUnits = "hasOnlySubstanceUnits";
	public static final String initialAmount = "initialAmount";
	public static final String spatialSizeUnits = "spatialSizeUnits";
	public static final String speciesType = "speciesType";
	public static final String denominator = "denominator";
	public static final String stoichiometry = "stoichiometry";
	public static final String constant = "constant";
	public static final String exponent = "exponent";
	public static final String kind = "kind";
	public static final String multiplier = "multiplier";
	public static final String offset = "offset";
	public static final String scale = "scale";
	public static final String listOfUnits = "listOfUnits";
	
	
	/**
	 * The previous value and the new value of the changed property in the
	 * {@link SBase}
	 */
	private Object oldValue, newValue;
	/**
	 * The name of the property that has changed.
	 */
	private String propertyName;

	/**
	 * @param source
	 * @param newValue
	 * @param oldValue
	 * @param propertyName
	 */
	public SBaseChangedEvent(SBase source, String propertyName,
			Object oldValue, Object newValue) {
		super(source);
		this.propertyName = propertyName;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * 
	 * @param sbaseChangedEvent
	 */
	public SBaseChangedEvent(SBaseChangedEvent sbaseChangedEvent) {
		super(sbaseChangedEvent.getSource());
		this.propertyName = sbaseChangedEvent.getPropertyName();
		this.oldValue = sbaseChangedEvent.getOldValue();
		this.newValue = sbaseChangedEvent.getNewValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public SBaseChangedEvent clone() {
		return new SBaseChangedEvent(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof SBaseChangedEvent) {
			SBaseChangedEvent sbce = (SBaseChangedEvent) o;
			boolean equal = sbce.getSource().equals(getSource());
			equal |= sbce.getPropertyName().equals(getPropertyName());
			equal |= sbce.getOldValue().equals(getOldValue());
			equal |= sbce.getNewValue().equals(getNewValue());
			return equal;
		}
		return false;
	}

	/**
	 * @return the newValue
	 */
	public Object getNewValue() {
		return newValue;
	}

	/**
	 * @return the oldValue
	 */
	public Object getOldValue() {
		return oldValue;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.EventObject#getSource()
	 */
	@Override
	public SBase getSource() {
		return (SBase) super.getSource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.EventObject#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"[source=%s, property=%s, oldValue=%s, newValue=%s]",
				getSource().toString(), getPropertyName(),
				(getOldValue() != null ? getOldValue() : "null"),
				getNewValue() != null ? getNewValue().toString() : "null");
	}
	
}
