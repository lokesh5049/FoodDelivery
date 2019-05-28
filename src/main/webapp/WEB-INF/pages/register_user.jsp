<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Register</title>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<%@ include file="header.jsp" %>
<script type="text/javascript">
Ext.onReady( function(){	
	var form=Ext.create('Ext.form.Panel', {
	    renderTo: document.body,
	    title: 'User SignUp',
	    defaultType: 'textfield',
	    floating:true,
	    items: [
	        {
	            fieldLabel: 'Firstname',
	            name: 'firstname',
	           	margin: '10 10 10 10',
	           	allowBlank: false
	        },
	        {
	            fieldLabel: 'Lastname',
	            name: 'lastname',
		        margin: '10 10 10 10',
		        allowBlank: false
	        },
	        {
	            fieldLabel: 'Email',
	            name: 'email',
	           	margin: '10 10 10 10',
	           	allowBlank: false,
	           	vtype: 'email'
	        },
	        {
	        	xtype: 'numberfield',
	            fieldLabel: 'Phone No.',
	            name: 'phoneNo',
	           	margin: '10 10 10 10',
	           	allowBlank: false,
	        	regex : /^(\+\d{1,3}[- ]?)?\d{10}$/
	        },
	        {
	            fieldLabel: 'Address',
	            name: 'address',
	           	margin: '10 10 10 10',
	           	allowBlank: false
	        },
	        {
	            fieldLabel: 'Password',
	            inputType:'password',
	           	margin: '10 10 10 10',
	           	name:'password',
	           	allowBlank: false,
	           	
	        },
	        {
	            xtype: 'hiddenfield',
	           	margin: '10 10 10 10',
	           	name:'enabled',
	           	value:'true'
	        },
	        {
	            xtype: 'hiddenfield',
	           	margin: '10 10 10 10',
	           	name:'role',
	           	value:'customer'
	        },
	        {
	        	 xtype: 'button',
	        	 text: 'Register',
	        	 margin: '10 10 10 10',
	        	 formBind: true,
	        	 handler: function(){ 
	        		 var form = this.up('form').getForm();
	                 var formValues = form.getValues(); 
	        			console.log(formValues);
	        			 $.ajax({
	        	             type: "POST",
	        	             url: "http://localhost:8085/fooddelivery/api/customer/register",
	        	             data: JSON.stringify(formValues),
	        	             contentType: "application/json; charset=utf-8",
	        	             crossDomain: true,
	        	             dataType: "json",
	        	             success: function (data, status, jqXHR) {

	        	            	 Ext.Msg.alert("registeration done");
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
	
	
})

	
</script>


</head>
<body style="background-color: powderblue;">
	<%@ include file="footer.jsp" %>

</body>
</html>