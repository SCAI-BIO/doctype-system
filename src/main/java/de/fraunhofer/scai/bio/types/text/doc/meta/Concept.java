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
import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * @author klein
 *
 */
public class Concept implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7443533790762224432L;
    private TextElement preferredLabel;
    private TextElement altLabel;
    private TextElement hiddenLabel;
    private TextElement identifier;

    private TextElement identifierSource;

    private TextElement[] identifierWithSource;
    private List<TextElement> altLabels;
    private List<TextElement> hiddenLabels;

    /**
     * 
     * @param altLabel the alternate label to set
     */
    public void addAltLabel(TextElement altLabel) {
	if (altLabels == null)
	    altLabels = new ArrayList<TextElement>();
	altLabels.add(altLabel);
    }

    /**
     * TODO check index!!!
     * 
     * @param index       index
     * @param hiddenLabel hidden label
     */
    public void addHiddenLabel(int index, TextElement hiddenLabel) {
	if (this.hiddenLabels == null)
	    this.hiddenLabels = new ArrayList<TextElement>();
	this.hiddenLabels.add(hiddenLabel);
    }
    
    public TextElement getAltLabel() {
	return this.altLabel;
    }

    public List<TextElement> getAltLabels() {
	return this.altLabels;
    }

    public TextElement getHiddenLabel() {
	return this.hiddenLabel;
    }

    public List<TextElement> getHiddenLabels() {
	return this.hiddenLabels;
    }

    public TextElement getIdentifier() {
	return this.identifier;
    }

    public TextElement getIdentifierSource() {
	return this.identifierSource;
    }

    public TextElement[] getIdentifierWithSource() {
	return this.identifierWithSource;
    }

    public TextElement getPrefLabel() {
	return this.preferredLabel;
    }

    public void setAltLabel(TextElement altlabel) {
	this.altLabel = altlabel;
    }

    /**
     * Set the {@link List} of alternate Labels.
     * 
     * @param altLabels the {@link List} of Labels.
     */
    public void setAltLabels(List<TextElement> altLabels) {
	this.altLabels = altLabels;
    }

    public void setHiddenLabel(TextElement hiddenlabel) {
	this.hiddenLabel = hiddenlabel;
    }

    /**
     * 
     * @param hiddenLabels the {@link List} of hidden labels to set
     */
    public void setHiddenLabels(List<TextElement> hiddenLabels) {
	this.hiddenLabels = hiddenLabels;
    }

    public void setIdentifier(TextElement identifier) {
	this.identifier = identifier;
    }

    public void setIdentifierSource(TextElement identifierSource) {
	this.identifierSource = identifierSource;
    }

    public void setIdentifierWithSource(TextElement[] identifierwithsource) {
	this.identifierWithSource = identifierwithsource;
    }

    public void setPrefLabel(TextElement label) {
	this.preferredLabel = label;
    }

}
