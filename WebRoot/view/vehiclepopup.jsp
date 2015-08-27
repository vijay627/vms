<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE HTML PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>VMS-Search Popup</title>
    
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
	var fromDate = null;
	var toDate = null;
	
	function fnOnload()
	{	
		 
		 currentPage = window.opener.document.getElementById('currentPage').value; 
		  		  
		 	if(currentPage == 'B')
			{
				
				fromDate = window.opener.document.getElementById('startDate').value;  
				toDate = window.opener.document.getElementById('endDate').value;
				
			}
			
			
			
							
							
			VmsDwrUtilities.getVehicleDetails(
				   				fromDate,
				   				toDate,
				   				{
				   					async:true,
				   					callback:getVehicleCallBack
				   				}	
							);
	 
	}
	
	function getVehicleCallBack(dataFromServer)
		{
			
			var table = document.getElementById('vehicleTable');               
			var rowCount = table.rows.length;             
			               
			
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
				row.onclick = function(){
				var selectedRow = new Number(this.rowIndex)-1;
				
					window.opener.document.getElementById('vehicleId').value=dataFromServer[selectedRow].vehicleId;
					
					if(currentPage != 'V')
					{
						window.opener.document.getElementById('vehicleName').value=dataFromServer[selectedRow].vehicleName;
					}
					
					window.close();
				}
				
				var cell1 = row.insertCell(0);             
				cell1.innerHTML = dataFromServer[index].vehicleId; 				
				
				var cell2 = row.insertCell(1);             
				cell2.innerHTML = dataFromServer[index].vehicleName; 				
				
				
				var cell3 = row.insertCell(2);             
				cell3.innerHTML = dataFromServer[index].registrationNumber;
				
				var cell4 = row.insertCell(3);             
				cell4.innerHTML = dataFromServer[index].chassisNumber;
				
				
			}
			 
		}
	
	
	</script>
	<script type="text/javascript">
		
		
		
		
		
	</script>

  </head>

  <body onload="fnOnload();">
			<div class="section" id="PopupDet" >
				<table id="vehicleTable" style="font-family: verdana; font-size: 12px;background-color: skyblue;margin-top:5px;margin-left:3%;margin-right:3%;width: 95%;" >
					<tr>
						<th align="left" >Vehicle Id&nbsp;&nbsp;&nbsp;</th>	
						<th align="left" >Vehicle Name&nbsp;&nbsp;&nbsp;</th>	
						<th align="left" >Registration Number&nbsp;&nbsp;&nbsp;</th>
						<th align="left" >Chassis Number&nbsp;&nbsp;&nbsp;</th>
					</tr>
				</table>
				
			</div>

		
  </body>
</html>
