
package acme.features.auditor.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Auditor;

@Repository
public interface AuditorAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.auditor.id = :auditorId")
	Collection<Audit> findManyAuditsByAuditor(int auditorId);

	@Query("select a from Audit a where a.id = :id")
	Audit findOneAuditById(int id);

	@Query("select a from Audit a")
	Collection<Audit> findAllAudits();

	@Query("select a from Auditor a WHERE a.id = :id")
	Auditor findOneAuditorById(int id);

	@Query("select a from Audit a where a.code = :code")
	Audit findOneAuditByCode(String code);

	@Query("SELECT a FROM AuditRecord a WHERE a.audit.id = :id")
	Collection<AuditRecord> findManyAuditRecordsByAuditId(int id);

}
