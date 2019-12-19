package de.hfu.residents.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

/**
 * @author Stefan Betermieux
 *
 */

public class BaseResidentService implements ResidentService {

  private ResidentRepository residentRepository;
  
  public BaseResidentService() {
  }
  
  /**
   * @param filterResident
   * @throws ResidentServiceException
   */
  @Override
	public Resident getUniqueResident(Resident filterResident) throws ResidentServiceException {
    if (containsWildcards(filterResident)) {
      throw new ResidentServiceException("Wildcards (*) sind nicht erlaubt!");
    }
    List<Resident> residents = getFilteredResidentsList(filterResident);
    if (residents.size() == 1) {
      return residents.get(0);
    } else {
      throw new ResidentServiceException("Suchanfrage lieferte kein eindeutiges Ergebnis!");
    }
  }

  /**
   * @param filterResident
   * @return
   */
  @Override
	public List<Resident> getFilteredResidentsList(Resident filterResident) {
    List<Resident> filteredResidents = new ArrayList<Resident>();

    Pattern givenNamePattern = compileFilterPattern(filterResident.getGivenName());
    Pattern familyNamePattern = compileFilterPattern(filterResident.getFamilyName());
    Pattern streetPattern = compileFilterPattern(filterResident.getStreet());
    Calendar calendarFilter = null;
    if (filterResident.getDateOfBirth() != null) {
      calendarFilter = Calendar.getInstance();
      calendarFilter.setTime(filterResident.getDateOfBirth());      
    }
    
    for (Resident resident: residentRepository.getResidents()) {
      if (matchesFilter(resident, givenNamePattern, familyNamePattern, streetPattern, calendarFilter)) {
        filteredResidents.add(resident);
      }
    }
    return filteredResidents;
  }
  
  private boolean containsWildcards(Resident data) {
    if ((data.getGivenName() != null) && (data.getGivenName().indexOf("*") != -1)) {
      return true;
    }
    if ((data.getFamilyName() != null) && (data.getFamilyName().indexOf("*") != -1)) {
      return true;
    }
    if ((data.getStreet() != null) && (data.getStreet().indexOf("*") != -1)) {
      return true;
    }
    return false;
  }
  
  private Pattern compileFilterPattern(String attribute) {
    if ((attribute == null) || (attribute.trim().length() == 0)) {
      return null;
    } else {
      return Pattern.compile(attribute.trim().toLowerCase().replaceAll("\\*", ".*?"));      
    }
  }
  
  private boolean matchesFilter(Resident current, Pattern givenNamePattern, Pattern familyNamePattern, Pattern streetPattern, Calendar calendarFilter) {
    if (!matchesAttribute(current.getGivenName(), givenNamePattern)) {
      return false;
    }
    if (!matchesAttribute(current.getFamilyName(), familyNamePattern)) {
      return false;
    }
    if (!matchesAttribute(current.getStreet(), streetPattern)) {
      return false;
    }
    if (calendarFilter != null) {
      Calendar calendarCurrent = Calendar.getInstance();
      calendarCurrent.setTime(current.getDateOfBirth());
      if (calendarFilter.get(Calendar.YEAR) != calendarCurrent.get(Calendar.YEAR)) {
        return false;
      }
      if (calendarFilter.get(Calendar.MONTH) != calendarCurrent.get(Calendar.MONTH)) {
        return false;
      }
      if (calendarFilter.get(Calendar.DAY_OF_MONTH) != calendarCurrent.get(Calendar.DAY_OF_MONTH)) {
        return false;
      }      
    }
    return true;
  }

  private boolean matchesAttribute(String originalCurrent, Pattern pattern) {
    String current = (originalCurrent == null ? null: originalCurrent.trim().toLowerCase());
    if (pattern == null) {
      return true;
    }
    Matcher matcher = pattern.matcher(current);
    if (matcher.matches()) {
      return true;
    }
    return false;
  }

  public void setResidentRepository(ResidentRepository residentRepository) {
    this.residentRepository = residentRepository;
  }
   
}
