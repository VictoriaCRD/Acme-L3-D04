
package acme.features.auditor.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Audit;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditUpdateService extends AbstractService<Auditor, Audit> {

	@Autowired
	protected AuditorAuditRepository repository;


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
		Audit audit;
		masterId = super.getRequest().getData("id", int.class);
		audit = this.repository.findOneAuditById(masterId);
		status = audit != null && audit.getNotPublished() == true && super.getRequest().getPrincipal().hasRole(audit.getAuditor()) && audit.getAuditor().getId() == super.getRequest().getPrincipal().getActiveRoleId();
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Audit object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneAuditById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Audit object) {
		assert object != null;

		super.bind(object, "code", "conclusion", "strongPoint", "weakPoint");
	}

	@Override
	public void validate(final Audit object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Audit existing;
			existing = this.repository.findOneAuditByCode(object.getCode());
			super.state(existing == null || existing.getId() == object.getId(), "code", "auditor.audit.form.error.duplicated");
		}
	}

	@Override
	public void perform(final Audit object) {
		assert object != null;
		this.repository.save(object);
	}
	@Override
	public void unbind(final Audit object) {
		assert object != null;
		Tuple tuple;

		tuple = super.unbind(object, "code", "conclusion", "strongPoint", "weakPoint", "mark", "notPublished");

		super.getResponse().setData(tuple);
	}
}
