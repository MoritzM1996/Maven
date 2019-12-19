package de.hfu.residents.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Stefan Betermieux
 *
 */
public class Resident implements Serializable {

  private String givenName;
  private String familyName;
  private String street;
  private String city;
  private Date dateOfBirth;
  
  public Resident() {
    super();
  }
  
  public Resident(String givenName, String familyName, String street, String city, Date dateOfBirth) {
    super();
    this.givenName = givenName;
    this.familyName = familyName;
    this.street = street;
    this.city = city;
    this.dateOfBirth = dateOfBirth;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
  
  
  
}
