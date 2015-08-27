<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String selectedFunction = (String)request.getAttribute("maintainFunction");

String btnPressed = (String)request.getParameter("btnPressed");
%>

<!DOCTYPE HTML PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>VMS-Maintenance</title>
    
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
		      inputField	: "repairDate",         // ID of the input field for calendar
		      button     	: "repairDate_image"       // ID of the button/image which is used to open the calendar by clicking it
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
			document.getElementById("MaintainCrit").style.display = "none";
		}else
		{
			document.getElementById("MaintainCrit").style.display = "block";
		}
		
		
		if(varfunction == 'N')
		{
			
			document.getElementById("MaintainDet").style.display = "none";
			document.getElementById("btnDetDiv").style.display = "none";
			document.getElementById("MaintainCritLable").style.display = "none";
		}else
		{
			
			document.getElementById("MaintainDet").style.display = "block";
			document.getElementById("btnDetDiv").style.display = "block";
			document.getElementById("MaintainCritLable").style.display = "block";
		}
		
	}
	
	function fnDisableEnable()
	{
		var varfunction = document.getElementById("function").value;
		var objId = document.getElementById("objId").value;
		
		if(varfunction == 'A' || varfunction == 'N')
		{
			document.getElementById("objId").disabled= true;
		}else
		{
			document.getElementById("objId").disabled= false;
		}
	}			  
	</script>
	<script type="text/javascript">
		function  fnCrit()
		{
			var varfunction = document.getElementById("function").value;
			var objId = document.getElementById("objId").value;
			
			if(varfunction ==null || varfunction =='' || varfunction == 'N')
			{
				alert('Please Select Function');
			
			}else if(varfunction != 'A'  && (objId ==null || objId ==''))
			{
				alert('Please Enter Repair Id');
			
			}else{
			
			document.maintenanceForm.action = "/vms/maintenance.spr?btnPressed=C" ;
			document.getElementById("maintenanceForm").submit(); 
			}
		}
		
		function fnSubmit()
		{
			document.maintenanceForm.action = "/vms/maintenance.spr?btnPressed=D" ;
			document.getElementById("maintenanceForm").submit(); 
		}
		function fnSearchPopup()
		{
			 var win = window.open("/vms/view/searchpopup.jsp",'Search','width=800,height=550,left=50,top=50');
		}
		
		
	</script>
	
	<jsp:include page="vmsScripts.jsp"/>

  </head>
  <jsp:include page="/view/menu.inc.jsp" />
  <body onload="fnOnload();">
	<input type="hidden" id="currentPage" name="currentPage" value="M"/>
	<form:form commandName="maintenanceCommand" id = "maintenanceForm" name = "maintenanceForm" action="maintenance.spr" method="POST" >
		<div id="NewCustomerDiv"  >
   			<table class="labelandfield"  class="list" style="margin-left: 10%;margin-right: 10%;background-color: white;">
   				<tr>
   					<td class="subhd" ><div id="mainHeaderid" style="border-bottom: 2px solid #ff9900;widows: 95%;margin-left: 2%">Maintenance</div></td>
   				</tr>
   				
   				<tr>
					<td>
						<div class="section" id="MaintainCrit">
							
							<table id="crit">
								<tr>
									<td class="lbl"  ><label for="function">function:</label></td>
									<td> <spring:bind path="maintenanceCommand.function"> 
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
	
									<td class="lbl"  ><label for="vehicleId">Repair Id:</label></td> 
									<td> 
										 <spring:bind path="maintenanceCommand.objId">
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     <img src="../vms/view/images/search.gif" id="search_image" name="search_image" onclick="fnSearchPopup()"/>
									     
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
						<div class="section" id="MaintainCritLable">
							
							<table id="crit" style="margin-left: 50px;" width="100%">
								<tr>
									
									<td class="subhd"  ><label for="function">Function:</label>
										  <%if ("A".equals(selectedFunction)){ %>Add 
										  <%}else if ("U".equals(selectedFunction)){ %>Update 
										  <%}else if ("D".equals(selectedFunction)){ %>Delete <%}%>
									     
									</td> 
	
									<td class="subhd"  ><label for="objId">Fuel Id:</label>
										  <c:out value="${maintenanceCommand.objId}"/>
									     
									</td>									
																
								</tr>
								
							</table>
						</div>
					</td>
				</tr>
   				
   				<tr>
   					<td width="100%">
   						<div class="section" id="MaintainDet">
   							
   							<table width="100%">
   								
				   				<tr>
									<td class="lbl"  ><label for="vehicleId">vehicle Id:</label></td> 
									<td> 
										 <spring:bind path="maintenanceCommand.objId">
											 <input type="hidden" name="<c:out value="${status.expression}"/>"
											    	id="<c:out value="${status.expression}"/>"
											    	value="<c:out value="${status.value}"/>"  size="40"  maxlength="40"/>
										</spring:bind>
										 <spring:bind path="maintenanceCommand.vehicleId">
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     <img src="../vms/view/images/search.gif" id="searchV_image" name="searchV_image" onclick="fnSearchVehicles()"/>
									</td> 
									<td class="lbl"  ><label for="vehicleName">Vehicle Name:</label></td>
									<td> <spring:bind path="maintenanceCommand.vehicleName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="driverId">Driver Id:</label></td>	
									<td> <spring:bind path="maintenanceCommand.driverId"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     <img src="../vms/view/images/search.gif" id="searchD_image" name="searchD_image" onclick="fnSearchDrivers()"/>
									</td> 
									<td class="lbl"  ><label for="driverName">Driver Name:</label></td>
									<td> <spring:bind path="maintenanceCommand.driverName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td>
								</tr>
								<tr>
									<td class="lbl"  ><label for="companyName">Garage/Company Name:</label></td>
									<td> <spring:bind path="maintenanceCommand.companyName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" "/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="repairDate">Repair Date:</label></td>
									<td> <spring:bind path="maintenanceCommand.repairDate"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" "/>
											<img src="../vms/view/calendar/calendar.png" id="repairDate_image" name="repairDate_image"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="totalCost"> Total Cost:</label></td>
									<td> <spring:bind path="maintenanceCommand.totalCost"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" "/>
									     </spring:bind>
									</td> 
									
									<td class="lbl"  ><label for="description"> Description:</label></td>
									<td> <spring:bind path="maintenanceCommand.description"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								
   				
   				
   				<tr>
					<td>
						<div id="btnDetDiv">
   							
   							<table width="100%">
   								<tr>
   								<td align="center">
									<input type="button" class="btn"  id="vehicleSubmit"  onclick="fnSubmit();" value='Submit' /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
			   						<input type="button" id="cancelBtn" name="cacelBtn" value="Cancel" onclick="window.location.href='<%=request.getContextPath()%>/maintenance.spr'">
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
