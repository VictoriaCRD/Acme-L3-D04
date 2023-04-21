
package acme.features.authenticated.note;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Note;
import acme.framework.components.accounts.Authenticated;
import acme.framework.controllers.AbstractController;

@Controller
public class AutheticatedNoteController extends AbstractController<Authenticated, Note> {

	@Autowired
	protected AuthenticatedNoteListService		listService;

	@Autowired
	protected AuthenticatedNoteShowService		showService;

	@Autowired
	protected AuthenticatedNoteCreateService	createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("create", this.createService);
	}

}
