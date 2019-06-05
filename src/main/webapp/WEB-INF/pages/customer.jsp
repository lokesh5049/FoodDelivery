<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Customer</title>
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
<!-- <link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css"
	rel="stylesheet" />  -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.fixed-header, .fixed-footer {
	width: 100%;
	position: fixed;
	background: #004475;
	padding: 10px 0;
	color: #fff;
}

.fixed-header {
	top: 0;
}

.fixed-footer {
	bottom: 0;
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
	Ext.define('Product_Model', {
		extend : 'Ext.data.Model',
		fields : [ 'id', 'name', '' ]
	});
	Ext.define('Order_History', {
		extend : 'Ext.data.Model',
		fields : [ 'foodname', 'number', 'resturentname', 'orderbyId',
				'location', 'date', 'cost' ]
	});

	Ext
			.onReady(function() {
				
				function progressBar(v) {
			        return function()	{
			           if(v == 10) {
			              Ext.MessageBox.hide();
			              result();
			           } else {
			              var i = v/9;
			              Ext.MessageBox.updateProgress(i, Math.round(100*i)+'% completed');
			           }
			        };
			     };
			     function showProgressBar() {
			        for(var i = 1; i < 11; i++) {
			           setTimeout(progressBar(i), i*500);
			        }
			     }
			     function result() {
			        Ext.Msg.alert('status', 'Order Placed!');
			     }

				var flag = true;
				var oldValue;
				var oldValue_price;

				var orderStore = Ext.create('Ext.data.Store', {
					model : 'Order_History',
					autoLoad: true,
					   pageSize: 5,
					   remoteSort: true,
					   sorters: [{
						          sortProperty: 'foodname',
					              property : 'foodname',
					              direction: 'asc'
					          }],
					proxy : {
						type : 'rest',
						url : '/fooddelivery/api/order/getbyuser',
						 reader: {
						        type: 'json',
						        root: 'data',
						        totalProperty: 'TotalCount'
						        	
						     }
					},

				});
				orderStore.load();
				$
						.ajax({
							type : "GET",
							dataType : "json",
							url : "http://localhost:8080/fooddelivery/api/wallet/balance",
							success : function(data) {
								console.log(data)
								$("#wallet_money").text('Rs. ' + data)
							}
						});
				Ext.create('Ext.grid.Panel', {
					id : 'my_order_id',
					store : orderStore,
					stripeRows : true,
					title : 'Recent Order History',
					renderTo : 'MyorderGrid',
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

					}, {
						header : "Order Date",
						dataIndex : 'date',
						flex : .5,
						sortable : true,
						hideable : false,
						format : 'Y-m-d g:i A',

					}, ]
				});
				// Store data
				var foodStore = Ext.create('Ext.data.Store', {
					model : 'Product_Model',
					proxy : {
						type : 'ajax',
						url : '/fooddelivery/api/customer/product'
					}
				});

				var form = Ext
						.create(
								'Ext.form.Panel',
								{
									renderTo : document.body,
									title : 'Order Food',
									defaultType : 'textfield',
									floating : true,
									renderTo : 'Form_user',
									items : [
											{
												xtype : 'combobox',
												fieldLabel : 'Food',
												store : foodStore.sort("id", "ASC"),
												editable : false,
												valueField : 'id',
												displayField : 'name',
												margin : '10 10 10 10',
												name : 'foodId',
												listeners : {
													change : function(combo,
															newValue, oldValue) {
														var value_index = foodStore
																.find('id',
																		newValue);
														var record = foodStore
																.getAt(value_index);
														Ext
																.getCmp('cost')
																.setValue(
																		record
																				.get("price"));
			
													}
												}
											},
											{
												xtype : 'numberfield',
												fieldLabel : 'Quentity',
												name : 'number',
												id : 'number',
												margin : '10 10 10 10',
												allowBlank : false,
												 minValue: 1,
									             allowExponential: false,
									             allowDecimals: true,
												listeners : {
													change : function() {

														if (flag) {
															oldValue = Ext
																	.getCmp(
																			'cost')
																	.getValue();
															flag = false;
														} else {
															var newValue = oldValue
																	* Ext
																			.getCmp(
																					'number')
																			.getValue();
															if (Ext.getCmp(
																	'number')
																	.getValue() >= 1) {
																Ext
																		.getCmp(
																				'cost')
																		.setValue(
																				newValue);
																console
																		.log(newValue);
																console
																		.log(oldValue);

															}
														}

													}
												}
											},
											{
												fieldLabel : 'Dilivery Location',
												name : 'location',
												margin : '10 10 10 10',
												allowBlank : false
											},
											{
												fieldLabel : 'Cost(Rs.)',
												name : 'cost',
												id : 'cost',
												editable : false,
												margin : '10 10 10 10',
												allowBlank : false,
												valueField : 'price',
												store : foodStore,

											},
											{
												xtype : 'button',
												text : 'Pay (Wallet Only.)',
												margin : '10 10 10 10',
												formBind : true,
												handler : function() {
													var form = this.up('form')
															.getForm();
													var formValues = form
															.getValues();
													console.log(formValues);
													$
															.ajax({
																type : "POST",
																url : "http://172.16.10.101:8080/fooddelivery/api/order",
																data : JSON
																		.stringify(formValues),
																contentType : "application/json; charset=utf-8",
																crossDomain : true,
																dataType : "json",
																success : function(
																		data,
																		status,
																		jqXHR) {
																	if (data != null) {
																		Ext.MessageBox.show ({
													                        title: 'Please wait',
													                        msg: 'Loading items...',
													                        progressText: 'Initializing...',
													                        width:300,
													                        progress:true,
													                        closable:false
													                     });
													                     showProgressBar();
																	} else {

																	}
																	location
																			.reload();
																},

																error : function(
																		jqXHR,
																		status) {
																	// error handler

																	Ext.Msg
																			.alert("Add money in wallet as order cost is high.");

																}
															});
												}

											}, {
												xtype : 'button',
												text : 'Clear',
												margin : '10 10 10 10',
												handler : function() {
													form.getForm().reset();
												}

											} ]
								});

			});
</script>

</head>
<body style="background-color: powderblue;">

	<div class="fixed-header">

		<div class="container">

			<nav>
				<span style="font: italic bold 12px/30px Georgia, serif;">Food
					Delivery System </span> <a href="/fooddelivery/home">Home</a> <a
					href="/fooddelivery/food">Foods</a>
					<div class="dropdown" style="float: right;padding-top: 22px;">
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
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button"
						data-toggle="dropdown">
						<span class='fas fa-wallet'> &nbsp;Wallet <span class="caret"></span></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="/fooddelivery/ewallet/"><span
								id="wallet_money" name="wallet_money">Rs.0.0</span></a></li>
					</ul>
				</div>

			</nav>

		</div>

	</div>

	<div id="Form_user" style="margin-top: 139px;"></div>

	<div id="MyorderGrid" style="margin-top: 260px;"></div>
	<%@ include file="footer.jsp"%>
</body>
</html>