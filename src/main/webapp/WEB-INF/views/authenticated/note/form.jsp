<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
    <acme:input-textbox code="authenticated.note.form.label.title" path="title"/>
    <acme:input-textarea code="authenticated.note.form.label.message" path="message"/>
	<acme:input-textbox code="authenticated.note.from.label.author" path="author"/>
	<acme:input-textbox code="authenticated.note.form.label.email" path="email"/>
	<acme:input-textbox code="authenticated.note.from.label.link" path="link"/>
	
	<jstl:choose>	

		<jstl:when test="${_command == 'create'}">
			<acme:submit code="authenticated.note.form.button.create" action="/authenticated/note/create"/>
		</jstl:when>		
	</jstl:choose>
	
	
</acme:form>