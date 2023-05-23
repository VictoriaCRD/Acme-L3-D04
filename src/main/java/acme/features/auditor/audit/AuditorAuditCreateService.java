
package acme.features.auditor.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Audit;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditCreateService extends AbstractService<Auditor, Audit> {

	@Autowired
	protected AuditorAuditRepository repository;


	@Override
	public void check() {
		super.getResponse().setChecked(true);

	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		final Audit object;
		Auditor auditor;

		auditor = this.repository.findOneAuditorById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Audit();
		object.setAuditor(auditor);
		object.setNotPublished(true);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Audit object) {
		assert object != null;
		super.bind(object, "code", "conclusion", "strongPoint", "weakPoint", "mark");

	}

	@Override
	public void validate(final Audit object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Audit existing;
			existing = this.repository.findOneAuditByCode(object.getCode());
			super.state(existing == null, "code", "auditor.audit.form.error.duplicated");
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

		tuple = super.unbind(object, "code", "conclusion", "strongPoint", "weakPoint", "mark");
		super.getResponse().setData(tuple);
	}

}
