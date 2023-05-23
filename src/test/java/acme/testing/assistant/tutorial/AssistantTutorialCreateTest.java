/*
 * AssistantTutorialCreateTest.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.assistant.tutorial;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AssistantTutorialCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/assistant/tutorial/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code, final String title, final String estimatedTime, final String course, final String abstractm, final String goals) {
		// HINT: this test authenticates as an assistant and then lists his or her
		// HINT: tutorials, creates a new one, and check that it's been created properly.

		super.signIn("assistant1", "assistant1");

		super.clickOnMenu("Asisstant", "My tutorials");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("estimatedTime", estimatedTime);
		super.fillInputBoxIn("course", course);
		super.fillInputBoxIn("abstractm", abstractm);
		super.fillInputBoxIn("goals", goals);

		super.clickOnSubmit("Create");

		super.clickOnMenu("Asisstant", "My tutorials");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 3, estimatedTime);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("estimatedTime", estimatedTime);
		super.checkInputBoxHasValue("course", course);
		super.checkInputBoxHasValue("abstractm", abstractm);
		super.checkInputBoxHasValue("goals", goals);

		super.clickOnButton("Tutorial sessions");
		super.checkListingExists();
		super.checkListingEmpty();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/assistant/tutorial/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int recordIndex, final String code, final String course, final String title, final String abstractm, final String goals, final String estimatedTime) {
		// HINT: this test attempts to create tutorials with incorrect data.

		super.signIn("assistant1", "assistant1");

		super.clickOnMenu("Asisstant", "My tutorials");
		super.clickOnButton("Create");
		super.checkFormExists();

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("course", course);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractm", abstractm);
		super.fillInputBoxIn("goals", goals);
		super.fillInputBoxIn("estimatedTime", estimatedTime);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	public void test300Hacking() {
		// HINT: this test tries to create a tutorial using principals with
		// HINT+ inappropriate roles.

		super.checkLinkExists("Sign in");
		super.request("/assistant/tutorial/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.request("/assistant/tutorial/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("auditor1", "auditor1");
		super.request("/assistant/tutorial/create");
		super.checkPanicExists();
		super.signOut();
	}

}
