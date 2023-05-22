/*
 * AssistantTutorialSessionTestRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.assistant.tutorialSession;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.Tutorial;
import acme.entities.TutorialSession;
import acme.framework.repositories.AbstractRepository;

public interface AssistantTutorialSessionTestRepository extends AbstractRepository {

	@Query("select j from Tutorial j where j.assistant.userAccount.username = :username")
	Collection<Tutorial> findManyTutorialsByAssistantUsername(String username);

	@Query("select d from TutorialSession d where d.tutorial.assistant.userAccount.username = :username")
	Collection<TutorialSession> findManyDutiesByAssistantUsername(String username);

}
