
package acme.testing.auditor.audit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuditorAuditListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/auditor/audit/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code, final String conclusions) {

		super.signIn("auditor1", "auditor1");

		super.clickOnMenu("Auditor", "Audits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, conclusions);

		super.checkButtonExists("Create");

		super.signOut();
	}

	@Test
	public void test300Hacking() {

		super.checkLinkExists("Sign in");
		super.request("/auditor/audit/list");
		super.checkPanicExists();

		super.checkLinkExists("Sign in");
		super.signIn("company3", "company3");
		super.request("/auditor/audit/list");
		super.checkPanicExists();
		super.signOut();

		super.checkLinkExists("Sign in");
		super.signIn("auditor4", "auditor4");
		super.request("/auditor/audit/list");
		super.checkPanicExists();
		super.signOut();
	}
}
