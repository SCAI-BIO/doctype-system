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

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import java.io.Serializable;
import java.util.ArrayList;
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
    private List<TextElement> publicationIds;
    private Title referenceTitle;
    private TextElement language;
    private TextElement referenceSource;
    private TextElement publicationType;
    private TextElement link;    // an uri

    /**
     * setter for author - sets
     *
     * @param author {@link Author} to add to the {@link List} of {@link Author}s
     */
    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<Author>();
        }
        this.authors.add(author);
    }

    /**
     * getter for authors - gets
     *
     * @return the {@link List} of {@link Author}s
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * setter for {@link Author}s
     *
     * @param authors the {@link List} of {@link Author}s
     */
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    // *--------------*
    // * Feature: titleText

    /**
     * getter for date - gets Date of the publication.
     *
     * @return the publication {@link Date} of the {@link Reference}
     */
    public Date getDate() {
        return date;
    }

    /**
     * setter for date - sets Date of the publication.
     *
     * @param date the publication {@link Date} of the {@link Reference} to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * getter for titleText - gets
     *
     * @return the {@link Title} of the {@link Reference}
     */
    public Title getTitle() {
        return this.referenceTitle;
    }

    /**
     * setter for titleText - sets
     *
     * @param title the {@link Title} of the {@link Reference}
     */
    public void setTitle(Title title) {
        this.referenceTitle = title;
    }

    /**
     * @return the language
     */
    public TextElement getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(TextElement language) {
        this.language = language;
    }

    /**
     * @return the referenceSource
     */
    public TextElement getReferenceSource() {
        return referenceSource;
    }

    /**
     * @param referenceSource the referenceSource to set
     */
    public void setReferenceSource(TextElement referenceSource) {
        this.referenceSource = referenceSource;
    }

    public List<TextElement> getPublicationIds() {
        return publicationIds;
    }

    public void setPublicationIds(List<TextElement> publicationIds) {
        this.publicationIds = publicationIds;
    }

    public void addPublicationId(TextElement publicationId) {
        if (this.publicationIds == null) {
            this.publicationIds = new ArrayList<TextElement>();
        }

        this.publicationIds.add(publicationId);
    }

    public TextElement getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(TextElement publicationType) {
        this.publicationType = publicationType;
    }

    /**
     * @return the link
     */
    public TextElement getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(TextElement link) {
        this.link = link;
    }

}
