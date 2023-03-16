
package acme.forms.individual.students;

import java.util.Map;

import com.google.common.math.Stats;

import acme.entities.group.EnumType;
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
