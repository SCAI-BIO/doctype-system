package de.fraunhofer.scai.bio.types.text.documentelement.meta;

import java.io.Serializable;

public class License implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -719683891725297487L;
    private String licenseName;
    private String licenseURL;
    private Date validUntil;
    private Person rightsHolder;

    /**
     * getter for licenseName - gets Name of the {@link License}, i.e. cc-by-sa.
     * 
     * @return value of the feature
     */
    public String getLicenseName() {
	return licenseName;
    }

    // *--------------*
    // * Feature: validUntil

    /**
     * getter for licenseURL - gets URL to the {@link License}.
     * 
     * @return the {@link License} URL
     */
    public String getLicenseURL() {
	return licenseURL;
    }

    /**
     * getter for rightsHolder
     * 
     * @return value of the feature
     */
    public Person getRightsHolder() {
	return rightsHolder;
    }

    /**
     * getter for validUntil - gets Date the {@link License} is valid unto.
     * 
     * @return the {@link Date} the {@link License} is valid unto
     */
    public Date getValidUntil() {
	return validUntil;
    }

    /**
     * setter for licenseName - sets Name of the license, i.e. cc-by-sa.
     * 
     * @param licenseName value to set into the feature
     */
    public void setLicenseName(String licenseName) {
	this.licenseName = licenseName;
    }

    /**
     * setter for licenseURL - sets URL to the {@link License}.
     * 
     * @param licenseURL to set into the feature
     */
    public void setLicenseURL(String licenseURL) {
	this.licenseURL = licenseURL;
    }

    /**
     * setter for the rightsHolder {@link Person} of the {@link License}
     * 
     * @param rightsHolder to set into the feature
     */
    public void setRightsHolder(Person rightsHolder) {
	this.rightsHolder = rightsHolder;
    }

    /**
     * setter for validUntil - sets {@link Date} the {@link License} is valid unto.
     * 
     * @param validUntil value to set into the feature
     */
    public void setValidUntil(Date validUntil) {
	this.validUntil = validUntil;
    }
}
