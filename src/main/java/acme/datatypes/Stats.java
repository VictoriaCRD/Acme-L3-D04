
package acme.datatypes;

import acme.framework.data.AbstractDatatype;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stats extends AbstractDatatype {

	protected static final long	serialVersionUID	= 1L;

	Double						average;
	Double						standardDesviation;
	Double						minimum;
	Double						maximum;
}
