
package acme.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.datatypes.EnumType;
import acme.framework.components.datatypes.Money;
import acme.framework.data.AbstractEntity;
import acme.roles.Lecturer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}[0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstraction;

	protected boolean			draftMode;

	protected Money				retailPrice;

	@URL
	protected String			link;
	// Derived attributes -----------------------------------------------------


	//Los cursos puramente teóricos deben ser rechazados por el sistema
	@Transient
	public EnumType courseNature(final List<Lecture> lectures) {
		EnumType res = EnumType.BALANCED;
		if (!lectures.isEmpty()) {
			final Map<EnumType, Integer> lecturesByNature = new HashMap<>();
			for (final Lecture lecture : lectures) {
				final EnumType nature = lecture.getType();
				if (lecturesByNature.containsKey(nature))
					lecturesByNature.put(nature, lecturesByNature.get(nature) + 1);
				else
					lecturesByNature.put(nature, 1);
			}
			if (lecturesByNature.containsKey(EnumType.HANDS_ON) && lecturesByNature.containsKey(EnumType.THEORETICAL))
				if (lecturesByNature.get(EnumType.HANDS_ON) > lecturesByNature.get(EnumType.THEORETICAL))
					res = EnumType.HANDS_ON;
				else if (lecturesByNature.get(EnumType.THEORETICAL) > lecturesByNature.get(EnumType.HANDS_ON))
					res = EnumType.THEORETICAL;
			if (lecturesByNature.containsKey(EnumType.HANDS_ON) && !lecturesByNature.containsKey(EnumType.THEORETICAL))
				res = EnumType.HANDS_ON;
			if (!lecturesByNature.containsKey(EnumType.HANDS_ON) && lecturesByNature.containsKey(EnumType.THEORETICAL))
				res = EnumType.THEORETICAL;
		}
		return res;
	}

	// Relationships ----------------------------------------------------------


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Lecturer lecturer;

}
