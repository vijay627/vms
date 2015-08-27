<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String selectedFunction = (String)request.getAttribute("fuelFunction");

String btnPressed = (String)request.getParameter("btnPressed");
%>

<!DOCTYPE HTML PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>VMS-Fuel</title>
    
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
		      inputField	: "fuelDate",         // ID of the input field for calendar
		      button     	: "fuelDate_image"       // ID of the button/image which is used to open the calendar by clicking it
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
			document.getElementById("FuelCrit").style.display = "none";
		}else
		{
			document.getElementById("FuelCrit").style.display = "block";
		}
		
		
		if(varfunction == 'N')
		{
			
			document.getElementById("FuelDet").style.display = "none";
			document.getElementById("btnDetDiv").style.display = "none";
			document.getElementById("FuelCritLable").style.display = "none";
		}else
		{
			
			document.getElementById("FuelDet").style.display = "block";
			document.getElementById("btnDetDiv").style.display = "block";
			document.getElementById("FuelCritLable").style.display = "block";
		}
	}
	
	function fnDisableEnable()
	{
		var varfunction = document.getElementById("function").value;
		var objId = document.getElementById("fuelId").value;
		
		if(varfunction == 'A' || varfunction == 'N')
		{
			document.getElementById("fuelId").disabled= true;
		}else
		{
			document.getElementById("fuelId").disabled= false;
		}
	}		  
	</script>
	<script type="text/javascript">
		function  fnCrit()
		{
			
			var varfunction = document.getElementById("function").value;
			var objId = document.getElementById("fuelId").value;
			
			if(varfunction ==null || varfunction =='' || varfunction == 'N')
			{
				alert('Please Select Function');
			
			}else if(varfunction != 'A'  && (objId ==null || objId ==''))
			{
				alert('Please Enter Fuel Id');
			
			}else{
			
			document.fuelForm.action = "/vms/fuel.spr?btnPressed=C" ;
			document.getElementById("fuelForm").submit(); 
			}
		}
		
		function fnSubmit()
		{
			document.fuelForm.action = "/vms/fuel.spr?btnPressed=D" ;
			document.getElementById("fuelForm").submit(); 
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
	<input type="hidden" id="currentPage" name="currentPage" value="F"/>
	<form:form commandName="fuelCommand" id = "fuelForm"  name = "fuelForm" action="fuel.spr" method="POST" >
		<div id="NewCustomerDiv"  >
   			<table class="labelandfield"  class="list" style="margin-left: 10%;margin-right: 10%;background-color: white;">
   				<tr>
   					<td class="subhd" ><div id="mainHeaderid" style="border-bottom: 2px solid #ff9900;widows: 95%;margin-left: 2%">Fuel</div></td>
   				</tr>
   				
   				<tr>
					<td>
						<div class="section"  id="FuelCrit">
							
							<table id="crit">
								<tr>
									<td class="lbl"  ><label for="function">Function:</label></td>
									<td> 
										<spring:bind path="fuelCommand.function">
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
	
									<td class="lbl"  ><label for="fuelId">Fuel Id:</label></td> 
									<td> 
										 <spring:bind path="fuelCommand.fuelId">
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
						<div class="section" id="FuelCritLable">
							
							<table id="crit" style="margin-left: 50px;" width="100%">
								<tr>
									
									<td class="subhd"  ><label for="function">Function:</label>
										  <%if ("A".equals(selectedFunction)){ %>Add 
										  <%}else if ("U".equals(selectedFunction)){ %>Update 
										  <%}else if ("D".equals(selectedFunction)){ %>Delete <%}%>
									     
									</td> 
	
									<td class="subhd"  ><label for="vehicleId">Fuel Id:</label>
										  <c:out value="${fuelCommand.fuelId}"/>
									     
									</td>									
																
								</tr>
								
							</table>
						</div>
					</td>
				</tr>
   				
   				<tr>
   					<td width="100%">
   						<div class="section" id="FuelDet">
   							
   							<table width="100%">
   								
				   				<tr>
									<td class="lbl"  ><label for="vehicleId">Vehicle Id:</label></td> 
									<td> 
										 <spring:bind path="fuelCommand.fuelId">
											 <input type="hidden" name="<c:out value="${status.expression}"/>"
											    	id="<c:out value="${status.expression}"/>"
											    	value="<c:out value="${status.value}"/>"  size="40"  maxlength="40"/>
										</spring:bind>
										 <spring:bind path="fuelCommand.vehicleId">
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     <img src="../vms/view/images/search.gif" id="searchV_image" name="searchV_image" onclick="fnSearchVehicles()"/>
									</td> 
									<td class="lbl"  ><label for="vehicleName">Vehicle Name:</label></td>
									<td> <spring:bind path="fuelCommand.vehicleName"> 
										  <input type="text" disabled="disabled" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="driverId">Driver Id:</label></td>	
									<td> <spring:bind path="fuelCommand.driverId"> 
										  <input type="text"  name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     <img src="../vms/view/images/search.gif" id="searchD_image" name="searchD_image" onclick="fnSearchDrivers()"/>
									</td> 
									<td class="lbl"  ><label for="driverName">Driver Name:</label></td>
									<td> <spring:bind path="fuelCommand.driverName"> 
										  <input type="text" disabled="disabled" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td>
								</tr>
								<tr>
									<td class="lbl"  ><label for="fuelDate">Fuel Date:</label></td>
									<td> <spring:bind path="fuelCommand.fuelDate"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" onblur="prepareEMI();"/>
											<img src="../vms/view/calendar/calendar.png" id="fuelDate_image" name="fuelDate_image"/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="fuelType">Fuel Type:</label></td>
									<td> <spring:bind path="fuelCommand.fuelType"> 
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
									<td class="lbl"  ><label for="fuelQuantity"> Fuel Quantity:</label></td>
									<td> <spring:bind path="fuelCommand.fuelQuantity"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" "/>
									     </spring:bind>
									</td> 
									
									<td class="lbl"  ><label for="totalCost"> Total Cost:</label></td>
									<td> <spring:bind path="fuelCommand.totalCost"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
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
									<input type="button" class="btn"  id="fuelSubmit"  onclick="fnSubmit();" value='Submit' /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
			   						<input type="button" id="cancelBtn" name="cacelBtn" value="Cancel" onclick="window.location.href='<%=request.getContextPath()%>/fuel.spr'">
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
