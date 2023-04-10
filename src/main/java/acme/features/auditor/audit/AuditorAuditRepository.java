
package acme.features.auditor.audit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Audit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRepository extends AbstractRepository {

	//@Query("select a from Audit a where a.auditor.id = :auditor.id")
	//Audit findManyAuditsByAuditor(int auditorId);

	@Query("select a from Audit a where a.id = :id")
	Audit findOneAuditById(int id);

	//@Query("select a from Audit a")
	//Collection<Audit> findAllAudits(int id);

}
