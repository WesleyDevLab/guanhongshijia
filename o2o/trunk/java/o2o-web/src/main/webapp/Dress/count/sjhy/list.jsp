<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/Dress/include/top.jsp"%>
<%--
  ~ Copyright (c) 2009-2016 SHENZHEN Eternal Dynasty Technology Co.,Ltd.
  ~ All rights reserved.
  ~
  ~ This file contains valuable properties of  SHENZHEN Eternal Dynasty
  ~ Technology Co.,Ltd.,  embodying  substantial  creative efforts  and
  ~ confidential information, ideas and expressions.    No part of this
  ~ file may be reproduced or distributed in any form or by  any  means,
  ~ or stored in a data base or a retrieval system,  without  the prior
  ~ written permission  of  SHENZHEN Eternal Dynasty Technology Co.,Ltd.
  ~
  --%>

<script type="text/javascript">
$(function(){
	$("#form1").validate();
})
function cz(){
		 document.getElementById("czlist").submit();
	}
	
	function exportt(){
		document.getElementById("czlist").action="<%=request.getContextPath()%>/html/manage/sjhy/export";
		 document.getElementById("czlist").submit();
	}
</script>


<table cellspacing="2" cellpadding="0" class="tab2">
	<tr>
		<td class="tab2_top">
		</td>
	</tr>
	<tr>
		<td>
			<form method="post" id="czlist" name="form1" action="<%=request.getContextPath()%>/html/manage/sjhy/list">
				<input type="hidden" name="type" value="${type }" />
				<input type="hidden" name="pageNo" id="pageNo" value="${PAGE_INFO.pageNo }" />
				<table b="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="tab2_tou">
							<a href="<%=request.getContextPath()%>/html/manage/sjhy/list">
								<img src="<%=request.getContextPath()%>/Dress/img/biao_03.gif"
									b="0" /> </a>
						</td>
						<td class="chazhaofanshi1">
							按日期查询：
						</td>
						<td>
							<input name="btime" type="text" id="btime" readonly onclick=""
								class="bianji_3 date" style="font-size: 12px" value="${btime }" />
							-
							<input name="etime" type="text" id="etime" readonly onclick=""
								class="bianji_3 date" style="font-size: 12px" value="${etime }" />
						</td>
						<td class="tab2_tou">
							<a href="javascript:cz();"><img
									src="<%=request.getContextPath()%>/Dress/img/222.gif" b="0" />
							</a>
						</td>
						<td class="tab2_tou">
							<a href="javascript:exportt();"><img src="<%=request.getContextPath()%>/Dress/img/chu.GIF" border="0" /></a>
						</td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="_method" value="delete" />
			<table cellspacing="0" cellpadding="0" class="table4_da">
				<thead>
					<tr>
						<td width="140">日期</td>
						<td width="80">会员名称</td>
						<td width="80">资产动态</td>
						<td width="150">店铺名称</td>
						<td>详细说明</td>
						<td>交易单号</td>
					</tr>
				</thead>
				<c:forEach var='Info' items='${DATA}' varStatus='index'>
					<tr>
						<td>${Info.time}</td>
						<td>${Info.huiyuan.name}</td>
						<td>
							<c:if test="${Info.flag==0}">
								<span style="color:red; font-weight:bold;">${Info.money}</span>
							</c:if>
							<c:if test="${Info.flag==1}">
								<span style="color:green; font-weight:bold;">+${Info.money}</span>
							</c:if>
						</td>
						<td>${Info.merchant.name}</td>
						<td>${Info.ps}</td>
						<td>${Info.order.tradeNo}</td>
					</tr>
				</c:forEach>
				<jsp:include page="/Dress/include/nofenye.jsp">
					<jsp:param name="url" value="/html/manage/sjhy/list" />
				</jsp:include>
			</table>
		</td>
	</tr>
</table>
		