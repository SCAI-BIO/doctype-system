package de.fraunhofer.scai.bio.types.text.doc.meta;

import java.io.Serializable;

import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

import lombok.Data;

@Data public class License implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -719683891725297487L;
    private TextElement licenseName;
    private TextElement licenseURL;
    private Date validUntil;
    private Person rightsHolder;

}
