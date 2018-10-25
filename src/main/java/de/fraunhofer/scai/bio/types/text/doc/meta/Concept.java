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
import java.util.Map;

/**
 * @author klein
 *
 */
public class Concept implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7443533790762224432L;
    private String preferredLabel;
    private String altLabel;
    private String hiddenLabel;
    private String identifier;

    private String identifierSource;

    private String[] identifierWithSource;
    private List<String> altLabels;
    private List<String> hiddenLabels;

    /**
     * 
     * @param altLabel the alternate label to set
     */
    public void addAltLabel(String altLabel) {
	if (altLabels == null)
	    altLabels = new ArrayList<String>();
	altLabels.add(altLabel);
    }

    /**
     * TODO check index!!!
     * 
     * @param index       index
     * @param hiddenLabel hidden label
     */
    public void addHiddenLabel(int index, String hiddenLabel) {
	if (this.hiddenLabels == null)
	    this.hiddenLabels = new ArrayList<String>();
	this.hiddenLabels.add(hiddenLabel);
    }
    
    public String getAltLabel() {
	return this.altLabel;
    }

    public List<String> getAltLabels() {
	return this.altLabels;
    }

    public String getHiddenLabel() {
	return this.hiddenLabel;
    }

    public List<String> getHiddenLabels() {
	return this.hiddenLabels;
    }

    public String getIdentifier() {
	return this.identifier;
    }

    public String getIdentifierSource() {
	return this.identifierSource;
    }

    public String[] getIdentifierWithSource() {
	return this.identifierWithSource;
    }

    public String getPrefLabel() {
	return this.preferredLabel;
    }

    public void setAltLabel(String altlabel) {
	this.altLabel = altlabel;
    }

    /**
     * Set the {@link List} of alternate Labels.
     * 
     * @param altLabels the {@link List} of Labels.
     */
    public void setAltLabels(List<String> altLabels) {
	this.altLabels = altLabels;
    }

    public void setHiddenLabel(String hiddenlabel) {
	this.hiddenLabel = hiddenlabel;
    }

    /**
     * 
     * @param hiddenLabels the {@link List} of hidden labels to set
     */
    public void setHiddenLabels(List<String> hiddenLabels) {
	this.hiddenLabels = hiddenLabels;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public void setIdentifierSource(String identifierSource) {
	this.identifierSource = identifierSource;
    }

    public void setIdentifierWithSource(String[] identifierwithsource) {
	this.identifierWithSource = identifierwithsource;
    }

    public void setPrefLabel(String label) {
	this.preferredLabel = label;
    }

}
