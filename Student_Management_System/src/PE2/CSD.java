package PE2;

import java.util.ArrayList;
import java.util.List;

/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS. 

Full Name: Huan Wu
Student Number: 219208263
Course Section: Section A
*/

public class CSD {

	ChairPerson chair;

	protected static int undegradStudentAmount = 0;
	protected static int gradStudentAmount = 0;
	protected static int studentAmount = 0;
	protected static int AcademicAmount = 0;
	protected static int facultyMemberAmount = 0;
	protected static int ChairPersonAmount = 0;

	protected int chairPersonAmountLimit = 1;
	protected int programDirectorAmountLimit = 3;
	protected int facultyAssignedToAProgramDirectorAmountLimit = 25;
	protected int facultyMemberAmountLimit = 70;
	protected int gradStudentAmountLimit = 150;
	protected int undegradStudentAmountLimit = 500;
	protected int facultyTAAmountLimit = 5;
	protected int facultyForAdvisingAmountLimit = 8;

	protected static ArrayList<UGrad> UGradRecord = new ArrayList<UGrad>();
	protected static ArrayList<Grad> GradRecord = new ArrayList<Grad>();
	protected static ArrayList<Faculty> facultyRecord = new ArrayList<Faculty>();
	protected static ArrayList<ArrayList<UGrad>> totalUGradAdvising = new ArrayList<ArrayList<UGrad>>();
	protected static ArrayList<ArrayList<Grad>> TAs = new ArrayList<ArrayList<Grad>>();
	protected static ArrayList<ArrayList<Grad>> totalGradAdvising = new ArrayList<ArrayList<Grad>>();
	protected static ArrayList<ProgramDirector> PDRecord = new ArrayList<ProgramDirector>();

	/**
	 * This is a constructor that initialize the CSD with a chairperson.
	 * 
	 * @param chair is the chairperson.
	 */
	protected CSD(ChairPerson chair) {
		this.chair = chair;
		ChairPersonAmount++;
		totalUGradAdvising = new ArrayList<ArrayList<UGrad>>(); // reset all the static attributes
		totalGradAdvising = new ArrayList<ArrayList<Grad>>();
		UGradRecord = new ArrayList<UGrad>();
		GradRecord = new ArrayList<Grad>();
		facultyRecord = new ArrayList<Faculty>();
		TAs = new ArrayList<ArrayList<Grad>>();
		PDRecord = new ArrayList<ProgramDirector>();
	}

	/**
	 * This is a constructor that initialize the CSD.
	 */
	protected CSD() {
	}

	/**
	 * This is a getter to get the chairperson of this CSD.
	 * 
	 * @return the chairperson of CSD.
	 */
	public ChairPerson getChairPerson() {
		return this.chair;
	}

	/**
	 * This is a method to hire a new faculty into the CSD.
	 * 
	 * @param f is the faculty we are hiring.
	 * @throws NoSpaceException when the number of faculty exceed the limit.
	 */
	public void HireFaculty(Faculty f) throws NoSpaceException {

		if ((facultyRecord.size() + 1) <= facultyMemberAmountLimit) { // if the number of the faculty is over the limit
																		// throw a NoSapceException.
			if (!facultyRecord.contains(f)) { // check that if faculty f has already been hired.

				facultyMemberAmount++;
				facultyRecord.add(f); // Hire faculty f;
				ArrayList<UGrad> newUGradList = new ArrayList<UGrad>(); // build a list to record the undergraduate
																		// student advised by f.
				ArrayList<Grad> newGradList = new ArrayList<Grad>(); // build a list to record the graduate student
																		// advised by f.
				ArrayList<Grad> TAsList = new ArrayList<Grad>(); // build a list to record the TAs of f.
				totalUGradAdvising.add(newUGradList);
				totalGradAdvising.add(newGradList);
				TAs.add(TAsList);
				f.UGradAdvisingList = totalUGradAdvising.get(facultyRecord.indexOf(f));
				f.GradAdvisingList = totalGradAdvising.get(facultyRecord.indexOf(f));
				f.TAsList = TAs.get(facultyRecord.indexOf(f));

			}

		} else
			throw new NoSpaceException();
	}

	/**
	 * This is a method to get a list of faculty of the CSD.
	 * 
	 * @return a list of faculty of the CSD.
	 */

	public List<Faculty> getFaculty() {
		List<Faculty> f = new ArrayList<Faculty>();
		for (Faculty i : facultyRecord) {
			f.add(i);
		}
		return f;
	}

	/**
	 * This is a method to get number of faculty.
	 * 
	 * @return the number of faculty
	 */
	public int getNumOfFaculty() {
		return facultyRecord.size();
	}

	/**
	 * This is a method to admit student into the CSD.
	 * 
	 * @param s is a undergraduate student we are going to admit.
	 * @throws NoSpaceException if the number of undergraduate student is over
	 *                          limit.
	 */
	public void AdmitStudent(UGrad s) throws NoSpaceException {
		if ((UGradRecord.size() + 1) <= undegradStudentAmountLimit) { // check if the number of undergraduate student is
																		// over limit. If yes then throw a
																		// NoSpaceException.
			if (!UGradRecord.contains(s)) { // check if s has already been admitted.
				for (ArrayList<UGrad> a : totalUGradAdvising) { // distribute s a faculty for advising.
					if (!a.contains(s)) {
						if (a.size() < 8) {
							a.add(s);
							s.Advisor = facultyRecord.get(totalUGradAdvising.indexOf(a));
							break;
						} else
							continue;
					}
					break;
				}

				UGradRecord.add(s); // Admit undergraduate student s;

			}
		} else
			throw new NoSpaceException();

	}

	/**
	 * This is a method to get the number of undergraduate student.
	 * 
	 * @return the number of undergraduate student.
	 */
	public int getNumOfUGradStudents() {
		return UGradRecord.size();
	}

	/**
	 * This is a method to hire TA into CSD.
	 * 
	 * @param s is a graduate student we are going to hire as a TA.
	 * @throws NoSpaceException when the number of TAs is over the limit.
	 */
	public void HireTA(Grad s) throws NoSpaceException {
		if (GradRecord.size() + 1 <= gradStudentAmountLimit) {// check if the number of TAs is over the limit. If yes
																// throw a NoSpaceException.
			if (!GradRecord.contains(s)) { // check if s has already been hired before.
				for (ArrayList<Grad> a : totalGradAdvising) { // distribute s a faculty as advisor.
					if (!a.contains(s)) {
						if (a.size() < 5) {
							a.add(s);
							s.Advisor = facultyRecord.get(totalGradAdvising.indexOf(a));
							break;
						} else
							continue;
					}
					break;
				}

				GradRecord.add(s); // Hire Graduate student s;

			}
			for (ArrayList<Grad> t : TAs) { // distribute s to a faculty as a TA.

				if (!t.contains(s)) {
					if (t.size() < 5) {
						t.add(s);
						break;
					} else
						continue;
				} else
					break;
			}

		} else
			throw new NoSpaceException();

	}

	/**
	 * This is a method to get number of graduate student.
	 * 
	 * @return the number of graduate student.
	 */
	public int getNumOfGradStudents() {
		return GradRecord.size();
	}

	/**
	 * This is a method to get number of Advising undergraduate students;
	 * 
	 * @return the number of Advising undergraduate students;
	 */
	public int getNumOfAdvisingUGrads() {
		int sum = 0;
		for (ArrayList<UGrad> a : totalUGradAdvising) {
			sum += a.size();
		}
		return sum;
	}

	/**
	 * This is a method to get the number of TAs.
	 * 
	 * @return the number of TAs.
	 */
	public int getNumOfTAs() {
		int sum = 0;
		for (ArrayList<Grad> a : totalGradAdvising) {
			sum += a.size();
		}
		return sum;
	}

	/**
	 * This is method that remove the undergraduate student s's information from the
	 * CSD record when s graduates.
	 * 
	 * @param s is the undergraduate student who is going to graduate.
	 */
	public void AlumnusUGrad(UGrad s) {
		UGradRecord.remove(s); // remove s's information from the undergraduate student record.
		for (ArrayList<UGrad> a : totalUGradAdvising) { // Remove s from the record of advising undergraduate student.
			if (a.contains(s)) {
				a.remove(s);
				break;
			} else
				continue;
		}
	}

	/**
	 * This is a method that remove the graduate student s's information from the
	 * CSD record when s graduates.
	 * 
	 * @param s is the graduate student who is going to graduate.
	 * @throws NoTAException when s is graduating from the department, but no other
	 *                       TA is available under supervision/work with a
	 *                       particular faculty.
	 */
	public void AlumnusGrad(Grad s) throws NoTAException {

		GradRecord.remove(s); // remove s from the CSD graduate student record.
		for (ArrayList<Grad> a : totalGradAdvising) { // remove s from the record of advising graduate student
			if (a.contains(s)) {
				a.remove(s);
				break;
			} else
				continue;
		}
		for (ArrayList<Grad> a : TAs) { // remove s from the record of TAS.
			if (a.contains(s)) {
				a.remove(s);
				if (a.size() == 0) {
					throw new NoTAException();
				}
				break;
			} else
				continue;
		}

	}

	/**
	 * This is a method to get all the undergraduate students information stored in
	 * the university students' record as a sorted list of students according to the
	 * students' full name.
	 * 
	 * @return a sorted list about all the undergraduate students information.
	 */
	public List<UGrad> ExtractAllUGradDetails() {
		ArrayList<UGrad> newUGradRecord = new ArrayList<UGrad>(); // create a new list which is the return
																	// list.
		ArrayList<UGrad> tmpRecord = new ArrayList<UGrad>(); // create tmpRecord for temporarily use.
		for (UGrad ug : UGradRecord) { // copy all the information into a new list.
			tmpRecord.add(ug);
		}
		UGrad tmp = null;
		while (tmpRecord.size() != 0) { // when the size of tmpRecord equal to zero stop the loop. Make sure
										// that every student in the tmpRecord were added into the return list.
			for (int i = 0; i < tmpRecord.size(); i++) {
				if (newUGradRecord.contains(tmpRecord.get(i))) // if the return list has the student then continue.
					continue;
				tmp = tmpRecord.get(i);
				for (int j = 0; j < tmpRecord.size(); j++) { // find the "smallest" name in the tmpRecord.
					if (newUGradRecord.contains(tmpRecord.get(j)))
						continue;
					String fullName1 = tmp.firstName + ", " + tmp.lastName;
					String fullName2 = tmpRecord.get(j).firstName + ", " + tmpRecord.get(j).lastName;
					if (fullName1.compareTo(fullName2) > 0)
						tmp = tmpRecord.get(j);
				}
				newUGradRecord.add(tmp); // add it into the return list.
				tmpRecord.remove(tmp); // remove it from the tmpRecord.
				i = -1; // Reset the pointer of the tmpRecord.
			}
		}

		return newUGradRecord;
	}

	/**
	 * This is a method to get all the graduate students information stored in the
	 * university students record as a sorted list of students according to the
	 * students' full name.
	 * 
	 * @return a sorted list about all the graduate students information.
	 */
	public List<Grad> ExtractAllGradDetails() {

		ArrayList<Grad> newGradRecord = new ArrayList<Grad>();// create a new list which is the return list.
		ArrayList<Grad> tmpRecord = new ArrayList<Grad>();// create tmpRecord for temporarily use.
		for (Grad g : GradRecord) {// copy all the information into a new list.
			tmpRecord.add(g);
		}
		Grad tmp = null;
		while (tmpRecord.size() != 0) {// when the size of tmpRecord equal to zero stop the loop. Make sure
										// that every student in the tmpRecord were added into the return list

			for (int i = 0; i < tmpRecord.size(); i++) {
				if (newGradRecord.contains(tmpRecord.get(i))) // if the return list has the student then continue.
					continue;
				tmp = tmpRecord.get(i);
				for (int j = 0; j < tmpRecord.size(); j++) { // find the "smallest" name in the tmpRecord.
					if (newGradRecord.contains(tmpRecord.get(j)))
						continue;
					String fullName1 = tmp.firstName + ", " + tmp.lastName;
					String fullName2 = tmpRecord.get(j).firstName + ", " + tmpRecord.get(j).lastName;
					if (fullName1.compareTo(fullName2) > 0) {
						tmp = tmpRecord.get(j);
					}
				}
				newGradRecord.add(tmp);// add it into the return list.
				tmpRecord.remove(tmp);// remove it from the tmpRecord.
				i = -1; // Reset the pointer of the tmpRecord.
			}
		}

		return newGradRecord;
	}

	/**
	 * This is a method to get all the faculty information stored in the university
	 * faculties record as a stored list of faculties according to their full name.
	 * 
	 * @return a sorted list about all the faculties information.
	 */
	public List<Faculty> ExtractAllFacultyDetails() {
		ArrayList<Faculty> newFacultyRecord = new ArrayList<Faculty>();// create a new list which is the return list.
		ArrayList<Faculty> tmpRecord = new ArrayList<Faculty>();// create tmpRecord for temporarily use.
		for (Faculty f : facultyRecord) // copy all the information into a new list.
			tmpRecord.add(f);
		Faculty tmp = null;
		while (tmpRecord.size() != 0) {// when the size of tmpRecord equal to zero stop the loop. Make sure
										// that every faculty in the tmpRecord were added into the return list
			for (int i = 0; i < tmpRecord.size(); i++) {
				if (newFacultyRecord.contains(tmpRecord.get(i)))// if the return list has the faculty then continue.
					continue;
				tmp = tmpRecord.get(i);
				for (int j = 0; j < tmpRecord.size(); j++) {// find the "smallest" name in the tmpRecord.
					if (newFacultyRecord.contains(tmpRecord.get(j)))
						continue;
					String fullName1 = tmp.firstName + ", " + tmp.lastName;
					String fullName2 = tmpRecord.get(j).firstName + ", " + tmpRecord.get(j).lastName;
					if (fullName1.compareTo(fullName2) > 0)
						tmp = tmpRecord.get(j);
				}
				newFacultyRecord.add(tmp);// add it into the return list.
				tmpRecord.remove(tmp);// remove it from the tmpRecord.
				i = -1; // Reset the pointer of the tmpRecord.
			}
		}
		return newFacultyRecord;
	}

	/**
	 * This is a method to get the faculty of the given program stored in the
	 * university faculties record as a stored list of faculties according to their
	 * full name.
	 * 
	 * @param program is the program we are selecting for.
	 * @return a sorted list about faculties' information of the given program.
	 */

	public List<Faculty> ExtractFacultyDetails(String program) {
		ArrayList<Faculty> newFacultyRecord = new ArrayList<Faculty>();// create a new list which include all the
																		// faculties in the given program.
		for (Faculty f : facultyRecord) {
			if (f.program.equals(program))
				newFacultyRecord.add(f);
		}

		ArrayList<Faculty> sortedFacultyRecord = new ArrayList<Faculty>(); // create a new list which is the return
																			// list.
		ArrayList<Faculty> tmpRecord = new ArrayList<Faculty>();// create tmpRecord for temporarily use.

		for (Faculty f : newFacultyRecord)
			tmpRecord.add(f);
		Faculty tmp = null;
		while (tmpRecord.size() != 0) {// when the size of tmpRecord equal to zero stop the loop. Make sure
										// that every faculty in the tmpRecord were added into the return list
			for (int i = 0; i < tmpRecord.size(); i++) {
				if (sortedFacultyRecord.contains(tmpRecord.get(i)))// if the return list has the faculty then continue.
					continue;
				tmp = tmpRecord.get(i);
				for (int j = 0; j < tmpRecord.size(); j++) { // find the "smallest" name in the tmpRecord.
					if (sortedFacultyRecord.contains(tmpRecord.get(j)))
						continue;
					String fullName1 = tmp.firstName + ", " + tmp.lastName;
					String fullName2 = tmpRecord.get(j).firstName + ", " + tmpRecord.get(j).lastName;
					if (fullName1.compareTo(fullName2) > 0)
						tmp = tmpRecord.get(j);
				}
				sortedFacultyRecord.add(tmp);// add it into the return list.
				tmpRecord.remove(tmp);// remove it from the tmpRecord.
				i = -1; // Reset the pointer of the tmpRecord.
			}
		}
		return sortedFacultyRecord;

	}

	/**
	 * This is a method to extract and return all the students information that are
	 * advisees of a particular faculty as a sorted list of students according to
	 * the student¡¯s full name.
	 * 
	 * @param f is the particular faculty whose advising student we are extracting.
	 * @return a sorted list of student.
	 */
	public List<UGrad> ExtractAdviseesDetails(Faculty f) {
		ArrayList<UGrad> AdvisingUGrad = new ArrayList<UGrad>();// create a new list which include all the
																// faculties in the given program.
		for (UGrad ug : f.getAdvisingUgrads()) {
			AdvisingUGrad.add(ug);
		}
		ArrayList<UGrad> sortedUGradList = new ArrayList<UGrad>();// create a new list which is the return list.
		ArrayList<UGrad> tmpRecord = new ArrayList<UGrad>();// create tmpRecord for temporarily use.

		for (UGrad ug : AdvisingUGrad)
			tmpRecord.add(ug);
		UGrad tmp = null;
		while (tmpRecord.size() != 0) {// when the size of tmpRecord equal to zero stop the loop. Make sure
										// that every student in the tmpRecord were added into the return list
			for (int i = 0; i < tmpRecord.size(); i++) {
				if (sortedUGradList.contains(tmpRecord.get(i)))// if the return list has the faculty then continue.
					continue;
				tmp = tmpRecord.get(i);
				for (int j = 0; j < tmpRecord.size(); j++) {// find the "smallest" name in the tmpRecord.
					if (sortedUGradList.contains(tmpRecord.get(j)))
						continue;
					String fullName1 = tmp.firstName + ", " + tmp.lastName;
					String fullName2 = tmpRecord.get(j).firstName + ", " + tmpRecord.get(j).lastName;
					if (fullName1.compareTo(fullName2) > 0)
						tmp = tmpRecord.get(j);
				}
				sortedUGradList.add(tmp);// add it into the return list.
				tmpRecord.remove(tmp);// remove it from the tmpRecord.
				i = -1; // Reset the pointer of the tmpRecord.

			}

		}
		return sortedUGradList;
	}

	/**
	 * This is a method to extract and return all the students information that are
	 * TAs of a particular faculty as a sorted list of students according to the
	 * student¡¯s full name.
	 * 
	 * @param f is the particular faculty whose advising students we are extracting.
	 * @return a sorted list of student.
	 */
	public List<Grad> ExtractTAsDetails(Faculty f) {
		ArrayList<Grad> sortedTAsList = new ArrayList<Grad>();// create a new list which is the return list.
		ArrayList<Grad> tmpRecord = new ArrayList<Grad>();// create tmpRecord for temporarily use.
		ArrayList<Grad> TAsList = f.getTAs(); // create a new list which include all the
												// faculties in the given program.
		for (Grad g : TAsList) {
			tmpRecord.add(g);
		}
		Grad tmp = null;
		while (tmpRecord.size() != 0) {// when the size of tmpRecord equal to zero stop the loop. Make sure
										// that every student in the tmpRecord were added into the return list
			for (int i = 0; i < tmpRecord.size(); i++) {
				if (sortedTAsList.contains(tmpRecord.get(i)))// if the return list has the faculty then continue.
					continue;
				tmp = tmpRecord.get(i);
				for (int j = 0; j < tmpRecord.size(); j++) {// find the "smallest" name in the tmpRecord.
					if (sortedTAsList.contains(tmpRecord.get(j)))
						continue;
					String fullName1 = tmp.firstName + ", " + tmp.lastName;
					String fullName2 = tmpRecord.get(j).firstName + ", " + tmpRecord.get(j).lastName;
					if (fullName1.compareTo(fullName2) > 0)
						tmp = tmpRecord.get(j);
				}
				sortedTAsList.add(tmp);// add it into the return list.
				tmpRecord.remove(tmp);// remove it from the tmpRecord.
				i = -1; // Reset the pointer of the tmpRecord.

			}
		}
		return sortedTAsList;
	}

	/**
	 * This is a method to add a program director into the CSD.
	 * 
	 * @param pd is a program director we are adding.
	 * @throws NoSpaceException if the number of program director is over limit.
	 */
	public void addProgramDirector(ProgramDirector pd) throws NoSpaceException {
		if (PDRecord.size() < 3) { // check if the number of program director is over the limit
			for (ProgramDirector PD : PDRecord) {
				if (pd.program == PD.program)
					throw new NoSpaceException();
			}
			PDRecord.add(pd);
		} else
			throw new NoSpaceException();
	}

	/**
	 * This is a method remove the information of a faculty from the CSD when the
	 * faculty is going to retired and reassign the advising students and TAs to the
	 * next available faculty member.
	 * 
	 * @param f is the faculty who is going to retired.
	 * @throws NoSpecialtyException if a faculty member is retiring from the
	 *                              university, but no other faculty is available
	 *                              with the same specialty of the program.
	 */
	public void RetireFaculty(Faculty f) throws NoSpecialtyException {

		int index = facultyRecord.indexOf(f);

		// reassign the TAs of f to the next available faculty.
		ArrayList<Grad> TAsList = f.getTAs();
		TAs.remove(index);
		for (Grad TA : TAsList) {
			for (ArrayList<Grad> TAList : TAs) {
				if (TAList.size() < 5) { // check if the number of the faculty's TAs is over the limit.
					TAList.add(TA); // if not add TA to the faculty
					break;
				}
			}
		}
		// reassign the advising undergraduate students of f to the next available
		// faculty.
		ArrayList<UGrad> UGradAdvisingList = f.getAdvisingUgrads();
		totalUGradAdvising.remove(index);
		for (UGrad ug : UGradAdvisingList) {
			for (ArrayList<UGrad> UGList : totalUGradAdvising) {
				if (UGList.size() < 8) { // check if the number of the faculty's advising undergraduate students is over
											// the limit.
					UGList.add(ug); // if not add the student to the faculty
					break;
				}
			}
		}
		// reassign the advising graduate students of f to the next available faculty.
		ArrayList<Grad> GradAdvisingList = f.getAdvisingGrads();
		totalGradAdvising.remove(index);
		for (Grad g : GradAdvisingList) {
			for (ArrayList<Grad> GList : totalGradAdvising) {
				if (GList.size() < 8) {// check if the number of the faculty's advising graduate students is over
										// the limit.
					GList.add(g);// if not add the student to the faculty
					break;
				}
			}
		}

		facultyRecord.remove(f); // remove the f from the record.
		// check if it is needed to throw an exception.
		boolean d = false;
		for (Faculty F : facultyRecord) {
			if (f.program == null || F.program.equals(f.program)) {
				d = true;
				break;
			}
		}
		if (d == false)
			throw new NoSpecialtyException();
	}

}

abstract class Person extends CSD {
	/**
	 * This is a constructor that create a person.
	 */
	public Person() {
		super();
	}

	protected String firstName;
	protected String lastName;
	protected int age;
	protected String Gender;
	protected String Address;

}

class Student extends Person implements Comparable {

	protected int studentID;
	Faculty Advisor;

	/**
	 * This is a method to get student ID of student.
	 * 
	 * @return the student ID.
	 */
	public int getStudentID() {
		return this.studentID;
	}

	/**
	 * override the compareTo method. If one student is equal to another then return
	 * 0, otherwise return 1;
	 */
	@Override
	public int compareTo(Object o) {
		Student s1 = (Student) o;
		if (this.equals(s1))
			return 0;
		else
			return 1;
	}

	/**
	 * This is a method to check if two student are equal. If their first name, last
	 * name, age, gender, address and studentID are all the same then return true,
	 * otherwise return false.
	 */
	@Override
	public boolean equals(Object o) {
		Student s1 = (Student) o;
		if (this.firstName.equals(s1.firstName) & this.lastName.equals(s1.lastName) & this.age == s1.age
				& this.Gender.equals(s1.Gender) & this.Address.equals(s1.Address) & this.studentID == s1.studentID)
			return true;
		else
			return false;
	}

}

class UGrad extends Student {
	/**
	 * This is a constructor that creates a new undergraduate student.
	 * 
	 * @param firstName is the first name of the student.
	 * @param lastName  is the last name of the student.
	 * @param age       is the age of the student.
	 * @param Gender    is the gender of the student.
	 * @param Address   is the address of the student.
	 */
	public UGrad(String firstName, String lastName, int age, String Gender, String Address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.Gender = Gender;
		this.Address = Address;
		this.studentID = 1000 + (studentAmount++);
	}

	/**
	 * This is a method to get advisor of the given undergraduate student.
	 * 
	 * @return the advisor of the given student.
	 */
	public Faculty getAdvisor() {

		for (ArrayList<UGrad> a : totalUGradAdvising) {
			if (a.contains(this))
				return facultyRecord.get(totalUGradAdvising.indexOf(a));
		}
		return this.Advisor;
	}

	/**
	 * Override toString method and use it to return the information about the
	 * undergraduate student which includes student ID, first name, last name, age,
	 * gender and address.
	 */
	public String toString() {
		return "Undergraduate " + "[" + this.studentID + "[[" + this.firstName + ", " + this.lastName + ", " + this.age
				+ ", " + this.Gender + ", " + this.Address + "]]]";
	}

}

class Grad extends Student {
	/**
	 * This is a constructor that creates a new graduate student.
	 * 
	 * @param firstName is the first name of the student.
	 * @param lastName  is the the last name of the student.
	 * @param age       is the age of the student.
	 * @param Gender    is the gender of the student.
	 * @param Address   is the address of the student.
	 */
	public Grad(String firstName, String lastName, int age, String Gender, String Address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.Gender = Gender;
		this.Address = Address;
		this.studentID = 1000 + (studentAmount++);
	}

	/**
	 * This is a method to get advisor of the given graduate student.
	 * 
	 * @return the advisor of the given student.
	 */
	public Faculty getAdvisor() {
		for (ArrayList<Grad> a : totalGradAdvising) {
			if (a.contains(this))
				return facultyRecord.get(totalGradAdvising.indexOf(a));
		}
		return this.Advisor;
	}

	/**
	 * Override toString method and use it to return the information about the
	 * graduate student which includes student ID, first name, last name, age,
	 * gender and address.
	 */
	public String toString() {
		return "Graduate " + "[" + this.studentID + "[[" + this.firstName + ", " + this.lastName + ", " + this.age
				+ ", " + this.Gender + ", " + this.Address + "]]]";
	}

}

abstract class Academics extends Person {
	protected int employeeID;
	protected double Salary;

	/**
	 * This is a method to set faculty's salary.
	 * 
	 * @param salary is a number we are setting to a faculty's salary.
	 */
	public abstract void setSalary(double salary);

	/**
	 * This is a method to get faculty's employeeID.
	 * 
	 * @return the employeeID of the faculty.
	 */
	public abstract int getEmployeeID();
}

class Administrator extends Academics {

	/**
	 * This is a method to get faculty's employeeID.
	 * 
	 * @return the employeeID of the faculty.
	 */
	public int getEmployeeID() {
		return this.employeeID;
	}

	/**
	 * This is a method to set faculty's salary.
	 * 
	 * @param salary is a number we are setting to a faculty's salary.
	 */
	@Override
	public void setSalary(double salary) {
		this.Salary = salary;
	}
}

class Faculty extends Academics implements Comparable {
	String program;
	ArrayList<UGrad> UGradAdvisingList;
	ArrayList<Grad> GradAdvisingList;
	ArrayList<Grad> TAsList;

	/**
	 * This is a constructor that create a new faculty and upload their information
	 * into the CSD.
	 * 
	 * @param firstName is the first name of the faculty.
	 * @param lastName  is the last name of the faculty.
	 * @param age       is the age of the faculty.
	 * @param Gender    is the gender of the faculty.
	 * @param Address   is the address of the faculty.
	 */
	public Faculty(String firstName, String lastName, int age, String Gender, String Address) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.Gender = Gender;
		this.Address = Address;
		this.employeeID = 100 + (AcademicAmount++);

	}

	/**
	 * This is a method to get the fauclty's TAs list.
	 * 
	 * @return the TAs list of the faculty.
	 */
	public ArrayList<Grad> getTAs() {
		return this.TAsList;
	}

	/**
	 * This is a method to get the faculty's advising graduate students list.
	 * 
	 * @return the advising graduate students list of the faculty.
	 */
	public ArrayList<Grad> getAdvisingGrads() {
		return this.GradAdvisingList;
	}

	/**
	 * This is a method to get the faculty's advising undergraduate student list.
	 * 
	 * @return the advising undergraduate students list of the faculty.
	 */
	public ArrayList<UGrad> getAdvisingUgrads() {
		return this.UGradAdvisingList;
	}

	/**
	 * This is a method to set faculty's salary.
	 * 
	 * @param salary is a number we are setting to the faculty's salary.
	 */
	@Override
	public void setSalary(double salary) {
		this.Salary = salary;
	}

	/**
	 * This is a method to get faculty's employeeID.
	 * 
	 * @return the employeeID of the faculty.
	 */
	public int getEmployeeID() {
		return this.employeeID;
	}

	/**
	 * This is a method to set a faculty's program.
	 * 
	 * @param program is the program which should be one of the "Computer Science",
	 *                "Soft Engineering" and "Digital Technology".
	 */
	public void setProgram(String program) {
		this.program = program;

	}

	/**
	 * This is a method to get the program of the faculty.
	 * 
	 * @return the program of the faculty.
	 */
	public String getProgram() {
		return this.program;
	}

	/**
	 * Override toString method and use it to return the information about the
	 * faculty which includes program, employeeID, salary, first name, last name,
	 * age, gender and address.
	 */
	public String toString() {
		return "Faculty " + this.program + "[[" + this.employeeID + ", " + this.Salary + "[" + this.firstName + ", "
				+ this.lastName + ", " + this.age + ", " + this.Gender + ", " + this.Address + "]]]";
	}

	/**
	 * override the compareTo method. If one faculty is equal to another then return
	 * 0, otherwise return 1;
	 */
	@Override
	public int compareTo(Object o) {
		Faculty f1 = (Faculty) o;
		if (this.equals(o))
			return 0;
		else
			return 1;
	}

	/**
	 * This is a method to check if two student are equal. If their first name, last
	 * name, age, gender, address and studentID are all the same then return true,
	 * otherwise return false.
	 */
	@Override
	public boolean equals(Object o) {
		Faculty f1 = (Faculty) o;
		if (this.firstName.equals(f1.firstName) & this.lastName.equals(f1.lastName) & this.age == f1.age
				& this.Gender.equals(f1.Gender) & this.Address.equals(f1.Address) & this.employeeID == f1.employeeID)
			return true;
		else
			return false;
	}

}

class ProgramDirector extends Administrator {
	String program;
	ArrayList<Faculty> programFaculty;

	/**
	 * This is s constructor that create a new program director and upload the
	 * information into the CSD.
	 * 
	 * @param firstName is the first name of the program director.
	 * @param lastName  is the last name of the program director.
	 * @param age       is the age of the program director.
	 * @param Gender    is the gender of the program director.
	 * @param Address   is the address of the program director.
	 */
	public ProgramDirector(String firstName, String lastName, int age, String Gender, String Address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.Gender = Gender;
		this.Address = Address;
		this.employeeID = 100 + (AcademicAmount++);
	}

	/**
	 * This is a method to set a faculty's program.
	 * 
	 * @param program is the program which should be one of the "Computer Science",
	 *                "Soft Engineering" and "Digital Technology".
	 */
	public void setProgram(String program) {
		this.program = program;
		for (Faculty f : facultyRecord) {
			if (f.program.equals(program))
				programFaculty.add(f);
		}
	}

	/**
	 * Override toString method and use it to return the information about the
	 * program director which includes program, employeeID, salary, first name, last
	 * name, age, gender and address.
	 */
	public String toString() {
		return "Program Director " + this.program + "[[" + this.employeeID + ", " + this.Salary + "[" + this.firstName
				+ ", " + this.lastName + ", " + this.age + ", " + this.Gender + ", " + this.Address + "]]]";
	}
}

class ChairPerson extends Administrator {
	/**
	 * This is a constructor that create a new chairperson and upload the
	 * information into the CSD.
	 * 
	 * @param firstName is the first name of the chairperson.
	 * @param lastName  is the last name of the chairperson.
	 * @param age       is the age of the chairperson.
	 * @param Gender    is the gender of the chairperson.
	 * @param Address   is the address of the chairperson.
	 */
	public ChairPerson(String firstName, String lastName, int age, String Gender, String Address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.Gender = Gender;
		this.Address = Address;
		this.employeeID = 100 + (AcademicAmount++);

	}

	/**
	 * Override toString method and use it to return the information about the
	 * chairperson which includes program, employeeID, salary, first name, last
	 * name, age, gender and address.
	 */
	public String toString() {
		return "Chair Person [[[" + this.employeeID + ", " + this.Salary + "[" + this.firstName + ", " + this.lastName
				+ ", " + this.age + ", " + this.Gender + ", " + this.Address + "]]]]";

	}
}

/**
 * 
 * This class is a user defined Exception used when the number of people exceeds
 * the number limit.
 *
 */
class NoSpaceException extends Exception {
	public NoSpaceException() {
		super();
	}

	public NoSpaceException(String message) {
		super(message);
	}
}

/**
 * 
 * This class is user defined Exception used when a faculty member is retiring
 * from the university, but no other faculty is available with the same
 * specialty of the program.
 *
 */
class NoSpecialtyException extends Exception {
	public NoSpecialtyException() {
		super();
	}

	public NoSpecialtyException(String message) {
		super(message);
	}
}

/**
 * 
 * This class is user defined Exception used when a TA is graduating from the
 * department, but no other TA is available under supervision/work with a
 * particular faculty.
 *
 */

class NoTAException extends Exception {
	public NoTAException() {
		super();
	}

	public NoTAException(String message) {
		super(message);
	}
}
