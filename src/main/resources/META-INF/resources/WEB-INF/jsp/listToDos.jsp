
<%@ include file="fragments/header.jspf"%>
<%@ include file="fragments/navigation.jspf"%>

<div class="container">
	<h1>To Do List for ${name}</h1>

	<table class="table">
		<thead>
			<tr>
				<!-- <th>ID</th> -->
				<th>Description</th>
				<th>Target Date</th>
				<th>Is Done?</th>
				<th>Delete</th>
				<th>Edit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<%-- <td>${todo.id}</td> --%>
					<td>${todo.description}</td>
					<td>${todo.targetDate}</td>
					<td>${todo.complete}</td>
					<td><a href="delete-todo?id=${todo.id}"
						class="btn btn-warning">X</a></td>
					<td><a href="update-todo?id=${todo.id}"
						class="btn btn-success">edit</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add ToDo Item</a>
</div>

<%@ include file="fragments/footer.jspf"%>
