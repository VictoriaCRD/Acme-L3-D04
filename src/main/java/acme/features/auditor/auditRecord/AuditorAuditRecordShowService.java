
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
public class AuditorAuditRecordShowService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	protected AuditorAuditRecordRepository repository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int auditRecordId;
		Audit audit;

		auditRecordId = super.getRequest().getData("id", int.class);
		audit = this.repository.findOneAuditRecordByAuditId(auditRecordId);
		status = audit != null && super.getRequest().getPrincipal().hasRole(audit.getAuditor());

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
	public void unbind(final AuditRecord object) {
		assert object != null;

		final Collection<Course> courses = this.repository.findAllCourses();
		SelectChoices choices;
		Tuple tuple;
		final Audit audit = object.getAudit();

		choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "subject", "assesment", "startDate", "endDate", "link");
		tuple.put("audit", audit);
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
