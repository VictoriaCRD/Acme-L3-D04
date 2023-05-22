/*
 * AssistantApplicationShowTest.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.assistant.tutorialSession;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.TutorialSession;
import acme.testing.TestHarness;

public class AssistantTutorialSessionShowTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantTutorialSessionTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/assistant/tutorialSession/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int tutorialRecordIndex, final String code, final int tutorialSessionRecordIndex, final String title, final String sessionType, final String startDate, final String endDate, final String abstractm) {
		// HINT: this test signs in as an assistant, lists his or her tutorials, selects
		// HINT+ one of them and checks that it's as expected.

		super.signIn("assistant1", "assistant1");

		super.clickOnMenu("Asisstant", "My tutorials");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(tutorialRecordIndex);
		super.clickOnButton("Tutorial sessions");
		super.checkListingExists();
		super.clickOnListingRecord(tutorialSessionRecordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("sessionType", sessionType);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("abstractm", abstractm);

		super.signOut();
	}

	@Test
	public void test200Negative() {
		// HINT: there's no negative test case for this listing, since it doesn't
		// HINT+ involve filling in any forms.
	}

	@Test
	public void test300Hacking() {
		// HINT: this test tries to show a tutorialSession of a tutorial that is in draft mode or
		// HINT+ not available, but wasn't published by the principal;

		Collection<TutorialSession> duties;
		String param;

		super.signIn("assistant1", "assistant1");
		duties = this.repository.findManyDutiesByAssistantUsername("assistant2");
		for (final TutorialSession tutorialSession : duties)
			if (tutorialSession.getTutorial().getNotPublished()) {
				param = String.format("id=%d", tutorialSession.getTutorial().getId());

				super.checkLinkExists("Sign in");
				super.request("/assistant/tutorialSession/show", param);
				super.checkPanicExists();

				super.signIn("administrator", "administrator");
				super.request("/assistant/tutorialSession/show", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("assistant2", "assistant2");
				super.request("/assistant/tutorialSession/show", param);
				super.checkPanicExists();
				super.signOut();

				super.signIn("worker1", "worker1");
				super.request("/assistant/tutorialSession/show", param);
				super.checkPanicExists();
				super.signOut();
			}
	}

}
