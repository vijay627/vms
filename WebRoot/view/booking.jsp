<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String selectedFunction = (String)request.getAttribute("bookingFunction");

String btnPressed = (String)request.getParameter("btnPressed");

%>

<!DOCTYPE HTML PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>VMS-Booking</title>
    
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
	

	
	
	<script type="text/javascript" for="window"  language="JavaScript">
		
	function fnOnload()
	{	
		 Calendar.setup(
		    {
		      inputField	: "bookingDate",         // ID of the input field for calendar
		      button     	: "bookingDate_image"       // ID of the button/image which is used to open the calendar by clicking it
		    }
		  );
		 Calendar.setup(
		    {
		      inputField	: "startDate",         // ID of the input field for calendar
		      button     	: "startDate_image"       // ID of the button/image which is used to open the calendar by clicking it
		    }
		  );
		  
		Calendar.setup(
		    {
		      inputField	: "endDate",         // ID of the input field for calendar
		      button     	: "endDate_image"       // ID of the button/image which is used to open the calendar by clicking it
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
			document.getElementById("BookingCrit").style.display = "none";
		}else
		{
			document.getElementById("BookingCrit").style.display = "block";
		}
		
		
		if(varfunction == 'N')
		{
			
			document.getElementById("BookingDet").style.display = "none";
			document.getElementById("btnDetDiv").style.display = "none";
			document.getElementById("BookingCritLable").style.display = "none";
		}else
		{
			
			document.getElementById("BookingDet").style.display = "block";
			document.getElementById("btnDetDiv").style.display = "block";
			document.getElementById("BookingCritLable").style.display = "block";
		}
	}
	
	function fnDisableEnable()
	{
		var varfunction = document.getElementById("function").value;
		var objId = document.getElementById("bookingId").value;
		
		if(varfunction == 'A' || varfunction == 'N')
		{
			document.getElementById("bookingId").disabled= true;
		}else
		{
			document.getElementById("bookingId").disabled= false;
		}
	}		  
	</script>
	<script type="text/javascript">
		function  fnCrit()
		{
			var varfunction = document.getElementById("function").value;
			var objId = document.getElementById("bookingId").value;
			
			if(varfunction ==null || varfunction =='' || varfunction == 'N')
			{
				alert('Please Select Function');
			
			}else if(varfunction != 'A'  && (objId ==null || objId ==''))
			{
				alert('Please Enter Booking Id');
			
			}else{
			
			
			document.bookingForm.action = "/vms/booking.spr?btnPressed=C" ;
			document.getElementById("bookingForm").submit(); 
			}
		}
		
		function fnSubmit()
		{
			document.bookingForm.action = "/vms/booking.spr?btnPressed=D" ;
			document.getElementById("bookingForm").submit(); 
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
	<input type="hidden" id="currentPage" name="currentPage" value="B"/>
	<form:form commandName="bookingCommand" id = "bookingForm" name = "bookingForm" action="booking.spr" method="POST" >
		
		<div id="NewCustomerDiv"  >
   			<table class="labelandfield"  class="list" style="margin-left: 10%;margin-right: 10%;background-color: white;">
   				<tr>
   					<td class="subhd" ><div id="mainHeaderid" style="border-bottom: 2px solid #ff9900;widows: 95%;margin-left: 2%">Booking</div></td>
   				</tr>
   				
   				<tr>
					<td>
						<div class="section" id="BookingCrit">
							
							<table id="crit">
								<tr>
									<td class="lbl"  ><label for="function">Function:</label></td>
									<td> <spring:bind path="bookingCommand.function"> 
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
	
									<td class="lbl"  ><label for="bookingId">Booking Id:</label></td> 
									<td> 
										 <spring:bind path="bookingCommand.bookingId">
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
						<div class="section" id="BookingCritLable">
							
							<table id="crit" style="margin-left: 50px;" width="100%">
								<tr>
									
									<td class="subhd"  ><label for="function">Function:</label>
										  <%if ("A".equals(selectedFunction)){ %>Add 
										  <%}else if ("U".equals(selectedFunction)){ %>Update 
										  <%}else if ("D".equals(selectedFunction)){ %>Delete <%}%>
									     
									</td> 
	
									<td class="subhd"  ><label for="bookingId">Booking Id:</label>
										  <c:out value="${bookingCommand.bookingId}"/>
									     
									</td>									
																
								</tr>
								
							</table>
						</div>
					</td>
				</tr>
   				
   				<tr>
   					<td width="100%">
   						<div class="section" id="BookingDet">
   							
   							<table width="100%">
   								
				   				<tr>
									<td class="lbl"  ><label for="customerName">Customer Name:</label></td> 
									<td> 
										 <spring:bind path="bookingCommand.bookingId">
											 <input type="hidden" name="<c:out value="${status.expression}"/>"
											    	id="<c:out value="${status.expression}"/>"
											    	value="<c:out value="${status.value}"/>"  size="40"  maxlength="40"/>
										</spring:bind>
										 <spring:bind path="bookingCommand.customerName">
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="customerContactNumber">Customer Contact Number:</label></td>
									<td> <spring:bind path="bookingCommand.customerContactNumber"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="customerAddress">Customer Address:</label></td>	
									<td> <spring:bind path="bookingCommand.customerAddress"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="bookingDate">Booking Date:</label></td>
									<td> <spring:bind path="bookingCommand.bookingDate"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
											<img src="../vms/view/calendar/calendar.png" id="bookingDate_image" name="bookingDate_image"/>
									     </spring:bind>
									</td>
								</tr>
								<tr>
									<td class="lbl"  ><label for="startDate">Start Date:</label></td>
									<td> <spring:bind path="bookingCommand.startDate"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" />
											<img src="../vms/view/calendar/calendar.png" id="startDate_image" name="startDate_image"/>
									     </spring:bind>
									</td> 
									<td class="lbl"  ><label for="startTime">Start Time(Hours):</label></td>
									<td> <spring:bind path="bookingCommand.startTime"> 
										  <select name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>" >
												<%for(int i=0;i<24;i++){ %>
												<option value="<%=i %>" ><%=i %></option>
												<%} %>
											</select>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="endDate"> End Date:</label></td>
									<td> <spring:bind path="bookingCommand.endDate"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40" />
											<img src="../vms/view/calendar/calendar.png" id="endDate_image" name="endDate_image"/>
									     </spring:bind>
									</td> 
									
									<td class="lbl"  ><label for="endTime"> End Time(Hours):</label></td>
									<td> <spring:bind path="bookingCommand.endTime"> 
										  <select name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>" >
												<%for(int i=0;i<24;i++){ %>
												<option value="<%=i %>" ><%=i %></option>
												<%} %>
											</select>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="fromLocation">From Location:</label></td>
									<td> <spring:bind path="bookingCommand.fromLocation"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     
									</td> 
								
									<td class="lbl"  ><label for="toLocation">To Location:</label></td>
									<td> <spring:bind path="bookingCommand.toLocation"> 
										  <input type="text"  name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="vehicleId">Vehicle Id:</label></td>
									<td> <spring:bind path="bookingCommand.vehicleId"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									     <img src="../vms/view/images/search.gif" id="searchV_image" name="searchV_image" onclick="fnSearchVehicles()"/>
									</td> 
								
									<td class="lbl"  ><label for="vehicleName">Vehicle Name:</label></td>
									<td> <spring:bind path="bookingCommand.vehicleName"> 
										  <input type="text"  name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="driverId">Driver Id:</label></td>
									<td> <spring:bind path="bookingCommand.driverId"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"  />
											<img src="../vms/view/images/search.gif" id="searchD_image" name="searchD_image" onclick="fnSearchDrivers()"/>
											
									     </spring:bind>
									</td>
								
									<td class="lbl"  ><label for="driverName">Driver Name:</label></td>
									<td> <spring:bind path="bookingCommand.driverName"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"  onblur="prepareEMI();"/>
											
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="costPerKm">Cost Per Km:</label></td>
									<td> <spring:bind path="bookingCommand.costPerKm"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								
									<td class="lbl"  ><label for="totalKms">Total Kms:</label></td>
									<td> <spring:bind path="bookingCommand.totalKms"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								<tr>
									<td class="lbl"  ><label for="totalAmount">Total Amount:</label></td>
									<td> <spring:bind path="bookingCommand.totalAmount"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								
									<td class="lbl"  ><label for="advanceAmount">Advance Amount:</label></td>
									<td> <spring:bind path="bookingCommand.advanceAmount"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								</tr>
								
								<tr>
									<td class="lbl"  ><label for="remianingAmount">Remianing Amount:</label></td>
									<td> <spring:bind path="bookingCommand.remianingAmount"> 
										  <input type="text" name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>"  size="30"  maxlength="40"/>
									     </spring:bind>
									</td> 
								
									<td class="lbl"  ><label for="status">Status:</label></td>
									<td> <spring:bind path="bookingCommand.status"> 
										   <select name="<c:out value="${status.expression}"/>"
											id="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}"/>" >
												<option value="P" >Pending</option>
												<option value="C" >Complete</option>
												<option value="X" >Cancel</option>
											</select>
									     </spring:bind>
									</td> 
								</tr>
								
								<tr>
									<td class="lbl"  ><label for="description">Description:</label></td>
									<td> <spring:bind path="bookingCommand.description"> 
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
									<input type="button" class="btn"  id="bookingSubmit"  onclick="fnSubmit();" value='Submit' /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
			   						<input type="button" id="cancelBtn" name="cacelBtn" value="Cancel" onclick="window.location.href='<%=request.getContextPath()%>/booking.spr'">
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
