package de.hfu.residents.service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.sql.Date;
import org.junit.Test;
import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepositoryStub;
import static org.easymock.EasyMock.*;

public class ResidentServiceTest {
	@Test
	public void testGetFiltered() {
		
		List<Resident> ret = new ArrayList<Resident>();
		ret.add(new Resident("Test1", "Familie1","strasse1","city1", new Date(10, 10, 10)));
		ret.add(new Resident("Test1", "Familie1","strasse1","city1", new Date(10, 10, 10)));
		ret.add(new Resident("Test2", "Familie2","strasse2","city2", null));
		ret.add(new Resident("Test3", "Familie3","strasse3","city3", new Date(12, 12, 12)));
		ret.add(new Resident("Moritz", "Mueller","HFU","Furti", new Date(13, 13, 13)));
	
		BaseResidentService service = new BaseResidentService();
		ResidentRepositoryStub stub = createMock(ResidentRepositoryStub.class); 
		service.setResidentRepository(stub);

		replay(stub);
		
		
		
		List<Resident> res = service.getFilteredResidentsList(new Resident());
		
		List<Resident> res1 = service.getFilteredResidentsList(new Resident("Te*", "F*", "s*", "c*", null));
		
		
		List<Resident> res2 = service.getFilteredResidentsList(new Resident("M*", "*", "*", "*", null));
		
		
		verify(stub);
		
		assertThat(res.size(), equalTo(4)); 
		assertEquals(res1.size(),3,3);
		assertEquals(res2.size(),1,1); 
	}

	@Test
	public void testGetUnique() {
		ResidentRepositoryStub stub = new ResidentRepositoryStub();

		BaseResidentService service = new BaseResidentService();
		service.setResidentRepository(stub);
		
		try {
			Resident res = service.getUniqueResident(new Resident("M*", "*", "*", "*", null));
			fail("Darfnicht kommen");
		}catch (Exception r) {
			System.out.println(r.getMessage());
		}
		try {
			Resident res = service.getUniqueResident(new Resident("Test1", "Familie1","strasse1","city1", new Date(10, 10, 10)));
			fail("Darfnicht kommen");
		}catch (Exception r) {
			System.out.println(r.getMessage());
		}
		try {
			Resident res = service.getUniqueResident(new Resident("Moritz", "Mueller","HFU","Furti", new Date(13, 13, 13)));
			assertEquals(res.getGivenName(),"Moritz");
		}catch (Exception r) {
			System.out.println(r.getMessage());
			fail("Darf nicht kommen");
		}
	}
	
	/*@Test
	public void testGetFiltered() {
		ResidentRepositoryStub stub = new ResidentRepositoryStub();

		BaseResidentService service = new BaseResidentService();
		service.setResidentRepository(stub);

		
		List<Resident> res = service.getFilteredResidentsList(new Resident());
		assertEquals(res.size(),4,4); //Gibt komplette Liste wieder
		
		for(Resident r : res)
			System.out.println("Resdentname: " + r.getGivenName() + " " + r.getFamilyName() + " " + r.getStreet() + " " + r.getCity() + " " + r.getDateOfBirth());
		
		List<Resident> res1 = service.getFilteredResidentsList(new Resident("Te*", "F*", "s*", "c*", null));
		assertEquals(res1.size(),3,3);
		
		List<Resident> res2 = service.getFilteredResidentsList(new Resident("M*", "*", "*", "*", null));
		assertEquals(res2.size(),1,1); 
	}

	@Test
	public void testGetUnique() {
		ResidentRepositoryStub stub = new ResidentRepositoryStub();

		BaseResidentService service = new BaseResidentService();
		service.setResidentRepository(stub);
		
		try {
			Resident res = service.getUniqueResident(new Resident("M*", "*", "*", "*", null));
			fail("Darfnicht kommen");
		}catch (Exception r) {
			System.out.println(r.getMessage());
		}
		try {
			Resident res = service.getUniqueResident(new Resident("Test1", "Familie1","strasse1","city1", new Date(10, 10, 10)));
			fail("Darfnicht kommen");
		}catch (Exception r) {
			System.out.println(r.getMessage());
		}
		try {
			Resident res = service.getUniqueResident(new Resident("Moritz", "Mueller","HFU","Furti", new Date(13, 13, 13)));
			assertEquals(res.getGivenName(),"Moritz");
		}catch (Exception r) {
			System.out.println(r.getMessage());
			fail("Darf nicht kommen");
		}
	}*/
}
