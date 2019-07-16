/* First created by JCasGen Mon Jul 16 13:21:36 CEST 2012 */
package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;

/**
 *
 */
public class ImageContent implements Serializable {

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

    /**
     * getter for bitDepth - gets
     *
     * @return the bit depth
     */
    public int getBitDepth() {
        return this.bitDepth;
    }

    /**
     * setter for bitDepth - sets
     *
     * @param bitDepth the bit depth
     */
    public void setBitDepth(int bitDepth) {
        this.bitDepth = bitDepth;
    }

    // *--------------*
    // * Feature: encoding

    /**
     * getter for colorModel - gets
     *
     * @return the color model
     */
    public String getColorModel() {
        return colorModel;
    }

    /**
     * setter for colorModel - sets
     *
     * @param colorModel the color model
     */
    public void setColorModel(String colorModel) {
        this.colorModel = colorModel;
    }

    // *--------------*
    // * Feature: compressionLevel

    /**
     * getter for compressionLevel - gets
     *
     * @return the compressionLevel
     */
    public float getCompressionLevel() {
        return compressionLevel;
    }

    /**
     * setter for compressionLevel - sets
     *
     * @param compressionLevel the compressionLevel
     */
    public void setCompressionLevel(float compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    /**
     * getter for contents - gets
     *
     * @return the contents
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * setter for contents - sets
     *
     * @param contents the contents
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * getter for encoding - gets
     *
     * @return the encoding
     */
    public String getEncoding() {
        return this.encoding;
    }

    /**
     * setter for encoding - sets
     *
     * @param encoding the encoding
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * getter for height - gets
     *
     * @return height the heigth
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * setter for height - sets
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * getter for width - gets
     *
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * setter for width - sets
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("(%s x %s)", width, height);
    }

}
