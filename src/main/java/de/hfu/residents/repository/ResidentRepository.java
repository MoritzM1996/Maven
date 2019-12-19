package de.hfu.residents.repository;

import java.util.List;

import de.hfu.residents.domain.Resident;

/**
 * @author Stefan Betermieux
 */
public interface ResidentRepository {

  List<Resident> getResidents();

}