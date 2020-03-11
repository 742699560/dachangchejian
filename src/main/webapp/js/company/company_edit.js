var SUCCESS = 200;
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
	if($('#editCompanyForm').valid()){  
		var params = getFormParams();
		if(params=='nochoose'){
			sweetAlert('请选择用户角色！');
			return false;
		}
		var cid = $('#container').data('cid');
		params.cid = cid;
		var url = cid?'company/doUpdateCompany.do':'company/doSaveCompany.do';
		$.post(url,params,function(result){
			if(result.state==SUCCESS){
				sweetAlert('操作成功！');
				clearData();
				$('#container').load('company/companyUI.do');
			}else{
				alert(result.message);
			}
		})
	}
}

//获取表单参数
function getFormParams(){
	var cname = $('#companyName').val();
	var legal = $('#companyLegal').val();
	var serialnumber = $('#serialnumber').val();
	var contats = $('#contats').val();
	var contactnumber = $('#contactnumber').val();
	var site = $('#site').val();
	var type = $('#type').val();
	var lng = $('#lng').val();
	var lat = $('#lat').val();

	var params = {
			'cname':cname,
			'legal':legal,
			'serialnumber':serialnumber,
			'contats':contats,
			'site':site,
			'contactnumber':contactnumber,
			'type':type,
			'lng':lng,
			'lat':lat
	}
	return params;
}

//点击返回按钮
function gobackUserList(){
	clearData();
	$('#container').load('company/companyUI.do');
}

//设置按钮文字
function setBtnVal(){
	var cid = $('#container').data('cid');
	if(cid){
		$('#btn_ok').val('修改');
		$('#editTitle').text('修改');
		findUserById(cid);   //根据id查询用户信息
		
	}else{
		$('#btn_ok').val('保存');
		$('#editTitle').text('新增');
		point = new BMap.Point(117.123618,39.980269); 
	}
}

function findUserById(cid){
	var param  = {'cid':cid};
	var url = 'company/findCompanyById.do';
	$.post(url,param,function(result){
		if(result.state==SUCCESS){
			loadEditUserForm(result.data);  //回显
			alllng=$('#lng').val();
			alllat=$('#lat').val();
			var cname = $('#companyName').val();
			var site = $('#site').val();
			var point = new BMap.Point(alllng,alllat);
			map.centerAndZoom(point,12); 
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.centerAndZoom(point, 15);
			var opts = {
					width : 100,     // 信息窗口宽度
					height: 50,     // 信息窗口高度
					title : cname , // 信息窗口标题
					enableMessage:true,//设置允许信息窗发送短息
					message:""
			}
			
			var infoWindow = new BMap.InfoWindow(site, opts);  // 创建信息窗口对象 
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
	$('#companyName').val(data.cname);
	$('#companyLegal').val(data.legal);
	$('#serialnumber').val(data.serialnumber);
	$('#contats').val(data.contats);
	$('#contactnumber').val(data.contactnumber);
	$('#site').val(data.site);
	$('#type').val(data.type);
	$('#lng').val(data.lng);
	$('#lat').val(data.lat);
}

//点击返回，保存，修改按钮，清除editForm数据
function clearData(){
	$('#editCompanyForm .dynamicClear').val('');
	$('#container').data('cid','');
}
//百度地图API功能
var map = new BMap.Map("allmap");
map.enableScrollWheelZoom(); 
map.enableContinuousZoom();
map.centerAndZoom(point,12); 

/*var comfordata = $('#lat').val();

// 创建地址解析器实例
var myGeo = new BMap.Geocoder();
// 将地址解析结果显示在地图上,并调整地图视野
myGeo.getPoint(comfordata, function(point){
	if (point) {
		map.centerAndZoom(point, 16);
		map.addOverlay(new BMap.Marker(point));
	}else{
		alert("您选择地址没有解析到结果!");
	}
}, "三河市");

*/
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






