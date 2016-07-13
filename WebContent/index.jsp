<!DOCTYPE HTML>
<html>

<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="js/hm.js"></script>
	
	<!-- JQuery CSS -->
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/redmond/jquery-ui.css" type="text/css" />
 
	<!-- jqGrid CSS -->
	<link rel="stylesheet" href="jqgrid5.1.1/css/ui.jqgrid.css" type="text/css"/>
 
 
<!-- The actual JQuery code -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<!-- The JQuery UI code -->
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
<!-- The jqGrid language file code-->
<script type="text/javascript" src="jqgrid5.1.1/js/i18n/grid.locale-en.js"></script>
<!-- The atual jqGrid code -->
<script type="text/javascript" src="jqgrid5.1.1/js/jquery.jqGrid.min.js"></script>
	

	
	
	<style>
		.session{
			text-align:right;
			float:right;
		}
	</style>
	
	<script>
		function logout(){
			$.get("/RESTfulDB/Logout",function(){
				location.reload();
			});		
		}
	</script>
	
	
</head>
<body>

	<h4 class="text-center">Person DB</h4>
	
	<div class="container-fluid">
		
		<div class="session">
			<div>User: <%=request.getRemoteUser()%></div>
			<button onclick="logout()" class="btn btn-danger">Log out</button>
		</div>
		<br>
		<div class="form-group">
			<label for="tc">TC:</label>
			<input type="text" class="form-control" id="inTC">
		</div>
		<div class="form-group">
			<label for="name">Name:</label>
			<input type="text" class="form-control" id="inName">
		</div>
		<div class="form-group">
			<label for="phone">Phone:</label>
			<input type="text" class="form-control" id="inPhone">
		</div>

		<div class="row">
			<div class="col-xs-3">
				<button onclick="getPerson()" class="btn-primary btn-block btn btn-default">Get</button>
			</div>
			<div class="col-xs-3">
				<button onclick="addPerson()" class="btn-success btn-block btn btn-default">Post</button>
			</div>
			<div class="col-xs-3">
				<button onclick="updatePerson()" class="btn-warning btn-block btn btn-default">Put</button>
			</div>
			<div class="col-xs-3">
				<button onclick="deletePerson()" class="btn-danger btn-block btn btn-default">Delete</button>
			</div>
		</div>
		
		<table id="gridTable"></table>
		<div id="pager"></div>
		

		<div class="result">
			<div class="alert" role="alert">
				<p id="message"></p>
				<p id="text-tc"></p>
				<p id="text-name"></p>
				<p id="text-phone"></p>
			</div>
		</div>
	</div>
</body>
</html>