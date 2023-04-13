
package acme.features.student.workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Activity;
import acme.entities.Course;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentWorkbookShowService extends AbstractService<Student, Activity> {

	@Autowired
	protected StudentWorkbookRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Course object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findCourseById(id);

		super.getBuffer().setData(object);
	}

	/*
	 * @Override
	 * public void unbind(final Course object) {
	 * assert object != null;
	 * 
	 * Tuple tuple;
	 * 
	 * tuple = super.unbind(object, "code", "title", "abstraction", "draftMode", "retailPrice", "link");
	 * 
	 * final String lecturer = object.getLecturer().getUserAccount().getUsername();
	 * tuple.put("lecturer", lecturer);
	 * super.getResponse().setData(tuple);
	 * }
	 */
}
