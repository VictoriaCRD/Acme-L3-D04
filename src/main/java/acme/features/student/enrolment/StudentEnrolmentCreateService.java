
package acme.features.student.enrolment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.Enrolment;
import acme.features.lecturer.course.LecturerCourseRepository;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;
import acme.roles.Student;

@Service
public class StudentEnrolmentCreateService extends AbstractService<Student, Enrolment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repository;

	// AbstractService<Authenticated, Provider> ---------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void load() {
		Course object;
		Lecturer lecturer;

		lecturer = this.repository.findOneLecturerById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Course();
		object.setDraftMode(true);
		object.setLecturer(lecturer);

		super.getBuffer().setData(object);
	}
	/*
	 * @Override
	 * public void bind(final Course object) {
	 * assert object != null;
	 * 
	 * super.bind(object, "code", "title", "abstract$", "retailPrice", "furtherInformation");
	 * }
	 * 
	 * @Override
	 * public void validate(final Course object) {
	 * assert object != null;
	 * 
	 * if (!super.getBuffer().getErrors().hasErrors("code")) {
	 * Course existing;
	 * 
	 * existing = this.repository.findOneCourseByCode(object.getCode());
	 * super.state(existing == null, "code", "lecturer.course.form.error.duplicated");
	 * }
	 * if (!super.getBuffer().getErrors().hasErrors("retailPrice"))
	 * super.state(object.getRetailPrice().getAmount() > 0, "retailPrice", "lecturer.course.form.error.negative-retailPrice");
	 * }
	 * 
	 * @Override
	 * public void perform(final Course object) {
	 * assert object != null;
	 * 
	 * this.repository.save(object);
	 * }
	 * 
	 * @Override
	 * public void unbind(final Course object) {
	 * Tuple tuple;
	 * 
	 * tuple = super.unbind(object, "code", "title", "abstract$", "draftMode", "retailPrice", "furtherInformation");
	 * 
	 * super.getResponse().setData(tuple);
	 * }
	 * 
	 * @Override
	 * public void onSuccess() {
	 * if (super.getRequest().getMethod().equals(HttpMethod.POST))
	 * PrincipalHelper.handleUpdate();
	 * }
	 */

}
