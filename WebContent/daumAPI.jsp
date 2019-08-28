<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="assets/css/main.css" />
<title>닫기가 가능한 커스텀 오버레이</title>
<style>
.wrap {
	position: absolute;
	left: 0;
	bottom: 40px;
	width: 288px;
	height: 132px;
	margin-left: -144px;
	text-align: left;
	overflow: hidden;
	font-size: 12px;
	font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
	line-height: 1.5;
}

.wrap * {
	padding: 0;
	margin: 0;
}

.wrap .info {
	width: 286px;
	height: 120px;
	border-radius: 5px;
	border-bottom: 2px solid #ccc;
	border-right: 1px solid #ccc;
	overflow: hidden;
	background: #fff;
}

.wrap .info:nth-child(1) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.info .title {
	padding: 5px 0 0 10px;
	height: 30px;
	background: #eee;
	border-bottom: 1px solid #ddd;
	font-size: 18px;
	font-weight: bold;
}

.info .close {
	position: absolute;
	top: 10px;
	right: 10px;
	color: #888;
	width: 17px;
	height: 17px;
	background:
		url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}

.info .close:hover {
	cursor: pointer;
}

.info .body {
	position: relative;
	overflow: hidden;
}

.info .desc {
	position: relative;
	margin: 13px 0 0 90px;
	height: 75px;
}

.desc .ellipsis {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.desc .jibun {
	font-size: 11px;
	color: #888;
	margin-top: -2px;
}

.info .img {
	position: absolute;
	top: 6px;
	left: 5px;
	width: 73px;
	height: 71px;
	border: 1px solid #ddd;
	color: #888;
	overflow: hidden;
}

.info:after {
	content: '';
	position: absolute;
	margin-left: -12px;
	left: 50%;
	bottom: 0;
	width: 22px;
	height: 12px;
	background:
		url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}

.info .link {
	color: #5085BB;
}
</style>
</head>
<body>
	<div id="map" style="width: 100%; height: 780px;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d5361ac9ac2c30d9d1c1750e254adc13"></script>
	<script>
		window.onload = function() {
			closeOverlay();
			closeOverlay1();
			closeOverlay2();
			closeOverlay3();
			closeOverlay4();
		}

		function rentSpot(data) {
			var xhttp = new XMLHttpRequest();
			var data;
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("spot").innerHTML = this.responseText;
				}
			}
			xhttp.open("GET",
					"/project01_biking/bikingcontroller?command=getRentSpot&rentSpotName="
							+ data, true);
			xhttp.send();
		}
	</script>


	<script>
		var mapContainer = document.getElementById('map'), // 지도의 중심좌표
		mapOption = {
			center : new kakao.maps.LatLng(37.49241777400508,
					127.01815187612493), // 지도의 중심좌표
			level : 4
		// 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 지도에 마커를 표시합니다 
		var marker = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(37.491824210507716,
					127.00832248880448)
		});

		// 커스텀 오버레이에 표시할 컨텐츠 입니다
		// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		// 별도의 이벤트 메소드를 제공하지 않습니다 

		var content = '<div class="wrap">'
				+ '    <div class="info">'
				+ '        <div class="title">'
				+ '            서초역 1번 출구 앞'
				+ '            <div class="close" onclick="closeOverlay()" title="닫기"></div>'
				+ '        </div>'
				+ '        <div class="body">'
				+ '            <div class="img">'
				+ '                <img src="http://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">'
				+ '           </div>'
				+ '            <div class="desc">'
				+ '                <div class="ellipsis"><div id="spot"></div></div>'
				+ '                <div class="jibun ellipsis">서울 서초구 서초대로 240</div>'
				+ '                <div><a href="bikingcontroller?command=getBike&rentSpotName=서초1">대여</a></div>'
				+ '            </div>' + '        </div>' + '    </div>'
				+ '</div>';

		// 마커 위에 커스텀오버레이를 표시합니다
		// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		var overlay = new kakao.maps.CustomOverlay({
			content : content,
			map : map,
			position : marker.getPosition()
		});

		// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
		kakao.maps.event.addListener(marker, 'click', function() {
			closeOverlay();
			closeOverlay1();
			closeOverlay2();
			closeOverlay3();
			closeOverlay4();
			overlay.setMap(map);
			rentSpot("서초1");

		});

		//커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
		function closeOverlay() {
			overlay.setMap(null);
		}

		//지도에 마커를 표시합니다 
		var marker1 = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(37.494166023007764,
					127.01611684441386)
		});

		//커스텀 오버레이에 표시할 컨텐츠 입니다
		var content = '<div class="wrap">'
				+ '    <div class="info">'
				+ '        <div class="title">'
				+ '            교대역 1번출구 앞'
				+ '            <div class="close" onclick="closeOverlay1()" title="닫기"></div>'
				+ '        </div>'
				+ '        <div class="body">'
				+ '            <div class="img">'
				+ '                <img src="http://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">'
				+ '           </div>'
				+ '            <div class="desc">'
				+ '                <div class="ellipsis"><div id="spot"></div></div>'
				+ '                <div class="jibun ellipsis">서울특별시 서초구 서초대로 314</div>'
				+ '                <div><a href="bikingcontroller?command=getBike&rentSpotName=교대1">대여</a></div>'
				+ '            </div>' + '        </div>' + '    </div>'
				+ '</div>';

		//마커 위에 커스텀오버레이를 표시합니다
		//마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		var overlay1 = new kakao.maps.CustomOverlay({
			content : content,
			map : map,
			position : marker1.getPosition()
		});

		//마커를 클릭했을 때 커스텀 오버레이를 표시합니다
		kakao.maps.event.addListener(marker1, 'click', function() {
			closeOverlay();
			closeOverlay1();
			closeOverlay2();
			closeOverlay3();
			closeOverlay4();
			overlay1.setMap(map);
			rentSpot("교대1");

		});

		//커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
		function closeOverlay1() {
			overlay1.setMap(null);
		}

		//지도에 마커를 표시합니다 
		var marker2 = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(37.48917434450379,
					127.01681682635304)
		});

		//커스텀 오버레이에 표시할 컨텐츠 입니다
		var content = '<div class="wrap">'
				+ '    <div class="info">'
				+ '        <div class="title">'
				+ '            교대 정문 앞'
				+ '            <div class="close" onclick="closeOverlay2()" title="닫기"></div>'
				+ '        </div>'
				+ '        <div class="body">'
				+ '            <div class="img">'
				+ '                <img src="http://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">'
				+ '           </div>'
				+ '            <div class="desc">'
				+ '                <div class="ellipsis"><div id="spot"></div></div>'
				+ '                <div class="jibun ellipsis">서울 서초구 서초동 1650</div>'
				+ '                <div><a href="bikingcontroller?command=getBike&rentSpotName=교대정문">대여</a></div>'
				+ '            </div>' + '        </div>' + '    </div>'
				+ '</div>';

		//마커 위에 커스텀오버레이를 표시합니다
		//마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		var overlay2 = new kakao.maps.CustomOverlay({
			content : content,
			map : map,
			position : marker2.getPosition()
		});

		//마커를 클릭했을 때 커스텀 오버레이를 표시합니다
		kakao.maps.event.addListener(marker2, 'click', function() {
			closeOverlay();
			closeOverlay1();
			closeOverlay2();
			closeOverlay3();
			closeOverlay4();
			overlay2.setMap(map);
			rentSpot("교대정문");

		});

		//커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
		function closeOverlay2() {
			overlay2.setMap(null);
		}

		//지도에 마커를 표시합니다 
		var marker3 = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(37.48725594519568,
					127.01030354088007)
		});

		//커스텀 오버레이에 표시할 컨텐츠 입니다
		var content = '<div class="wrap">'
				+ '    <div class="info">'
				+ '        <div class="title">'
				+ '            교대 입구 교차로'
				+ '            <div class="close" onclick="closeOverlay3()" title="닫기"></div>'
				+ '        </div>'
				+ '        <div class="body">'
				+ '            <div class="img">'
				+ '                <img src="http://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">'
				+ '           </div>'
				+ '            <div class="desc">'
				+ '                <div class="ellipsis"><div id="spot"></div></div>'
				+ '                <div class="jibun ellipsis">서울 서초구 반포대로 76</div>'
				+ '                <div><a href="bikingcontroller?command=getBike&rentSpotName=교대입구">대여</a></div>'
				+ '            </div>' + '        </div>' + '    </div>'
				+ '</div>';

		//마커 위에 커스텀오버레이를 표시합니다
		//마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		var overlay3 = new kakao.maps.CustomOverlay({
			content : content,
			map : map,
			position : marker3.getPosition()
		});

		//마커를 클릭했을 때 커스텀 오버레이를 표시합니다
		kakao.maps.event.addListener(marker3, 'click', function() {
			closeOverlay();
			closeOverlay1();
			closeOverlay2();
			closeOverlay3();
			closeOverlay4();
			overlay3.setMap(map);
			rentSpot("교대입구");
		});

		//커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
		function closeOverlay3() {
			overlay3.setMap(null);

		}

		//지도에 마커를 표시합니다 
		var marker4 = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(37.492617548785645,
					127.02224253876764)
		});

		//커스텀 오버레이에 표시할 컨텐츠 입니다
		var content = '<div class="wrap">'
				+ '    <div class="info">'
				+ '        <div class="title">'
				+ '            교대 입구 교차로'
				+ '            <div class="close" onclick="closeOverlay4()" title="닫기"></div>'
				+ '        </div>'
				+ '        <div class="body">'
				+ '            <div class="img">'
				+ '                <img src="http://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">'
				+ '           </div>'
				+ '            <div class="desc">'
				+ '                <div class="ellipsis"><div id="spot"></div></div>'
				+ '                <div class="jibun ellipsis">서울 서초구 서초대로64길 73</div>'
				+ '                <div><a href="bikingcontroller?command=getBike&rentSpotName=교대교차로">대여</a></div>'
				+ '            </div>' + '        </div>' + '    </div>'
				+ '</div>';

		//마커 위에 커스텀오버레이를 표시합니다
		//마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		var overlay4 = new kakao.maps.CustomOverlay({
			content : content,
			map : map,
			position : marker4.getPosition()
		});

		//마커를 클릭했을 때 커스텀 오버레이를 표시합니다
		kakao.maps.event.addListener(marker4, 'click', function() {
			closeOverlay();
			closeOverlay1();
			closeOverlay2();
			closeOverlay3();
			closeOverlay4();
			overlay4.setMap(map);
			rentSpot("교대교차로");
		});

		//커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
		function closeOverlay4() {
			overlay4.setMap(null);

		}
	</script>
	<div>
		<p></p>
		<ul class="actions special">
			<li><a href="idCheck.jsp" class="button wide">뒤로 가기</a></li>
		</ul>
	</div>
</body>
</html>