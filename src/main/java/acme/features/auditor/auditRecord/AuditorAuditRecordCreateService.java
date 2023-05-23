
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.entities.Course;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordCreateService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	protected AuditorAuditRecordRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("masterId", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int masterId;
		final Audit audit;

		masterId = super.getRequest().getData("masterId", int.class);

		audit = this.repository.findOneAuditById(masterId);
		status = audit != null && audit.getNotPublished() && super.getRequest().getPrincipal().hasRole(audit.getAuditor());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditRecord object;
		Audit audit;
		int masterId;

		masterId = super.getRequest().getData("masterId", int.class);
		audit = this.repository.findOneAuditById(masterId);

		object = new AuditRecord();
		object.setAudit(audit);

		super.getBuffer().setData(object);

	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;
		int courseId;
		Course course;
		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findOneCourseById(courseId);

		super.bind(object, "subject", "assesment", "startDate", "endDate", "link");
		object.setCourse(course);
	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

		if (!(super.getBuffer().getErrors().hasErrors("startDate") || super.getBuffer().getErrors().hasErrors("endDate"))) {
			final boolean endDateIsAfter = object.getEndDate().after(object.getStartDate());
			super.state(endDateIsAfter, "endDate", "auditor.record.form.error.endDate");
			super.state(object.getDurationInHours() >= 1., "endDate", "auditor.record.form.error.period");

		}

	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;
		Collection<Course> courses;
		SelectChoices choices;
		Tuple tuple;
		courses = this.repository.findAllCourses();
		choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "subject", "assesment", "startDate", "endDate", "link");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
