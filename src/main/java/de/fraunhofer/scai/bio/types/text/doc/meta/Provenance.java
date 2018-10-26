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

import java.util.Date;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

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
public class Provenance {

    License license;
    TextElement version;
    TextElement source; // file, database, ...
    Date date;
    TextElement collection; // eg. a collection of documents

    /**
     * constructor
     */
    public Provenance() {
    }

    public TextElement getCollection() {
	return collection;
    }

    public Date getDate() {
	return date;
    }

    public License getLicense() {
	return license;
    }

    public TextElement getSource() {
	return source;
    }

    public TextElement getVersion() {
	return version;
    }


    public void setCollection(TextElement corpus) {
	this.collection = corpus;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public void setLicense(License license) {
	this.license = license;
    }

    public void setSource(TextElement source) {
	this.source = source;
    }

    public void setVersion(TextElement version) {
	this.version = version;
    }
}
