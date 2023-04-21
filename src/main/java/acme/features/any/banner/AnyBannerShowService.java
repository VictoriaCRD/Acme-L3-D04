
package acme.features.any.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Banner;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AnyBannerShowService extends AbstractService<Any, Banner> {

	@Autowired
	protected AnyBannerRepository repository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int id;
		Banner banner;

		id = super.getRequest().getData("id", int.class);
		banner = this.repository.findOneBannerById(id);
		status = banner != null && MomentHelper.isPresentOrFuture(banner.getDisplayEnd());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;
		Banner object;

		//id = ThreadLocalRandom.current().nextInt(1, 5);
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneBannerById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "slogan", "picture", "link");

		super.getResponse().setData(tuple);
	}

}
