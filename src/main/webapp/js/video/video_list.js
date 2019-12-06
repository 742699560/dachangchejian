$(document).ready(function(){
	$("#modal-dialog").on('click','.ok',doSaveOrUpdate);
	var cid=$("#modal-dialog").data("cid");
	if(cid)doGetObjectById(cid);
	$("#modal-dialog").on(
	   "hidden.bs.modal",function(){
		$(this).off('click', '.ok').removeData("cid")
	});
})
function doGetObjectById(cid){
	var url="camera/findCameraById.do";
	var params={"cid":cid};
	$.post(url,params,function(result){
		if(result.state==1){
			doFillFormData(result.data);
		}else{
			alert(result.message);
		}
	});
}
function doFillFormData(obj){
	var code = obj.tcode;
	console.log(code)
	$("#cname").val(obj.cname);
	$("#comtcode").val(code);
	$("#nChannel").val(obj.nChannel); 
	$("#model").val(obj.model);
	$("#brand").val(obj.brand);
}
//保存或更新数据
function doSaveOrUpdate(){
	if($("#editFormId").valid()){
	var params=doGetEditFormData();
	//2.将数据提交到服务端
	var cid=$("#modal-dialog").data("cid");
	var url=cid?"camera/doUpdateCamera.do":"camera/doSaveCamera.do";
	$.post(url,params,function(result){
		if(result.state==1){
			$("#modal-dialog").modal("hide");
			doGetObjects();
		}else{
		   alert(result.message);
		}
	})
	}
}
//获得表单数据
function doGetEditFormData(){
	var params={
		"cid":$("#modal-dialog").data("cid"),//更新时需要
		"cname":$("#cname").val(),
		"tcode":$("#comtcode").val(),
		"nChannel":$("#nChannel").val(),
		"model":$("#model").val(),
		"brand":$("#brand").val(),
		"vid":$('#container').data('vid')
	};
	return params;
}

