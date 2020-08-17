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
package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import de.fraunhofer.scai.bio.types.text.doc.meta.Annotation;

import lombok.Data;

/**
 *
 */
@Data public class TextElement implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9172199956790819065L;

    /**
     * The text (plain text without any other object mapping).
     */
    private String text;

    private UUID uuid;

    private Set<Annotation> annotations;

    /**
     * @param annotation the annotation to add
     */
    public void addAnnotation(Annotation annotation) {
        if (this.annotations == null) {
            this.annotations = new HashSet<>();
        }
        this.annotations.add(annotation);
    }

    /**
     * Creates a new {@link UUID}.
     */
    public void setUuid() {
        this.uuid = UUID.randomUUID();
    }

}
