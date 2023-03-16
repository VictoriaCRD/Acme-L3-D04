
package acme.entities;

import javax.persistence.Entity;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	protected EnumType			courseType;

}
