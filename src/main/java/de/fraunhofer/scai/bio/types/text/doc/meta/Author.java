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

/**
 * The autor(s) of the document.
 */
public class Author implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3790237726324694559L;
    private Person author;

    /**
     * getter for author - gets
     * 
     * 
     * @return author The {@link Person}
     */
    public Person getAuthor() {
	return this.author;
    }

    /**
     * setter for author - sets
     * 
     * 
     * @param author the {@link Person} to set
     */
    public void setAuthor(Person author) {
	this.author = author;
    }
    
}
