<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>	
	<acme:input-textbox code="student.course.list.label.code" path="code" />
	<acme:input-textbox code="student.course.list.label.title" path="title" />
	<acme:input-textbox code="student.course.list.label.abstraction" path="abstraction" />
	<acme:input-money code="student.course.list.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="student.course.list.label.link" path="link" />
	<acme:input-textbox code="lecturer.course.form.label.nature" path="nature" readonly="true"/>

    
</acme:form>
