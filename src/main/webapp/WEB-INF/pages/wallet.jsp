<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Wallet</title>
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
	src="http://github.com/edspencer/Ext.ux.Exporter/blob/master/Exporter-all.js"></script>
	
	

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
	margin: 0 auto; /* Center the DIV horizontally */
}

nav a {
	color: #fff;
	text-decoration: none;
	padding: 7px 25px;
	display: inline-block;
}
.deduction_class .x-grid-cell {
    background-color: Red;
    /*color: #900;*/
}
</style>
<script type="text/javascript">

Ext.define('Tx_history', {
	extend : 'Ext.data.Model',
	fields:['id','userid','amount','date','cardNo','type']
}); 
	Ext.onReady(function() {
		 Ext.tip.QuickTipManager.init();
		 
		var txStore = Ext.create('Ext.data.Store', {
			model : 'Tx_history',
			proxy: {
		        type: 'ajax',
		        url : '/fooddelivery/api/wallet/tx'
		    },

		});
		txStore.load();
		//add mony form
		var form=Ext.create('Ext.form.Panel', {
		    title: 'Add Money',
		    defaultType: 'textfield',
			renderTo: document.body,
		   /*  floating:true, */
		    items: [
		    	{
		            fieldLabel: 'Card No.',
		            name: 'cardNo',
		            regex : /\b\d{4}(| |-)\d{4}\1\d{4}\1\d{4}\b/,
			        margin: '10 10 10 10',
			        allowBlank: false
		        },
		        {
		            fieldLabel: 'CVV No.',
		            name: 'cvvno',
		            regex:/^[0-9]{3,4}$/,
			        margin: '10 10 10 10',
			        allowBlank: false
		        },
		        {
		        	fieldLabel: 'Expiry Date',
		            name: 'expirydate',
		            regex:/\b(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})\b/,
		           	margin: '10 10 10 10',
		           	allowBlank: false
		        },
		        {
		        	xtype: 'numberfield',
		        	fieldLabel: 'Amount',
		            name: 'amount',
		            forcePrecision: true,
					decimalPrecision : 4,
		           	margin: '10 10 10 10',
		           	allowBlank: false
		        },
		        {
		        	 xtype: 'button',
		        	 text: 'Add',
		        	 margin: '10 10 10 10',
		        	 formBind: true,
		        	 handler: function(){ 
		        		 var form = this.up('form').getForm();
		                 var formValues = form.getValues(); 
		        			console.log(formValues);
		        			 $.ajax({
		        	             type: "POST",
		        	             url: "http://localhost:8085/fooddelivery/api/wallet",
		        	             data: JSON.stringify(formValues),
		        	             contentType: "application/json; charset=utf-8",
		        	             crossDomain: true,
		        	             dataType: "json",
		        	             success: function (data, status, jqXHR) {

		        	            	 Ext.Msg.alert("Money Added!!"+data);
		        	            	 location.reload(); 
		        	             }
		        	             ,
		        	             
		        	             error: function (jqXHR, status) {
		        	                 // error handler
		        	                 Ext.Msg.alert("Error");
		        	             }
		        	          });
		        	    }
		        	 
		        },
		        {
		        	 xtype: 'button',
		        	 text: 'Clear' ,
		 	         margin: '10 10 10 10',
		 	        handler:function(){
		 	        	form.getForm().reset();  
		 	        }
		 	         
		           
		        }
		    ]
		});
		
		//transection grid
		
	var tx_grid=Ext.create('Ext.grid.Panel', {
		id : 'tx_id',
		store : txStore,
		stripeRows : true,
		title : 'Transection History',
		collapsible : true, 
		enableColumnMove : true, 
		enableColumnResize : true, 
		viewConfig: {
	        stripeRows: false, // if stripeRows is true, your rows are ... stripped!
	        getRowClass: function(record) {
	           if (record.get('Deduction') == 1){
	        	   
	        	   return "deduction_class";
	           }
	           else return "";
	        }
	    },
		columns : [
				{
					header : "Transection Id",
					dataIndex : 'id',
					id : 'id',
					flex : 1, 
					sortable : true, 
					hideable : true
				
				},
				{
					header : "Email",
					dataIndex : 'userid',
					flex : .5,
					sortable : true,
					hideable : false
				
				},
				{
					header : "Amount",
					dataIndex : 'amount',
					flex : .5,
					sortable : true,
					hideable : false
				
				},
				{
					header : "Date",
					dataIndex : 'date',
					flex : .5,
					sortable : true,
					hideable : false
				
				},
				{
					header : "Card No.",
					dataIndex : 'cardNo',
					flex : .5,
					sortable : true,
					hideable : false,
					format: 'Y-m-d g:i A',
				
				},
				{
					header : "Type",
					dataIndex : 'type',
					flex : .5,
					sortable : true,
					hideable : false
				
				},
				 ]
	});
		
		//panel 
		 Ext.create('Ext.tab.Panel', {
			renderTo : Ext.getBody(),
			items : [ {
				xtype : 'panel',
				title : 'Add Money',
				layout:'fit',
				items : [form],
				listeners : {
					render : function() {
						/* Ext.MessageBox.alert('Tab one', 'Tab One was clicked.'); */
					}
				}
			}, {
				// xtype for all Component configurations in a Container
				title : 'Passbook',
				items : [tx_grid],
				listeners : {
					render : function() {
						/*  Ext.MessageBox.alert('Tab two', 'Tab Two was clicked.'); */
					}
				}
			}]
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
					href="/fooddelivery/food">Foods</a><a
					href="/fooddelivery/au2">Order Now</a>
				<div class="dropdown" style="float: right;padding-top: 2px;">
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
	<div></div>
	
	<div class="fixed-footer">

		<div class="container">Copyright &copy; 2019 SapphireIms</div>

	</div>
</body>
</html>