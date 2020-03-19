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
function commitUserForm() {
    if ($('#editCompanyForm').valid()) {
        var params = getFormParams();
        var data = $('#container').data('rowData');
        if(data)
            params.id = data.id;
        var url = params.id ? 'company/doUpdateCompany.do' : 'company/doSaveCompany.do';
        $.post(url, params, function (result) {
            if (result.state == SUCCESS) {
                sweetAlert('操作成功！');
                clearData();
                $('#container').load('company/companyUI.do');
            } else {
                sweetAlert(result.message);
            }
        })
    }
}

//获取表单参数
function getFormParams() {
    var name = $('#name').val();
    var phone = $('#phone').val();
    var address = $('#address').val();
    var contats = $('#contats').val();
    var description = $('#description').val();
    var lng = $('#lng').val();
    var lat = $('#lat').val();

    var params = {
        'name': name,
        'phone': phone,
        'address': address,
        'description':description,
        'contats': contats,
        'lng': lng,
        'lat': lat
    }
    return params;
}

//点击返回按钮
function gobackUserList() {
    clearData();
    $('#container').load('company/companyUI.do');
}

//设置按钮文字
function setBtnVal() {
    var data = $('#container').data('rowData');
    if (data) {
        $('#btn_ok').val('修改');
        $('#editTitle').text('修改');
        loadEditUserForm(data);
        point = new BMap.Point(data.lng, data.lat);
    } else {
        $('#btn_ok').val('保存');
        $('#editTitle').text('新增');
        point = new BMap.Point(116.905114, 39.921127);
    }
}

//回显
function loadEditUserForm(data){
	$('#name').val(data.name);
	$('#phone').val(data.phone);
	$('#address').val(data.address);
	$('#contats').val(data.contats);
	$('#description').val(data.description);
	$('#lng').val(data.lng);
	$('#lat').val(data.lat);
}

//点击返回，保存，修改按钮，清除editForm数据
function clearData() {
    $('#editCompanyForm .dynamicClear').val('');
    $('#container').data('rowData', '');
}

//百度地图API功能
var map = new BMap.Map("allmap");
map.enableScrollWheelZoom();
map.enableContinuousZoom();
map.centerAndZoom(point, 15);

/*var comfordata = $('#lat').val();

// 创建地址解析器实例
var myGeo = new BMap.Geocoder();
// 将地址解析结果显示在地图上,并调整地图视野
myGeo.getPoint(comfordata, function(point){
	if (point) {
		map.centerAndZoom(point, 16);
		map.addOverlay(new BMap.Marker(point));
	}else{
		sweetAlert("您选择地址没有解析到结果!");
	}
}, "三河市");

*/
//单击获取点击的经纬度
map.addEventListener("click", function (e) {
    $('#lng').val(e.point.lng);
    $('#lat').val(e.point.lat);
    var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));

    marker.addEventListener("click", attribute);

});

function attribute() {
    var p = marker.getPosition();  //获取marker的位置
    sweetAlert("marker的位置是" + p.lng + "," + p.lat);
}






