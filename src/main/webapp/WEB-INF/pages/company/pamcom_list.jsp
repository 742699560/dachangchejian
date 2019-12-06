<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script src="${basePath}/js/common/page.js"></script>
<style>
#zr_0{
width: 100%;
}
</style>


<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a
			href="indexUI.do">主页</a></li>
		<li class="active">企业统计</li>
	</ul>


<div class="row" style="margin: 0px;">

	<div class="col-md-6">
	 <div id="containerpam"style="height: 500px; margin: 20px;"></div>
	</div>
	<div  class="col-md-6">
		<table  class="table table-striped table-bordered table-hover" style="width: 80%;margin: 20px;" >
			<thead>
				<tr>
					<th data-align="center">景区</th>
					<th data-align="center">酒店</th>
				</tr>
			</thead>

			<tbody id="tablespam">

			</tbody>
		</table>
	</div>
</div>
</div>
<script type="text/javascript">

doGetObjectspam();

function doGetObjectspam(){
	var url = 'company/findComtnumberPar.do';
	$.post(url,function(result){
			setTableRowspam(result);
			
			var dom = document.getElementById("containerpam");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			app.title = '';

			option = {
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data: ['酒店','景区']
			    },
			    series: [
			        {
			            name:'企业数量',
			            type:'pie',
			            radius: ['50%', '70%'],
			            avoidLabelOverlap: false,
			            label: {
			                normal: {
			                    show: false,
			                    position: 'center'
			                },
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '30',
			                        fontWeight: 'bold'
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            data:[
			                {value:result.comnumber, name:'酒店'},
			                {value:result.tnumber, name:'景区'},
			            ]
			        }
			    ]
			};
			
			    myChart.setOption(option, true);
			    
			    window.onresize = function(){
			        myChart.resize();
				 };
			    
	})
}
function setTableRowspam(list){
	var tBody=$('#tablespam');
	tBody.empty();
	var tds='<tr><td>[comnumber]</td><td>[tnumber]</td></tr>';
		
	    tBody.append(tds.replace('[comnumber]',list.comnumber)
		      			.replace('[tnumber]',list.tnumber));
	
}


</script>

