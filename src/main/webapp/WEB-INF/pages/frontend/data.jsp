<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
// 获得项目完全路径（假设你的项目叫myWork，那么获得到的地址就是 http://localhost:8080/myWork/）:
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// 将 "项目路径basePath" 放入pageContext中
%>

<!DOCTYPE html>
<html  lang="zh-CN">
<head>

    <meta charset="utf-8">
    <title>三河市旅游管理平台</title>
    <base href="<%=basePath%>" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache,must-revalidate" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="Content-Type" content="no-cache, must-revalidate" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="renderer" content="webkit"> 

	<link type="image/png" rel="shortcut icon" href="frontend/images/logo.png"/>
    <link rel="stylesheet" type="text/css" href="frontend/css/global.css"/>

    
    <style>

        .anchorBL{
            display:none;
        }
        
        .datagrid-mask-m{
            position: absolute;
            left: 0;
            top: 0;
            background-color:transparent;
        }
        
        .dataCharts td{
         	border:0;
         	padding:10px;
        }
        
        .fullInherit{
	       	 width:100%;
	       	 height:100%;
	       	 background-color: rgba(255,255,255,0.1);
        }
       
      
		.areaLayout li:first-child{
		   height:20px;
		   width:100%;
		   border:0;
		   border-left:1px solid #80ACF9;
		   border-bottom: 1px solid #80ACF9;
		   position: relative;
		}
		
		.areaLayout li:first-child div{
			position: absolute;
			z-index: 10;
			height:40px;
			width:auto;
			left:0;
			bottom:0;
		}
		
		.areaLayout li:first-child div img{
			width:40px;
			height:100%;
			float: left;
			margin: 0 9px
		}
		
		.areaLayout li:first-child div h5{
		display:inline-block;
			width:auto;
			height:100%;
			color:#BCCBE8;
			float: left;
			font-size: 17px;
			font-weight: normal;
			vertical-align: middle;
			line-height: 48px;
			letter-spacing: 2px
		}
		
		.areaLayout li:nth-child(2){
			  width:100%;
			  margin-top:7px;
			  position: relative;
		}
		
		.fontTag{
		   border:0;
		   position: absolute;
		   width:30px;
		   height:30px;
		   z-index:-1;
		   border-style: solid;
		   border-color: #80ACF9;
		}
		
		.fontTagTopLeft{
			top:-1px;
			left:-1px;
			border-top-width:1px;
			border-left-width:1px;
		}
		
		.fontTagTopRight{
			top:-1px;
			right:-1px;
			border-top-width:1px;
			border-right-width:1px;
		}
		
		.fontTagBottomLeft{
			bottom:-1px;
			left:-1px;
			border-bottom-width:1px;
			border-left-width:1px;
		}
		
		.fontTagBottomRight{
			bottom:-1px;
			right:-1px;
			border-bottom-width:1px;
			border-right-width:1px;
		}
		
		body pre{
			color:white !important;
		}


		.loadingDiv{
		
			position: absolute;
			left: 0;
			top: 0;
			width: 100%;
			height: 100%;
			overflow: hidden;
			z-index: 2147483647;
			background-color:rgba(255,255,255,0.1);
			cursor:url('frontend/images/thinking.ani'),wait;
			
		}
		
		.loadingImg{
		
			position:absolute;
			left:47.6%;
			top:44.2%;
			display:block;
            background-attachment: scroll;
		    background-clip: border-box;
		    background-position: center center;
		    background-repeat: no-repeat;
		    background-size:contain;
		    width:39px;
			height:39px;
		    background-image: url("frontend/images/loading.gif");
            background-color:transparent;
            
		}
		
    </style>
</head>

<body style="background-color:transparent;position: relative;padding: 10px">


 	
<!-- 上面部分得到chart -->
<table style="width: 100%;" border="0" class="dataCharts" >

    <tr >
    
        <!-- 三河市分布图 -->
        <td rowspan="2"   align="center"  style="height:668px;width:66%">
            <ul class="areaLayout">
               <li>
                    <div>
                    		<img src="frontend/images/fbchartsIco.png" class="imgrotate"/>
               				<h5>三河市旅游点分布图</h5>
                    </div>
               </li>
               <li style="height:648px;">
               
               		<div class="loadingDiv fbChartLoad">
					 		<div  class="loadingImg"></div>
					</div>
               
                    <div class="fontTag fontTagTopLeft "></div>
                    <div class="fontTag fontTagTopRight "></div>
                    <div class="fontTag fontTagBottomLeft "></div>
                    <div class="fontTag fontTagBottomRight "></div>
                    
               		<div id="fbChart" class="fullInherit" ></div>
               </li>
            </ul>
            
        </td>
        
        <!-- 柱状图 -->
        <td  align="center" style="height: 334px;width:34%">
        	 <ul class="areaLayout">
               <li>
                    <div>
                    		<img src="frontend/images/zzchartsIco.png" class="imgrotate" style="width:35px;height:35px;position: relative;top:4px;"/>
               				<h5>旅游点总数柱状图</h5>
                    </div>
               </li>
               <li style="height:294px;">
               
               		<div class="loadingDiv zzChartLoad">
					 		<div  class="loadingImg"></div>
					</div>
					
                    <div class="fontTag fontTagTopLeft "></div>
                    <div class="fontTag fontTagTopRight "></div>
                    <div class="fontTag fontTagBottomLeft "></div>
                    <div class="fontTag fontTagBottomRight "></div>
                    
            		<div id="zzChart" class="fullInherit"></div>
               </li>
            </ul>
        </td>
        
    </tr>
    
    <tr >
        
          <!-- 饼状图 -->
        <td  align="center" style="height: 334px;width:34%">
        	<ul class="areaLayout">
               <li>
                    <div>
                    		<img src="frontend/images/bzchartsIco.png" class="imgrotate" style="width:32px;height:32px;position: relative;top:4px;"/>
               				<h5>旅游点总数占比饼图</h5>
                    </div>
               </li>
               <li  style="height:294px;">
               
               		<div class="loadingDiv bzChartLoad">
					 		<div  class="loadingImg"></div>
					</div>
					
                    <div class="fontTag fontTagTopLeft "></div>
                    <div class="fontTag fontTagTopRight "></div>
                    <div class="fontTag fontTagBottomLeft "></div>
                    <div class="fontTag fontTagBottomRight "></div>
                    
          			<div id="bzChart" class="fullInherit"></div>
               </li>
            </ul>
        </td>
        
    </tr>
    
    <tr>
         <!-- 折现统计图 -->
        <td  align="center" colspan="2" style="height: 334px;width:100%;position: relative;">
        		
        		<ul class="areaLayout">
	               <li>
	                    <div>
	                    		<img src="frontend/images/zxchartsIco.png" class="imgrotate"/>
	               				<h5><span id="curData">XXXX年 XX月</span>前  - 报警统计折线图</h5>        			           
	                    </div>
	                   
	               </li>
	               <li  style="height:314px;">
	               
	               		<div class="loadingDiv zxChartLoad">
					 		<div  class="loadingImg"></div>
						</div>
					
	                    <div class="fontTag fontTagTopLeft "></div>
	                    <div class="fontTag fontTagTopRight "></div>
	                    <div class="fontTag fontTagBottomLeft "></div>
	                    <div class="fontTag fontTagBottomRight "></div>
               	 		<div id="zxChart" class="fullInherit"></div>
	               </li>
	            </ul>

        </td>    	
    </tr>
    
</table>


<script type="text/javascript" src="frontend/js/jquery.min.js"></script>
<!-- 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZoqWrRKN1PYg60hW5gMH1NOd1vUFgk52"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script> 
-->
<script type="text/javascript" src="frontend/js/echarts.js"></script>
<script type="text/javascript" src="frontend/js/bmap.js?v=1"></script>
<script type="text/javascript" src="frontend/js/util.js"></script>
<script type="text/javascript" src="frontend/js/dark.js"></script>
<!-- 初始化数据    -->
<script>

    $(document).ready(function(){
    	setLoadingPage();

    	//异步加载百度脚本
        $.ajax({
    	    url:"http://api.map.baidu.com/api",
    	    data:{v:"2.0",ak:"YNlx4aWosE7KWvnAGLRkDzdv",callback:"setfbChart"},
    	    scriptCharset:"utf-8",
    	    dataType:"script",
    	    timeout:10000,
    	    complete:function(XHR,TS){
    	        if(TS=="timeout"){
    	        	XHR.abort();
    	        	console.error("地图资源获取超时!");
    	        	 $(".fbChartLoad").fadeOut('fast');
    	        }else if(TS=="error"){
    	        	console.error("地图资源获取异常!");
    	        	 $(".fbChartLoad").fadeOut('fast');
    	        }
    	    }
        });
    	
    	/* 分布图  */
    	//setfbChart();
    	
        /* 饼状图 */
        setbzChart();
        /* 柱状图 */
        setzzChart();
        /* 折线图 */
        setzxChart();

        
    });
    
    $(window).load(function(){
    	$(".loadingDiv").fadeOut('fast');
    });
    
    

</script>

<!-- 统计图js -->
<script type="text/javascript">

var bmap = null;
var fbChart = null;
/* 分布图设置 */
function setfbChart(){
	$.getScript("http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js");
    /* 初始化echarts */
    fbChart = echarts.init($("#fbChart")[0],'dark');
    var fbChartOptions = {
            tooltip : {
                trigger: 'item',
                formatter:"{b}"
            },
            toolbox: {
                feature: {
                     dataView: {
                    	 readOnly: true,
                    	 title : '数据'
                     }
                }
            },
            legend: {
                orient : 'horizontal',
                x : 5,
                y : 5,
                data:["旅游局","旅行社","酒店","景区"]
            },
            bmap: {
                mapStyle: {style:'hardedge'}
            }
    }
    
    var seriesArr = new Array();
    var lyj = {

    		name: '旅游局',
            type: 'effectScatter',
            coordinateSystem: 'bmap',
            symbol:"pin",
            symbolSize: function (val) {
                return 30;
            },
            data: [
                   {"name":"三河市旅游局 (廊坊 鼎盛东大街312 ) ","value":[117.075615,39.992008]}
            ],
            showEffectOn: 'emphasis',
            rippleEffect: {
                brushType: 'stroke'
            },
            zlevel:10,
            z:10,
            hoverAnimation: true,
            itemStyle: {
                normal: {
                    color: '#DD4B39',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            zlevel: 1
            
    };
    seriesArr.push(lyj);

    var lxs = {
    		
            name: '旅行社',
            type: 'effectScatter',
            coordinateSystem: 'bmap',
            symbolSize: function (val) {
                return 20;
            },
            data:${requestScope.lxsData},
            showEffectOn: 'emphasis',
            rippleEffect: {
                brushType: 'stroke'
            },
            symbol:"pin",
            hoverAnimation: true,
            itemStyle: {
                normal: {
                    color: '#EC971F',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            zlevel: 1
		
	};
	seriesArr.push(lxs);
	
	var hotel = {
    		
            name: '酒店',
            type: 'effectScatter',
            coordinateSystem: 'bmap',
            symbolSize: function (val) {
                return 20;
            },
            symbol:"pin",
            data:${requestScope.hotelData},
            showEffectOn: 'emphasis',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            itemStyle: {
                normal: {
                    color: '#00C0EF',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            zlevel: 1
		
	};
	seriesArr.push(hotel);

    var lyjd = {

            name: '景区',
            type: 'effectScatter',
            coordinateSystem: 'bmap',
            symbolSize: function (val) {
                return 20;
            },
            symbol:"pin",
            data:${requestScope.lyjdData},
            showEffectOn: 'emphasis',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            itemStyle: {
                normal: {
                    color: '#00A65A',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            zlevel: 1
            
    };
    seriesArr.push(lyjd);
    fbChartOptions['series'] = seriesArr;
    /* 设置相应参数 */
    fbChart.setOption(fbChartOptions);
	/* 获取地图对象 */
	bmap = fbChart.getModel().getComponent('bmap').getBMap();
	handleMap();
}

	var lxsNum = ${zzChartData.lxs};
	var hotelNum = ${zzChartData.hotel};
	var lyjdNum = ${zzChartData.lyjd};

   function setbzChart(){
	   
	   var bzChart = echarts.init($('#bzChart')[0],'dark');
       var bzChartOption = {

               tooltip : {
                   trigger: 'item',
                   formatter: "{a} <br/>{b} : {c} ({d}%)"
               },
               toolbox: {
                   feature: {
                       dataView: {
                    	   readOnly: true
                       },
                       saveAsImage: {
                     	  backgroundColor: 'rgb(52,62,118)'
                        },
                       restore: {},
                   }
               },
               legend: {
                   orient : 'vertical',
                   x : 5,
                   y : 5,
                   data:['旅行社',"酒店",'景区']
               },
               calculable : true,
               series : [
                   {
                       name:'占比',
                       type:'pie',
                       radius : '70%',
                       center: ['60%', '55%'],
                       data:[
                           {value:lxsNum, name:'旅行社'},
                           {value:hotelNum, name:'酒店'},
                           {value:lyjdNum, name:'景区'}
                       ]
                   }]
           };
       	bzChart.setOption(bzChartOption);
        $(".bzChartLoad").fadeOut('fast');
   }
   
   function setzzChart(){
	   
	   var zzChart = echarts.init($('#zzChart')[0],'dark');
       var zzChartOption = {
               tooltip : {
                   trigger: 'axis',
                  　 			   textStyle:{
                       align:'left'
                   }
               },
               toolbox: {
                   feature: {
                	   magicType: {type: ['stack']},
                       dataView: {
                    	   readOnly: true
                       },
                       saveAsImage: {
                     	  backgroundColor: 'rgb(52,62,118)'
                        },
                       restore: {},
                   }
               },
               legend: {
                   data:['旅行社',"酒店",'景区'],
                   x : 5,
                   y : 5
               },
               calculable : true,
               xAxis : [
                   {
                       type : 'category',
                       data : ['总数']
                   }
               ],
               yAxis : [
                        {
							name : "( 个数 )",
                            type : 'value',
                        }
                    ],
               series : [
                   {
                       name:'旅行社',
                       type:'bar',
                       data:[lxsNum],
                       label: {
                           normal: {
                               show: true,
                               position: 'top'
                           }
                       }
                   },
                   {
                       name:'酒店',
                       type:'bar',
                       data:[hotelNum],
                       label: {
                           normal: {
                               show: true,
                               position: 'top'
                           }
                       }
                   },
                   {
                       name:'景区',
                       type:'bar',
                       data:[lyjdNum],
                       label: {
                           normal: {
                               show: true,
                               position: 'top'
                           }
                       }
                   }
               ]
           };
       
       zzChart.setOption(zzChartOption);
       $(".zzChartLoad").fadeOut('fast');
   }

   function setzxChart(){
	   $("#curData").text("${zxChartData.curData}");
	   var zxChart = echarts.init($('#zxChart')[0],'dark');
       var zxChartOption = {
    		   color: [
   		            '#00A65A',
   		            '#EC971F',
   		            '#DD4B39'
   		        ],
               tooltip : {
                   trigger: 'axis',
                   axisPointer: {
                       type: 'cross',
                       label: {
                           backgroundColor: '#6a7985'
                       }
                   }
               },
               grid: {
                   left: '3%',
                   right: '4%',
                   bottom: '3%',
                   containLabel: true
               },
               toolbox: {
                   feature: {
                	   /* dataZoom: {
                           yAxisIndex: 'none',
                           title : {
                               dataZoom : '区域缩放',
                               dataZoomReset : '区域缩放-后退'
                           }
                       }, */
                       magicType: {type: ['bar']},
                       dataView: {
                    	   readOnly: true,
                    	   optionToContent: function(opt) {
                               var axisData = opt.xAxis[0].data;
                               var series = opt.series;
                               var table = '<table style="width:100%;text-align:center"><tbody><tr>'
                                            + '<td>时间</td>'
                                            + '<td>' + series[0].name + '</td>'
                                            + '<td>' + series[1].name + '</td>'
                                            + '<td>' + series[2].name + '</td>'
                                            + '</tr>';
                               for (var i = 0, l = axisData.length; i < l; i++) {
                                   table += '<tr>'
                                            + '<td>' + axisData[i] + '</td>'
                                            + '<td>' + series[0].data[i] + '</td>'
                                            + '<td>' + series[1].data[i] + '</td>'
                                            + '<td>' + series[2].data[i] + '</td>'
                                            + '</tr>';
                               }
                               table += '</tbody></table>';
                               return table;
                           }
                       },
                       saveAsImage: {
                     	  backgroundColor: 'rgb(52,62,118)',
                     	 show : true,
                         title : '保存为图片',
                         type : 'jpeg',
                         lang : ['点击本地保存'] 
                        },
                       restore: {},
                   }
               },
               /* dataZoom: [ {
                   show: true
               },
               {
                   type: 'inside'
               }], */
               legend: {
                   data:['一般','严重','非常严重'],
                   y : 5,
                   x : 'center',
                   selectedMode: 'single'
               },
               calculable : true,
               xAxis : [
                   {
                       type : 'category',
                       boundaryGap : false,
                       data : ${zxChartData.warnData.monthData}.map(function (str) {
                           return str.replace(' ', '\n')
                       })
                   }
               ],
               yAxis : [
                   {
                       type : 'value',
                   	   axisLabel: {
                              formatter: '{value} 条'
                       },
                       scale : true,
                       show : true,
                       splitLine:{
                    	   show:true
                       },
                       position : 'left'
                   }
               ],
               series : [
                   {
                       name:'一般',
                       type:'line',
                       stack: '总量',
                       data: ${zxChartData.warnData.normalData},
				       showAllSymbol:true,
                       smooth: true,
                       symbol: 'triangle',
                       symbolSize: 10,
                       zlevel:10,
                       z:10,
                       areaStyle: {normal: {}},
                       markPoint: {
                           data: [
                               {type: 'max', name: '最大值', xAxis: 1, yAxis: -1.5},
                               {type: 'min', name: '最小值', xAxis: 1, yAxis: -1.5}
                           ]
                       }
                   },
                   {
                       name:'严重',
                       type:'line',
                       stack: '总量',
                       data: ${zxChartData.warnData.seriousData},
				       showAllSymbol:true,
                       smooth: true,
                       symbol: 'triangle',
                       zlevel:11,
                       z:11,
                       symbolSize: 10,
                       areaStyle: {normal: {}},
                       markPoint: {
                           data: [
                               {type: 'max', name: '最大值', xAxis: 1, yAxis: -1.5},
                               {type: 'min', name: '最小值', xAxis: 1, yAxis: -1.5}
                           ]
                       }
                   },
                   {
                       name:'非常严重',
                       type:'line',
                       stack: '总量',
                       data: ${zxChartData.warnData.errorData},
                       smooth: true,
     				    showAllSymbol:true,
     				   zlevel:12,
     		            z:12,
                       symbol: 'triangle',
                       symbolSize: 10,
                       areaStyle: {normal: {}},
                       markPoint: {
                           data: [
                               {type: 'max', name: '最大值', xAxis: 1, yAxis: -1.5},
                               {type: 'min', name: '最小值', xAxis: 1, yAxis: -1.5}
                           ]
                       }
           
                   }
               ]
           };
           zxChart.setOption(zxChartOption);
           $(".zxChartLoad").fadeOut('fast');
   }
   
</script>

<!-- 处理函数   -->
<script type="text/javascript">
   
   
   			function handleMap(){
   				
 			  	//设置样式
 			     bmap.setMapStyle({
 			        styleJson: [
 			            {//不显示点信息
 			                "featureType": "poi",
 			                "elementType": "all",
 			                "stylers": {
 			                    "color": "#ffffff",
 			                    "visibility": "off"
 			                }
 			            }
 			        ]
 			    }); 
   			  	
   			bmap.centerAndZoom(new BMap.Point(117.017323, 39.952711),12);
   			bmap.disableDragging();//禁止拖动
   			bmap.disableDoubleClickZoom();//禁止双击缩放
 			setPol("三河市");
   			 	
   			}
   			
   			var blist = [];
   			var districtLoading = 0;
			function setPol(districtName){
				
				//使用计数器来控制加载过程
			    districtLoading++;
			    var bdary = new BMap.Boundary();
			    bdary.get(districtName, function (rs) {       //获取行政区域
			        var count = rs.boundaries.length; //行政区域的点有多少个
			        if (count === 0) {
			            alert('未能获取当前输入行政区域');
			            return;
			        }
			        for (var i = 0; i < count; i++) {
			            blist.push({ points: rs.boundaries[i], name: districtName });
			        };
			        //加载完成区域点后计数器-1
			        districtLoading--;
			        if (districtLoading == 0) {
			            //全加载完成后画端点
			            drawBoundary();
			        }
			    });
			    
			}
	

			function drawBoundary() {
				
			    //包含所有区域的点数组
			    var pointArray = [];
			    /*画遮蔽层的相关方法
			    *思路: 首先在中国地图最外画一圈，圈住理论上所有的中国领土，然后再将每个闭合区域合并进来，并全部连到西北角。
			    *      这样就做出了一个经过多次西北角的闭合多边形*/
			    //定义中国东南西北端点，作为第一层
			    var pNW = { lat: 59.0, lng: 73.0 }
			    var pNE = { lat: 59.0, lng: 136.0 }
			    var pSE = { lat: 3.0, lng: 136.0 }
			    var pSW = { lat: 3.0, lng: 73.0 }
			    //向数组中添加一次闭合多边形，并将西北角再加一次作为之后画闭合区域的起点
			    var pArray = [];
			    pArray.push(pNW);
			    pArray.push(pSW);
			    pArray.push(pSE);
			    pArray.push(pNE);
			    pArray.push(pNW);
			    
			    //循环添加各闭合区域
			    for (var i = 0; i < blist.length; i++) {
			    	
			        //添加多边形层并显示
			        var ply = new BMap.Polygon(blist[i].points, { strokeWeight:2, strokeColor: "#658BD0", fillOpacity: 0.01, fillColor: "#FFFFFF" }); //建立多边形覆盖物
			        ply.name = blist[i].name;


			        //将点增加到视野范围内
			        var path = ply.getPath();
			        pointArray = pointArray.concat(path);
			        //将闭合区域加到遮蔽层上，每次添加完后要再加一次西北角作为下次添加的起点和最后一次的终点
			        pArray = pArray.concat(path);
			        pArray.push(pArray[0]);
			        
			    }


			   
			    //添加遮蔽层
			    var plyall = new BMap.Polygon(pArray, { strokeOpacity: 0.0000001, strokeColor: "#000000", strokeWeight: 0.00001, fillColor: "#3349A1", fillOpacity:1}); //建立多边形覆盖物
			    bmap.addOverlay(plyall);
		        bmap.addOverlay(ply);
		        
		        $(".fbChartLoad").fadeOut('fast');
			    
			}
			
			
			 function setLoadingPage(){
				 
				    var pli = $(".loadingDiv").parent("li");
				    
				    $.each(pli,function(i,v){
				    	
				    	var pageHeight = v.clientHeight,pageWidth  = v.clientWidth;
		    		var loadingTop  = pageHeight > 39 ? (pageHeight -39) / 2 : 0,
		    			loadingLeft = pageWidth  > 39 ? (pageWidth  -39) / 2 : 0;
		    			var styleParams = {
		    					"left":loadingLeft+"px",
		    					"top":loadingTop+"px"
		    			}
		    		$(v).find(".loadingImg").css(styleParams);
				    });
		        
		        }


</script>
</body>
</html>