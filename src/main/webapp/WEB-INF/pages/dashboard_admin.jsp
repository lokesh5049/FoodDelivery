<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <meta charset="utf-8" />

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.gridster/0.5.6/jquery.gridster.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.gridster/0.5.6/jquery.gridster.css">
    <script type="text/javascript" src="https://cdnjs.com/libraries/Chart.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
    
</head>
<body>
<script>
var gridster;

$(function(){

  gridster = $(".gridster ul").gridster({
    widget_base_dimensions: [100, 50],
    widget_margins: [5, 5],
    helper: 'clone',
    resize: {
      enabled: true,
      max_size: [8, 8],
      min_size: [3, 3]
    }
  }).data('gridster');


});

</script>
<style>

.gridster {
  background-color: cyan;
}

.gridster ul {
  list-style: none;
}

.gridster ul li canvas {
  background-color: white;
}
.gridster ul li {
  background-color: white;
}
.title {
  text-align: center;
  color: white;
  font-size: 20px;
  font-weight: bold;
}


</style>

<div class="gridster">
    <ul>
        <li data-row="1" data-col="1" data-sizex="1" data-sizey="1" >
       <canvas id="myChart" width="400" height="200"></canvas>
        </li>
        <li data-row="2" data-col="2" data-sizex="2" data-sizey="1"></li>
        <li data-row="3" data-col="2" data-sizex="2" data-sizey="2"></li>
 
        <!-- <li data-row="1" data-col="2" data-sizex="2" data-sizey="1"></li>
        <li data-row="2" data-col="2" data-sizex="2" data-sizey="2"></li>
 
        <li data-row="1" data-col="4" data-sizex="1" data-sizey="1"></li> -->
        <!-- <li data-row="2" data-col="4" data-sizex="2" data-sizey="1"></li>
        <li data-row="3" data-col="4" data-sizex="1" data-sizey="1"></li>
 
        <li data-row="1" data-col="5" data-sizex="1" data-sizey="1"></li>
        <li data-row="3" data-col="5" data-sizex="1" data-sizey="1"></li>
 
        <li data-row="1" data-col="6" data-sizex="1" data-sizey="1"></li>
        <li data-row="2" data-col="6" data-sizex="1" data-sizey="2"></li> -->
    </ul>
</div>
<script type="text/javascript">


var canvas = document.getElementById('myChart');
var data = {
    labels: ["January", "February", "March", "April", "May", "June", "July"],
    datasets: [
        {
            label: "My First dataset",
            backgroundColor: "rgba(255,99,132,0.2)",
            borderColor: "rgba(255,99,132,1)",
            borderWidth: 2,
            hoverBackgroundColor: "rgba(255,99,132,0.4)",
            hoverBorderColor: "rgba(255,99,132,1)",
            data: [65, 59, 30, 81, 56, 55, 40],
        }
    ]
};
var option = {
animation: {
				duration:5000
}

};


var myBarChart = Chart.Bar(canvas,{
	data:data,
  options:option
});

</script>
</body>
</html>