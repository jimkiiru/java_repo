<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
                                    <div class="dispalyArea">
									<div id="title">
										<h2 style="margin-top: 2%">Basic Information</h2>
									</div>
									<form name="Myform" id="profileform" action="savePatient.do"
										method="POST" onsubmit="return validateformFields()"
										enctype="multipart/form-data">
										<table border="0" align="center" cellpadding="10"
											cellspacing="10">



											<tr>

												<td><label for="firstname" style="margin-left: 2%">First
														Name:</label><input type="text" class="form-control"
													name="firstname" id="firstname" value="${fname}" required
													minlength="3" autofocus /></td>
												<td><label style="margin-left: 2%">Middle Name:</label><input
													type="text" class="form-control" id="fname"
													name="middlename" value="${mname}" required /></td>
												<td><label style="margin-left: 2%">Surname:</label><input
													type="text" class="form-control" id="lastname"
													name="surname" value="${lname}" required /></td>

											</tr>
											<tr>
												<td><label style="margin-left: 2%">Maiden Name:</label><input
													type="text" class="form-control" id="lastname"
													name="maidenname" value="${lname}" required /></td>
													
													
												<td><label style="margin-left: 2%">Id Number:</label><input
													type="text" class="form-control" id="lastname"
													name="idnumber" value="${lname}" required /></td>

												<td><label style="margin-left: 2%">PNO:</label><input
													type="text" class="form-control" id="lastname"
													name="pno" value="${lname}" required /></td>
											</tr>

											<tr>
												<td><label style="margin-left: 2%">PhoneNOK:</label><input
													type="text" class="form-control" id="phone" name="phone_nok"
													value="${phone}" required /></td>
												<td><label style="margin-left: 2%">Marital Status:</label><input
													type="text" class="form-control" id="E_mail" name="marital_status"
													value="${email}" required /></td>
												<td><label style="margin-left: 2%">DOB:</label><input
													type="text" class="form-control" id="id_no"
													name="dob" value="${idno}" required
													 /></td>

											</tr>
											<tr>

												<td><label style="margin-left: 2%">Gender:</label><input
													type="text" class="form-control" id="district"
													name="gender" value="${district}"
													onblur="validatesname()" /></td>
												<td><label style="margin-left: 2%">Place of birth
														</label><input type="text" class="form-control"
													id="datepicker" name="birthplace" value="${dob}" required /></td>
												<td><label style="margin-left: 2%">Tribe
														:</label><input type="text" class="form-control"
													id="ethnic" name="tribe" value="${ethnic}"
													onblur="validatecontact()" /></td>
											</tr>
											<tr>
												<td><label style="margin-left: 2%">Religion:</label><input
													type="text" class="form-control" id="profession"
													name="religion" value="${proff}" required /></td>
												<td><label style="margin-left: 2%">EducationLevel:</label><input
													type="text" class="form-control" id="pin_no" name="educationlevel"
													value="${pinno}" /></td>
												<td><label style="margin-left: 2%">Occupation
														:</label><input type="text" class="form-control"
													id="license" name="occupation" value="${license}"
													 /></td>


											</tr>
											<tr>
												<td><label style="margin-left: 2%">Smoking status:</label><input
													type="text" class="form-control" id="secname"
													name="smoking_status" value="${nssfno}"
													/></td>
												<td><label style="margin-left: 2%">Drinking status
														:</label><input type="text" class="form-control"
													id="secname" name="drinking_status" value="${passno}"
													/></td>
												
										</table>
										
                                       
											<button class="btn text-muted text-center btn-danger31"
											type="reset">Clear</button>
										<button class="btn text-muted text-center btn-danger31"
											type="submit">~Save~</button>
										
									</form>

								</div>
</body>
</html>