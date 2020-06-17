/* First created by JCasGen Mon Jul 16 13:21:36 CEST 2012 */
package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;

import lombok.Data;

/**
 *
 */
@Data public class ImageContent implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1909910975893097612L;
    private int height;
    private int width;
    private String encoding;
    private float compressionLevel;
    private int bitDepth;
    private String colorModel;
    private byte[] content;

}
