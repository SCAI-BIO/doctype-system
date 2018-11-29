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
package de.fraunhofer.scai.bio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author marc
 *
 *         provenance information:
 * 
 *         Software provenance encompasses the origin of software and its
 *         licensing terms. For example, when incorporating a free, open source
 *         or proprietary software component in an application, one may wish to
 *         understand its provenance to ensure that licensing requirements are
 *         fulfilled and that other software characteristics can be understood.
 * 
 *         Data provenance covers the provenance of computerized data. There are
 *         two main aspects of data provenance: ownership of the data and data
 *         usage. Ownership will tell the user who is responsible for the source
 *         of the data, ideally including information on the originator of the
 *         data. Data usage gives details regarding how the data has been used
 *         and modified and often includes information on how to cite the data
 *         source or sources. Data provenance is of particular concern with
 *         electronic data, as data sets are often modified and copied without
 *         proper citation or acknowledgement of the originating data set.
 *         Databases make it easy to select specific information from data sets
 *         and merge this data with other data sources without any documentation
 *         of how the data was obtained or how it was modified from the original
 *         data set or sets.
 */
public class Provenance implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2419659801198983422L;
    private String license;
    // TODO: map version nummer to arty version number
    private String version = "1.0";
    private String source; // file, database, ...
    private Date date;
    private String collection; // eg. a collection of documents

    private List<String> comments;
    
    /**
     * constructor
     */
    public Provenance() {
    }

    /**
     * 
     * @return the collection the Document is stored
     */
    public String getCollection() {
	return collection;
    }

    /**
     * 
     * @return the {@link Date} this {@link Document} was generated
     */
    public Date getDate() {
	return date;
    }

    /**
     * 
     * @return the License of the {@link Document}
     */
    public String getLicense() {
	return license;
    }

    /**
     * 
     * @return the source of the {@link Document}
     */
    public String getSource() {
	return source;
    }

    /**
     * 
     * @return the version of the {@link Document}
     */
    public String getVersion() {
	return version;
    }

    /**
     * The corpus/collection the {@link Document} belongs to
     * @param corpus the corpus/collection
     */
    public void setCollection(String corpus) {
	this.collection = corpus;
    }

    /**
     * Sets the generation {@link Date} 
     * @param date the {@link Date} this {@link Document} was generated
     */
    public void setDate(Date date) {
	this.date = date;
    }

    /**
     * Sets the liccense string.
     * @param license the license string
     */
    public void setLicense(String license) {
	this.license = license;
    }

    /**
     * Sets the source 
     * @param source the source string
     */
    public void setSource(String source) {
	this.source = source;
    }
    
    /**
     * Sets the version string
     * @param version the version string
     */
    public void setVersion(String version) {
	this.version = version;
    }

    /**
     * @return the comments
     */
    public List<String> getComments() {
	return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void addComment(String comment) {
	if (this.comments == null)
	    this.comments = new ArrayList<String>();
	this.comments.add(comment);
    }
    
    /**
     * @param comments the comments to set
     */
    public void setComments(List<String> comments) {
	this.comments = comments;
    }
}
