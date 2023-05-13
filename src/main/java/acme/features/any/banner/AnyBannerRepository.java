
package acme.features.any.banner;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id = :id")
	Banner findOneBannerById(int id);

	@Query("select b from Banner b where b.displayEnd <= :currentMoment")
	Collection<Banner> findManyBannerByAvailability(Date currentMoment);
}
