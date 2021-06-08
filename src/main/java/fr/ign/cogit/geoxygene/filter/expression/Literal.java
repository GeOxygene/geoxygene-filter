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

package fr.ign.cogit.geoxygene.filter.expression;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Julien Perret
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Literal")
public class Literal extends Expression {

  /**
	 * 
	 */
  public Literal() {
  }

  /**
   * @param value
   */
  public Literal(String value) {
    this.setValue(value);
  }

  @XmlMixed
  private final String[] value = new String[1];

  /**
   * @return the value of the Literal
   */
  public String getValue() {
    return this.value[0];
  }

  /**
   * @param value
   */
  public void setValue(String value) {
    this.value[0] = value;
  }

  @Override
  public Object evaluate(Object object) {
    return new BigDecimal(this.getValue());
  }

  @Override
  public String toString() {
    return this.getValue();
  }

  // @Override
  // public boolean equals(Object o) {
  // if (!Literal.class.isAssignableFrom(o.getClass())) {
  // return false;
  // }
  // Literal l = (Literal) o;
  // return l.getValue().equals(this.getValue());
  // }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(this.value);
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
    Literal other = (Literal) obj;
    if (!this.getValue().equals(other.getValue())) {
      return false;
    }
    return true;
  }
}
