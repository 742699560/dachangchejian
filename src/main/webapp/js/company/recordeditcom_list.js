$(document).ready(function(){
	/* $(".btn-default").hide();*/
	 $(".ok").hide();
	var eid=$("#modal-dialog").data("eid");
	if(eid)doGetObjectByIds(eid);
})
function doGetObjectByIds(eid){
	var url="company/findrecordCompanyById.do";
	var params={"eid":eid};
	$.post(url,params,function(result){
		if(result.state==1){
			doFillFormDatas(result.data);
			$('#modal-dialog').data('vid','');
		}else{
			sweetAlert(result.message);
		}
	});
}
function doFillFormDatas(list){
	console.log(list.content)
	$("#content").html(list.content);
}


