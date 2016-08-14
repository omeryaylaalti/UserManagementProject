function doAjaxPost() {
	// get the form values
	var firstname = $('#firstname').val();
	var lastname = $('#lastname').val();
	var phonenumber = $('#phonenumber').val();

	$.ajax({
		type : "POST",
		url : "/usermanagement/AddUser",
		data : "firstname=" + firstname + "&lastname=" + lastname
				+ "&phonenumber=" + phonenumber,
		success : function(response) {
			// we have the response
			$('#info').html(response);
			setTimeout(function() {
				location.reload();
			}, 1500);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function doUpdate() {
	// get the form values
	var id = $('#userId').val();
	var firstname = $('#newFirstname').val();
	var lastname = $('#newLastname').val();
	var phonenumber = $('#newPhonenumber').val();
	var userData = {
		"id" : id,
		"firstname" : firstname,
		"lastname" : lastname,
		"phonenumber" : phonenumber
	};
	console.log(userData);
	$.ajax({
		type : "POST",
		url : "/usermanagement/UpdateUser",
		data : "id=" + id + "&firstname=" + firstname + "&lastname=" + lastname
				+ "&phonenumber=" + phonenumber,
		success : function(response) {
			$('#updatedInfo').html(response);
			setTimeout(function() {
				location.reload();
			}, 1500);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

$(document)
		.ready(
				function() {
					$("#deleteUserModal").on(
							'show.bs.modal',
							function(e) {
								$(this).find('#btnDeleteUser').attr('href',
										$(e.relatedTarget).data('href'));
							});

					$("#EditUserModal").on(
							'show.bs.modal',
							function(e) {
								$(this).find('#userId').val(
										$(e.relatedTarget).data('user-no'));
								$(this).find('#newFirstname').val(
										$(e.relatedTarget).data(
												'user-firstname'));
								$(this).find('#newLastname').val(
										$(e.relatedTarget)
												.data('user-lastname'));
								$(this).find('#newPhonenumber').val(
										$(e.relatedTarget).data('user-phone'));
							});

					$('#phonenumber , #newPhonenumber')

							.keydown(
									function(e) {
										var key = e.charCode || e.keyCode || 0;
										$phone = $(this);

										// Auto-format- do not expose the mask
										// as the user begins to type
										if (key !== 8 && key !== 9) {
											if ($phone.val().length === 4) {
												$phone.val($phone.val() + ')');
											}
											if ($phone.val().length === 5) {
												$phone.val($phone.val() + ' ');
											}
											if ($phone.val().length === 9) {
												$phone.val($phone.val() + '-');
											}
										}

										// Allow numeric (and tab, backspace,
										// delete) keys only
										return (key == 8 || key == 9
												|| key == 46
												|| (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
									})

							.bind('focus click', function() {
								$phone = $(this);

								if ($phone.val().length === 0) {
									$phone.val('(');
								} else {
									var val = $phone.val();
									$phone.val('').val(val); // Ensure cursor
									// remains at
									// the end
								}
							})

							.blur(function() {
								$phone = $(this);

								if ($phone.val() === '(') {
									$phone.val('');
								}

							});

					$("input[id=firstname]")
							.keyup(
									function() {
										var validator = $(this).val();
										var desen = "";
										if (validator == desen) {
											$(".durum")
													.html(
															"<font color='red'>This field a required</font>");
											$('#btn').attr("disabled", true);

										} else {
											$(".durum").html("");
											$('#btn').attr("disabled", false);
										}
									});

					$("input[id=lastname]")
							.keyup(
									function() {
										var validator = $(this).val();
										var desen = "";
										if (validator == desen) {
											$(".durum2")
													.html(
															"<font color='red'>This field a required</font>");
											$('#btn').attr("disabled", true);

										} else {
											$(".durum2").html("");
											$('#btn').attr("disabled", false);
										}
									});

					$("input[id=phonenumber]")
							.keyup(
									function() {
										var validator = $(this).val();
										if (validator == "(") {
											$(".durum3")
													.html(
															"<font color='red'>This field a required</font>");
											$('#btn').attr("disabled", true);

										} else {
											$(".durum3").html("");
											$('#btn').attr("disabled", false);
										}
									});

					$("input[id=newFirstname]")
							.keyup(
									function() {
										var validator = $(this).val();
										var desen = "";
										if (validator == desen) {
											$(".result")
													.html(
															"<font color='red'>This field a required</font>");
											$('#resultbtn').attr("disabled",
													true);

										} else {
											$(".result").html("");
											$('#resultbtn').attr("disabled",
													false);
										}
									});

					$("input[id=newLastname]")
							.keyup(
									function() {
										var validator = $(this).val();
										var desen = "";
										if (validator == desen) {
											$(".result2")
													.html(
															"<font color='red'>This field a required</font>");
											$('#resultbtn').attr("disabled",
													true);

										} else {
											$(".result2").html("");
											$('#resultbtn').attr("disabled",
													false);
										}
									});

					$("input[id=newPhonenumber]")
							.keyup(
									function() {
										var validator = $(this).val();
										var desen = "(";
										if (validator == desen) {
											$(".result3")
													.html(
															"<font color='red'>This field a required</font>");
											$('#resultbtn').attr("disabled",
													true);

										} else {
											$(".result3").html("");
											$('#resultbtn').attr("disabled",
													false);
										}
									});

					var validator = $("#add-conference").validate({
						ignore : ".ignore",
						rules : {
							city : {
								required : function() {
									if ($("#city-name").val()) {
										return false;
									} else {
										return true;
									}
								}
							},
							country : {
								required : function() {
									if ($("#country-name").val()) {
										return false;
									} else {
										return true;
									}
								}
							},
							"hiddenRecaptcha" : {
								required : function() {
									if (grecaptcha.getResponse() == '') {
										return true;
									} else {
										return false;
									}
								}
							}
						}
					});

				});
