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

package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractRole;
import acme.roles.Assistant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tutorial extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "“[A-Z]{1,3}[0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstractm;

	//Separados por ;
	@NotBlank
	@Length(max = 100)
	protected String			goals;

	protected double			estimatedTime;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)

	protected Course			course;

	@NotNull
	@Valid
	@ManyToOne(optional = false)

	protected Assistant			assistant;

}
