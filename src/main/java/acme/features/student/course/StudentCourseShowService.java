/*
 * AuthenticatedConsumerController.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.student.course;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.EnumType;
import acme.entities.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentCourseShowService extends AbstractService<Student, Course> {

	//Internal state ---------------------------------------------------------

	@Autowired
	protected StudentCourseRepository repository;

	//AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		Course course;
		int id;

		id = super.getRequest().getData("id", int.class);
		course = this.repository.findCourseById(id);

		final Boolean authorise = !course.getNotPublished();

		super.getResponse().setAuthorised(authorise);
	}

	@Override
	public void load() {
		Course course;
		int id;

		id = super.getRequest().getData("id", int.class);
		course = this.repository.findCourseById(id);

		super.getBuffer().setData(course);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		//		final int id = object.getId();
		//		final Collection<CourseLecture> mapper = this.repository.findCourseLectureByCourseId(id);
		//		final List<String> lectures = mapper.stream().map(m -> m.getLecture().getTitle()).collect(Collectors.toList());
		//
		//		final String lecturer = object.getLecturer().getAlmaMater();
		//		tuple.put("lectures", lectures);
		//		tuple.put("lecturer", lecturer);
		final Tuple tuple = super.unbind(object, "code", "title", "abstracts", "price", "link");
		final List<Lecture> lectures = this.repository.findManyLecturesByCourseId(object.getId()).stream().collect(Collectors.toList());
		final EnumType nature = object.nature(lectures);
		tuple.put("nature", nature);
		super.getResponse().setData(tuple);
	}

}
