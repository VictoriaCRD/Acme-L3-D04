
package acme.datatypes;

import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stats extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	Double						average;
	Double						standardDesviation;
	Double						minimum;
	Double						maximum;

}
