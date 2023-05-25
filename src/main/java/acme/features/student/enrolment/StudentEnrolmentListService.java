/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.student.enrolment;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Activity;
import acme.entities.Enrolment;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentListService extends AbstractService<Student, Enrolment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


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
		Collection<Enrolment> enrolments;

		final int id = super.getRequest().getPrincipal().getAccountId();
		enrolments = this.repository.findAllEnrolmentsByStudentId(id);
		super.getBuffer().setData(enrolments);
	}

	@Override
	public void unbind(final Enrolment object) {
		assert object != null;

		Tuple tuple;
		Activity activity;
		double diferenciaHoras = 0.0;
		double total = 0.0;
		final Collection<Activity> a = this.repository.findManyActivityByEnrolmentId(object.getId());
		final List<Activity> ListaActivities = a.stream().collect(Collectors.toList());

		if (ListaActivities == null)
			total = 0.0;

		for (int i = 0; i < ListaActivities.size(); i++) {
			activity = ListaActivities.get(i);
			final Date inicialPeriod = activity.getInicialPeriod();
			final Date finalPeriod = activity.getFinalPeriod();
			final long milisegundosInicio = inicialPeriod.getTime();
			final long milisegundosFin = finalPeriod.getTime();
			final long diferenciaMilisegundos = milisegundosFin - milisegundosInicio;
			if (diferenciaMilisegundos > 0) {
				diferenciaHoras = (double) diferenciaMilisegundos / (1000 * 60 * 60);
				total += diferenciaHoras;
			}

		}
		final int hours = (int) total;
		final int minutes = (int) ((total - hours) * 60);

		final double diffInHoursWithFormat = Double.parseDouble(hours + "." + minutes);

		tuple = super.unbind(object, "code");
		tuple.put("workTime", diffInHoursWithFormat);
		super.getResponse().setData(tuple);
	}

}
