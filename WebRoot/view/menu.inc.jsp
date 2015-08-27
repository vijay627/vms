<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>VMS</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type=text/javascript charset=utf-8 
	src="<%=request.getContextPath()%>/view/css/jquery.min.js"></script>
	
	<script type=text/javascript 
	src="<%=request.getContextPath()%>/view/css/hoverpopup.js"></script>
	
	<script type=text/javascript 
	src="<%=request.getContextPath()%>/view/css/clearbox.js"></script>
	
	<link rel=stylesheet type=text/css 
	href="<%=request.getContextPath()%>/view/css/clearbox.css">
	
	<link rel=home href="http://www.clarkbuilders.com/">
	
	<link title=News rel=alternate type=application/rss+xml href="http://www.clarkbuilders.com/rss/news.rss">
	
	<link title="Job listings - Field" rel=alternate type=application/rss+xml 
	href="http://www.clarkbuilders.com/rss/jobs-field.rss">
	
	<link title="Job listings - Office" rel=alternate type=application/rss+xml 
	href="http://www.clarkbuilders.com/rss/jobs-office.rss">
	
	<!-- jCarousel files -->
	<script type=text/javascript 
	src="<%=request.getContextPath()%>/view/js/jquery.jcarousel.js"></script>
	
	
	<script type=text/javascript 
	src="<%=request.getContextPath()%>/view/js/curvycorners.src.js"></script>
	
	<link rel=stylesheet type=text/css 
	href="<%=request.getContextPath()%>/view/css/jquery.jcarousel.css">
	
	<link 	rel=stylesheet type=text/css 
	href="<%=request.getContextPath()%>/view/css/jcarousel.css">
	
	<link 	rel=stylesheet type=text/css 
	href="<%=request.getContextPath()%>/view/css/clark.css">
	
	<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/view/css/defaults.css">
	
	<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/view/css/typography.css">
	
	<link rel="stylesheet" type="text/css" href="/vms/view/engine1/style.css" />
		
	<link rel=stylesheet type=text/css 
	href="<%=request.getContextPath()%>/view/css/ttcs.css">
	
	<script type="text/javascript" src="/vms/view/engine1/jquery.js"></script>
	<!--[if IE 6]>
	<script src="/js/DD_belatedPNG.js"></script><script>  DD_belatedPNG.fix('#newsticker, .enticer, #headlinestopfade, #headlinesbotfade, .photo-meta-data');</script><link href="/css/nav_ie.css" rel="stylesheet" type="text/css" />
	<![endif]-->
	
	<link rel=stylesheet type=text/css 	href="<%=request.getContextPath()%>/view/js/jquery-ui.css">
	
	<script type=text/javascript src="<%=request.getContextPath()%>/view/js/jquery-ui.js"></script>
	
	<script type=text/javascript src="<%=request.getContextPath()%>/view/js/formCode.js"></script>
	
	<script type=text/javascript>    $(document).ready(function() {    	$('.datepicker').datepicker();        $('#inputborder').example('Search');    });</script>
	
	<script type=text/javascript src="<%=request.getContextPath()%>/view/js/brand"></script>
	
	<style type=text/css>#toppic {
		BACKGROUND-IMAGE: url(../../images/headers/content_toppic8.jpg)
	}
	
	H1#replace {
		TEXT-TRANSFORM: uppercase; FONT-FAMILY: aniversregular; FONT-SIZE: 28px
	}
	
	H2.replace {
		TEXT-TRANSFORM: uppercase; FONT-FAMILY: aniversregular; COLOR: #e61938; FONT-SIZE: 22px; FONT-WEIGHT: normal
	}
	
	H2.title {
		WIDTH: 590px; FONT-FAMILY: aniversregular; COLOR: #e61938; FONT-SIZE: 22px; FONT-WEIGHT: normal
	}
	
	H3.replace {
		TEXT-TRANSFORM: uppercase; FONT-FAMILY: aniversregular; COLOR: #3b3b3b; FONT-SIZE: 16px
	}
	</style>

  </head>
  
  <body class=inside>
	<div id=wrapper>
	<div id=container>
	<div id=topbar>
	<span style="color: #7a06ff;font-size: 30px;margin-top: 20px;font-style: italic"><b>Vehicle Management System</b></span>
		
	<div id=nav  >
		<ul id=navigation style="float: right;">

	  		<li class="current"><a href="<%=request.getContextPath()%>/vehicle.spr"><b>Vehicle</b></a></li>
	  		<li ><a href="<%=request.getContextPath()%>/driver.spr"><b>Driver</b></a> </li>	    	
	    	<li ><a href="<%=request.getContextPath()%>/booking.spr"><b>Booking</b></a></li>	
	    	<li ><a href="<%=request.getContextPath()%>/fuel.spr"><b>Fuel</b></a></li>	    	
	    	<li ><a href="<%=request.getContextPath()%>/maintenance.spr"><b>Maintenance</b></a> </li>
	    	<li ><a href="<%=request.getContextPath()%>/view/report.jsp"><b>Report</b></a> </li>
	    	<li class=nodd><a href="<%=request.getContextPath()%>/contacts.spr"><b>Contact List</b></a> </li>
	
		</ul>	
		
	</div>	
	</div>
	<div class=clear></div>
	</div>	
	</div>
	
	
	<div id=secondarynavigation>
		
		<marquee scrolldelay="100"  direction="left" style="color: white;font-weight: bold; font-size: medium;"> Vehicle Management System  Guntur AP</marquee>
  	
   </div>
   
	
		
  </body>
</html>
