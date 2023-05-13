
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.audit.auditor.id = :auditorId")
	Collection<AuditRecord> findManyAuditRecordsByAuditor(int auditorId);

	@Query("SELECT a FROM AuditRecord a where a.audit.id = :id")
	Collection<AuditRecord> findManyAuditRecordsByAuditId(int id);

	@Query("select a from Audit a WHERE a.id = :id")
	Audit findOneAuditById(int id);
}
