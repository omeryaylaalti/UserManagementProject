<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management Screen</title>
<script src="/usermanagement/resources/js/jquery.js"></script>
<script src="/usermanagement/resources/js/inputmask_phone.js"></script>
<script src="/usermanagement/resources/js/custom.js"></script>
<link rel="stylesheet"
	href="/usermanagement/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="/usermanagement/resources/js/jquery.min.js"></script>
<script
	src="/usermanagement/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>


</head>
<body>
	<div style="text-align: center">
		<h2>
			Welcome to User Management Panel !..<br> <br>
		</h2>
		<div class="container">

			<table class="table table-bordered" id="contactTable">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Phone Number</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${Users}" var="user">
						<tr>
							<td><c:out value="${user.firstname}" /></td>
							<td><c:out value="${user.lastname}" /></td>
							<td><c:out value="${user.phonenumber}" /></td>
							<td><a href="#" data-toggle="modal"
								data-target="#EditUserModal" class="btn btn-primary"
								data-user-no="${user.id}"
								data-user-firstname="${user.firstname}"
								data-user-lastname="${user.lastname}"
								data-user-phone="${user.phonenumber}">Edit <i
									class="glyphicon glyphicon-refresh" aria-hidden="true"></i></a></td>
							<td><a
								data-href="${pageContext.request.contextPath}/delete/${user.id}"
								data-toggle="modal" data-target="#deleteUserModal"
								class="btn btn-danger">Delete <i
									class="glyphicon glyphicon-remove"></i></a></td>


						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="userResponse"></div>
		</div>
	</div>
	<div class="container">
		<button type="button" class="btn btn-info btn-md" data-toggle="modal"
			data-target="#myModal">Add New User</button>

		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog ">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Create New User</h4>
					</div>
					<div class="modal-body">
						<table class="table table-bordered">
							<tr>
								<td>Enter your first name :</td>
								<td><input type="text" id="firstname"><br>
									<div class="durum" align="center"></div></td>
							</tr>
							<tr>
								<td>Enter your last name :</td>
								<td><input type="text" id="lastname"><br />
									<div class="durum2" align="center"></td>
							</tr>
							<tr>
								<td>Enter your phone number :</td>
								<td><input type="text" id="phonenumber"
									placeholder="(XXX)-XXXXXXX"><br />
									<div class="durum3" align="center"></div></td>

							</tr>
							<tr>
								<td></td>
								<td><input type="hidden" class="hiddenRecaptcha required"
									name="hiddenRecaptcha" id="hiddenRecaptcha">
									<div class="g-recaptcha"
										data-sitekey="6Lc6liYTAAAAAAsDvqiZTWSlywQAe-UzmEClTwq5"></div>
								</td>
							</tr>
						</table>
						<div id="info" style="color: green;"></div>
					</div>

					<div class="modal-footer">

						<button type="button" value="Add User" id="btn"
							class="btn btn-success" onclick="doAjaxPost()" disabled>Add
							User</button>


						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="EditUserModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Update User</h4>
					</div>
					<div class="modal-body">
						<table class="table table-bordered">
							<tr>
								<td>Enter your first name :</td>
								<td><input type="text" id="newFirstname"><br />
									<div class="result" align="center"></div></td>
							</tr>
							<tr>
								<td>Enter your last name :</td>
								<td><input type="text" id="newLastname"><br />
									<div class="result2" align="center"></div></td>
							</tr>
							<tr>
								<td>Enter your phone number :</td>
								<td><input type="text" id="newPhonenumber"><br />
									<div class="result3" align="center"></div></td>
							</tr>
						</table>
						<div id="updatedInfo" style="color: green;"></div>
						<input type="hidden" id="userId" value="" />
					</div>
					<div class="modal-footer">

						<button type="button" id="resultbtn" value="Edit"
							class="btn btn-primary" onclick="doUpdate()">Edit</button>

						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="deleteUserModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Warning !</h4>
					</div>

					<div class="modal-body">
						<p class="bg-danger">
							<b>Are you sure ?</b>
						</p>
						<a href="#" class="btn btn-danger btn-lg" id="btnDeleteUser">Ok</a>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>