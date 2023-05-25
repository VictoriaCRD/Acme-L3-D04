
package acme.entities;

public enum Mark {

	Aplus("A+"), A("A"), B("B"), C("C"), F("F"), Fminus("F-");


	private String name;


	Mark(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
