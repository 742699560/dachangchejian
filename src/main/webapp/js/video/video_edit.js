var SUCCESS = 200;
setBtnVal();
$('#btn_return').click(gobackUserList);
$('#btn_ok').click(commitUserForm);
$("#editTravelForm").on("click",".btn-add",showEditDialog);
$('#projectList').on('click','.doVideoUpdate',showEditDialog);
$('#projectList').on('click','.doVideodelete',doVideodelete);
var alllng = null;
var alllat = null;
var point;

function doVideodelete(){
	swal("这是一条信息！")
	var cid = $(this).parent().parent().parent().data("cid");
	var param = {'cid':cid};
	var url = 'camera/deleteCamera.do';
	swal({
		title: "是否确定删除", 
		text: "将无法恢复该数据！", 
		type: "warning",
		showCancelButton: true, 
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "确定！", 
		cancelButtonText: "取消！",
		closeOnConfirm: false, 
		closeOnCancel: false	
	},function(isConfirm){
		 if (isConfirm) { 
			  swal({title:"删除成功！",  
                 text:"您已经永久删除了这条信息。",  
                 type:"success"},function(){
               	  $.post(url,param,function(result){ 
               		doGetObjects();
               		})
			    })
			  } else { 
			    swal("取消！", "已取消删除。","success"); 
			}
	
	
	});
}

function showEditDialog(){
	var url="camera/editUI.do";
	var title;
	if($(this).hasClass("btn-add")){
		title="添加摄像头"
	}
	if($(this).hasClass("doVideoUpdate")){
		title="修改摄像头"
		$("#modal-dialog").data("cid",$(this).parent().parent().parent().data("cid"));
	}
	$("#modal-dialog .modal-body").load(url,
			function(){
		 $(".modal-title").html(title);
		 $("#modal-dialog").modal("show");
	})
	
}

function doGetObjects(){
	  var url="camera/findPageObjects.do";
	  var params=getQueryFormData();
	  var pageCurrent=$("#pageId").data("pageCurrent");
	  if(pageCurrent){params.pageCurrent=pageCurrent};
	  $.post(url,params,function(result){
		 console.log(JSON.stringify(result.data.list));
		 if(result.state==1){
		 setTableRows(result.data.list);
		setPagination(result.data.pageObject);
		}else{
			 sweetAlert(result.message);
		} 
	  });
	}
function getQueryFormData(){
	var params={
	"vid":$('#container').data('vid'),
	}
	return params;
}
function setTableRows(list){
	 var tBody=$('#tbodyId');
	 tBody.empty();
	 var temp='<td>[tcode]</td>'
	       +'<td>[cname]</td>'
	       +'<td>[nChannel]</td>'
	       +'<td>[brand]</td>'
	       +'<td>[model]</td>'
	       +'<td><div class="hidden-sm hidden-xs action-buttons"><a class="green doVideoUpdate"><i class="ace-icon fa fa-pencil bigger-130"></i></a>'
	       +' &nbsp;&nbsp;<a class="red doVideodelete" id="btn-delect"><i class="ace-icon fa fa-trash-o bigger-130"></i></a></div></td>'
		 for(var i in list){
		 var tr=$('<tr id="comid'+list[i].cid+'"></tr>');
		 tr.data("cid",list[i].cid);				 
         tr.append(temp
        .replace('[cname]',list[i].cname)		        
        .replace('[nChannel]',list[i].nChannel)
        .replace('[model]',list[i].model)
        .replace('[brand]',list[i].brand)
        .replace('[tcode]',list[i].tcode)
         );
        tBody.append(tr);
		 }
}

//点击保存/修改按钮
function commitUserForm(){
	if($('#editTravelForm').valid()){  
		var params = getFormParams();
		if(params=='nochoose'){
			sweetAlert('请选择用户角色！');
			return false;
		}
		var vid = $('#container').data('vid');
		params.vid = vid;
		var url = vid?'video/doUpdateVideo.do':'video/doSaveVideo.do';
		$.post(url,params,function(result){
			if(result.state==SUCCESS){
				sweetAlert('操作成功！');
				clearData();
				$('#container').load('video/videoUI.do');
			}else{
				sweetAlert(result.message);
			}
		})
	}
}

//获取表单参数
function getFormParams(){
	var vname = $('#vname').val();
	var tcode = $('#tcode').val();
	var type = $('#type').val();
	var vport = $('#vport').val();
	var longitude = $('#longitude').val();
	var latitude = $('#latitude').val();
	var dvrIP = $('#dvrIP').val();
	var userName = $('#userName').val();
	var params = {
			'vname':vname,
			'tcode':tcode,
			'type':type,
			'vport':vport,
			'tcode':tcode,
			'longitude':longitude,
			'latitude':latitude,
			'dvrIP':dvrIP,
			'userName':userName
	}
	return params;
}

function commitaddForm(){
	$('#container').load('video/videoUI.do');
}


//点击返回按钮
function gobackUserList(){
	clearData();
	$('#container').load('video/videoUI.do');
}

//设置按钮文字
function setBtnVal(){
	var vid = $('#container').data('vid');
	if(vid){
		$('#btn_ok').val('修改');
		$('#editTitle').text('修改');
		findUserById(vid);   //根据id查询用户信息
		doGetObjects();
		
	}else{
		$('#btn_ok').val('保存');
		$('#editTitle').text('新增');
		point = new BMap.Point(117.123618,39.980269); 
	}
}

function findUserById(vid){
	var param  = {'vid':vid};
	var url = 'video/findVideoById.do';
	$.post(url,param,function(result){
		if(result.state==SUCCESS){
			loadEditUserForm(result.data);  //回显
			alllng=$('#longitude').val();
			alllat=$('#latitude').val();
			var vname = $('#vname').val();
			var dvrIP = $('#dvrIP').val();
			var point = new BMap.Point(alllng,alllat);
			map.centerAndZoom(point,12); 
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.centerAndZoom(point, 15);
			var opts = {
					width : 100,     // 信息窗口宽度
					height: 50,     // 信息窗口高度
					title : vname , // 信息窗口标题
					enableMessage:true,//设置允许信息窗发送短息
					message:""
			}
			
			var infoWindow = new BMap.InfoWindow(dvrIP, opts);  // 创建信息窗口对象 
			marker.addEventListener("click", function(){          
				map.openInfoWindow(infoWindow,point); //开启信息窗口
			});
					
		}else{
			sweetAlert(result.message);
		}
	})
}
//回显
function loadEditUserForm(data){
	$('#vname').val(data.vname);
	$('#tcode').val(data.tcode);
	$('#type').val(data.type);
	$('#vport').val(data.vport);
	$('#tcode').val(data.tcode);
	$('#longitude').val(data.longitude);
	$('#latitude').val(data.latitude);
	$('#dvrIP').val(data.dvrIP);
	$('#userName').val(data.userName);
}

//点击返回，保存，修改按钮，清除editForm数据
function clearData(){
	$('#editTravelForm .dynamicClear').val('');
	$('#container').data('vid','');
}
//百度地图API功能
var map = new BMap.Map("allmap");
map.enableScrollWheelZoom(); 
map.enableContinuousZoom();
map.centerAndZoom(point,12);     
//单击获取点击的经纬度
map.addEventListener("click",function(e){
	$('#longitude').val(e.point.lng);
	$('#latitude').val(e.point.lat);
	var marker = new BMap.Marker(new BMap.Point(e.point.lng,e.point.lat));
	
	marker.addEventListener("click",attribute);

});
function attribute(){
	var p = marker.getPosition();  //获取marker的位置
	sweetAlert("marker的位置是" + p.longitude + "," + p.latitude);
}

$(document).ready(function(){
	$("#pageId").on('click','.pre,.next',jumpToPage);
});
//设置分页
function setPagination(pageObject){
 //绑定总页数
 $("#pageId").data("pageCount",pageObject.pageCount);
 //绑定当前页面
 $("#pageId").data("pageCurrent",pageObject.pageCurrent);
}
//跳转到下一页
function jumpToPage(){
	//获得点击对象上class属性对应的值
	var clazz=$(this).attr("class");
	//获得pageId对象上绑定的pageCurrent对应的值
	var pageCurrent=$('#pageId').data("pageCurrent");
	//获得pageId对象上绑定的pageCount对应的值
	var pageCount=$('#pageId').data("pageCount")
	//判断点击的是否是上一页
	if(clazz=='pre'&&pageCurrent>1){
		pageCurrent--;
	}
	//判断点击的是否是下一页
	if(clazz=="next"&&pageCurrent<pageCount){
		pageCurrent++;
	}
	//重写绑定pageCurrent的值,
	$('#pageId').data("pageCurrent",pageCurrent);
	//重新执行查询操作
	doGetObjects();
}






