
package acme.features.auditor.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.AuditRecord;
import acme.framework.controllers.AbstractController;
import acme.roles.Auditor;

@Controller
public class AuditorAuditRecordController extends AbstractController<Auditor, AuditRecord> {

	@Autowired
	protected AuditorAuditRecordShowService		showService;

	@Autowired
	protected AuditorAuditRecordListService		listService;

	@Autowired
	protected AuditorAuditRecordDeleteService	deleteService;

	@Autowired
	protected AuditorAuditRecordCreateService	createService;


	@PostConstruct
	protected void initilialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("create", this.createService);
	}
}
