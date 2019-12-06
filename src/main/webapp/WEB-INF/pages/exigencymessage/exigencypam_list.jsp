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
		<li class="active">报警统计</li>
	</ul>


<div class="row" style="margin: 0px;">

	<div class="col-md-6">
	 <div id="containerpam"style="height: 500px; margin: 20px;"></div>
	</div>
	<div  class="col-md-6">
		<table  class="table table-striped table-bordered table-hover" style="width: 80%;margin: 20px;" >
			<thead>
				<tr>
					<th data-align="center">月份</th>
					<th data-align="center">数量</th>
				</tr>
			</thead>

			<tbody id="tablespam">

			</tbody>
		</table>
	</div>
	<div class="col-md-11">
	<div id="containerpamchar"style="margin: 20px;height: 400px;">
	
	</div>
	</div>
</div>
</div>
<script type="text/javascript">

doGetObjectspam();




/* option = {
    title: {
        text: '报警数量条形图',        
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
    },
    series: [
        {
            name: '该月报警数量',
            type: 'bar',
            data: [1, 2, 3, 4, 5,7,4,1,2,3,3,1]
        }
    ]
}; */


function doGetObjectspam(){
	var url = 'project/findParameterTime.do';
	$.post(url,function(result){
			setTableRowspam(result);
			
			var dom = document.getElementById("containerpam");
			var domchar = document.getElementById("containerpamchar");
			var myCharttow =echarts.init(domchar);
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			app.title = '';

			options = {
				    title : {
				        text: '年度报警数据综合图',
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['报警数量']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'报警数量',
				            type:'bar',
				            data:[result.jan,result.feb,result.mar, result.apr, result.may, result.jun, result.jul, result.aug, result.sep, result.oct,result.nov, result.decs],
				            markPoint : {
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name : '平均值'}
				                ]
				            }
				        }
				    ]
				};

			option = {
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
			    },
			    series: [
			        {
			            name:'报警数量',
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
			                {value:result.jan, name:'一月'},
			                {value:result.feb, name:'二月'},
			                {value:result.mar, name:'三月'},
			                {value:result.apr, name:'四月'},
			                {value:result.may, name:'五月'},
			                {value:result.jun, name:'六月'},
			                {value:result.jul, name:'七月'},
			                {value:result.aug, name:'八月'},
			                {value:result.sep, name:'九月'},
			                {value:result.oct, name:'十月'},
			                {value:result.nov, name:'十一月'},
			                {value:result.decs, name:'十二月'},
			                
			            ]
			        }
			    ]
			};
			
			    myChart.setOption(option, true);
			    myCharttow.setOption(options, true);
			    
			    window.onresize = function(){
			        myChart.resize();
			        myCharttow.resize();
				 };
			    
	})
}
function setTableRowspam(list){
	var tBody=$('#tablespam');
	tBody.empty();
	var tds='<tr><td>[janfak]</td><td>[jan]</td></tr>'+
			'<tr><td>[febfak]</td><td>[feb]</td></tr>'+
	        '<tr><td>[marfak]</td><td>[mar]</td></tr>'+
	        '<tr><td>[aprfak]</td><td>[apr]</td></tr>'+
	        '<tr><td>[mayfak]</td><td>[may]</td></tr>'+
	        '<tr><td>[junfak]</td><td>[jun]</td></tr>'+
	        '<tr><td>[julfak]</td><td>[jul]</td></tr>'+
	        '<tr><td>[augfak]</td><td>[aug]</td></tr>'+
	        '<tr><td>[sepfak]</td><td>[sep]</td></tr>'+
	        '<tr><td>[octfak]</td><td>[oct]</td></tr>'+
	        '<tr><td>[novfak]</td><td>[nov]</td></tr>'+
	        '<tr><td>[decsfak]</td><td>[decs]</td></tr>';
	
		
	  
	    tBody.append(tds.replace('[janfak]',"一月")
	  	      .replace('[jan]',list.jan)
		      .replace('[febfak]',"二月")
		      .replace('[feb]',list.feb)
		      .replace('[marfak]',"三月")
		      .replace('[mar]',list.mar)
		      .replace('[aprfak]',"四月")
		      .replace('[apr]',list.apr)
		      .replace('[mayfak]',"五月")
		      .replace('[may]',list.may)
		      .replace('[junfak]',"六月")
		      .replace('[jun]',list.jun)
		      .replace('[julfak]',"七月")
		      .replace('[jul]',list.jul)
		      .replace('[augfak]',"八月")
		      .replace('[aug]',list.aug)
		      .replace('[sepfak]',"九月")
		      .replace('[sep]',list.sep)
		      .replace('[octfak]',"十月")
		      .replace('[oct]',list.oct)
		      .replace('[novfak]',"十一月")
		      .replace('[nov]',list.nov)
		      .replace('[decsfak]',"十二月")
		      .replace('[decs]',list.decs));
	
}


</script>

