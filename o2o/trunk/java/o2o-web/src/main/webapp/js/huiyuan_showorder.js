
/*
 * Copyright (c) 2009-2016 SHENZHEN Eternal Dynasty Technology Co.,Ltd.
 * All rights reserved.
 *  
 * This file contains valuable properties of  SHENZHEN Eternal Dynasty 
 * Technology Co.,Ltd.,  embodying  substantial  creative efforts  and 
 * confidential information, ideas and expressions.    No part of this 
 * file may be reproduced or distributed in any form or by  any  means,
 * or stored in a data base or a retrieval system,  without  the prior 
 * written permission  of  SHENZHEN Eternal Dynasty Technology Co.,Ltd.
 *
 */

$(function () {
	$("#wdsd").css("color", "#f0125b");
	$("#wdsd").css("background-color", "#e0e0e0");
});
function toIssue() {
	if ($(".img").length == 0) {
		$(".errMsg").css("display", "");
		return;
	}
	document.form1.submit();
}

