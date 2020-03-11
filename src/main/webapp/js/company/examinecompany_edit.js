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
			$('.chosen-select').chosen({width: "100%",max_selected_options: 2})
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
	var url = 'company/saveCompanyExobject.do';
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

//获取表单参数
function getFormParams(){
	var examinetime = $('#examinetime').val();
	var type = $('#type').val();
	var cid = $('#container').data('cid');
	var content = $('#content').val();
	var result = $('#result').val();
	var arr = $("select").val();
	var exaname=arr.join(","); 
	var params = {
			'cid':cid,
			'type':type,
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
	$('#container').load('company/companyUI.do');
}

//设置按钮文字
function setBtnVal(){
	var cid = $('#container').data('cid');
	if(cid){
		$('#btn_ok').val('保存');
		$('#editTitle').text('日常检查');
		findUserById(cid);   

	}else{
		sweetAlert("请选择一条数据")
	}
}

function findUserById(cid){
	var param  = {'cid':cid};
	var url = 'company/findCompanyById.do';
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
	$('#cname').val(data.cname);
	if(data.type == "1"){
		$('#type').val("酒店");
	}else if(data.type == "2"){
		$('#type').val("景点");
	}
	
}
function clearData(){
	$('#editTravelForm .dynamicClear').val('');
	$('#container').data('cid','');
}



