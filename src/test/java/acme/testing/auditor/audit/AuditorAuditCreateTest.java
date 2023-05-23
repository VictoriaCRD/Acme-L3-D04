
package acme.testing.auditor.audit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuditorAuditCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/auditor/audit/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code, final String conclusion, final String strongPoint, final String weakPoint) {

		super.signIn("auditor1", "auditor1");

		super.clickOnMenu("Auditor", "Audits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("conclusion", conclusion);
		super.fillInputBoxIn("strongPoint", strongPoint);
		super.fillInputBoxIn("weakPoint", weakPoint);
		super.clickOnSubmit("Create");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, conclusion);

		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("conclusion", conclusion);
		super.checkInputBoxHasValue("strongPoint", strongPoint);
		super.checkInputBoxHasValue("weakPoint", weakPoint);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/auditor/audit/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int recordIndex, final String code, final String conclusion, final String strongPoint, final String weakPoint) {
		// HINT: this test attempts to create duties using wrong data.

		super.signIn("auditor1", "auditor1");

		super.clickOnMenu("Auditor", "Audits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("conclusion", conclusion);
		super.fillInputBoxIn("strongPoint", strongPoint);
		super.fillInputBoxIn("weakPoint", weakPoint);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();

		super.signOut();
	}

}
