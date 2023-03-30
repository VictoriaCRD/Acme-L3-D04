
package acme.forms;

import java.util.Map;

import acme.datatypes.EnumType;
import acme.datatypes.Stats;
import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDashboard extends AbstractForm {

	protected static final long			serialVersionUID	= 1L;

	protected Map<EnumType, Integer>	totalNumberOfActivities;

	protected Stats						stadisticsOfTimePeriodFromActivies;

	protected Stats						stadisticsOfWorkingTimeFromEnrolmentstotalNumberOfHandsOnActivities;

}
