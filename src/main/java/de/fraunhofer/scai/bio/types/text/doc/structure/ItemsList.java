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
package de.fraunhofer.scai.bio.types.text.documentelement.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An enumeration of items.
 */
public class ItemsList implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3151038534425542434L;
    private List<String> items;

    /**
     * getter for items - gets Items of the list. Every item is a text string.
     * 
     * @return value of the feature
     */
    public java.util.List<String> getItems() {
	return this.items;
    }
    
    /**
     * setter for items - sets Items of the list. Every item is a text string.
     * 
     * 
     * @param v value to set into the feature
     */
    public void addItem(String item) {
	if (this.items == null) {
	    this.items = new ArrayList<String>();
	}
	this.items.add(item);
    }
    
    /**
     * setter for items - sets Items of the list. Every item is a text string.
     * 
     * 
     * @param v value to set into the feature
     */
    public void setItems(java.util.List<String> v) {
	this.items = v;
    }

}
