//
//package acme.testing.student.enrolment;
//
//import java.util.Collection;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import acme.entities.Enrolment;
//import acme.testing.TestHarness;
//
//public class StudentEnrolmentShowTest extends TestHarness {
//
//	// Internal state ---------------------------------------------------------
//
//	@Autowired
//	protected StudentEnrolmentRepositoryTest repository;
//
//	// Test data --------------------------------------------------------------
//
//
//	@ParameterizedTest
//	@CsvFileSource(resources = "/student/enrolment/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
//	public void test100Positive(final int enrolmentIndex, final String code, final String motivation, final String goals, final String course, final String expiryDate, final String cvc, final String creditCard, final String holderName) {
//
//		super.signIn("student1", "student1");
//
//		super.clickOnMenu("Student", "Enrolment List");
//		super.sortListing(0, "asc");
//		super.clickOnListingRecord(enrolmentIndex);
//		super.checkFormExists();
//
//		super.checkInputBoxHasValue("code", code);
//		super.checkInputBoxHasValue("motivation", motivation);
//		super.checkInputBoxHasValue("goals", goals);
//		super.checkInputBoxHasValue("course", course);
//		super.checkInputBoxHasValue("expiryDate", expiryDate);
//		super.checkInputBoxHasValue("cvc", cvc);
//		super.checkInputBoxHasValue("creditCard", creditCard);
//		super.checkInputBoxHasValue("holderName", holderName);
//
//		super.signOut();
//	}
//
//	@Test
//	public void test300Hacking() {
//
//		Collection<Enrolment> enrolments;
//		String param;
//
//		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student3");
//		for (final Enrolment enrolment : enrolments)
//			if (enrolment.isDraftMode()) {
//				param = String.format("id=%d", enrolment.getId());
//
//				super.checkLinkExists("Sign in");
//				super.request("/student/enrolment/show", param);
//				super.checkPanicExists();
//
//				super.signIn("administrator", "administrator");
//				super.request("/student/enrolment/show", param);
//				super.checkPanicExists();
//				super.signOut();
//
//				super.signIn("assistant5", "assistant5");
//				super.request("/student/enrolment/show", param);
//				super.checkPanicExists();
//				super.signOut();
//
//				super.signIn("student1", "student1");
//				super.request("/student/enrolment/show", param);
//				super.checkPanicExists();
//				super.signOut();
//
//				super.signIn("lecturer1", "lecturer1");
//				super.request("/student/enrolment/show", param);
//				super.checkPanicExists();
//				super.signOut();
//
//				super.signIn("company1", "company1");
//				super.request("/student/enrolment/show", param);
//				super.checkPanicExists();
//				super.signOut();
//
//				super.signIn("auditor1", "auditor1");
//				super.request("/student/enrolment/show", param);
//				super.checkPanicExists();
//				super.signOut();
//			}
//	}
//
//}
