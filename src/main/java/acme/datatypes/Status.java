/*
 * Consumer.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.datatypes;

import acme.entities.Tutorialm;
import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Tutorialm					sum;
	Tutorialm					average;
	Tutorialm					desviation;
	Tutorialm					minimum;
	Tutorialm					maximum;


	public Status(final Tutorialm sum, final Tutorialm average, final Tutorialm desviation, final Tutorialm minimum, final Tutorialm maximum) {
		this.sum = sum;
		this.average = average;
		this.desviation = desviation;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public Status() {
	}

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
