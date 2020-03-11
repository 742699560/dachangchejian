var SUCCESS = 200;
//设置按钮的文字,以及编辑框的标题
setBtnVal();



docomExamineName();


//点击返回按钮
$('#btn_return').click(gobackUserList);
//点击保存/修改按钮
$('#btn_ok').click(commitUserForm);

$('#btn-addexamine').click(showEditDialogs);
$('#btn-editexamine').click(showEditDialogs);

function showEditDialogs(){
	var url="travel/editexamineUI.do";
	var title;
	if($(this).hasClass("btn-addexamine")){
		title="添加执法人员"
	}
	if($(this).hasClass("btn-editexamine")){
		title="修改执法人员"
		var b = $("select option:selected").attr("id");
		console.log(b)
		$("#modal-dialog").data("eid",b);
	}
	$("#modal-dialog .modal-body").load(url,
			function(){
		 $(".modal-title").html(title);
		 $("#modal-dialog").modal("show");
	})
	
}


function docomExamineName(){
	var url = 'travel/comexaminameUI.do';
	$.post(url,function(result){
		if(result.state==SUCCESS){
			setExamineName(result.data);
			$('.chosen-select').chosen({width: "100%",height:"50px;",max_selected_options: 2})
		}else{
			alert(result.message);
		}
	})
}

function setExamineName(list){
	console.log(list)
	 var tBody=$('#form-field-select-4');
	 tBody.empty();
	   var temp='<option id="[eid]" value="[examinename]">[examin]</option>'
	    for(var i in list){	    	
	        tBody.append(temp
	        	.replace('[eid]',list[i].eid)
	            .replace('[examinename]',list[i].examinename)
	            .replace('[examin]',list[i].examinename)
	        );
	    }
}

//点击保存/修改按钮
function commitUserForm(){
	var params = getFormParams();
	console.log(params)
	var url = 'travel/doExobject.do';
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

//获取表单参数
function getFormParams(){
	var examinetime = $('#examinetime').val();
	var tid = $('#container').data('tid');
	var content = $('#content').val();
	var result = $('#result').val();
	var arr = $("select").val();
	var exaname=arr.join(","); 
	var params = {
			'tid':tid,
			'examinetime':examinetime,
			'content':content,
			'result':result,
			'exaname':exaname
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
		$('#btn_ok').val('保存');
		$('#editTitle').text('日常检查');
		findUserById(tid);   

	}else{
		sweetAlert("请选择一条数据")
	}
}

function findUserById(tid){
	var param  = {'tid':tid};
	var url = 'travel/findTravelById.do';
	$.post(url,param,function(result){
		if(result.state==SUCCESS){
			loadEditUserForm(result.data);  					
		}else{
			sweetAlert(result.message);
		}
	})
}

//回显
function loadEditUserForm(data){
	$('#tname').val(data.tname);	
}
function clearData(){
	$('#editTravelForm .dynamicClear').val('');
	$('#container').data('tid','');
}



