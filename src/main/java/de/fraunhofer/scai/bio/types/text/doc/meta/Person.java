/*
 * Copyright 2018 Fraunhofer Institute SCAI, St. Augustin, Germany
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.fraunhofer.scai.bio.types.text.doc.meta;

import java.io.Serializable;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * @author klein
 *
 */
public class Person implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1899080439065479768L;
    private TextElement surname;
    private TextElement forename;
    private Affiliation affiliation;
    private TextElement degree;

    /**
     * getter for affiliation - gets Affiliation of the author. i.e. University of
     * Bonn
     * 
     * @return affiliation of the {@link Person}
     */
    public Affiliation getAffiliation() {
	return affiliation;
    }

    /**
     * getter for degree - gets Academic degree of the person, like B.Sc., M.Sc. or
     * Dr.
     * 
     * @return the degree of the {@link Person}
     */
    public TextElement getDegree() {
	return degree;
    }

    /**
     * getter for forename - gets Forename of the author. i.e. Max
     * 
     * @return the forename of the {@link Person}
     */
    public TextElement getForename() {
	return forename;
    }

    /**
     * getter for surname - gets Family name of the author. i.e. Musterman
     * 
     * @return the surname of the {@link Person}
     */
    public TextElement getSurname() {
	return surname;
    }

    /**
     * setter for affiliation - sets Affiliation of the author. i.e. University of
     * Bonn
     * 
     * @param affiliation value to set into the feature
     */
    public void setAffiliation(Affiliation affiliation) {
	this.affiliation = affiliation;
    }

    /**
     * setter for degree - sets Academic degree of the person, like B.Sc., M.Sc. or
     * Dr.
     * 
     * @param degree of the {@link Person} to set
     */
    public void setDegree(TextElement degree) {
	this.degree = degree;
    }

    /**
     * setter for forename - sets Forename of the author. i.e. Max
     * 
     * @param forename of the {@link Person} to set
     */
    public void setForename(TextElement forename) {
	this.forename = forename;
    }

    /**
     * setter for surname - sets Family name of the author. i.e. Musterman
     * 
     * @param surname of the {@link Person} to set
     */
    public void setSurname(TextElement surname) {
	this.surname = surname;
    }
}
