/*
 * AuthenticatedConsumerUpdateService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package folder;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.EnumType;
import acme.entities.Tutorial;
import acme.entities.TutorialSession;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantTutorialSessionUpdateService1 extends AbstractService<Assistant, TutorialSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantTutorialSessionRepository1 repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int masterId;
		TutorialSession tutorialSession;
		Assistant assistant;
		Tutorial tutorial;

		masterId = super.getRequest().getData("id", int.class);
		tutorialSession = this.repository.findOneTutorialSessionById(masterId);
		tutorial = tutorialSession == null ? null : tutorialSession.getTutorial();
		assistant = tutorial == null ? null : tutorial.getAssistant();
		status = tutorialSession != null && //
			tutorial != null && //
			tutorial.getNotPublished() && //
			super.getRequest().getPrincipal().hasRole(assistant) && //
			tutorial.getAssistant().getId() == super.getRequest().getPrincipal().getActiveRoleId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		TutorialSession object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneTutorialSessionById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final TutorialSession object) {
		assert object != null;

		super.bind(object, "title", "abstractm", "sessionType", "startDate", "endDate", "link");
	}

	@Override
	public void validate(final TutorialSession object) {
		assert object != null;

		if (!(super.getBuffer().getErrors().hasErrors("startDate") || super.getBuffer().getErrors().hasErrors("endDate"))) {
			final boolean endDateIsAfter = object.getEndDate().after(object.getStartDate());
			final Date currentDate = MomentHelper.getCurrentMoment();
			final Long durationSinceCurrentDate = MomentHelper.computeDuration(currentDate, object.getStartDate()).toDays();
			final boolean startDateIsOneDayAhead = durationSinceCurrentDate.doubleValue() >= 1.;
			super.state(endDateIsAfter, "endDate", "assistant.session.form.error.endDate");
			super.state(startDateIsOneDayAhead, "startDate", "assistant.session.form.error.startDate");
			super.state(object.getDurationInHours() >= 1. && // 
				object.getDurationInHours() <= 5., "endDate", "assistant.session.form.error.period");

		}
	}

	@Override
	public void perform(final TutorialSession object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final TutorialSession object) {
		assert object != null;

		Tuple tuple;
		SelectChoices choices;
		Tutorial tutorial;

		choices = SelectChoices.from(EnumType.class, object.getSessionType());
		tutorial = object.getTutorial();

		tuple = super.unbind(object, "title", "abstractm", "sessionType", "startDate", "endDate", "link");
		tuple.put("masterId", tutorial.getId());
		tuple.put("notPublished", tutorial.getNotPublished());
		tuple.put("types", choices);

		super.getResponse().setData(tuple);
	}

}
