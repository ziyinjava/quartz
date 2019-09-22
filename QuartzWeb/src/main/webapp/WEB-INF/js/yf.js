/**
 * 修改： 根据弹层内容自适应宽高，也可指定宽高
 */
var Qt = {
	Dialog : function(c) {
		var t = document;
		var d = t.createElement('div');
		var cc = t.createElement('div');
		var p = t.createElement('div');
		var _p = t.createElement('div');
		var cont = null;
		var tp = null;
		var timer = null;
		var mousePos = null;
		var mouseOffset = null;
		var sa = null;
		var conf = null;
		var browser = null;
		var sels = null;
		if (c == 'undefined' || c == null)
			return null;
		else {
			// 浏览器检测
			if ($.browser.msie) {
				if (parseInt($.browser.version) > 6) {
					browser = 'ie_new';
				} else {
					browser = 'ie_old';
					// cc = t.createElement('iframe');
					// sels = $('select:visible').hide();
				}
			} else if ($.browser.mozilla) {
				browser = 'ff';
			} else {
				browser = 'others';
			}
			function resize() {
				conf.x = (parseInt($(window).width()) - parseInt(conf.width))
						/ 2 + $(window).scrollLeft();
				conf.y = (parseInt($(window).height()) - parseInt(conf.height))
						/ 2 + $(window).scrollTop();
				if (d) {
					$(d).css( {
						height : conf.height + 'px',
						width : conf.width + 'px',
						top : conf.y + 'px',
						left : conf.x + 'px'
					});
					$(cont).width('100%').height('100%');
				}
			}
			// 构造窗口
			function build() {
				if (c.cur) {
					cur();
					_p.appendChild(d);
				} else {
					t.body.appendChild(d);
				}
				if(document.activeElement.tagName != 'BODY') {
					$(document.activeElement).blur();
				}
				conf = {};
				conf.autoSize = true;
				if (c.autoSize != undefined) {
					conf.autoSize = c.autoSize;
				}
				conf.drag = c.drag ? c.drag : true;
				conf.capHeight = c.caption ? (c.caption.height ? c.caption.height
						: '15px')
						: '0px';
				$(d).css( {
					position : 'absolute',
					background : 'white'
				});// 暂时不显示弹出层
				$(window).bind('resize', ap);// 窗体改变大小时 遮罩层跟随改变
				cont = t.createElement('div');
				$(cont).css( {
					position : 'relative',
					top : 0,
					left : 0
				}).height('100%').width('100%');
				var content = t.createElement('div');
				$(content).css( {
					position : 'absolute',
					top : parseInt(conf.capHeight) + 'px',
					left : '0px'
				});
				if (c.className) {
					$(content).addClass(c.className);
				}
				if (c.css) {
					$(content).css(c.css);
				}
				if (c.url) {
					var frame = t.createElement('iframe');
					frame.frameBorder = 0;
					frame.scrolling = 'no';
					frame.src = c.url;
					frame.id = c.id || 'QT_CONTENT_FRAME', $(frame).css( {
						height : (parseInt(c.height)) + 'px',
						width : (parseInt(c.width)) + 'px'
					});
					content.appendChild(frame);
				} else if (c.innerDom) {
					content.appendChild(c.innerDom);
				} else {
					$(content).append('There is nothing to be showed...');
				}
				if (c.css) {
					$(content).css(c.css);
				}
				cont.appendChild(content);
				d.appendChild(cont);
				conf.width = parseInt(c.width);
				conf.height = parseInt(c.height);
				if (conf.autoSize) {
					conf.width = $(content).width();
					conf.height = $(content).height()
							+ parseInt(conf.capHeight);
				}
			}

			// 显示窗口
			function show() {
				build();
				if (c.caption) {
					var caption = t.createElement('div');
					$(caption)
							.css( {
								position : 'absolute',
								top : '0px',
								left : '0px',
								width : $(d).width() + 'px',
								background : '#3BB5F8'
							})
							.height(
									c.caption.height ? c.caption.height
											: '25px')
							.append(
									'<span style="float:left;margin:5px"><font size="2"><b>' + c.caption.title + '</b></font></span>')
					if (c.caption.className) {
						$(caption).addClass(c.caption.className);
					}
					if (c.caption.css) {
						$(caption).css(c.caption.css);
					}
					if (c.caption.closeBtn) {
						var cs = t.createElement('span');
						$(cs).css( {
							float : 'right',
							margin : '5px',
							cursor : 'pointer'
						}).bind('click', close)
						if (c.caption.closeBtn == 'text') {
							$(cs)
									.append(
											'<font size="2"><b>\u5173\u95ed</b></font>');
						} else {
							$(cs).append(
									'<img src="' + c.caption.closeBtn + '"/>')
						}
						caption.appendChild(cs);
					}
					cont.appendChild(caption);
				}

				if (conf.drag) {
					var fab = t.createElement('div');
					$(fab).css(
							{
								position : 'absolute',
								top : '0px',
								left : '0px',
								cursor : 'move',
								width : conf.width - 50 + 'px',
								height : parseInt(conf.capHeight) == 0 ? '25px'
										: parseInt(conf.capHeight) + 'px'
							});
					cont.appendChild(fab);
					// 注册拖拽事件
					$(fab).mousedown(function(e) {
						timer = window.setInterval(drag, 10);
						tp = t.createElement("div");
						d.style.MozUserSelect = 'none';
						d.style.MozUserFocus = 'ignore';
						if (d.setCapture) {
							d.setCapture();
						}
						$(tp).css( {
							position : 'absolute',
							top : 0,
							left : 0,
							filter : 'Alpha(opacity=10)',
							opacity : 0,
							zIndex : 999
						}).height('100%').width('100%');
						cont.appendChild(tp);
						getOffset(e);
						$(t).bind('mousemove', getMousePosition);
						$(t).bind('mouseup', unDrag);
					});
				}
				resize();
			}
			function ap() {
				$(p).width('100%');
			}
			// 关闭窗口
			function close() {
				if (c.cur) {
					$(window).unbind('resize', ap);
					t.body.removeChild(p);
				} else {
					t.body.removeChild(d);
				}
				if (sels != null) {
					sels.show();
				}
				d = null;
				t = null;
				p = null;
				_p = null;
				cc = null;
			}
			// 加入遮罩层
			function cur() {
				if (browser == 'ie_old') {
					sels = $('select:visible');
					sels.hide();
				}
				var _hp = document.documentElement.scrollHeight>document.documentElement.clientHeight?$(t).height():$(window).height();
				t.body.appendChild(p);
				$(p).css( {
					position : 'absolute',
					height : _hp + 'px',
					zIndex : 9999,
					top : 0,
					left : 0
				}).width('100%');
				p.appendChild(_p);
				$(_p).css( {
					position : 'relative',
					top : 0,
					left : 0
				}).height('100%').width('100%');
				_p.appendChild(cc);
				// if(cc.tagName != 'DIV') {
				// $(cc).attr("allowtransparency","true").attr("src","nothing.html");
				// $(cc.document.body).css({backgroundColor:"transparent"});
				// }
				$(cc).css( {
					position : 'absolute',
					background : 'gray',
					top : 0,
					left : 0,
					filter : 'Alpha(opacity=20)',
					opacity : 0.2
				}).height('100%').width('100%');
			}
			// 拖拽
			function drag() {
				d.style.position = 'absolute';
				var _x = mousePos.x - mouseOffset.x + $(window).scrollLeft();
				var _y = mousePos.y - mouseOffset.y + $(window).scrollTop();
				if (_y < 2 + $(window).scrollTop()) {
					_y = 2 + $(window).scrollTop();
				}
				if (_y > ($(window).scrollTop() + $(window).height() - conf.height) - 2) {
					_y = $(window).scrollTop()
							+ ($(window).height() - conf.height - 2);
				}
				if (_x < 2 + $(window).scrollLeft()) {
					_x = 2 + $(window).scrollLeft();
				}
				if (_x > $(window).scrollLeft()
						+ ($(window).width() - conf.width) - 2) {
					_x = $(window).scrollLeft()
							+ ($(window).width() - conf.width - 2);
				}
				d.style.left = _x + 'px';
				d.style.top = _y + 'px';
			}
			// 拖拽结束
			function unDrag() {
				if (tp == null || tp == 'undefined')
					return false;
				if (d.releaseCapture) {
					d.releaseCapture();
				}
				window.clearInterval(timer);
				d.style.MozUserSelect = '';
				d.style.MozUserFocus = '';
				cont.removeChild(tp);
				tp = null;
				$(t).unbind('mousemove', getMousePosition);
				$(t).unbind('mouseup', unDrag);
			}
			// 鼠标位置记录
			function getMousePosition(e) {
				e = e || window.event;
				if (e.pageX || e.pageY) {
					mousePos = {
						x : e.pageX,
						y : e.pageY
					};
				}
				mousePos = {
					x : e.clientX + document.body.scrollLeft
							- document.body.clientLeft,
					y : e.clientY + document.body.scrollTop
							- document.body.clientTop
				};
			}
			// 计算偏移量及拖拽范围
			function getOffset(e) {
				var docPos = getPosition(d);
				getMousePosition(e);
				mouseOffset = {
					x : mousePos.x - docPos.x
							+ parseInt($(window).scrollLeft()),
					y : mousePos.y - docPos.y + parseInt($(window).scrollTop())
				};
			}
			// 控件的绝对位置
			function getPosition(e) {
				var left = 0;
				var top = 0;
				while (e.offsetParent) {
					left += e.offsetLeft;
					top += e.offsetTop;
					e = e.offsetParent;
				}
				left += e.offsetLeft;
				top += e.offsetTop;
				return {
					x : left,
					y : top
				};
			}
			return {
				show : show,
				close : close
			};
		}

	},
	alert : function(_c, _s, handler) {
		var doc = document;
		_s = _s == 'success' ? 'ico_dui' : 'icon_1';
		var c = doc.createElement('div');
		$(c)
				.append(
						'<div class="layer_agree layer_agreeww"><div class="layer_off"><a href="javascript:void(0)"><img src="http://www.specl.com/images/off_ico.gif" /></a></div>'
								+ '<dl style="margin:20px auto;"><dt class="f_l"><span class="'
								+ _s
								+ '" style="margin:0 0 10px 20px;">&nbsp;</span></dt>'
								+ '<dd class="f_l" style="width:170px"><p class="font14">'
								+ _c
								+ '</p></dd><div class="clear"></div><dd style="width:100%;text-align:center;"><p class="mar_"><input type="button" value="确定" class="charbgbtn" /></p></dd></dl><div>');
		var dialog = new Qt.Dialog( {
			innerDom : c,
			cur : true
		});
		dialog.show();
		$(c).find(':button').bind('click', function() {
			if (handler)
				handler();
			dialog.close();
		});
		$(c).find('a').click(function() {
			dialog.close();
		});
	},
	confirm : function(_c, handler, param) {
		var doc = document;
		var c = doc.createElement('div');
		$(c)
				.addClass('layer_agree')
				.addClass('layer_agreeww')
				.append(
						'<div class="layer_off"><a href="javascript:void(0)"><img src="http://www.specl.com/images/off_ico.gif" /></a></div>'
								+ '<dl class="font14 marginim" style="text-align:center;"><dt class="f_l"><span class="ico_qustion">&nbsp;</span></dt>'
								+ '<dd class="f_l" style="width:185px;text-align:left;"><p>'
								+ _c
								+ '</p></dd><div class="clear"></div><dd>'
								+ '<p style="width:100%;"><input type="button" value="确定" class="charbgbtn mar_r" />'
								+ '<input type="button" value="取消" class="charbgbtn" /></p></dd></dl>');
		var dialog = new Qt.Dialog( {
			innerDom : c,
			cur : true
		});
		dialog.show();
		$(c).find(':button').first().bind('click', function() {
			dialog.close();
			handler(true, param);
		});
		$(c).find(':button').last().bind('click', function() {
			dialog.close();
			handler(false, param);
		});
		$(c).find('a').click(function() {
			dialog.close();
		});
	}
};
