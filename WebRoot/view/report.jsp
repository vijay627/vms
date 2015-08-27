<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE HTML PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>VMS-Report</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="online banking">
	<meta http-equiv="description" content="New customer creation ">
	
	<link rel=stylesheet type=text/css 
	href="<%=request.getContextPath()%>/view/css/ttcs.css">
	
	<style> 
	 .odd{background-color: #FFFF99;} 
	 .even{background-color: aqua;}
	 
	 .changecolor{background-color: #00FFFF;} 
	 .resetcolor{background-color: ;} 
	  
	</style>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/scripts/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/scripts/interface.js"></script>
	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/lang/calendar-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/calendar/calendar-setup.js"></script>
	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/view/calendar/skins/assyst/theme.css" title="assyst" />
	<link rel="alternate stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/view/calendar/skins/calendar-brown.css" title="summer" />
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/VmsDwrUtilities.js'></script>
	

	
	<script type="text/javascript" for="window"  language="JavaScript">
		
	var currentPage;
	var vehicleId;
	var fromDate;
	var toDate;
	
	function fnOnload()
	{	
		 
		  
		  Calendar.setup(
		    {
		      inputField	: "fromDateCrit",         // ID of the input field for calendar
		      button     	: "fromDateCrit_image"       // ID of the button/image which is used to open the calendar by clicking it
		    }
		  );
		  
		   Calendar.setup(
		    {
		      inputField	: "toDateCrit",         // ID of the input field for calendar
		      button     	: "toDateCrit_image"       // ID of the button/image which is used to open the calendar by clicking it
		    }
		  );
		  
		 
	 
	}
	
	
	</script>
	<script type="text/javascript">
		
		function fnCrit()
		{
			
		
			
			vehicleId =document.getElementById('vehicleIdCrit').value;
			fromDate =document.getElementById('fromDateCrit').value;
			toDate =document.getElementById('toDateCrit').value;
			
			
				document.getElementById("bookingTable").style.display = "block";
				
				VmsDwrUtilities.getBookingDetails(
				   				vehicleId,
				   				fromDate,
				   				toDate,
				   				{
				   					async:false,
				   					callback:getBookingCallBack
				   				}	
							);
			
				document.getElementById("fuelTable").style.display = "block";
				
				VmsDwrUtilities.getFuelDetails(
				   				vehicleId,
				   				fromDate,
				   				toDate,
				   				{
				   					async:false,
				   					callback:getFuelCallBack
				   				}	
							);
			
				document.getElementById("maintenanceTable").style.display = "block";
				VmsDwrUtilities.getMaintenanceDetails(
				   				vehicleId,
				   				fromDate,
				   				toDate,
				   				{
				   					async:false,
				   					callback:getMaintenanceCallBack
				   				}	
							);
			
			
		}
	
		function getBookingCallBack(dataFromServer)
		{
			var table = document.getElementById('bookingTable');               
			var rowCount = table.rows.length;             
			 
			for(var i = table.rows.length - 1; i > 0; i--)
			{
			    table.deleteRow(i);
			}              
			
			for(var index=0; index<dataFromServer.length; index++)
			{
				var row = table.insertRow(index+1);
				if((index+1) % 2 == 0){ 
			       row.className = "even"; 
			     }else{ 
			       row.className = "odd"; 
			     }       
				row.onmouseout = function()
				{
					var varRow = new Number(this.rowIndex);
					if((varRow) % 2 == 0){ 
				       this.className = "even"; 
				     }else{ 
				       this.className = "odd"; 
				     } 
				}
				row.onmouseover = function()
				{
					this.className = "changecolor"; 
				}
				
				
				var cell1 = row.insertCell(0);             
				cell1.innerHTML = dataFromServer[index].bookingId; 
				
				var cell2 = row.insertCell(1);             
				cell2.innerHTML = dataFromServer[index].vehicleName;
				
				var cell3 = row.insertCell(2);             
				cell3.innerHTML = dataFromServer[index].driverName;				
				
				var cell4 = row.insertCell(3);             
				cell4.innerHTML = dataFromServer[index].customerName; 
				
				var startDate = new Date(dataFromServer[index].startDate);
				
				var dateString = startDate.getDate()+'/'+new Number(startDate.getMonth()+1)+'/'+startDate.getFullYear();
				
				var cell5 = row.insertCell(4);             
				cell5.innerHTML = dateString;
				
				var endDate = new Date(dataFromServer[index].endDate);
				
				dateString = endDate.getDate()+'/'+new Number(endDate.getMonth()+1)+'/'+endDate.getFullYear();
				
				var cell6 = row.insertCell(5);             
				cell6.innerHTML = dateString;
				
				var cell7 = row.insertCell(6);             
				cell7.innerHTML = dataFromServer[index].fromLocation;
				
				var cell8 = row.insertCell(7);             
				cell8.innerHTML = dataFromServer[index].toLocation;
				
				var cell9 = row.insertCell(8);             
				cell9.innerHTML = dataFromServer[index].costPerKm;
				
				var cell10 = row.insertCell(9);             
				cell10.innerHTML = dataFromServer[index].totalKms;
				
				var cell11 = row.insertCell(10);             
				cell11.innerHTML = dataFromServer[index].totalAmount;
			}
			 
		}
		
		
		function getFuelCallBack(dataFromServer)
		{
			var table = document.getElementById('fuelTable');               
			var rowCount = table.rows.length;             
			               
			for(var i = table.rows.length - 1; i > 0; i--)
			{
			    table.deleteRow(i);
			} 
			
			for(var index=0; index<dataFromServer.length; index++)
			{
				var row = table.insertRow(index+1);
				if((index+1) % 2 == 0){ 
			       row.className = "even"; 
			     }else{ 
			       row.className = "odd"; 
			     }       
				row.onmouseout = function()
				{
					var varRow = new Number(this.rowIndex);
					if((varRow) % 2 == 0){ 
				       this.className = "even"; 
				     }else{ 
				       this.className = "odd"; 
				     } 
				}
				row.onmouseover = function()
				{
					this.className = "changecolor"; 
				}
				
				
				var cell1 = row.insertCell(0);             
				cell1.innerHTML = dataFromServer[index].fuelId; 
				
				
				var fuelDate = new Date(dataFromServer[index].fuelDate);
				
				var dateString = fuelDate.getDate()+'/'+new Number(fuelDate.getMonth()+1)+'/'+fuelDate.getFullYear();
				
				var cell2 = row.insertCell(1);             
				cell2.innerHTML = dateString;
				
				
				var cell3 = row.insertCell(2);             
				cell3.innerHTML = dataFromServer[index].fuelType;
				
				var cell4 = row.insertCell(3);             
				cell4.innerHTML = dataFromServer[index].fuelQuantity;
				
				var cell5 = row.insertCell(4);             
				cell5.innerHTML = dataFromServer[index].totalCost;
				
				
				var cell6 = row.insertCell(5);             
				cell6.innerHTML = dataFromServer[index].vehicleId;
				
				var cell7 = row.insertCell(6);             
				cell7.innerHTML = dataFromServer[index].vehicleName;
				
				
			}
			 
		}
		
		function getMaintenanceCallBack(dataFromServer)
		{
			var table = document.getElementById('maintenanceTable');               
			var rowCount = table.rows.length;             
			               
			for(var i = table.rows.length - 1; i > 0; i--)
			{
			    table.deleteRow(i);
			} 
			
			for(var index=0; index<dataFromServer.length; index++)
			{
				var row = table.insertRow(index+1);
				if((index+1) % 2 == 0){ 
			       row.className = "even"; 
			     }else{ 
			       row.className = "odd"; 
			     }       
				row.onmouseout = function()
				{
					var varRow = new Number(this.rowIndex);
					if((varRow) % 2 == 0){ 
				       this.className = "even"; 
				     }else{ 
				       this.className = "odd"; 
				     } 
				}
				row.onmouseover = function()
				{
					this.className = "changecolor"; 
				}
				
				
				var cell1 = row.insertCell(0);             
				cell1.innerHTML = dataFromServer[index].objId; 
				
				
				var repairDate = new Date(dataFromServer[index].repairDate);
				
				var dateString = repairDate.getDate()+'/'+new Number(repairDate.getMonth()+1)+'/'+repairDate.getFullYear();
				
				var cell2 = row.insertCell(1);             
				cell2.innerHTML = dateString;
				
				var cell3 = row.insertCell(2);             
				cell3.innerHTML = dataFromServer[index].vehicleId;
				
				var cell4 = row.insertCell(3);             
				cell4.innerHTML = dataFromServer[index].vehicleName;
				
				var cell5 = row.insertCell(4);             
				cell5.innerHTML = dataFromServer[index].companyName;
				
				var cell6 = row.insertCell(5);             
				cell6.innerHTML = dataFromServer[index].totalCost;
				
				
			}
			 
		}
		
		
		
	</script>

  </head>
<jsp:include page="/view/menu.inc.jsp" />
  <body onload="fnOnload();">
	
	
		<div style="background-color: white; margin-left: 10%;margin-right: 10%">
   			<div class="subhd" style="border-bottom: 2px solid #ff9900;margin-left: 2%">Consolidated Report</div>
   			<table class="labelandfield"  class="list" style="margin-left: 2%;margin-right: 2%;background-color: white;">
   				
   				
   				<tr>
					<td>
						<div class="section" id="PopupCrit" >
							
							<table id="crit" >
								
								
								<tr>
									<td class="lbl"  ><label for="vehicelIdCrit">Vehicle Id:</label></td>
									<td> 
										<input type="text" id="vehicleIdCrit" name="vehicleIdCrit" />
									</td> 
									
									<td class="lbl"  ><label for="startDateCrit">Start Date:</label></td>
									<td> 
										<input type="text" id="fromDateCrit" name="fromDateCrit" />
										<img src="/vms/view/calendar/calendar.png" id="fromDateCrit_image" name="fromDateCrit_image"/>
									    
									</td> 
								</tr>
								
								<tr>
									
									<td class="lbl"  ><label for="endDateCrit"> End Date:</label></td>
									<td> 
										<input type="text" id="toDateCrit" name="toDateCrit" />
										<img src="/vms/view/calendar/calendar.png" id="toDateCrit_image" name="toDateCrit_image"/>
										
									</td> 
									
									<td align="center">
										<input type="button" class="btn"  id="btnCrit"  onclick="fnCrit();" value=' GO ' /> &nbsp;&nbsp;
									</td>
								</tr>
								
								
							</table>
						</div>
					</td>
				</tr>
   				
   				<tr>
   					<td>&nbsp;
   					</td>
   				</tr>
   				
   				<tr >
   					<td width="100%">
   						<div class="section" id="PopupDet" style="width: 950px;;">
   							<div class="subhd" style="border-bottom: 2px solid #ff9900;margin-left: 2%">Booking Details</div>

   							<table id="bookingTable" style=" font-family: verdana; font-size: 12px;background-color: skyblue;margin-top:5px;margin-left:3%;margin-right:3%;" >
								<tr>
									<th align="left" >Booking Id&nbsp;&nbsp;&nbsp;</th>	
									<th align="left" >Vehicle Name&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Driver Name&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Customer Name&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Start Date&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >End Date&nbsp;&nbsp;&nbsp;</th>	
									<th align="left" >Start Location&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >End Location&nbsp;&nbsp;&nbsp;</th>									
									<th align="left">Cost Per Km&nbsp;&nbsp;&nbsp;</th>
									<th align="left">Total Kms&nbsp;&nbsp;&nbsp;</th>
									<th align="left">Total Amount&nbsp;&nbsp;&nbsp;</th>
								</tr>
							</table><br>
							<div class="subhd" style="border-bottom: 2px solid #ff9900;margin-left: 2%">Fuel Details</div>
							
							<table id="fuelTable" style="font-family: verdana; font-size: 12px;background-color: skyblue;margin-top:5px;margin-left:3%;margin-right:3%;" >
								<tr>
									<th align="left" >Fuel Id&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Fuel Date&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Fuel Type&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Fuel Quantity&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Total Cost&nbsp;&nbsp;&nbsp;</th>		
									<th align="left" >Vehicle Id&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Vehicle Name&nbsp;&nbsp;&nbsp;</th>
									
								</tr>
							</table><br>
							<div class="subhd" style="border-bottom: 2px solid #ff9900;margin-left: 2%">Maintenance Details</div>
							
							<table id="maintenanceTable" style="font-family: verdana; font-size: 12px;background-color: skyblue;margin-top:5px;margin-left:3%;margin-right:3%;" >
								<tr>
									<th align="left" >Repair Id&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Repair Date&nbsp;&nbsp;&nbsp;</th>	
									<th align="left" >Vehicle Id&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Vehicle Name&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Garage Name&nbsp;&nbsp;&nbsp;</th>
									<th align="left" >Repair Cost&nbsp;&nbsp;&nbsp;</th>
								</tr>
							</table>
							
   						</div>
   					</td>
   				</tr>
   				
   			</table>
   		</div>

		
  </body>
</html>
