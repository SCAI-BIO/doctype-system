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
import java.util.Date;
import java.util.List;

/**
 * Reference to cited literature.
 */
public class Reference implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4484585069783542995L;
    private List<Author> authors;
    private Date date;
    private String referenceDoi;
    private String isbn;
    private Title referenceTitle;

    /**
     * setter for author - sets
     * 
     * 
     * @param author {@link Author} to add to the {@link List} of {@link Author}s
     */
    public void addAuthor(Author author) {
	if (this.authors == null)
	    this.authors = new ArrayList<Author>();
	this.authors.add(author);
    }

    /**
     * getter for authors - gets
     * 
     * 
     * @return the {@link List} of {@link Author}s
     */
    public List<Author> getAuthors() {
	return authors;
    }

    /**
     * getter for date - gets Date of the publication.
     * 
     * 
     * @return the publication {@link Date} of the {@link Reference}
     */
    public Date getDate() {
	return date;
    }

    // *--------------*
    // * Feature: titleText

    /**
     * getter for doi - gets Digital Object Identifier of the referenced document.
     * 
     * 
     * @return the Digital Object Identifier of the {@link Reference}
     */
    public String getDoi() {
	return referenceDoi;
    }

    /**
     * getter for isbn - gets International Standard Book Number of the referenced
     * document.
     * 
     * 
     * @return the ISBN of the {@link Reference}
     */
    public String getIsbn() {
	return isbn;
    }

    /**
     * getter for titleText - gets
     * 
     * 
     * @return the {@link Title} of the {@link Reference}
     */
    public Title getTitle() {
	return this.referenceTitle;
    }

    /**
     * setter for {@link Author}s
     * 
     * 
     * @param authors the {@link List} of {@link Author}s
     */
    public void setAuthors(List<Author> authors) {
	this.authors = authors;
    }

    /**
     * setter for date - sets Date of the publication.
     * 
     * 
     * @param date the publication {@link Date} of the {@link Reference} to set
     */
    public void setDate(Date date) {
	this.date = date;
    }

    /**
     * setter for doi - sets Digital Object Identifier of the referenced document.
     * 
     * 
     * @param doi the Digital Object Identifier of the {@link Reference}
     */
    public void setDoi(String doi) {
	this.referenceDoi = doi;
    }

    /**
     * setter for isbn - sets International Standard Book Number of the referenced
     * document.
     * 
     * 
     * @param isbn the ISBN of the {@link Reference} to set
     */
    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    /**
     * setter for titleText - sets
     * 
     * 
     * @param title the {@link Title} of the {@link Reference}
     */
    public void setTitle(Title title) {
	this.referenceTitle = title;
    }
}
