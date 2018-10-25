/* First created by JCasGen Mon Jul 16 13:21:36 CEST 2012 */
package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;
import java.util.List;

/** 
 * 
 */
public class ImageContent implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1909910975893097612L;
    int height;
    int width;
    String encoding;
    float compressionLevel;
    int bitDepth;
    String colorModel;
    List<?> contents;

    /**
     * getter for bitDepth - gets
     * 
     * @return the bit depth
     */
    public int getBitDepth() {
	return this.bitDepth;
    }

    /**
     * getter for colorModel - gets
     * 
     * @return the color model
     */
    public String getColorModel() {
	return colorModel;
    }

    // *--------------*
    // * Feature: encoding

    /**
     * getter for compressionLevel - gets
     * 
     * @return the compressionLevel
     */
    public float getCompressionLevel() {
	return compressionLevel;
    }

    /**
     * getter for contents - gets
     * 
     * @return the contents
     */
    public List<?> getContents() {
	return contents;
    }

    // *--------------*
    // * Feature: compressionLevel

    /**
     * getter for encoding - gets
     * 
     * @return the encoding
     */
    public String getEncoding() {
	return this.encoding;
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
     * getter for width - gets
     * 
     * @return width
     */
    public int getWidth() {
	return this.width;
    }

    /**
     * setter for bitDepth - sets
     * 
     * @param bitDepth the bit depth
     */
    public void setBitDepth(int bitDepth) {
	this.bitDepth = bitDepth;
    }

    /**
     * setter for colorModel - sets
     * 
     * @param colorModel the color model
     */
    public void setColorModel(String colorModel) {
	this.colorModel = colorModel;
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
     * setter for contents - sets
     * 
     * @param contents the contents
     */
    public void setContents(List<?> contents) {
	this.contents = contents;
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
     * setter for height - sets
     * 
     * @param height the height
     */
    public void setHeight(int height) {
	this.height = height;
    }

    /**
     * setter for width - sets
     * 
     * @param width the width
     */
    public void setWidth(int width) {
	this.width = width;
    }
}
