
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditor extends AbstractRole {

	protected static final long	serialVersionUID	= 1L;

	@NotBlank
	@Min(76)
	protected String			firm;

	@NotBlank
	@Min(26)
	protected String			sector;

}
