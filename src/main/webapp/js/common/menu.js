var SUCCESS = "200";
function loadMenuList(){
	//到controller请求菜单
	var url = 'menuList.do';
	$.post(url,function(result){
		if(result.state==SUCCESS){
			loadMenu(result.data);
		}else{
			sweetAlert(result.message);
		}
	});
}
/*function loadMenu(list){
	//加载一级菜单
	var menuList = $('#menuList');
	for(var i in list){
		if(list[i].parentId==null||list[i].parentId==''){
			var template = '<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"'+
			' role="button" aria-haspopup="true" aria-expanded="false">'+list[i].name+'<span class='+
			'"caret"></span></a><ul class="dropdown-menu"  id="menu'+list[i].id+'"></ul></li>';
			menuList.append(template);
		}
	}
	//加载二级菜单
	for(var i in list){
		if(list[i].parentId!=null && list[i].parentId!=''){
			var parentEle = $('#menu'+list[i].parentId);
			var li = $('<li></li>');
			li.data('url',list[i].url);
			var temp = '<a  class="menuBtn">'+list[i].name+'</a>';
			temp = li.append(temp);
			var separetor = '<li role="separator" class="divider"></li>';
			parentEle.append(temp).append(separetor);
		}
	}
}*/
function loadMenu(list){
	//加载一级菜单
	var menuList = $('#menuList');
	console.log(list)
	for(var i in list){
		if(list[i].name =='系统管理'){
			if(list[i].parentId==null||list[i].parentId==''){
				var templateone = '<li><a class="dropdown-toggle" data-toggle="collapse"role="button"aria-expanded="true">'+
				'<i class="menu-icon fa fa-desktop"></i> <span class="menu-text">'+list[i].name+
				'</span><b class="arrow fa fa-angle-down"></b></a><b class="arrow"></b><ul class="submenu nav-hide"style="display: none;"  id="menu'+list[i].id+'"></ul></li>';
				menuList.append(templateone);
			}
		}
		if(list[i].name =='信息管理'){
			if(list[i].parentId==null||list[i].parentId==''){
				var templatetwo = '<li><a class="dropdown-toggle" data-toggle="collapse"role="button"aria-expanded="true">'+
				'<i class="menu-icon fa  fa-bar-chart-o"></i> <span class="menu-text">'+list[i].name+
				'</span><b class="arrow fa fa-angle-down"></b></a><b class="arrow"></b><ul class="submenu nav-hide"style="display: none;"  id="menu'+list[i].id+'"></ul></li>';
				menuList.append(templatetwo);
			} 
		}
	/*	if(list[i].name =='电子地图'){
			if(list[i].parentId==null||list[i].parentId==''){
				var templatethree = '<li><a class="dropdown-toggle" data-toggle="collapse"role="button"aria-expanded="true">'+
				'<i class="menu-icon fa  fa-globe"></i> <span class="menu-text">'+list[i].name+
				'</span><b class="arrow fa fa-angle-down"></b></a><b class="arrow"></b><ul class="submenu nav-hide"style="display: none;"  id="menu'+list[i].id+'"></ul></li>';
				menuList.append(templatethree);
			}
		}*/
		if(list[i].name =='统计报表'){
			if(list[i].parentId==null||list[i].parentId==''){
				var templatefor = '<li><a class="dropdown-toggle" data-toggle="collapse"role="button"aria-expanded="true">'+
				'<i class="menu-icon fa fa-book"></i> <span class="menu-text">'+list[i].name+
				'</span><b class="arrow fa fa-angle-down"></b></a><b class="arrow"></b><ul class="submenu nav-hide"style="display: none;"  id="menu'+list[i].id+'"></ul></li>';
				menuList.append(templatefor);	
			}
		
		}
		
	}
			
		
			
			
/*	for(var i in list){
		if(list[i].parentId==null||list[i].parentId==''){	
			var template = '<li><a class="dropdown-toggle" data-toggle="collapse"role="button"aria-expanded="true">'+
			'<i class="menu-icon fa fa-list"></i> <span class="menu-text">'+list[i].name+
			'</span><b class="arrow fa fa-angle-down"></b></a><b class="arrow"></b><ul class="submenu nav-hide"style="display: none;"  id="menu'+list[i].id+'"></ul></li>';
			menuList.append(template);
		}
	 }*/
	//加载二级菜单
	for(var i in list){
		if(list[i].parentId!=null && list[i].parentId!=''){
			var parentEle = $('#menu'+list[i].parentId);
			var li = $('<li></li>');
			li.data('url',list[i].url);
			var temp = '<a style="cursor:pointer; " id="menuBtn"><i class="menu-icon fa fa-caret-right"></i>'+list[i].name+'</a><b class="arrow"></b>';
			temp = li.append(temp);
			parentEle.append(temp);
		}
	}
}

function loadContainer(){
	var url = $(this).parent().data('url');
	url = url + '?t=;'+Math.random(1000);
	$("#container").load(url);
}