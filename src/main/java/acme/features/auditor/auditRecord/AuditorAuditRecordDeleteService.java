
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
public class AuditorAuditRecordDeleteService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	protected AuditorAuditRecordRepository repository;

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
		final AuditRecord auditRecord;
		final Audit audit;
		final Auditor auditor;

		masterId = super.getRequest().getData("id", int.class);
		auditRecord = this.repository.findOneAuditRecordById(masterId);
		audit = auditRecord == null ? null : auditRecord.getAudit();
		auditor = audit == null ? null : audit.getAuditor();
		status = auditRecord != null && audit != null && audit.getNotPublished() && super.getRequest().getPrincipal().hasRole(auditor) && audit.getAuditor().getId() == super.getRequest().getPrincipal().getActiveRoleId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditRecord object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneAuditRecordById(id);

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
	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;

		this.repository.delete(object);
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
