
package forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Map<String, Integer>		totalNomberOfPracticalGroupedByMoth;

	Double						averagePeriorPracticum;
	Double						deviationPeriorOfPracticum;
	Double						minimumPeriodOfPracticum;
	Double						maximumPeriodOfPracticum;
	Double						averagePeriodOfSessions;
	Double						deviationPeriodOfSessions;
	Double						miniumPeriodOfSessions;
	Double						maximumPeriodOfSessions;
}
