var SUCCESS = 200;
//设置按钮的文字,以及编辑框的标题
setBtnVal();
//点击返回按钮
$('#btn_return').click(gobackUserList);
//点击保存/修改按钮
$('#btn_ok').click(commitUserForm);

//点击保存/修改按钮
function commitUserForm(){
	if($('#editUserForm').valid()){  
		var params = getFormParams();
		if(params=='nochoose'){
			alert('请选择用户角色！');
			return false;
		}
		var userId = $('#container').data('userId');
		var userPwd = $('#userPwd').val();
		params.password = userPwd;
		params.id = userId;
		var url = userId?'user/updateUser.do':'user/saveUser.do';
		$.post(url,params,function(result){
			if(result.state==SUCCESS){
				alert('操作成功！');
				clearData();
				$('#container').load('user/listUI.do');
			}else{
				alert(result.message);
			}
		})
	}
}

//获取表单参数
function getFormParams(){
	var userName = $('#userName').val();
	var company = $('#company').val();
	var utype = $('#utype').val();
	var roleIdList = new Array();
	var pernumber = $('#pernumber').val();
	var realname = $('#realname').val();
	var password = $('#userPwd').val();
	var performer;
    
        if ($(".ace-switch-7").is(':checked')) {  
        	performer =  2;//on
        } else {  
        	performer = 1; 
        }  
   
  	console.log(performer)
	$('option[name="checkedItem"]').each(function(){
		if($(this).is(':selected')){
			roleIdList.push($(this).val());
		}
	})
	if(roleIdList.length==0){
		return 'nochoose';
	}
	roleIdList = roleIdList.toString();
	var params = {
		'username':userName,
		'company':company,
		'pernumber':pernumber,
		'performer':performer,
		'utype':utype,
		'realname':realname,
		'roleIdList':roleIdList,
		'password':password
	}
	return params;
}

//点击返回按钮
function gobackUserList(){
	clearData();
	$('#container').load('user/listUI.do');
}

//查询所有角色  -- 如果使修改，有roleIdList
function loadRoleList(roleIdList){
	var url ='user/findRoleList.do';
	$.getJSON(url,function(result){
		if(result.state==SUCCESS){
			fillRoleList(result.data);
			if(roleIdList){
				for(var i in roleIdList){
					$('#roleList option[name="checkedItem"]').each(function(){
						if($(this).val()==roleIdList[i]){
							$("#roleList option[value='"+roleIdList[i]+"']").attr("selected","selected");
						}
					})
				}
			}
		}else{
			alert(result.message);
		}
	})
}

//加载填充角色列表
function fillRoleList(roleList){
	var parentEle = $('#roleList');
	for(var i in roleList){
		var temp = '<option value="[id]"name="checkedItem">[name]</option>';
		temp = temp.replace('[id]',roleList[i].id).replace('[name]',roleList[i].name);
		parentEle.append(temp);
	}
/*	var value=$("#roleList option:selected").val(); 
	$("#numbers option:selected").text(); 
	console.log(value)*/
}

//设置按钮文字
function setBtnVal(){
	var userId = $('#container').data('userId');
	if(userId){
		$('#btn_ok').val('修改');
		$('#editTitle').text('修改');
		findUserById(userId);   //根据id查询用户信息
	}else{
		$('#btn_ok').val('保存');
		$('#editTitle').text('新增');
		//加载所有角色
		loadRoleList();
	}
}

function findUserById(userId){
	var param  = {'userId':userId};
	var url = 'user/findUserById.do';
	$.post(url,param,function(result){
		if(result.state==SUCCESS){
			loadEditUserForm(result.data);  //回显
		}else{
			alert(result.message);
		}
	})
}

//回显
function loadEditUserForm(user){
	$('#userName').val(user.username);
	$('#company').val(user.company);
	$('#pernumber').val(user.pernumber);
	$('#realname').val(user.realname);
	if(user.performer == 1){
		$("#performer").attr("checked",false)
	}else if(user.performer == 2){
		$("#performer").attr("checked",true)
	}
	$('#utype').val(user.utype);
	loadRoleList(user.roleIdList);
}

//点击返回，保存，修改按钮，清除editForm数据
function clearData(){
	$('#editUserForm .dynamicClear').val('');
	$('#newPwdDiv').css('display','none');
	$('#roleList').empty();
	$('#container').data('userId','');
}