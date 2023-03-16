
package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import acme.roles.Company;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Peep extends AbstractEntity { 

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Length(max=75)
	protected String			title;

    @Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				startDate;

	@NotBlank
	@Length(max = 75)
	protected String			message;

	@NotBlank
	@Length(max = 100)
	protected String			nick;

	@URL
	protected String			link;

	@Email
	protected String			email;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
