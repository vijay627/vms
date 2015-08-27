<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String selectedFunction = (String)request.getAttribute("driverFunction");

String btnPressed = (String)request.getParameter("btnPressed");
%>

<!DOCTYPE HTML PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>VMS-Driver</title>
    
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
		
	function fnOnload()
	{	
		Calendar.setup(
		    {
		      inputField	: "driverAge",         // ID of the input field for calendar
		      button     	: "driverAge_image"       // ID of the button/image which is used to open the calendar by clicking it
		    }
		  );
		  
		 Calendar.setup(
		    {
		      inputField	: "expiryDate",         // ID of the input field for calendar
		      button     	: "expiryDate_image"       // ID of the button/image which is used to open the calendar by clicking it
		    }
		  );
		  
		  
		  
		  fnDisableEnable();
		  
		  fnDisplayDet();
	}
	
	function fnDisplayDet()
	{
	
		var varfunction = document.getElementById("function").value;
		
		
		
		var varBtnPressed = '<%=btnPressed%>';
		
	
		
		if(varBtnPressed =='C')
		{
			document.getElementById("DriverCrit").style.display = "none";
		}else
		{
			document.getElementById("DriverCrit").style.display = "block";
		}
		
		
		
		if(varfunction == 'N')
		{
			
			document.getElementById("DriverDet").style.display = "none";
			document.getElementById("btnDetDiv").style.display = "none";
			document.getElementById("DriverCritLable").style.display = "none";
		}else
		{
			
			document.getElementById("DriverDet").style.display = "block";
			document.getElementById("btnDetDiv").style.display = "block";
			document.getElementById("DriverCritLable").style.display = "block";
		}
	}
	
	function fnDisableEnable()
	{
		var varfunction = document.getElementById("function").value;
		var objId = document.getElementById("driverIdCrit").value;
		
		if(varfunction == 'A' || varfunction == 'N')
		{
			document.getElementById("driverIdCrit").disabled= true;
		}else
		{
			document.getElementById("driverIdCrit").disabled= false;
		}
	}		  
	</script>
	<script type="text/javascript">
		function  fnCrit()
		{
			var varfunction = document.getElementById("function").value;
			var objId = document.getElementById("driverIdCrit").value;
			
			//alert(varfunction);
			if(varfunction ==null || (varfunction =='' || varfunction == 'N'))
			{
				alert('Please Select Function');
			
			}else if(varfunction != 'A'  && (objId ==null || objId ==''))
			{
				alert('Please Enter Driver Id');
			
			}else{
			
			document.driverForm.action = "/vms/driver.spr?btnPressed=C" ;
			document.getElementById("driverForm").submit(); 
			}
		}
		
		function fnSubmit()
		{
			document.driverForm.action = "/vms/driver.spr?btnPressed=D" ;
			document.getElementById("driverForm").submit(); 
		}
		
		
	</script>
	
	<jsp:include page="vmsScripts.jsp"/>

  </head>
  <jsp:include page="/view/menu.inc.jsp" />
  <body onload="fnOnload();">
	
	<form:form commandName="driverCommand" id = "driverForm" name="driverForm" action="driver.spr" method="POST" >
		<div id="NewCustomerDiv"  >
   			<table class="labelandfield"  class="list" style="margin-left: 10%;margin-right: 10%;background-color: white;">
   				<tr>
   					<td class="subhd" ><div id="mainHeaderid" style="border-bottom: 2px solid #ff9900;widows: 95%;margin-left: 2%">Driver</div></td>
   				</tr>
   				
   				<tr>
					<td>
						<div class="section" id="DriverCrit">
							
							<table id="crit">
								<tr>
									<td class="lbl"  ><label for="function">Function:</label></td>
									<td> 
										  <spring:bind path="driverCommand.function">
										 
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
	
									<td class="lbl"  ><label for="driverIdCrit">Driver Id:</label></td> 
									<td> 
										 <spring:bind path="driverCommand.driverIdCrit">
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     
									     <spring:bind path="driverCommand.objId">
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
						<div class="section" id="DriverCritLable">
							
							<table id="crit" style="margin-left: 50px;" width="100%">
								<tr>
									
									<td class="subhd"  ><label for="function">Function:</label>
										  <%if ("A".equals(selectedFunction)){ %>Add 
										  <%}else if ("U".equals(selectedFunction)){ %>Update 
										  <%}else if ("D".equals(selectedFunction)){ %>Delete <%}%>
									     
									</td> 
	
									<td class="subhd"  ><label for="vehicleId">Driver Id:</label>
										  <c:out value="${driverCommand.driverId}"/>
									     
									</td>									
																
								</tr>
								
							</table>
						</div>
					</td>
				</tr>
   				
   				<tr>
   					<td width="100%">
   						<div class="section" id="DriverDet">
   							
   							<table width="100%">
   								
				   				<tr>
									<td class="lbl"  ><label for="driverId">Driver Id:</label></td> 
									<td> 
								
										 <spring:bind path="driverCommand.driverId">
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="firstName">First Name:</label></td>
									<td> <spring:bind path="driverCommand.firstName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="lastName">Last Name:</label></td>	
									<td> <spring:bind path="driverCommand.lastName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="driverAge">Date Of Birth:</label></td>
									<td> <spring:bind path="driverCommand.driverAge"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     <img src="../vms/view/calendar/calendar.png" id="driverAge_image" name="driverAge_image"/>
									</td>
								</tr>
								<tr>
									<td class="lbl"  ><label for="address">Address:</label></td>
									<td> <spring:bind path="driverCommand.address"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" onblur="prepareEMI();"/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="city">City:</label></td>
									<td> <spring:bind path="driverCommand.city"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" onblur="prepareEMI();"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="state"> State:</label></td>
									<td> <spring:bind path="driverCommand.state"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" onblur="prepareEMI();"/>
									     </spring:bind>
									</td> 
									
									<td class="lbl"  ><label for="pin"> PIN:</label></td>
									<td> <spring:bind path="driverCommand.pin"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="mobileNumber">Mobile Number:</label></td>
									<td> <spring:bind path="driverCommand.mobileNumber"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								
									<td class="lbl"  ><label for="licenseNumber">License Number:</label></td>
									<td> <spring:bind path="driverCommand.licenseNumber"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="expiryDate">Expiry Date:</label></td>
									<td> <spring:bind path="driverCommand.expiryDate"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"  />
											<img src="../vms/view/calendar/calendar.png" id="expiryDate_image" name="expiryDate_image"/>
									     </spring:bind>
									</td>
								
									<td class="lbl"  ><label for="experience">Experience:</label></td>
									<td> <spring:bind path="driverCommand.experience"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"  />											
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
									<input type="button" class="btn"  id="driverSubmit"  onclick="fnSubmit();" value='Submit' /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
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
