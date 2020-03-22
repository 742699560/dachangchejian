$(document).ready(function(){
	$("#modal-dialog").on('click','.ok',doSaveOrUpdate);
	var eid=$("#modal-dialog").data("eid");
	if(eid)doGetObjectById(eid);
	$("#modal-dialog").on(
	   "hidden.bs.modal",function(){
		$(this).off('click', '.ok').removeData("eid")
	});
})
function doGetObjectById(eid){
	var url="travel/findExamineById.do";
	var params={"eid":eid};
	$.post(url,params,function(result){
		if(result.state==1){
			doFillFormDatas(result.data);
		}else{
			sweetAlert(result.message);
		}
	});
}
function doFillFormDatas(list){
	console.log(list.examinename)
	$("#examinename").val(list.examinename);
}
//保存或更新数据
function doSaveOrUpdate(){
	if($("#editFormIds").valid()){
	var params=doGetEditFormData();
	//2.将数据提交到服务端
	var eid=$("#modal-dialog").data("eid");
	var url=eid?"travel/doUpdateExamine.do":"travel/doSaveExamine.do";
	$.post(url,params,function(result){
		if(result.state==1){
			$("#modal-dialog").modal("hide");
			docomExamineName();
		}else{
			sweetAlert(result.message);
		}
	})
	}
}
//获得表单数据
function doGetEditFormData(){
	var params={
		"eid":$("#modal-dialog").data("eid"),//更新时需要
		"examinename":$("#examinename").val()
	};
	return params;
}

