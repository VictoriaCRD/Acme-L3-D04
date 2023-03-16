
package acme.forms;

import java.util.Map;

import javax.persistence.EnumType;

import acme.datatypes.Stats;
import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard extends AbstractForm {

	protected static final long	serialVersionUID	= 1L;

	Map<EnumType, Integer>		totalNumberOfAuditPerCourseType;
	Stats						NumberOfRecordPerAudit;
	Stats						LengthOfPeriodPerRecord;

}
