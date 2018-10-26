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
 * Bibliographic informations about the document itself. Like titleText, author,
 * etc.
 */
public class Bibliographic implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7449243668495438139L;
    private TextElement documentAbstract;
    private List<Author> authors;
    private Date pubDate;
    private List<License> licenses;
    private Title title;
    private TextElement language;
    private TextElement source;
    private TextElement issn;

    /**
     * @param author the {@link Author} to add to the {@link List} of authors
     */
    public void addAuthor(Author author) {
	if (this.authors == null)
	    this.authors = new ArrayList<Author>();
	this.authors.add(author);
    }

    /**
     * @param license the {@link License} to add
     */
    public void addLicense(License license) {
	if (this.licenses == null)
	    this.licenses = new ArrayList<License>();
	this.licenses.add(license);
    }

    /**
     * @return the first {@link Author} of list
     */
    public Author getAuthor() {
	if (this.authors != null && !this.authors.isEmpty()) {
	    return this.authors.get(0);
	}
	return null;
    }

    /**
     * @return the {@link List} of {@link Author}s
     */
    public List<Author> getAuthors() {
	return authors;
    }

    /**
     * @return the document {@link Abstract}
     */
    public TextElement getDocumentAbstract() {
	return documentAbstract;
    }

    /**
     * @return the issn
     */
    public TextElement getIssn() {
	return issn;
    }

    /**
     * @return the language
     */
    public TextElement getLanguage() {
	return language;
    }

    /**
     * @return the first {@link License} of list
     */
    public License getLicense() {
	if (this.licenses != null && !this.licenses.isEmpty()) {
	    return this.licenses.get(0);
	}
	return null;
    }

    /**
     * @return the {@link List} of {@link License}s
     */
    public List<License> getLicenses() {
	return licenses;
    }

    /**
     * @return the publication {@link Date}
     */
    public Date getPubDate() {
	return pubDate;
    }

    /**
     * @return the source
     */
    public TextElement getSource() {
	return source;
    }

    /**
     * @return the {@link Title}
     */
    public Title getTitle() {
	return this.title;
    }

    /**
     * @param authors the {@link List} of {@link Author}s to set
     */
    public void setAuthors(List<Author> authors) {
	this.authors = authors;
    }

    /**
     * @param documentAbstract the document {@link Abstract} to set
     */
    public void setDocumentAbstract(TextElement documentAbstract) {
	this.documentAbstract = documentAbstract;
    }

    /**
     * @param issn the issn to set
     */
    public void setIssn(TextElement issn) {
	this.issn = issn;
    }

    public void setLanguage(TextElement language) {
	this.language = language;
    }

    /**
     * @param licenses the {@link List} of {@link License}s to set
     */
    public void setLicenses(List<License> licenses) {
	this.licenses = licenses;
    }

    /**
     * @param pubDate the publication {@link Date} to set
     */
    public void setPubDate(Date pubDate) {
	this.pubDate = pubDate;
    }

    public void setSource(TextElement source) {
	this.source = source;

    }

    /**
     * @param title the {@link Title} to set
     */
    public void setTitle(Title title) {
	this.title = title;
    }

}
