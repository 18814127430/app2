<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>下载</title>
</head>
<body>
	<script type="text/javascript">
			var browser={
				versions:function(){
					var u = navigator.userAgent, app = navigator.appVersion;
					return {
						trident: u.indexOf('Trident') > -1, //IE内核
						presto: u.indexOf('Presto') > -1, //opera内核
						webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
						gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
						mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //是否为移动终端
						ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
						android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
						iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
						iPad: u.indexOf('iPad') > -1, //是否iPad
						symbian: u.indexOf('Symbian') > -1, //是否Symbian
						webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部            
					};
				}()
			} 
			var isMobile = browser.versions.ios || browser.versions.android || browser.versions.symbian;
			var cWidth = document.body.clientWidth;
			var sWidth = window.screen.width;
			var tWidth = (document.body.clientWidth + window.screen.width)/2;		
			var fontSize = isMobile?parseInt(tWidth/9):parseInt(tWidth/30);
			var imgWidth = isMobile?parseInt(tWidth/1.5):parseInt(tWidth/4.5);
			var imgHeight = parseInt(imgWidth*(16/49));
			var imgWidth2 = isMobile?parseInt(tWidth/4):parseInt(tWidth/12);
			var imgHeight2 = parseInt(imgWidth2*(54/124));
			var cot = "<center><div style='border:0px solid red;padding-top:20px;'>";
			cot += "<div style='padding-top:10px;padding-bottom:10px;'></div>";
			cot += "<div style='font-size:"+fontSize+"px;padding-top:10px;padding-bottom:20px;'>";
			if(browser.versions.iPad || browser.versions.iPhone){
				cot += "<div><a href='/app/easyfresh.apk' id='actionTo'>苹果用户下载</a></div>";
			}else if(browser.versions.android){
				cot += "<div style='padding-top:5px;'><a href='/app/easyfresh.apk' id='actionTo'>安卓用户下载</a></div>";
			}else if(browser.versions.symbian){
				cot += "<div style='padding-top:5px;'><a href='/app/easyfresh.apk' id='actionTo'>塞班用户下载</a></div>";
			}else if(isMobile){
				cot += "<div style='padding-top:5px;'>目前没有适合您手机的软件</div>";
			}else{
				cot += "<div style='padding-top:5px;'><a href='/app/easyfresh.apk'>苹果用户下载</a></div>";
				cot += "<div style='padding-top:5px;'><a href='/app/easyfresh.apk'>安卓用户下载</a></div>";
				cot += "<div style='padding-top:5px;'><a href='/app/easyfresh.apk' id='actionTo'>塞班用户下载</a></div>";
			}
			//cot += "<div style='padding-top:5px;'>"+navigator.userAgent+"</div>";
			cot += "<div style='padding-top:20px;padding-bottom:0px;text-align:right;padding-right:5px'></div>"
			cot += "</div></div></center>";
			document.write(cot);
			if (/msie/i.test(navigator.userAgent)){ //ie brower 
				document.getElementById("actionTo").click(); 
			}else{ 
				var e = document.createEvent('MouseEvent');    
				e.initEvent('click', false, false);    
				setTimeout(document.getElementById("actionTo").dispatchEvent(e),2000);    
			}
	</script>
</body>

</html>

