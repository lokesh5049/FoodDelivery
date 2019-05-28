<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" 
%><!DOCTYPE html>
<html>
<title>Home</title>
<head><meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta charset="utf-8" />
<%@ include file="header.jsp" %>
</head>
<script>
	
	Ext.define('ProductModel', {
		extend : 'Ext.data.Model',
		fields:['name','price','resturentName']
	}); 

	Ext.onReady(function() {
	
	
		var gridStore = Ext.create('Ext.data.Store', {
			model : 'ProductModel',
			 autoLoad: true,
			   pageSize: 10,
			   remoteSort: true,
			   sorters: [{
				        sortProperty: 'price',
			              property : 'price',
			              direction: 'asc'
			          }],
			proxy: {
		        type: 'ajax',
		        url : '/fooddelivery/api/customer/product',
		        reader : {
					type : 'json',
					root:'data',
					totalProperty: 'TotalCount'
				}
		    }
		});
		gridStore.load();
		Ext.create('Ext.grid.Panel', {
			id : 'food_id',
			store : gridStore,
			stripeRows : true,
			title : 'Food', 
			renderTo : 'productGrid', 
			collapsible : false, 
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
			columns : [
					{
						header : "Food Name",
						dataIndex : 'name',
						id : 'name',
						flex : 1,	
						sortable : true, 
						hideable : true,
						cls:'F_name',
						editor: new Ext.form.TextField({
						    fieldStyle: 'text-transform:uppercase;',
						    listeners: {
						       change: function(field, newValue, oldValue) {
						           field.setValue(newValue.toUpperCase());
						       }
						    }
						    
						})
					
					},
					{
						header : "Price(Rs.)",
						dataIndex : 'price',
						id : 'price',
						flex : .5,
						sortable : true,
						hideable : false,
						decimalPrecision: 2,
				
					},
					{
						header : "Resurent Name",
						dataIndex : 'resturentName',
						id : 'r_name',
						cls:'r_name',
						flex : .5,
						sortable : true,
						hideable : false
					
					}

					 ]
		});
	});
</script>



<body  style="background-color: powderblue;">


	<marquee direction="right" speed="normal" behavior="loop" >
   <span style=" font: italic bold 12px/30px Georgia, serif;color:red">  Order Food ,Add food and Only COD Available! Have a nice day!</span>
  </marquee>
	<div id="productGrid" style="margin-top: 27px">
	
	</div>

	<%@ include file="footer.jsp" %>
</body>
</html>