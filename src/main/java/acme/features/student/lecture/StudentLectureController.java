
package acme.features.student.lecture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Lecture;
import acme.framework.controllers.AbstractController;
import acme.roles.Student;

// NotMandatory
@Controller
public class StudentLectureController extends AbstractController<Student, Lecture> {

	@Autowired
	protected StudentLectureListService	service;

	@Autowired
	protected StudentLectureShowService	serviceShow;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.service);
		super.addBasicCommand("show", this.serviceShow);
	}
}
