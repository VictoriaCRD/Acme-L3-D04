/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.moneyExchange;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.forms.MoneyExchange;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.datatypes.Money;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.StringHelper;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedMoneyExchangePerformService extends AbstractService<Authenticated, MoneyExchange> {

	// AbstractService interface ----------------------------------------------

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
		MoneyExchange object;

		object = new MoneyExchange();

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final MoneyExchange object) {
		assert object != null;

		super.bind(object, "source", "targetCurrency", "date", "target");
	}

	@Override
	public void validate(final MoneyExchange object) {
		assert object != null;
	}

	@Override
	public void perform(final MoneyExchange object) {
		assert object != null;

		Money source, target;
		String targetCurrency;
		Date date;
		MoneyExchange exchange;

		source = super.getRequest().getData("source", Money.class);
		targetCurrency = super.getRequest().getData("targetCurrency", String.class);
		exchange = this.computeMoneyExchange(source, targetCurrency);
		super.state(exchange != null, "*", "authenticated.money-exchange.form.label.api-error");
		if (exchange == null) {
			object.setTarget(null);
			object.setDate(null);
		} else {
			target = exchange.getTarget();
			object.setTarget(target);
			date = exchange.getDate();
			object.setDate(date);
		}
	}

	@Override
	public void unbind(final MoneyExchange object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "source", "targetCurrency", "date", "target");

		super.getResponse().setData(tuple);
	}

	// Ancillary methods ------------------------------------------------------

	public MoneyExchange computeMoneyExchange(final Money source, final String targetCurrency) {
		assert source != null;
		assert !StringHelper.isBlank(targetCurrency);

		MoneyExchange result;
		RestTemplate api;
		String sourceCurrency;
		Double sourceAmount;
		final Double targetAmount, rate;
		Money target;

		try {
			api = new RestTemplate();

			sourceCurrency = source.getCurrency();
			sourceAmount = source.getAmount();

			target = new Money();

			target.setCurrency(targetCurrency);

			result = new MoneyExchange();
			result.setSource(source);
			result.setTargetCurrency(targetCurrency);

			result.setTarget(target);
		} catch (final Throwable oops) {
			result = null;
		}

		return result;
	}

}
