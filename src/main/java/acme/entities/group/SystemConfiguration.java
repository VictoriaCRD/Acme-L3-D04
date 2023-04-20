package acme.entities.group;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// Serialization identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	protected String			systemCurrency = "EUR";

	@NotBlank
	private List<String> 		acceptedCurrencies;
	
	
	
	
}
