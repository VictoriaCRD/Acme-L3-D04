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

package acme.features.student.activity;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Activity;
import acme.entities.Course;
import acme.entities.Enrolment;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Student;

@Repository
public interface StudentActivityRepository extends AbstractRepository {

	@Query("SELECT s FROM Student s")
	List<Student> findAllStudents();

	@Query("SELECT a FROM Activity a WHERE a.enrolment.student.userAccount.id = :id")
	Collection<Activity> findAllActivitiesByStudentId(int id);

	@Query("SELECT s FROM Student s WHERE s.id = :id")
	Student findStudentById(int id);

	@Query("SELECT a FROM Activity a")
	Collection<Activity> findAllActivities();

	@Query("SELECT a FROM Activity a WHERE a.id = :id")
	Activity findActivityById(int id);

	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findCourseById(int id);

	@Query("SELECT c FROM Course c")
	Collection<Course> findCourses();

	@Query("SELECT e FROM Enrolment e")
	List<Enrolment> findAllEnrolments();

	@Query("SELECT e FROM Enrolment e WHERE e.student.userAccount.id = :id AND e.draftMode =:boo")
	List<Enrolment> findAllEnrolmentsByStudentId(int id, boolean boo);

	@Query("select e from Enrolment e where e.id= :id")
	Enrolment findEnrolmentById(int id);

	@Query("select s from Student s where s.id= :id")
	Student findOneStudentById(int id);

	@Query("select e from Enrolment e where e.id= :id")
	Collection<Activity> findAllActivitiesByEnrolmentId(int id);

	@Query("select a from Activity a where a.enrolment.id = :id")
	Collection<Activity> findManyActivityByEnrolmentId(int id);

	@Query("select a.enrolment from Activity a where a.id = :id")
	Enrolment findOneEnrolmentByActivityId(int id);
}
