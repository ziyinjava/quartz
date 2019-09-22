<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ page import="org.quartz.CronExpression"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>友凡</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<style media="all" type="text/css">@import "/Quaztweb/css/all.css";</style>
	<script src="/Quaztweb/js/jquery.js" type="text/javascript"></script>
	<script src="/Quaztweb/js/jquery.cookie.js" type="text/javascript"></script>
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
				<h1>任务管理/任务列表</h1>
			</div>
		  <div class="select-bar">
	    	<form action="/setup.action" method="get">
	    	<input type="hidden" name="action" value="listJobs" />
			<label>
				组名：
				<select id="queryGroup" name="queryGroup">
				<option value="">请选择组名</option>
				<c:forEach var="group"  items="${groups}">
				 <option value="${group}"   <c:if test="${group==queryGroup}">selected</c:if>>${group}</option>
				</c:forEach>
				</select>
				</label>
				<label>
				 &nbsp;&nbsp;任务名：<input type="text"  name="queryJobName" id="queryJobName" value="${queryJobName}" /> 
				</label>
				 <label>
				 <input type="submit" value="查询">
				 </label>
			</form>
		  </div>
		  <form action="setup.action">
			<div class="table">
				<img src="img/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="img/bg-th-right.gif" width="7" height="7" alt="" class="right" />
                <input type="hidden" name="queryGroup" value="${queryGroup}" />
				<input type="hidden" name="queryJobName" value="${queryJobName}" />
				<input type="hidden" name="action" value="pauseOrRunAllJobs" />
				<table class="listing" cellpadding="0" cellspacing="0">
					<tr>
						<th width="1%">
							
						</th>
						<th width="10%">名称</th>
						<th width="10%">类名</th>
						<th width="15%">触发器状态</th>
						<th width="15%">并发数</th>
						<th class="last" width="10%">相关操作</th>
					</tr>
					<c:forEach var="jobInfoMap"  items="${jobList}" varStatus="status">
					<c:set var="jobDetail" value="${jobInfoMap.get('jobDetail')}" />
					<c:set var="triggers" value="${jobInfoMap.get('triggers')}" />
					<c:set var="jobDataMap" value="${jobDetail.jobDataMap}" />
					<c:choose>
					  <c:when test="${status.first==true}">
					        <tr class="first">
					  </c:when>
					  <c:when test="${status.first==false && status.count%2==0}">
					       <tr class="bg">
					  </c:when>
					  <c:otherwise>
					  <tr></tr>
					  </c:otherwise>
					</c:choose> 
						<td><input type="checkbox" name="jobsInfo" value="${jobDetail.key.toString()}" /></td>
						<td><c:out value="${jobDetail.key.toString()}" /></td>
						<td><c:out value="${jobDetail.jobClass.getName()}" /></td>
						<td>
					    <c:forEach var="trigger"  items="${triggers}">
					    <c:set value="${scheduler.getTriggerState(trigger.key)}" var="triggerStatus"/>
					    <c:out value="${trigger.key.toString()}" />(<c:out value="${triggerStatus.name()}" />:<c:out value="${triggerStatus.ordinal()}" />)<br/>					    
					    </c:forEach>
						</td>
						<td>
						${jobInfoMap.get('pinelines')}
						</td>
						<td>
						<a href="resumeJob?jobKey=${jobDetail.key.toString()}&queryGroup=${queryGroup}&queryJobName=${queryJobName}" onclick="return confirm('您确定要继续此任务执行么?');">开始任务</a><br/>
						<a href="deleteJob?jobKey=${jobDetail.key.toString()}&queryGroup=${queryGroup}&queryJobName=${queryJobName}" onclick="return confirm('您确定要删除此任务么?');">删除任务</a>
						</td>
						</tr>
					</c:forEach>
				</table>
				<input type="hidden" name="type" id="type" />
			    &nbsp;&nbsp;&nbsp;&nbsp;总共查询出${count}条数据.
			</div>
		</div>
		 </form>
		<div id="right-column">
			<strong class="h">特别注意</strong>
			<div class="box">请注意，任务名称、任务组可使用中文进行命名。</div>
	  </div>
	</div>
	<div id="footer"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#logoutSpan").html('欢迎<b><font size="2">youfan</font></b>登录任务管理系统,&nbsp;&nbsp;<a href="#" style="color:red">退出登录</a>');
});
$("#checkAll").click(
	       function(){ 
	    	  	 if ($("#checkAll").attr("checked") == true) {
	    	  		  $("#checkAll2").attr("checked",true);
	    	  		  $("input[name='jobsInfo']").each(function() {
	         		   $(this).attr("checked", true);
	         		  });
	    	 	 }else{
	    	 		$("#checkAll2").attr("checked",false);
	    	 		$("input[name='jobsInfo']").each(function() {
	          		   $(this).attr("checked", false);
	          		  }); 
	      		 }
	       }
	);
$("#checkAll2").click(
	       function(){ 
	    	  	 if ($("#checkAll2").attr("checked") == true) {
	    	  		  $("#checkAll").attr("checked",true);
	    	  		  $("input[name='jobsInfo']").each(function() {
	         		   $(this).attr("checked", true);
	         		  });    
	    	 	 }else{
	    	 		$("#checkAll").attr("checked",false);
	    	 		$("input[name='jobsInfo']").each(function() {
	          		   $(this).attr("checked", false);
	          		  }); 
	      		 }
	       }
	);
	
  function changeType(type){
	  $("#type").val(type);
  }
  
  $("#jobGroup").change(function(){
	  $("#jobName").val("");
  });
</script>
</body>
</html>
