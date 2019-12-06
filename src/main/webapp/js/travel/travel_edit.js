var SUCCESS = 1;
//设置按钮的文字,以及编辑框的标题
setBtnVal();
//点击返回按钮
$('#btn_return').click(gobackUserList);
//点击保存/修改按钮
$('#btn_ok').click(commitUserForm);

var alllng = null;
var alllat = null;
var point;
//点击保存/修改按钮
function commitUserForm(){
	if($('#editTravelForm').valid()){  
		var params = getFormParams();
		if(params=='nochoose'){
			sweetAlert('请选择用户角色！');
			return false;
		}
		var tid = $('#container').data('tid');
		params.tid = tid;
		var url = tid?'travel/doUpdateTravel.do':'travel/doSaveTravel.do';
		$.post(url,params,function(result){
			if(result.state==SUCCESS){
				sweetAlert('操作成功！');
				clearData();
				$('#container').load('travel/travelUI.do');
			}else{
				alert(result.message);
			}
		})
	}
}

//获取表单参数
function getFormParams(){
	var tname = $('#tname').val();
	var stime = $('#stime').val();
	console.log(stime)
	var legal = $('#legal').val();
	var contats = $('#contats').val();
	var tcode = $('#tcode').val();
	var phone = $('#phone').val();
	var address = $('#address').val();
	var lng = $('#lng').val();
	var lat = $('#lat').val();
	var user = $('#user').val();
	var passwd = $('#passwd').val();
/*	var num = $('#num').val();*/
	var expire = $('#expire').val();
	var params = {
			'tname':tname,
			'legal':legal,
			'stime':stime,
			'contats':contats,
			'tcode':tcode,
			'phone':phone,
			'address':address,
			'lng':lng,
			'lat':lat,
			'user':user,
/*			'num':num,*/
			'expire':expire,
			'passwd':passwd
	}
	return params;
}

//点击返回按钮
function gobackUserList(){
	clearData();
	$('#container').load('travel/travelUI.do');
}

//设置按钮文字
function setBtnVal(){
	var tid = $('#container').data('tid');
	if(tid){
		$('#btn_ok').val('修改');
		$('#editTitle').text('修改');
		findUserById(tid);   //根据id查询用户信息
		
	}else{
		$('#btn_ok').val('保存');
		$('#editTitle').text('新增');
		point = new BMap.Point(117.123618,39.980269); 
	}
}

function findUserById(tid){
	var param  = {'tid':tid};
	var url = 'travel/findTravelById.do';
	$.post(url,param,function(result){
		if(result.state==SUCCESS){
			loadEditUserForm(result.data);  //回显
			alllng=$('#lng').val();
			alllat=$('#lat').val();
			var tname = $('#tname').val();
			var address = $('#address').val();
			var point = new BMap.Point(alllng,alllat);
			map.centerAndZoom(point,12); 
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.centerAndZoom(point, 15);
			var opts = {
					width : 100,     // 信息窗口宽度
					height: 50,     // 信息窗口高度
					title : tname , // 信息窗口标题
					enableMessage:true,//设置允许信息窗发送短息
					message:""
			}
			
			var infoWindow = new BMap.InfoWindow(address, opts);  // 创建信息窗口对象 
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
	$('#tname').val(data.tname);
	$('#legal').val(data.legal);
	$('#stime').val(data.stime);
	$('#contats').val(data.contats);
	$('#tcode').val(data.tcode);
	$('#phone').val(data.phone);
	$('#address').val(data.address);
	$('#lng').val(data.lng);
	$('#lat').val(data.lat);
	$('#user').val(data.user);
/*	$('#num').val(data.num);*/
	$('#passwd').val(data.passwd);
	$('#expire').val(data.expire);
}

//点击返回，保存，修改按钮，清除editForm数据
function clearData(){
	$('#editTravelForm .dynamicClear').val('');
	$('#container').data('tid','');
}
//百度地图API功能
var map = new BMap.Map("allmap");
map.enableScrollWheelZoom(); 
map.enableContinuousZoom();
map.centerAndZoom(point,12);     
//单击获取点击的经纬度
map.addEventListener("click",function(e){
	$('#lng').val(e.point.lng);
	$('#lat').val(e.point.lat);
	var marker = new BMap.Marker(new BMap.Point(e.point.lng,e.point.lat));
	marker.addEventListener("click",attribute);
});
function attribute(){
	var p = marker.getPosition();  //获取marker的位置
	alert("marker的位置是" + p.lng + "," + p.lat);    
}






