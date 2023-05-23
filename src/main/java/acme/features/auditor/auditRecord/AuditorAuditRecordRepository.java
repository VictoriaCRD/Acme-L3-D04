
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.entities.Course;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.audit.auditor.id = :auditorId")
	Collection<AuditRecord> findManyAuditRecordsByAuditor(int auditorId);

	@Query("SELECT a FROM AuditRecord a where a.audit.id = :id")
	Collection<AuditRecord> findManyAuditRecordsByAuditId(int id);

	@Query("select a from Audit a WHERE a.id = :id")
	Audit findOneAuditById(int id);

	@Query("select a from AuditRecord a WHERE a.id = :id")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select a.audit from AuditRecord a where a.id = :id")
	Audit findOneAuditRecordByAuditId(int id);

	@Query("SELECT c FROM Course c WHERE c.notPublished = false")
	Collection<Course> findAllCourses();

	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findOneCourseById(int id);
}
