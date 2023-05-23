
package acme.testing.auditor.audit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuditorAuditPublishTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/auditor/audit/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex) {

		super.signIn("auditor1", "auditor1");

		super.clickOnMenu("Auditor", "Audits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();
		super.clickOnListingRecord(recordIndex);
		super.checkNotButtonExists("Publish");
		super.checkNotButtonExists("Update");
		super.checkNotButtonExists("Delete");

		super.signOut();
	}
}
