/*
 * AuthenticatedConsumerRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package folder;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Tutorial;
import acme.entities.TutorialSession;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Assistant;

@Repository
public interface AssistantTutorialSessionRepository1 extends AbstractRepository {

	@Query("SELECT ts FROM TutorialSession ts WHERE ts.id = :id")
	TutorialSession findOneTutorialSessionById(int id);

	@Query("SELECT ts FROM TutorialSession ts WHERE ts.tutorial.id = :id")
	Collection<TutorialSession> findManyTutorialSessionsByTutorialId(int id);

	@Query("SELECT a FROM Assistant a WHERE a.id = :id")
	Assistant findOneAssistantById(int id);

	@Query("SELECT t FROM Tutorial t WHERE t.id = :id")
	Tutorial findOneTutorialById(int id);

}
