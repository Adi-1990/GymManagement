package com.gym;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.dal.EquipmentsDAO;
import com.model.impl.Equipment;

public class EquipmentsDAOTest {

	@Test
	public void loadEquipmentsTest() {

		EquipmentsDAO dao = new EquipmentsDAO();
		List<Equipment> equipments = dao.loadEquipments("./equipments.txt");
		assertEquals(1, equipments.size());
	}

	@Test
	public void saveEquipmentsTest() {

		Equipment e1 = new Equipment(1, "aaa");
		Equipment e2 = new Equipment(2, "bbb");

		List<Equipment> listOfEquipments = new ArrayList<Equipment>();
		listOfEquipments.add(e1);
		listOfEquipments.add(e2);

		EquipmentsDAO dao = new EquipmentsDAO();

		dao.saveEquipments("./test.txt", listOfEquipments);
		List<Equipment> loadEquipments = dao.loadEquipments("./test.txt");

		assertEquals(2, loadEquipments.size());
	}
}