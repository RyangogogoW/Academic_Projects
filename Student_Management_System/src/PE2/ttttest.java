package PE2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ttttest {

	@Order(1)
	@Test
	void ExtractAllUGradSameName() {
		ChairPerson chair = new ChairPerson("Rebert", "Jack", 59, "Male", "Birchmount Road");
		CSD csd = new CSD(chair);
		Faculty f1 = new Faculty("Elizabeth", "Smith", 53, "Female", "Lawrence Avenue East");
		f1.setProgram("Software Engineering");
		Faculty f2 = new Faculty("Sean", "Smith", 48, "Male", "Avenue East");
		f2.setProgram("Computer Science");
		UGrad s1 = new UGrad("a", "Mark", 35, "Male", "Canlish Road");
		UGrad s2 = new UGrad("a", "Mark", 45, "Male", "Guildwood Parkway");
		UGrad s3 = new UGrad("a", "Mark", 55, "Male", "Danforth Road");
		UGrad s4 = new UGrad("a", "Mark", 35, "Male", "Canlish Road");
		UGrad s5 = new UGrad("a", "Mark", 45, "Male", "Rockwood Drive");
		UGrad s6 = new UGrad("a", "Mark", 32, "Male", "Tawoon Road");
		UGrad s7 = new UGrad("a", "Mark", 27, "Male", "Pizza Road");
		UGrad s8 = new UGrad("a", "Mark", 21, "Male", "Candy Road");
		UGrad s9 = new UGrad("a", "Mark", 15, "Female", "Qudra Road");
		UGrad s10 = new UGrad("a", "Mark", 35, "Male", "Some Road");

		try {
			csd.HireFaculty(f1);
			csd.HireFaculty(f2);
			csd.AdmitStudent(s1);
			csd.AdmitStudent(s2);
			csd.AdmitStudent(s3);
			csd.AdmitStudent(s4);
			csd.AdmitStudent(s5);
			csd.AdmitStudent(s6);
			csd.AdmitStudent(s7);
			csd.AdmitStudent(s3);
			csd.AdmitStudent(s8);
			csd.AdmitStudent(s9);
			csd.AdmitStudent(s10);

		} catch (NoSpaceException e) {
			fail();
		}

		List<UGrad> lst = new ArrayList<UGrad>();
		lst.add(s1);
		lst.add(s2);
		lst.add(s3);
		lst.add(s4);
		lst.add(s5);
		lst.add(s6);
		lst.add(s7);
		lst.add(s8);
		lst.add(s9);
		lst.add(s10);
		System.out.println(csd.ExtractAllUGradDetails());
		System.out.println(lst);
		assertTrue(lst.equals(csd.ExtractAllUGradDetails()));
	}

	@Test
	@Order(2)
	void checkCap() {
		ChairPerson chair = new ChairPerson("Reb", "Jak", 59, "Male", "Birchmount Road");
		CSD csd = new CSD(chair);
		boolean except = false;

		try {
			ProgramDirector p1 = new ProgramDirector("harsh", "DADDY", 21, "helicopter", "dw");
			p1.setProgram("Software Engineering");
			ProgramDirector p2 = new ProgramDirector("harsh", "DADDY", 21, "helicopter", "dw");
			p2.setProgram("Computer Science");
			csd.addProgramDirector(p1);
			csd.addProgramDirector(p2);
			for (int i = 0; i < 63; i++) {
				csd.HireFaculty(new Faculty("ok", "yes", 2, "gender", "ok"));

			}
			for (int i = 0; i <= 500; i++) {
				csd.AdmitStudent(new UGrad("harsh", "DADDY", 21, "helicopter", "dw"));
			}

		} catch (NoSpaceException e) {
			except = true;
		}
		System.out.println(csd.getNumOfUGradStudents());
		System.out.println(csd.PDRecord.size());
		assertTrue(except);
	}

}
