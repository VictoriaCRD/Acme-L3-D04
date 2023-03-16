
package acme.forms;

import java.util.Map;

import acme.datatypes.Stats;
import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	//Strings represent every role: Assistant, Auditor, Consumer, Lecturer, Provider, Student
	Map<String, Integer>		numberOfPrincipalsByRol;
	Double						peepsWithEmailAndLinkRatio;
	Double						criticalBulletinsRatio;
	Double						nonCriticalBulletinsRatio;

	//Strings represent currencies: EUR, USD, GBP

	protected Stats				stadisticsOfCurrency;
	protected Stats				stadisticsOfNotesOverLastTenWeeks;

}
