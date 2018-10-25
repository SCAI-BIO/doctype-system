package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;

public class DataTable implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -959679218635912715L;
    public static final String CONTENT_TYPE = "IAIS-Table";
    private String identifier;
    private String version;
    private String content;

    /**
     * 
     * @return the content
     */
    public String getContent() {
	return content;
    }

    /**
     * 
     * @return the content type
     */
    public String getContentType() {
	return CONTENT_TYPE;
    }

    /**
     * 
     * @return the identifier
     */
    public String getIdentifier() {
	return identifier;
    }

    /**
     * 
     * @return the version
     */
    public String getVersion() {
	return version;
    }

    /**
     * 
     * @param content the content string
     */
    public void setContent(String content) {
	this.content = content;
    }

    /**
     * 
     * @param identifier the identifier string
     */
    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    /**
     * 
     * @param version the version string to set
     */
    public void setVersion(String version) {
	this.version = version;
    }
}
