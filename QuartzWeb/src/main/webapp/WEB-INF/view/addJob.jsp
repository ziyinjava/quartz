<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>友凡</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<style media="all" type="text/css">@import "/Quaztweb/css/all.css";</style>
	<link href="/Quaztweb/css/layer.css" rel="stylesheet" type="text/css" />
	<script src="/Quaztweb/js/jquery.js" type="text/javascript"></script>
	<script src="/Quaztweb/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="/Quaztweb/js/tinybox_2.js" type="text/javascript"></script>
	<script src="/Quaztweb/js/tinybox.js" type="text/javascript"></script>
	<script src="/Quaztweb/js/yf.js"></script>
	<style>
	.table{border-collapse:collapse;font-size: 12px;border:1;margin:0 auto;}
	.table th{color: #0b85a7;}
	.table tr td{text-align:left;}
	</style> 
	<script type="text/javascript">
	function clearContent(obj,defaultValue){
		if($(obj).val()==defaultValue){
			$(obj).val("");
		}
	}
	
	function defaultContent(obj,defaultValue){
		if(!$(obj).val()){
			$(obj).val(defaultValue);
		}
	}
	
		
	var max_line_num=100;
	function add_line(){
	  if(max_line_num == undefined) {
	    max_line_num=100;
	  }else{
	    max_line_num=parseInt(max_line_num);
		max_line_num+=1;
	  }
	  $('#paramsTable').append("<tr id='line"+max_line_num+"'><td><input type='text' name=\"argsNames\" value=\"参数名\" onclick=\"clearContent(this,'参数名')\" onblur=\"defaultContent(this,'参数名')\" size=5 /></td><td><input type='text' class=\"input1 in_w_5\" name=\"argsValues\" value=\"参数值\" onclick=\"clearContent(this,'参数值')\" onblur=\"defaultContent(this,'参数值')\" /></td><td><a href='javascript:void(0);' onclick='remove_line(\"line"+max_line_num+"\");'><img src=\"/Quaztweb/img/hr.gif\"></a></td></tr>");
	}
	function remove_line(currentStep){
	  $("#"+currentStep).remove();
	}
</script>
<style type="text/css">
	.zxx_list_title{background:#eeeeee; border:1px solid #cccccc; padding:1em;}
	.zxx_list_content{padding:1em;}
	#tinybox{position:absolute; display:none; padding:10px; background:#ffffff url(/img/preload.gif) no-repeat 50% 50%; border:10px solid #e3e3e3; z-index:2000;}
	#tinymask{position:absolute; display:none; top:0; left:0; height:100%; width:100%; background:#000000; z-index:1500;}
	#tinycontent{background:#ffffff; font-size:1.1em;}
</style>
</head>
<body>
<div id="main">
	<div id="header"> 
		<span id="logoutSpan" style="position: absolute; right: 14px; bottom: 23px;"></span>
		<a href="#" class="download">友凡原创，盗版必究</a>
		<ul id="top-navigation">
			<li class="active"><span><span>任务列表</span></span></li>
		</ul>
	</div>
	<div id="middle">
		<div id="left-column">
			<h3>Header</h3>

			<ul class="nav">
				<li><a href="list">任务列表</a></li>
				<li class="last"><a href="toAddJob">添加任务</a></li>
				
				<li class="last"><a href="listordertask">任务顺序列表</a></li>
				<li class="last"><a href="toaddordertask">任务顺序添加</a></li>
				<li class="last"><a href="#">联系我们</a></li>
			</ul>
		</div>
		<div id="center-column">
			<div class="top-bar">
				<h1>任务管理/添加任务</h1>
			</div>
		    <div class="select-bar2" >
		    </div>
		  <div class="table">
				<img src="/Quaztweb/img/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="/Quaztweb/img/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<form action="addJob" method="post">
				<table class="listing form" cellpadding="0" cellspacing="0">
					<tr>
						<th class="full" colspan="2">请填写任务的各个属性，以下全是必填项:</th>
					</tr>
					<tr>
						<td class="first" width="100"><strong>组名称:</strong></td>
						<td class="last" style=vertical-align:bottom;" id="groupTd">
						<select id="jobGroup" name="jobGroup">
						    <option value="">请选择组名称</option>
							<c:forEach var="group"  items="${jobGroups}">
							 <option value="${group}" <c:if test="${group==jobDetail.group}">selected</c:if>>${group}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;<a  href="javascript:void(0);" id="otherGroups">新建组</a>
					</td>
					</tr>
					<tr class="bg">
						<td class="first" width="100"><strong>任务名称:</strong></td>
						<td class="last"><input type="text" class="text" id="jobName" name="jobName" value=""/></td>
					</tr>
					<tr class="bg">
						<td class="first" width="100"><strong>任务class:</strong></td>
						<td class="last">
						<input type="text" class="text" id="jobClassName" name="jobClassName" value=""/>
					    </td>
					</tr>
					<tr class="bg">
						<td class="first" width="100"><strong>并发度:</strong></td>
						<td class="last">
						<input type="text" class="text" id="concurrentdegree" name="concurrentdegree" value=""/>
					    </td>
					</tr>
					<tr class="bg">
						<td><strong>触发器设置:</strong></td>
						<td>
						<input type="radio" name="triggerType" value="1" checked onclick="displayFields();">简单触发器 &nbsp;&nbsp;<input type="radio" name="triggerType" value="2" onclick="displayFields();">cron表达式触发器 
						</td>
						</tr>
					<tr>
						<td class="first" width="100"></td>
						<td class="last">
						<table id="simpleTriggerTable" style="display:block;">
						    <tr><td width="20%">执行频率:</td><td width="80%"><input type="text" id="rate" name="rate" value="" />秒钟执行一次</td></tr>		
						    <tr><td width="20%">执行次数:</td><td width="80%"><input type="text" id="times" name="times" value="-1" />次&nbsp;&nbsp;(-1表示永远执行下去)</td></tr>		
						</table>
						<table id="cronTriggerTable" style="display:none;">
						    <tr><td width="20%">秒:</td><td width="80%"><input type="text" id="secondField" name="secondField" value="0" /></td></tr>		
						    <tr><td width="20%">分:</td><td width="80%"><input type="text" id="minutesField" name="minutesField" value="*" /></td></tr>
						    <tr><td width="20%">时:</td><td width="80%"><input type="text" id="hourField" name="hourField" value="*" /></td></tr>	
						    <tr><td width="20%">日:</td><td width="80%"><input type="text" id="dayField" name="dayField" value="*" /></td></tr>	
						    <tr><td width="20%">月:</td><td width="80%"><input type="text" id="monthField" name="monthField" value="*" /></td></tr>
						    <tr><td width="20%">周:</td><td width="80%"><input type="text" id="weekField" name="weekField" value="?" /></td></tr>		
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;"><input type="submit" id="submitButton"  value="提交"></td>
					</tr>
				</table>
				</form>
	        <p>&nbsp;</p>
		  </div>
		</div>
		<div id="right-column">
			<strong class="h">特别注意</strong>
			<div class="box">请注意，任务名称、任务组可使用中文进行命名。</div>
	  </div>
	</div>
	<div id="footer"></div>

</div>
<script type="text/javascript">
function displayFields(){
	var triggerType = $("input[name='triggerType']:checked").val();
    if(triggerType==1){
		$("#simpleTriggerTable").show();
		$("#cronTriggerTable").hide();
	}else{
		$("#simpleTriggerTable").hide();
		$("#cronTriggerTable").show();
	}
}
$(document).ready(
function(){
	var originalHtml=$("#groupTd").html();
	$("#otherGroups").click(
		function(){
			$("#groupTd").html("<input type='text' id=\"jobGroup\"  class=\"text\" name=\"jobGroup\">&nbsp;&nbsp;<a href=\"javascript:void(0);\" id=\"cancelJobGroup\">取消</a>");
			$("#cancelJobGroup").click(function(){$("#groupTd").html(originalHtml);});
		}	
	);
	$("#submitButton").click(function(){
		jobGroup = $.trim($("#jobGroup").val());
		jobName = $.trim($("#jobName").val());
		jobClassName = $.trim($("#jobClassName").val());
		cronExpression = $.trim($("#cronExpression").val());
		description = $.trim($("#description").val());
		if(!jobGroup){
			alert("请选择/填写分组！");
			return false;
		}
		if(!jobName){
			alert("请填写任务名！");
			return false;
		}
		if(!jobClassName){
			alert("请选择类名！");
			return false;
		}
		
		var argsLength =$("input[name='argsNames']").length;
		if(argsLength == 1){
			firstArgsName = $.trim($("input[name='argsNames']")[0].value);
			firstArgsValue = $.trim($("input[name='argsValues']")[0].value);
			flag1=true;flag2=true;
			if(!firstArgsName || firstArgsName == "参数名"){
				flag1=false;
			}
			if(!firstArgsValue || firstArgsValue == "参数值"){
				flag2=false;
			}			
			if(flag1 == flag2){
				
			}else{
				alert("请检查方法参数设置信息.");
				return false;
			}
		}else{
			for(i=0;i<argsLength;i++){
				argsName = $.trim($("input[name='argsNames']")[i].value);
				argsValue = $.trim($("input[name='argsValues']")[i].value);
				argsType = $.trim($("select[name='classtypes']")[i].value);	
				if(!argsName || argsName=="参数名"){
					alert("请检查第"+(i+1)+"个参数信息的参数名.");
					return false;
				}
				if(!argsValue || argsValue=="参数值"){
					alert("请检查第"+(i+1)+"个参数信息的参数值.");
					return false;
				}				
			}
		}		
		return true;
	});
	displayFields();
}
);
</script>
<script type="text/javascript">
function setJobClassName(jobClassName){
    document.getElementById("jobClassName").value=jobClassName;
    TINY.box.hide();
}
$(document).ready(function(){
	$("#logoutSpan").html('欢迎<b><font size="2">youfan</font></b>登录任务管理系统,&nbsp;&nbsp;<a href="#" style="color:red">退出登录</a>');
});
</script>
</body>
</html>