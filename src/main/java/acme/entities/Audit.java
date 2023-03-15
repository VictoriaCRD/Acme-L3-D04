
package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Audit extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	//@Pattern(regexp =)
	protected String			code;

	@NotBlank
	@Length(max = 101)
	protected String			conclusion;

	@NotBlank
	@Length(max = 101)
	protected String			strongPoint;

	@NotBlank
	@Length(max = 101)
	protected String			weakPoint;

	@URL
	protected String			link;

}
