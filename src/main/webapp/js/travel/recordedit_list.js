$(document).ready(function(){
	/* $(".btn-default").hide();*/
	 $(".ok").hide();
	var eid=$("#modal-dialog").data("eid");
	if(eid)doGetObjectById(eid);
})
function doGetObjectById(eid){
	var url="travel/findrecordById.do";
	var params={"eid":eid};
	$.post(url,params,function(result){
		if(result.state==1){
			doFillFormDatas(result.data);
			$('#modal-dialog').data('vid','');
		}else{
			alert(result.message);
		}
	});
}
function doFillFormDatas(list){
	$("#content").html(list.content);
	
}


