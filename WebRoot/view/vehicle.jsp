<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String selectedFunction = (String)request.getAttribute("vehicleFunction");

String btnPressed = (String)request.getParameter("btnPressed");
%>

<!DOCTYPE HTML PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>VMS-Vehicle</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="online banking">
	<meta http-equiv="description" content="New customer creation ">
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/scripts/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/scripts/interface.js"></script>
	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/lang/calendar-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/calendar-setup.js"></script>
	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/view/calendar/skins/assyst/theme.css" title="assyst" />
	<link rel="alternate stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/view/calendar/skins/calendar-brown.css" title="summer" />
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/TtcsDwrUtilities.js'></script>
	
	
	
	
	<script type="text/javascript" for="window"  language="JavaScript">
		
	window.onload = fnOnload;
	
	function fnOnload()
	{	
		 
		  
		  fnDisableEnable();
		  
		  fnDisplayDet();
	}
	
	function fnDisplayDet()
	{
	
		
		var varfunction = document.getElementById("function").value;
		
		var varBtnPressed = '<%=btnPressed%>';
		
		
		if(varBtnPressed =='C')
		{
			document.getElementById("VehicleCrit").style.display = "none";
		}else
		{
			document.getElementById("VehicleCrit").style.display = "block";
		}
		

		if(varfunction == 'N')
		{
			document.getElementById("VehicleDet").style.display = "none";
			document.getElementById("btnDetDiv").style.display = "none";
			document.getElementById("VehicleCritLable").style.display = "none";
			
		}else
		{
			document.getElementById("VehicleDet").style.display = "block";
			document.getElementById("btnDetDiv").style.display = "block";
			document.getElementById("VehicleCritLable").style.display = "block";
		}
	}
	
	function fnDisableEnable()
	{
		var varfunction = document.getElementById("function").value;
		var objId = document.getElementById("vehicleIdCrit").value;
		
		if(varfunction == 'A' || varfunction == 'N')
		{
			document.getElementById("vehicleIdCrit").disabled= true;
		}else
		{
			document.getElementById("vehicleIdCrit").disabled= false;
		}
	}	  
	</script>
	<script type="text/javascript">
		function  fnCrit()
		{
			//document.getElementById("vehicleForm1").submit(); 
			
			var varfunction = document.getElementById("function").value;
			var objId = document.getElementById("vehicleIdCrit").value;
			
			if(varfunction ==null || varfunction =='' || varfunction == 'N')
			{
				alert('Please Select Function');
			
			}else if(varfunction != 'A'  && (objId ==null || objId ==''))
			{
				alert('Please Enter Vehicle Id');
			
			}else{
			
				document.vehicleForm.action = "/vms/vehicle.spr?btnPressed=C" ;
				document.getElementById("btnPressed").value = 'C';
				document.vehicleForm.submit();	
				}
		}
		
		function fnSubmit()
		{
			//alert('hi');
			//document.getElementById("vehicleForm1").submit(); 
			
			document.vehicleForm.action = "/vms/vehicle.spr?btnPressed=D" ;
			document.getElementById("btnPressed").value = 'D';
			document.getElementById("vehicleForm").submit(); 
		}
		
		
	</script>
	
	<jsp:include page="vmsScripts.jsp"/>

  </head>
  <jsp:include page="/view/menu.inc.jsp" />
  <body onload="fnOnload();" >
	
	<input type="hidden" id="btnPressed" name="btnPressed">
	<form:form commandName="vehicleCommand" id = "vehicleForm" name = "vehicleForm" action="vehicle.spr" method="POST" >
		<div id="NewCustomerDiv"  >
   			<table class="labelandfield"  class="list" style="margin-left: 10%;margin-right: 10%;background-color: white;">
   				<tr>
   					<td class="subhd" ><div id="mainHeaderid" style="border-bottom: 2px solid #ff9900;widows: 95%;margin-left: 2%">Vehicle</div></td>
   				</tr>
   				
   				
				
   				<tr>
					<td>
						<div class="section" id="VehicleCrit">
							
							<table id="crit">
								<tr>
									
									<td class="lbl"  ><label for="function">Function:</label></td>
									<td> 
										  <spring:bind path="vehicleCommand.function">
										 
										  <select name="<c:out value="${status.expression}"/>"
											  id="<c:out value="${status.expression}"/>"    value="<c:out value="${status.value}"/>" onblur="fnDisableEnable();">
											<option value="N" <%if ("N".equals(selectedFunction)){ %>selected="selected" <%}%>></option>
											<option value="A" <%if ("A".equals(selectedFunction)){ %>selected="selected" <%}%>>ADD</option>
											<option value="U" <%if ("U".equals(selectedFunction)){ %>selected="selected" <%}%>>UPDATE</option>
											<option value="H" <%if ("H".equals(selectedFunction)){ %>selected="selected" <%}%>>HISTORY</option>
											<option value="D" <%if ("D".equals(selectedFunction)){ %>selected="selected" <%}%>>DELETE</option>
											</select>
										</spring:bind>
									     
									</td> 
	
									<td class="lbl"  ><label for="vehicleId">Vehicle Id:</label></td> 
									<td> 
										 <spring:bind path="vehicleCommand.vehicleIdCrit">
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     
									     <spring:bind path="vehicleCommand.objId">
										 <input type="hidden" name="<c:out value="${status.expression}"/>"
										    	id="<c:out value="${status.expression}"/>"
										    	value="<c:out value="${status.value}"/>"  />
										</spring:bind>
									</td>
									
									<td align="center">
										<input type="button" class="btn"  id="btnCrit"  onclick="fnCrit();" value=' Go ' /> &nbsp;&nbsp;
									</td>							
								</tr>
								
							</table>
						</div>
					</td>
				</tr>
   				
   				<tr>
					<td>
						<div class="section" id="VehicleCritLable">
							
							<table id="crit" style="margin-left: 50px;" width="100%">
								<tr>
									
									<td class="subhd"  ><label for="function">Function:</label>
										  <%if ("A".equals(selectedFunction)){ %>Add 
										  <%}else if ("U".equals(selectedFunction)){ %>Update 
										  <%}else if ("D".equals(selectedFunction)){ %>Delete <%}%>
									     
									</td> 
	
									<td class="subhd"  ><label for="vehicleId">Vehicle Id:</label>
										  <c:out value="${vehicleCommand.vehicleId}"/>
									     
									</td>									
																
								</tr>
								
							</table>
						</div>
					</td>
				</tr>
   				
   				<tr>
   					<td width="100%">
   						
   						<div class="section" id="VehicleDet" style="height: 300px;">
   							
   							<table width="100%">
   								
				   				<tr>
									<td class="lbl"  ><label for="vehicleId">Vehicle Id:</label></td> 
									<td> 
										 
										 <spring:bind path="vehicleCommand.vehicleId">
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="vehicleName">Vehicle Name:</label></td>
									<td> <spring:bind path="vehicleCommand.vehicleName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="registrationNumber">Registration Number:</label></td>	
									<td> <spring:bind path="vehicleCommand.registrationNumber"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="chassisNumber">Chassis Number:</label></td>
									<td> <spring:bind path="vehicleCommand.chassisNumber"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td>
								</tr>
								<tr>
									<td class="lbl"  ><label for="engineNumber">Engine Number:</label></td>
									<td> <spring:bind path="vehicleCommand.engineNumber"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" />
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="makerName">Maker Name:</label></td>
									<td> <spring:bind path="vehicleCommand.makerName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" />
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="ownerName"> Owner Name:</label></td>
									<td> <spring:bind path="vehicleCommand.ownerName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" />
									     </spring:bind>
									</td> 
									
									<td class="lbl"  ><label for="fuelType"> Fuel Type:</label></td>
									<td> <spring:bind path="vehicleCommand.fuelType"> 
										  <select name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>" >
												<option value="Petrol" selected="selected">Petrol</option>
												<option value="Diesel" >Diesel</option>
												<option value="LPG" >LPG</option>
											</select>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="vehicleType">Vehicle Type:</label></td>
									<td> <spring:bind path="vehicleCommand.vehicleType"> 
										  <select name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>" />
										 	<option value="Light Weight" selected="selected">Light Motor Vehicle</option>
											<option value="Heavy Weight">Heavy Motor Vehicle</option>
										 </select>
									     </spring:bind>
									</td> 
								
									<td class="lbl"  ><label for="vehicleCost">Vehicle Cost:</label></td>
									<td> <spring:bind path="vehicleCommand.vehicleCost"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="costPerKM">Cost Per KM:</label></td>
									<td> <spring:bind path="vehicleCommand.costPerKM"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"  
											onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;"/>
											
									     </spring:bind>
									</td>
								
									<td class="lbl"  ><label for="insurerName">Insurer Name:</label></td>
									<td> <spring:bind path="vehicleCommand.insurerName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" />											
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="companyName">Company Name:</label></td>
									<td> <spring:bind path="vehicleCommand.companyName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								
									<td class="lbl"  ><label for="dateOfInsurance">Date Of Insurance:</label></td>
									<td> <spring:bind path="vehicleCommand.dateOfInsurance"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
											<img src="../vms/view/calendar/calendar.png" id="dateOfInsurance_image" name="dateOfInsurance_image"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="dateOfExpiry">Date Of Expiry:</label></td>
									<td> <spring:bind path="vehicleCommand.dateOfExpiry"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
											<img src="../vms/view/calendar/calendar.png" id="dateOfExpiry_image" name="dateOfExpiry_image"/>
									     </spring:bind>
									</td> 
								
									<td class="lbl"  ><label for="insuranceCost">Insurance Cost:</label></td>
									<td> <spring:bind path="vehicleCommand.insuranceCost"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"
											onkeypress="if ( isNaN( String.fromCharCode(event.keyCode) )) return false;"/>
									     </spring:bind>
									</td> 
								</tr>
								
   							</table>
   							
   						</div>
   						
   					</td>
   				</tr>
   				
   				
   				<tr>
					<td>
						<div id="btnDetDiv">
   							
   							<table width="100%">
   								<tr>
   								<td align="center">
									<input type="button" class="btn"  id="vehicleSubmit"  onclick="fnSubmit();" value='Submit' /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
			   						<input type="button" id="cancelBtn" name="cacelBtn" value="Cancel" onclick="window.location.href='<%=request.getContextPath()%>/vehicle.spr'">
			   					</td>
								</tr>
							</table>
						</div>
					
					</td>
				</tr>
				
				
   			</table>
   		</div>
	</form:form>
		
  </body>
</html>
