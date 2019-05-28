<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Admin</title>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/packages/charts/classic/charts.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/packages/charts/classic/classic/resources/charts-all.css"
	rel="stylesheet" />


<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.fixed-header {
	width: 100%;
	position: absolute;
	background: #004475;
	padding: 10px 0;
	color: #fff;
}

.fixed-header {
	top: 0;
}

.fixed-footer {
	bottom: 0;
	width: 100%;
	position: fixed;
	background: #004475;
	padding: 10px 0;
	color: #fff;
}

.container {
	width: 80%;
	margin: 0 auto;
}

nav a {
	color: #fff;
	text-decoration: none;
	padding: 7px 25px;
	display: inline-block;
}
.x-grid-item{
text-transform: uppercase;
}
</style>

<script type="text/javascript">
	Ext.define('AdminModel', {
		extend : 'Ext.data.Model',
		fields : [ 'firstname', 'lastname', 'email', 'phoneNo', 'address' ]
	});

	Ext.define('OrderModel', {
		extend : 'Ext.data.Model',
		fields : [ 'foodname', 'number', 'resturentname', 'orderbyId',
				'location', 'date', 'cost' ]
	});

	Ext.onReady(function() {

		var orderStore = Ext.create('Ext.data.Store', {
			model : 'OrderModel',
			id : "order_store",
			 autoLoad: true,
			   pageSize: 10,
			   remoteSort: true,
			   sorters: [{
				        sortProperty: 'foodname',
			              property : 'foodname',
			              direction: 'asc'
			          }],
			proxy : {
				type : 'rest',
				url : '/fooddelivery/api/order/get',
				reader : {
					type : 'json',
					root:'data',
					totalProperty: 'TotalCount'
				}
			},
		});
		orderStore.load();

		//Chart for admin
	var Barchart=	Ext.create({
			xtype : 'cartesian',
			title : 'Order per user ',
			flipXY : true,
			height : 300,
			width : 550,
			collapsible : true,
			store : orderStore,

			legend : {
				docked : 'bottom',
				fileds : 'orderbyId'
			},
			axes : [ {
				type : 'numeric',
				position : 'bottom',
				grid : true,
				minimum : 0,
				renderer : function(label, layout, lastLabel) {
					var string = layout.toString();
					var found = string.indexOf('.');
					if (found == '-1') {
						return layout;
					} else {
						return ' '
					}

				},
				title : {
					text : 'Number of order.',
					fontSize : 15
				}

			}, {
				type : 'category',
				position : 'left',
				title : {
					text : 'Users',
					fontSize : 15
				}

			} ],
			series : [ {
				type : 'bar',
				xField : 'orderbyId',
				yField : 'number',
				axis : 'left',
				subStyle : {
					fill : [ '#388FAD' ],
					stroke : '#1F6D91'
				},
			} ]
		});
		var gridStore = Ext.create('Ext.data.Store', {
			model : 'AdminModel',
			id : "admin_store",
			autoLoad: true,
			   pageSize: 5,
			   remoteSort: true,
			   sorters: [{
				          sortProperty: 'firstname',
			              property : 'firstname',
			              direction: 'asc'
			          }],
			proxy : {
				type : 'rest',
				url : '/fooddelivery/api/customer/get',
				 reader: {
				        type: 'json',
				        root: 'data',
				        totalProperty: 'TotalCount'
				        	
				     }
			},
		});
		gridStore.load();

		// Creation of first grid
	var usersGrid=	Ext.create('Ext.grid.Panel', {
			id : 'admin_id',
			style: '',
			store : gridStore,
			stripeRows : true,
			title : 'Users',
			collapsible : true,
			enableColumnMove : true,
			enableColumnResize : true,
			selType : 'rowmodel',
			dockedItems: [
                {
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    displayInfo: true,
                    store: gridStore
                }
            ],
			plugins : [ {
				ptype : 'rowediting',
				clicksToEdit : 1
			} ],

			columns : [ {
				header : "First Name",
				dataIndex : 'firstname',
				id : 'f_name',
				flex : 1,
				sortable : true,
				hideable : true
			}, {
				header : "Last Name",
				dataIndex : 'lastname',
				flex : .5,
				sortable : true,
				hideable : false
			}, {
				header : "Email",
				dataIndex : 'email',
				flex : .5,
				sortable : true,
				hideable : false

			}, {
				header : "Phone No.",
				dataIndex : 'phoneNo',
				flex : .5,
				sortable : true,
				hideable : false,
				editor: new Ext.form.TextField({
					
					
					
	            })

			}, {
				header : "Address",
				dataIndex : 'address',
				flex : .5,
				sortable : true,
				hideable : false,

			} ]
		});

		console.log(orderStore);
	var orderHistory=	Ext.create('Ext.grid.Panel', {
			id : 'order_id',
			style: '',
			store : orderStore,
			stripeRows : true,
			title : 'Order History',
			collapsible : true,
			enableColumnMove : true,
			enableColumnResize : true,
			selType : 'rowmodel',
			dockedItems: [
                {
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    displayInfo: true,
                    store: orderStore
                }
            ],
			columns : [ {
				header : "Food Name",
				dataIndex : 'foodname',
				id : 'food_name',
				flex : 1,
				sortable : true,
				hideable : true

			}, {
				header : "Quantity",
				dataIndex : 'number',
				flex : .5,
				sortable : true,
				hideable : false

			}, {
				header : "Resturent Name",
				dataIndex : 'resturentname',
				flex : .5,
				sortable : true,
				hideable : false

			}, {
				header : "Order By",
				dataIndex : 'orderbyId',
				flex : .5,
				sortable : true,
				hideable : false

			}, {
				header : "Location",
				dataIndex : 'location',
				flex : .5,
				sortable : true,
				hideable : false

			}, {
				header : "Cost(Rs.)",
				dataIndex : 'cost',
				flex : .5,
				sortable : true,
				hideable : false

			},

			{
				header : "Order Date",
				dataIndex : 'date',
				flex : .5,
				sortable : true,
				hideable : false,
				format : 'Y-m-d g:i A'
			}, ]
		});
		
		//panel 
		 Ext.create('Ext.tab.Panel', {
			renderTo : Ext.getBody(),
			items : [ {
				xtype : 'panel',
				title : 'Dashboard',
				layout:'fit',
				 autoLoad:{
				      url:'/fooddelivery/dashboard'
				    },
		
				listeners : {
					render : function() {
						/* Ext.MessageBox.alert('Tab one', 'Tab One was clicked.'); */
					}
				}
			}, {
				// xtype for all Component configurations in a Container
				title : 'Users',
				items : [usersGrid],
				listeners : {
					render : function() {
						/*  Ext.MessageBox.alert('Tab two', 'Tab Two was clicked.'); */
					}
				}
			}, {
				// xtype for all Component configurations in a Container
				title : 'Order History',
				items : [orderHistory],
				listeners : {
					render : function() {
						/*  Ext.MessageBox.alert('Tab two', 'Tab Two was clicked.'); */
					}
				}
			}]
		}); 
		 
	});
	
	function startTime() {
		
		var date =new Date();
		 document.getElementById('ClockRun').innerHTML="[ "+date+" ]";
		
	}
	function loadURL(url) {
		var oRequest = new XMLHttpRequest();
		oRequest.open('GET', url, false);
		oRequest.setRequestHeader("User-Agent", navigator.userAgent);
		oRequest.send(null)
		
		return oRequest.responseText;
	};
</script>


</head>
<body style="background-color: powderblue;"  onload="startTime()">

	<div class="fixed-header">

		<div class="container">

			<nav>
				<span style="font: italic bold 12px/30px Georgia, serif;">Food
					Delivery System </span> <a href="/fooddelivery/home">Home</a><a
					href="/fooddelivery/food">Foods</a>
					<span>Today's Date :<span id="ClockRun" ></span></span>
				<div class="dropdown" style="float: right">
					<button class="btn btn-primary dropdown-toggle" type="button"
						data-toggle="dropdown">
						<span class="glyphicon glyphicon-user">:User<span class="caret"></span></span>
					</button>
					
					<ul class="dropdown-menu">
						<li><sec:authorize access="hasRole('customer')">
						<!-- For login user -->
						<c:url value="/logout" var="logoutUrl" />
						<form action="${logoutUrl}" method="post" id="logoutForm">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<script>
							function formSubmit() {
								document.getElementById("logoutForm").submit();
							}
						</script>

						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<ul style="padding-left: 13px;">
						         <li>
								<span style="color: black"> 
								${pageContext.request.userPrincipal.name}</span> </li>
							    <li><a href="javascript:formSubmit()" style="color: black"> Logout</a></li>
					            <li><a style="color: black">Edit Profile</a></li>
					        </ul>
						</c:if>
					</sec:authorize>			
					</li>
					
					</ul>
				</div>

			</nav>

		</div>

	</div>
	<!-- <div id="chart-body" style="margin-top: 8px"></div>
	<h4></h4>

	<div id="adminGrid"></div>
	<h4></h4>
	<div id="orderGrid"></div>
 -->
	<div class="fixed-footer">

		<div class="container">Copyright &copy; 2019 SapphireIms</div>

	</div>

</body>
</html>