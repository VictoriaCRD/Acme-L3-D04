
package acme.features.lecturer.course;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.EnumType;
import acme.entities.Course;
import acme.entities.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCoursePublishService extends AbstractService<Lecturer, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int courseId;
		Course course;
		Lecturer lecturer;

		courseId = super.getRequest().getData("id", int.class);
		course = this.repository.findOneCourseById(courseId);
		lecturer = course == null ? null : course.getLecturer();
		status = course != null && course.isDraftMode() && super.getRequest().getPrincipal().hasRole(lecturer);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Course object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCourseById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Course object) {
		assert object != null;

		super.bind(object, "code", "title", "abstract$", "retailPrice", "furtherInformation", "courseNature");
	}

	@Override
	public void validate(final Course object) {
		assert object != null;

		final Collection<Lecture> lectures = this.repository.findManyLecturesByCourseId(object.getId());
		super.state(!lectures.isEmpty(), "courseNature", "lecturer.course.form.error.lecture-not-found");
		if (!lectures.isEmpty()) {
			final boolean handsOnLecture = lectures.stream().anyMatch(x -> x.getType().equals(EnumType.HANDS_ON));
			super.state(handsOnLecture, "courseNature", "lecturer.course.form.error.no-hands-on-lecture");
			final boolean publishedLectures = lectures.stream().allMatch(x -> x.isDraftMode() == false);
			super.state(publishedLectures, "courseNature", "lecturer.course.form.error.no-published-lecture");
		}
	}

	@Override
	public void perform(final Course object) {
		assert object != null;

		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Course object) {
		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "abstract$", "draftMode", "retailPrice", "furtherInformation");
		final List<Lecture> lectures = this.repository.findManyLecturesByCourseId(object.getId()).stream().collect(Collectors.toList());
		final EnumType nature = object.courseNature(lectures);
		tuple.put("courseNature", nature);
		super.getResponse().setData(tuple);
	}

}
