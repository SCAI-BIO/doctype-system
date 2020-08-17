package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;

import lombok.Data;

@Data public class DataTable implements Serializable {

    public static final String CONTENT_TYPE = "IAIS-Table";
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -959679218635912715L;
    private TextElement identifier;
    private TextElement version;
    private TextElement content;

    /**
     * @return the content type
     */
    @Deprecated
    public String getContentType() {
        return CONTENT_TYPE;
    }

}
