
package acme.features.authenticated.bulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Bulletin;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedBulletinShowService extends AbstractService<Authenticated, Bulletin> {

	@Autowired
	protected AuthenticatedBulletinRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		Bulletin object;
		final int id = super.getRequest().getData("id", int.class);
		object = this.repository.findBulletinById(id);
		final Date date = new Date();
		super.getResponse().setAuthorised(date.compareTo(object.getMoment()) > 0);
	}

	@Override
	public void load() {
		Bulletin object;
		final int id = super.getRequest().getData("id", int.class);
		object = this.repository.findBulletinById(id);
		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Bulletin object) {
		assert object != null;
		final Tuple tuple = super.unbind(object, "moment", "title", "message", "critical", "link");
		super.getResponse().setData(tuple);
	}

}
