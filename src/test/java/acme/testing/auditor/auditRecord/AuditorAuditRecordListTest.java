
package acme.testing.auditor.auditRecord;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.testing.TestHarness;
import acme.testing.auditor.audit.AuditorAuditTestRepository;

public class AuditorAuditRecordListTest extends TestHarness {

	@Autowired
	protected AuditorAuditTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/auditor/audit-record/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int auditRecordIndex, final int auditRecordRecordIndex, final String subject, final String assesment) {
		// HINT: this test authenticates as an employer, then lists his or her jobs, 
		// HINT+ selects one of them, and check that it has the expected duties.

		super.signIn("auditor1", "auditor1");

		super.clickOnMenu("Auditor", "Audits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(auditRecordIndex);
		super.clickOnButton("Records");

		super.checkListingExists();
		super.checkColumnHasValue(auditRecordRecordIndex, 0, subject);
		super.checkColumnHasValue(auditRecordRecordIndex, 1, assesment);
		super.clickOnListingRecord(auditRecordRecordIndex);

		super.signOut();
	}
	/*
	 * @Test
	 * public void test200Negative() {
	 * // HINT: there's no negative test case for this listing, since it doesn't
	 * // HINT+ involve filling in any forms.
	 * }
	 * 
	 * @Test
	 * public void test300Hacking() {
	 * // HINT: this test tries to list the duties of a job that is unpublished
	 * // HINT+ using a principal that didn't create it.
	 * 
	 * Collection<Job> jobs;
	 * String param;
	 * 
	 * jobs = this.repository.findManyJobsByEmployerUsername("employer1");
	 * for (final Job job : jobs)
	 * if (job.isDraftMode()) {
	 * param = String.format("masterId=%d", job.getId());
	 * 
	 * super.checkLinkExists("Sign in");
	 * super.request("/employer/duty/list", param);
	 * super.checkPanicExists();
	 * 
	 * super.signIn("administrator", "administrator");
	 * super.request("/employer/duty/list", param);
	 * super.checkPanicExists();
	 * super.signOut();
	 * 
	 * super.signIn("employer2", "employer2");
	 * super.request("/employer/duty/list", param);
	 * super.checkPanicExists();
	 * super.signOut();
	 * 
	 * super.signIn("worker1", "worker1");
	 * super.request("/employer/duty/list", param);
	 * super.checkPanicExists();
	 * super.signOut();
	 * }
	 * }
	 */
}
