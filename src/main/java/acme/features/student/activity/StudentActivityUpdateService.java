
package acme.features.student.activity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Activity;
import acme.entities.Enrolment;
import acme.entities.EnumType;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentActivityUpdateService extends AbstractService<Student, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentActivityRepository repository;

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
		int activityId;
		Enrolment enrolment;

		activityId = super.getRequest().getData("id", int.class);
		enrolment = this.repository.findOneEnrolmentByActivityId(activityId);
		status = enrolment != null && !enrolment.getNotPublished() && super.getRequest().getPrincipal().hasRole(enrolment.getStudent());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Activity object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneActivityById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Activity object) {
		assert object != null;

		super.bind(object, "title", "textAbstract", "typeOfActivity", "initialDate", "finishDate", "link");
	}

	@Override
	public void validate(final Activity object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("initialDate")) {
			Calendar calendar;
			final Date date;

			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2000);
			calendar.set(Calendar.MONTH, Calendar.JANUARY);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			date = new Date(calendar.getTimeInMillis());

			super.state(MomentHelper.isAfter(object.getInitialDate(), date), "initialDate", "student.activity.form.error.wrong-start");
		}
		if (!super.getBuffer().getErrors().hasErrors("finishDate")) {
			Calendar calendar;
			final Date date;

			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2100);
			calendar.set(Calendar.MONTH, Calendar.DECEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 0);
			date = new Date(calendar.getTimeInMillis());

			super.state(MomentHelper.isBefore(object.getFinishDate(), date), "finishDate", "student.activity.form.error.wrong-end");
		}
		if (!super.getBuffer().getErrors().hasErrors("finishDate"))
			super.state(MomentHelper.isBefore(object.getInitialDate(), object.getFinishDate()), "finishDate", "student.activity.form.error.wrong-dates");
	}

	@Override
	public void perform(final Activity object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Activity object) {
		assert object != null;

		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(EnumType.class, object.getTypeOfActivity());

		tuple = super.unbind(object, "title", "textAbstract", "typeOfActivity", "initialDate", "finishDate", "link");
		tuple.put("enrolmentId", object.getEnrolment().getId());
		tuple.put("notPublished", object.getEnrolment().getNotPublished());
		tuple.put("types", choices);

		super.getResponse().setData(tuple);
	}

}
