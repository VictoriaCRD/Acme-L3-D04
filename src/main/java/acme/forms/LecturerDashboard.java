/*
 * Dashboard.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.forms;

import java.util.Map;

import acme.datatypes.Stats;
import acme.entities.EnumType;
import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class lecturerDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	private Map<EnumType, Integer>	totalLecturePerType;

	private Stats					totalTimePerLearningLectures;

	private Stats					totalTimePerLearningCourse;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}