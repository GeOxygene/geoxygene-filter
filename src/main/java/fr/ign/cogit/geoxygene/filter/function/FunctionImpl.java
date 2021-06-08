/*
 * This file is part of the GeOxygene project source files. GeOxygene aims at
 * providing an open framework which implements OGC/ISO specifications for the
 * development and deployment of geographic (GIS) applications. It is a open
 * source contribution of the COGIT laboratory at the Institut Géographique
 * National (the French National Mapping Agency). See:
 * http://oxygene-project.sourceforge.net Copyright (C) 2005 Institut
 * Géographique National This library is free software; you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the License,
 * or any later version. This library is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with this library (see file
 * LICENSE if present); if not, write to the Free Software Foundation, Inc., 59
 * Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package fr.ign.cogit.geoxygene.filter.function;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import fr.ign.cogit.geoxygene.filter.expression.Expression;
import fr.ign.cogit.geoxygene.filter.expression.ExpressionFactory;
import fr.ign.cogit.geoxygene.filter.expression.Function;

/**
 * @author Julien Perret
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Function")
public class FunctionImpl extends Function {
    String fallbackValue;

    @Override
    public String getFallbackValue() {
        return this.fallbackValue;
    }

    @Override
    public void setFallbackValue(String fallbackValue) {
        this.fallbackValue = fallbackValue;
    }

    @XmlAttribute
    String name = "Function default implementation"; //$NON-NLS-1$

    @Override
    public String getName() {
        return this.name;
    }

    @XmlElementRefs(@XmlElementRef)
    List<Expression> parameters = new ArrayList<Expression>();

    @Override
    public List<Expression> getParameters() {
        return this.parameters;
    }

    /**
     * Affecte la valeur de l'attribut parameters.
     * 
     * @param parameters
     *            l'attribut parameters à affecter
     */
    @Override
    public void setParameters(List<Expression> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Object evaluate(Object object) {
        Function function = ExpressionFactory.createFunction(this.name);
        if (function != null) {
            function.getParameters().addAll(this.parameters);
            return function.evaluate(object);
        }
        return this.getFallbackValue();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((this.fallbackValue == null) ? 0 : this.fallbackValue
                        .hashCode());
        result = prime * result
                + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result
                + ((this.parameters == null) ? 0 : this.parameters.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        FunctionImpl other = (FunctionImpl) obj;
        if (this.fallbackValue == null) {
            if (other.fallbackValue != null) {
                return false;
            }
        } else if (!this.fallbackValue.equals(other.fallbackValue)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.parameters == null) {
            if (other.parameters != null) {
                return false;
            }
        } else if (!this.parameters.equals(other.parameters)) {
            return false;
        }
        return true;
    }

}
