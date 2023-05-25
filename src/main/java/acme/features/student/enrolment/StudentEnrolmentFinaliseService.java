/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.student.enrolment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.Enrolment;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentFinaliseService extends AbstractService<Student, Enrolment> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		Enrolment enrolment;
		Student student;
		int id;
		boolean status;

		id = super.getRequest().getData("id", int.class);
		enrolment = this.repository.findEnrolmentById(id);
		student = enrolment == null ? null : enrolment.getStudent();
		status = enrolment != null && enrolment.isDraftMode() && super.getRequest().getPrincipal().hasRole(student);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final Enrolment enrolment = this.repository.findEnrolmentById(id);

		super.getBuffer().setData(enrolment);
	}

	@Override
	public void bind(final Enrolment object) {

		final int courseId = super.getRequest().getData("course", int.class);
		final Course course = this.repository.findCourseById(courseId);

		super.bind(object, "motivation", "goals", "code", "holderName", "creditCard", "cvc", "expiryDate");
		object.setCourse(course);
	}

	@Override
	public void validate(final Enrolment object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			final boolean duplicatedCode = this.repository.findAllEnrolments().stream().filter(e -> e.getId() != object.getId()).anyMatch(e -> e.getCode().equals(object.getCode()));

			super.state(!duplicatedCode, "code", "student.enrolment.form.error.duplicated-code");
		}
		if (!super.getBuffer().getErrors().hasErrors("holderName")) {
			final String holderName = object.getHolderName();
			final boolean wrongholderName = holderName.isEmpty();

			super.state(!wrongholderName, "holderName", "student.enrolment.form.error.wrong-holder");
		}
		if (!super.getBuffer().getErrors().hasErrors("creditCard")) {
			final String creditCard = object.getCreditCard();
			final boolean wrongCard = creditCard.matches("^\\d{4}\\/\\d{4}\\/\\d{4}\\/\\d{4}$");

			super.state(wrongCard, "creditCard", "student.enrolment.form.error.wrong-card");
		}
		if (!super.getBuffer().getErrors().hasErrors("cvc")) {
			final String cvc = object.getCvc();
			final boolean wrongcvc = cvc.matches("^\\d{3}$");

			super.state(wrongcvc, "cvc", "student.enrolment.form.error.wrong-cvc");
		}
		if (!super.getBuffer().getErrors().hasErrors("expiryDate")) {
			final String expiryDate = object.getExpiryDate();
			final boolean wrongExpiryDate = expiryDate.matches("^\\d{2}\\/\\d{2}$");

			super.state(wrongExpiryDate, "expiryDate", "student.enrolment.form.error.wrong-expiry-date");
		}
	}

	@Override
	public void perform(final Enrolment object) {
		assert object != null;

		object.setDraftMode(false);

		final String card = super.getRequest().getData("creditCard", String.class);

		object.setLowerNibble(card.substring(card.length() - 4));

		this.repository.save(object);
	}

	@Override
	public void unbind(final Enrolment object) {
		assert object != null;
		Tuple tuple;

		Collection<Course> courses;
		SelectChoices choices;
		courses = this.repository.findAllCourse(false);
		choices = SelectChoices.from(courses, "code", object.getCourse());

		tuple = super.unbind(object, "code", "draftMode", "motivation", "goals", "holderName", "creditCard", "cvc", "expiryDate");

		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}

}
