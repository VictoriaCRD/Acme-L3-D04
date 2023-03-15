
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard extends AbstractForm {

	protected static final long	serialVersionUID	= 1L;

	Double						totalNumberOfJAuditPerCourse;
	Double						averageNumberOfRecordPerAudit;
	Double						deviationNumberOfRecordPerAudit;
	Double						minNumberOfRecordPerAudit;
	Double						maxNumberOfRecordPerAudit;
	Double						averageLengthOfPeriodPerRecord;
	Double						deviationLengthOfPeriodPerRecord;
	Double						minLenghtOfPeriodRecord;
	Double						maxLenghtOfPeriodRecord;

}
