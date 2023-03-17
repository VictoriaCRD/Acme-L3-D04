
package acme.entities;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractEntity;
import acme.roles.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Enrolment extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}[0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			motivation;

	@NotBlank
	@Length(max = 100)
	protected String			goals;

	@ManyToOne(optional = false)
	@Valid
	protected Student			student;

	// Derived attributes -----------------------------------------------------


	@Transient
	//TODO: funci�n derivada
	private static Integer period() {
		/*
		 * Se trata de una propiedad derivada
		 * que va a calcular el periodo de tiempo de una inscripccion
		 * mediante las actividades asociadas a esta.
		 */
		return null;
	}

	// Relationships ----------------------------------------------------------


	@ManyToOne(optional = false)
	@NotNull
	@Valid
	protected Course course;

}
