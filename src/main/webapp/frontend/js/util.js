function judgeNull(value){
	 if("null"!=value && ""!=value && null!=value && "[]"!=value){
		 return true;
	 }else{
		 return false;
	 }
}

/* 显示提示信息 */
function showMsg(msg){
    $.messager.alert('消息',msg,'info');
}

