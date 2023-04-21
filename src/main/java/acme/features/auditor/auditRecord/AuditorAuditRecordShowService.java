
package acme.features.auditor.auditRecord;

import org.springframework.stereotype.Service;

import acme.entities.AuditRecord;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordShowService extends AbstractService<Auditor, AuditRecord> {

}
