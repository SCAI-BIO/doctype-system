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
 * TODO implement/enhance this class (organization, address, ...)
 * @author klein
 *
 */
public class Affiliation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3254832719650327389L;

    private TextElement organization;
    private List<String> affiliationInfos;

    /**
     * @return the organization
     */
    public TextElement getOrganization() {
	return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(TextElement organization) {
	this.organization = organization;
    }

    /**
     * @return the affiliationInfo
     */
    public List<String> getAffiliationInfo() {
	return affiliationInfos;
    }

    /**
     * @param affiliationInfo the affiliationInfo to set
     */
    public void addAffiliationInfo(String affiliationInfo) {
	if (this.affiliationInfos == null)
	    this.affiliationInfos = new ArrayList<String>();
	this.affiliationInfos.add(affiliationInfo);
    }
    
    /**
     * @param affiliationInfo the affiliationInfo to set
     */
    public void setAffiliationInfos(List<String> affiliationInfos) {
	this.affiliationInfos = affiliationInfos;
    }
    
}
