
package acme.testing.auditor.audit;

import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Audit;
import acme.testing.TestHarness;

public class AuditorAuditShowTest extends TestHarness {

	@Autowired
	protected AuditorAuditTestRepository repository;


	@ParameterizedTest
	@CsvFileSource(resources = "/auditor/audit/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code, final String conclusion, final String strongPoint, final String weakPoint) {

		super.signIn("auditor1", "auditor1");

		super.clickOnMenu("Auditor", "Audits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("conclusion", conclusion);
		super.fillInputBoxIn("strongPoint", strongPoint);
		super.fillInputBoxIn("weakPoint", weakPoint);

		super.signOut();
	}

	public void test300Hacking() {

		final Collection<Audit> audits = this.repository.findManyAuditsByAuditor("auditor1");
		String param;

		for (final Audit audit : audits)
			if (audit.getNotPublished()) {
				param = String.format("id=%d", audit.getId());

				super.checkLinkExists("Sign in");
				super.request("/auditor/audit/show", param);
				super.checkPanicExists();

				super.signIn("administrator", "administrator");
				super.request("/auditor/audit/show", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("auditor2", "auditor2");
				super.request("/auditor/audit/show", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("company1", "company1");
				super.request("/auditor/audit/show", param);
				super.checkPanicExists();
				super.signOut();
			}
	}
}
