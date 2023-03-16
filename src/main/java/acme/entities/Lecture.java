package acme.entities;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public class Lecture extends AbstractEntity{

	protected static final long	serialVersionUID	= 1L;
	
	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			textAbstract;
	
	
	
	@NotBlank
	@Length(max = 100)
	protected String			body;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				initialDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				finishDate;
	//TODO Custom restriction=> initialDate must be < finishDate 
	//& finishDate must be > iniialDate

	//TODO Derived attribute=> Double timePeriod= finishDate-initialDate
	
	@URL
	protected String			link;
	
	
	
	
}
