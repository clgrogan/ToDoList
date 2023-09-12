
<%@ include file="fragments/header.jspf"%>
<%@ include file="fragments/navigation.jspf"%>

<div class="container">
	<h1>Enter the To Do Item Details</h1>
	<pre>${invalid}</pre>
	<form:form method="post" modelAttribute="todo">
		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="required" />
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>
		<fieldset class="mb-3">
			<form:label path="targetDate">Target Date</form:label>
			<form:input type="text" path="targetDate" required="required" />
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>
		<form:input type="hidden" path="complete" />
		<form:input type="hidden" path="id" />
		<input type="submit" class="btn btn-success">
	</form:form>
</div>

<%@ include file="fragments/footer.jspf"%>
<script type="text/javascript">
	$('#targetDate').datepicker({
		format : 'mm-dd-yyyy'
	});
</script>