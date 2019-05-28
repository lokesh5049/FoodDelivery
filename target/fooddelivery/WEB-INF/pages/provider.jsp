<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Provider</title>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<%@ include file="header.jsp"%>
</head>
<script type="text/javascript">
	Ext
			.onReady(function() {

				var form = Ext
						.create(
								'Ext.form.Panel',
								{
									renderTo : document.body,
									title : 'Add Food',
									defaultType : 'textfield',
									floating : true,
									items : [
											{

												fieldLabel : 'Food Name',
												name : 'name',
												margin : '10 10 10 10',
												allowBlank : false
											},
											{
												xtype : 'numberfield',
												fieldLabel : 'Price(Rs.)',
												name : 'price',
											    forcePrecision: true,
												decimalPrecision : 4,
												margin : '10 10 10 10',
												allowBlank : false
											},
											{
												fieldLabel : 'Resturent Name',
												name : 'resturentName',
												margin : '10 10 10 10',
												allowBlank : false
											},
											{
												xtype : 'button',
												text : 'Add',
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
																url : "http://localhost:8085/fooddelivery/api/product/register",
																data : JSON
																		.stringify(formValues),
																contentType : "application/json; charset=utf-8",
																crossDomain : true,
																dataType : "json",
																success : function(
																		data,
																		status,
																		jqXHR) {

																	Ext.Msg
																			.alert("food added!!"
																					+ data);
																	location
																			.reload();
																},

																error : function(
																		jqXHR,
																		status) {
																	// error handler
																	Ext.Msg
																			.alert("Error");
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


<body style="background-color: powderblue;">

	<%@ include file="footer.jsp"%>
</body>
</html>