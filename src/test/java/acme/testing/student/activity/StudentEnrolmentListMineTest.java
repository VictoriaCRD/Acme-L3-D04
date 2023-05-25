package acme.testing.student.activity;
//
//package acme.testing.student.enrolment;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
//
//import acme.testing.TestHarness;
//
//public class StudentEnrolmentListMineTest extends TestHarness {
//
//	@ParameterizedTest
//	@CsvFileSource(resources = "/student/enrolment/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
//	public void test100Positive(final int enrolmentRecordIndex, final String code, final String workTime) {
//
//		super.signIn("student1", "student1");
//
//		super.clickOnMenu("Student", "Enrolment List");
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//
//		super.checkColumnHasValue(enrolmentRecordIndex, 0, code);
//		super.checkColumnHasValue(enrolmentRecordIndex, 1, workTime);
//
//		super.signOut();
//	}
//
//	@Test
//	public void test200Negative() {
//
//	}
//
//	@Test
//	public void test300Hacking() {
//
//		super.checkLinkExists("Sign in");
//		super.request("/student/enrolment/list-all");
//		super.checkPanicExists();
//
//		super.signIn("administrator", "administrator");
//		super.request("/student/enrolment/list-all");
//		super.checkPanicExists();
//		super.signOut();
//
//		super.signIn("assistant1", "assistant1");
//		super.request("/student/enrolment/list-all");
//		super.checkPanicExists();
//		super.signOut();
//
//		super.signIn("lecturer1", "lecturer1");
//		super.request("/student/enrolment/list-all");
//		super.checkPanicExists();
//		super.signOut();
//
//		super.signIn("company1", "company1");
//		super.request("/student/enrolment/list-all");
//		super.checkPanicExists();
//		super.signOut();
//
//		super.signIn("auditor1", "auditor1");
//		super.request("/student/enrolment/list-all");
//		super.checkPanicExists();
//		super.signOut();
//	}
//
//}
