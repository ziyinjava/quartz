/**
 * 复制到剪切板
 * @param id 
 * @returns
 */
function copyToClipboard(id) {
	var txt = $('#' + id).html();
	if(window.clipboardData) {
		window.clipboardData.clearData();
		window.clipboardData.setData("Text", txt);
		
	} else if(navigator.userAgent.indexOf("Opera") != -1) {
		window.location = txt;

	} else if (window.netscape) {
		try {
	　　		netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	　　} catch (e) {
	　　		alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
	　　}
	
		try{
			var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
		　　if (!clip)
		　　		return;
		}catch(e){
			
		}
	　　
	　　var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
	　　if (!trans)
	　　		return;
	　　trans.addDataFlavor('text/unicode');
	　　var str = new Object();
	　　var len = new Object();
	　　var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
	　　var copytext = txt;
	　　str.data = copytext;
	　　trans.setTransferData("text/unicode",str,copytext.length*2);
	　　var clipid = Components.interfaces.nsIClipboard;
	　　if (!clip)
	　　		return false;
	
	　　clip.setData(trans,null,clipid.kGlobalClipboard);
	}
	alert("复制成功,您可以粘贴到浏览器地址栏了^_^");
}

/**
 * 初始化复制剪切板
 */
function initClip(){
	var clip = new ZeroClipboard.Client();
	clip.setHandCursor(true);
	clip.addEventListener('complete', function (){alert("复制成功,您可以粘贴到浏览器地址栏了^_^");});
	clip.addEventListener( "mouseOver", function(client) {
	    var url = $('#' + id).html();
	    alert(url);
	    client.setText(url); // 重新设置要复制的值
	});
	clip.glue('copylink');
}

/**
 * 构造tinybox组件的弹出框
 * @param content 弹出的内容
 */
function loading(content) {
		var dom = document.createElement('div');
		$(dom).append(content).css({width:'350px',height:'250px'});
		var content1 = "<a href=\"javascript:void(0);\" onclick=\"closeBox();\" style=\"float:right;color:#EA2E34\">关闭</a>" +
			"<br/><span>"+content;
		
		content1 = content1 + "</span>";
        TINY.box.show(content1,0,600,400,0);
}

function loadingChangeSort(id,sort,max,callback) {
	var cstr= callback+"("+id+","+max+")";
	var content = "<a href=\"javascript:void(0);\" onclick=\"closeBox();\" style=\"float:right;color:#EA2E34\">关闭</a><br/>";
    content = content + "更改排序:</br><input type='text' value="+sort+" maxlength=10 id='changeSortValue' /> <span class=\"h_button_bg1\"><a href='javascript:"+ cstr +"' id='changeSortBtn'>修改</a></span>";
    TINY.box.show(content,0,350,120,0);
}

/**
 * 关闭tinybox组件的弹出框
 */
function closeBox(){
	TINY.box.hide();
}
