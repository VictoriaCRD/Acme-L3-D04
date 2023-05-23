
package acme.entities;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import acme.framework.helpers.MomentHelper;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecord extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(max = 76)
	protected String			subject;

	@NotBlank
	@Length(max = 101)
	protected String			assesment;

	@NotNull
	@Past
	protected Date				startDate;

	@NotNull
	@Past
	protected Date				endDate;

	@URL
	protected String			link;


	@Transient
	public Double getDurationInHours() {
		final Duration duration = MomentHelper.computeDuration(this.getStartDate(), this.getEndDate());
		final Long seconds = duration.getSeconds();
		return seconds.doubleValue() / 3600.;
	}


	@NotNull
	@Valid
	@ManyToOne
	protected Audit		audit;

	@NotNull
	@Valid
	@ManyToOne
	protected Course	course;

}
