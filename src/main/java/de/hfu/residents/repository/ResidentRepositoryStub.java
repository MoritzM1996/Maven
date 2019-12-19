package de.hfu.residents.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import de.hfu.residents.domain.Resident;

public class ResidentRepositoryStub implements ResidentRepository{

	@Override
	public List<Resident> getResidents() {
		List<Resident> ret = new ArrayList<Resident>();
		ret.add(new Resident("Test1", "Familie1","strasse1","city1", new Date(10, 10, 10)));
		ret.add(new Resident("Test1", "Familie1","strasse1","city1", new Date(10, 10, 10)));
		ret.add(new Resident("Test2", "Familie2","strasse2","city2", null));
		ret.add(new Resident("Test3", "Familie3","strasse3","city3", new Date(12, 12, 12)));
		ret.add(new Resident("Moritz", "Mueller","HFU","Furti", new Date(13, 13, 13)));
		//ret.add(new Resident());
		return ret;
	}

}
