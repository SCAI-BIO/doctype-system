package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;

public class DataTable implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -959679218635912715L;
    public static final String CONTENT_TYPE = "IAIS-Table";
    private TextElement identifier;
    private TextElement version;
    private TextElement content;

    /**
     * 
     * @return the content
     */
    public TextElement getContent() {
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
    public TextElement getIdentifier() {
	return identifier;
    }

    /**
     * 
     * @return the version
     */
    public TextElement getVersion() {
	return version;
    }

    /**
     * 
     * @param content the content string
     */
    public void setContent(TextElement content) {
	this.content = content;
    }

    /**
     * 
     * @param identifier the identifier string
     */
    public void setIdentifier(TextElement identifier) {
	this.identifier = identifier;
    }

    /**
     * 
     * @param version the version string to set
     */
    public void setVersion(TextElement version) {
	this.version = version;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	return String.format("%s (%s)\n%s", identifier.getText(), version.getText(), content.getText());
    }

}
