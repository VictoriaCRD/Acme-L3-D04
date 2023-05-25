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
//public class StudentEnrolmentFinaliseTest extends TestHarness {
//
//	// Internal data ----------------------------------------------------------
//
//	@Autowired
//	protected StudentEnrolmentRepositoryTest repository;
//
//	// Test methods -----------------------------------------------------------
//
//
//	@ParameterizedTest
//	@CsvFileSource(resources = "/student/enrolment/finalise-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
//	public void test100Negative(final int enrolmentIndex, final String code, final String motivation, final String goals, final String course, final String expiryDate, final String cvc, final String creditCard, final String holderName) {
//
//		super.signIn("student5", "student5");
//
//		super.clickOnMenu("Student", "Enrolment List");
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//
//		super.clickOnListingRecord(enrolmentIndex);
//		super.checkFormExists();
//		super.fillInputBoxIn("code", code);
//		super.fillInputBoxIn("motivation", motivation);
//		super.fillInputBoxIn("goals", goals);
//		super.fillInputBoxIn("course", course);
//		super.fillInputBoxIn("expiryDate", expiryDate);
//		super.fillInputBoxIn("creditCard", creditCard);
//		super.fillInputBoxIn("cvc", cvc);
//		super.fillInputBoxIn("holderName", holderName);
//		super.clickOnSubmit("Finalise");
//		super.checkErrorsExist();
//
//		super.signOut();
//	}
//
//	@ParameterizedTest
//	@CsvFileSource(resources = "/student/enrolment/finalise-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
//	public void test200Positive(final int enrolmentIndex, final String code, final String motivation, final String goals, final String course, final String expiryDate, final String cvc, final String creditCard, final String holderName) {
//
//		super.signIn("student5", "student5");
//
//		super.clickOnMenu("Student", "Enrolment List");
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//		super.checkColumnHasValue(enrolmentIndex, 0, code);
//
//		super.clickOnListingRecord(enrolmentIndex);
//		super.checkFormExists();
//		super.fillInputBoxIn("code", code);
//		super.fillInputBoxIn("motivation", motivation);
//		super.fillInputBoxIn("goals", goals);
//		super.fillInputBoxIn("course", course);
//		super.fillInputBoxIn("expiryDate", expiryDate);
//		super.fillInputBoxIn("creditCard", creditCard);
//		super.fillInputBoxIn("cvc", cvc);
//		super.fillInputBoxIn("holderName", holderName);
//		super.clickOnSubmit("Finalise");
//		super.checkNotErrorsExist();
//
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//		super.checkColumnHasValue(enrolmentIndex, 0, code);
//
//		super.clickOnListingRecord(enrolmentIndex);
//		super.checkFormExists();
//
//		super.clickOnButton("Activities");
//		super.checkListingExists();
//		super.checkListingEmpty();
//
//		super.signOut();
//	}
//
//	@Test
//	public void test300Hacking() {
//
//		Collection<Enrolment> enrolments;
//		String params;
//
//		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student2");
//		for (final Enrolment enrolment : enrolments)
//			if (enrolment.isDraftMode()) {
//				params = String.format("id=%d", enrolment.getId());
//
//				super.checkLinkExists("Sign in");
//				super.request("/student/enrolment/finalise", params);
//				super.checkPanicExists();
//
//				super.signIn("administrator", "administrator");
//				super.request("/student/enrolment/finalise", params);
//				super.checkPanicExists();
//				super.signOut();
//
//				super.signIn("assistant1", "assistant1");
//				super.request("/student/enrolment/finalise", params);
//				super.checkPanicExists();
//				super.signOut();
//
//				super.signIn("lecturer1", "lecturer1");
//				super.request("/student/enrolment/finalise", params);
//				super.checkPanicExists();
//				super.signOut();
//
//				super.signIn("company1", "company1");
//				super.request("/student/enrolment/finalise", params);
//				super.checkPanicExists();
//				super.signOut();
//
//				super.signIn("auditor1", "auditor1");
//				super.request("/student/enrolment/finalise", params);
//				super.checkPanicExists();
//				super.signOut();
//			}
//	}
//
//	@Test
//	public void test301Hacking() {
//
//		Collection<Enrolment> enrolments;
//		String params;
//
//		super.signIn("student1", "student1");
//		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student1");
//		for (final Enrolment enrolment : enrolments)
//			if (!enrolment.isDraftMode()) {
//				params = String.format("id=%d", enrolment.getId());
//				super.request("/student/enrolment/finalise", params);
//			}
//		super.signOut();
//	}
//
//	@Test
//	public void test302Hacking() {
//
//		Collection<Enrolment> enrolments;
//		String params;
//
//		super.signIn("student1", "student1");
//		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student2");
//		for (final Enrolment enrolment : enrolments) {
//			params = String.format("id=%d", enrolment.getId());
//			super.request("/student/enrolment/finalise", params);
//
//		}
//		super.signOut();
//	}
//
//}
