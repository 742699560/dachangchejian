<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript">
//初始化datepicker对象
$('.datepicker').datepicker({
    format: 'yyyy-mm-dd',
    language:"zh-CN",
    autoclose: true//选中自动关闭
})
</script>
<style>
.search-field{
height:32px;
font-size: 15px;
}
</style>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a
			href="indexUI.do">主页</a></li>
		<li><a href="#">日常检查</a></li>
	</ul>
</div>

<div id="wrapper">
	<div class="main">
		<div class="main-content" style="padding: 0px;">
			<div class="container-fluid">
				<div class="container" style="width: 100%;">
					<div class="panel panel-default">
						<div class="panel-heading" id="editTitle"
							style="background: #ececec"></div>
						<div class="row">
							<div class="col-md-12">
								<form class="form-horizontal" id="editTravelForm"
									style="padding-left: 100px; padding-top: 20px;">
									<div class="form-group" style="padding-top: 20px;">
										<label for="project-code" class="col-sm-2 control-label">
											公司名称：
										</label>
										<div class="col-sm-10">
											<input style="width: 59%;" type="text" name="cname" id="cname" readonly>
										</div>
									</div>
									<div class="form-group" >
										<label for="project-code" class="col-sm-2 control-label">
											类型：
										</label>
										<div class="col-sm-10">
											<input style="width: 59%;" type="text" name="type" id="type" readonly>
										</div>
									</div>
									<div class="form-group" >
										<div class="col-sm-2 control-label">
											<font color="red">*</font> 执法人员：
										</div>
										<div class="col-sm-6">
											<select multiple="multiple"
												class="chosen-select form-control dynamicClear tag-input-style required"
												id="form-field-select-4" data-placeholder="请选择执法人员">
											</select>
										</div>
									</div>

									<div class="form-group">
										<label for="project-code" class="col-sm-2 control-label">
											<font color="red">*</font> 检查日期：
										</label>
										<div class="col-sm-6">
											<input type="text" class="form-control dynamicClear datepicker required"
												name="examinetime" id="examinetime">
										</div>
									</div>

									<div class="form-group">
										<label for="noteId" class="col-sm-2 control-label">检查内容：</label>
										<div class="col-sm-6">
											<textarea style="height: 200px" class="form-control"
												name="content" id="content"></textarea>
										</div>
									</div>

									<div class="form-group">
										<label for="project-code" class="col-sm-2 control-label">
											<font color="red">*</font> 检查结果：
										</label>
										<div class="radio">
													<label class="col-sm-1 control-label" >
														<input name="form-field-radio" type="radio" id="result" class="ace" value="正常" />
														<span class="lbl">正常</span>
													</label>
													<label class="col-sm-1 control-label" style="width: 15%;">
														<input name="form-field-radio" type="radio" id="result" class="ace" value="立案" />
														<span class="lbl">立案</span>
													</label>
													<label class="col-sm-1 control-label"style="width: 15%;">
														<input name="form-field-radio" type="radio" id="result" class="ace" value="责令整改" />
														<span class="lbl">责令整改</span>
													</label>
													
										</div>
									</div>

									<div class="form-group" >
										<div class="col-sm-4 control-label"></div>
										<!-- <input type="button" value="添加执法人员"
											class="btn btn-primary btn-addexamine" id="btn-addexamine">&nbsp;&nbsp;
											<input type="button" value="修改执法人员"
											class="btn btn-success btn-editexamine" id="btn-editexamine">&nbsp;&nbsp; -->
										<input type="button" class="btn btn-primary" id="btn_ok">
										&nbsp;&nbsp; <input type="button" value="返回"
											class="btn btn-warning" id="btn_return">&nbsp;&nbsp;
											
									</div>
								</form>
							</div>

						</div>
						<div></div>
						<div></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	/* 		jQuery(function($) {
				if(!ace.vars['touch']) {
					$('.chosen-select').chosen({allow_single_deselect:true}); 
					$(window)
					.off('resize.chosen')
					.on('resize.chosen', function() {
						$('.chosen-select').each(function() {
							 var $this = $(this);
							 $this.next().css({'width': $this.parent().width()});
						})
					}).trigger('resize.chosen');
					$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
						if(event_name != 'sidebar_collapsed') return;
						$('.chosen-select').each(function() {
							 var $this = $(this);
							 $this.next().css({'width': $this.parent().width()});
						})
					});
				}
			});
			 */
			/* var tag_input = $('#form-field-tags');
			try{
				tag_input.tag(
				  {
					placeholder:tag_input.attr('placeholder'),
					source: ace.vars['US_STATES'],
				  }
				)
		
				var $tag_obj = $('#form-field-tags').data('tag');
				
				var index = $tag_obj.inValues('some tag');
				$tag_obj.remove(index);
			}
			catch(e) {
				tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
			} */
		</script>
<script type="text/javascript"
	src="${basePath}/js/company/examinecompany_edit.js"></script>