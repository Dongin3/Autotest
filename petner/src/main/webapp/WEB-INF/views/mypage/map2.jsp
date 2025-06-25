<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>다음지도 - 현재 GPS상에서 반경2km 이내의 업체들 거리, 도보/자전거 이동시간 구하기</title>
</head>
<body>
<div id="map" style="width:100%;height:500px;"></div>
<div id="coordXY"></div>
<div id="distance"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e2d6fc6008c147d8c5d81603f2166c5d&libraries=services"></script>
<script>
function
var mapContainer = document.getElementById("map");
var coordXY   = document.getElementById("coordXY");
var distanceGap  = document.getElementById("distance");
var radius = 2000;	// 반경 미터(m), 2km
/////////////////지도의 중심을 현재 위치로 변경///////////////////////
// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        var x = position.coords.latitude, y = position.coords.longitude; // x:위도, y:경도
        var latlngyo = new daum.maps.LatLng(x, y);
        var mapOption = {
          		center: latlngyo, // 지도의 중심좌표
                level: 6      // 지도의 확대 레벨
         };
        var lat = x;
        var lng = y;
        //좌표 > 도로명주소
        getAddr(lat,lng);
        function getAddr(lat,lng){
            let geocoder = new kakao.maps.services.Geocoder();
            let coord = new kakao.maps.LatLng(lat, lng);
            let callback = function(result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    console.log(result[0].address.address_name);
                    console.log(result[0].address.region_1depth_name);
                    console.log(result[0].address.region_2depth_name);
                    console.log(result[0].address.region_3depth_name);
                    if(!result[0].road_address.zone_no){
                    	alert("현재 위치의 정보를 불러올 수 없습니다.");
                    }else{
                    	console.log(result[0].road_address.zone_no);
                    	 $("#zipcodeP").text("["+result[0].road_address.zone_no+"]");
		                 $("#zipcode").val(result[0].road_address.zone_no);
                    }
                    $("#addrP").text(result[0].address.address_name);
                }
               //searchAjax();
            }
            geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
        }
        
        var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

        var circle = new daum.maps.Circle({
            map: map,
            center : latlngyo,
            radius: radius,
            strokeWeight: 2,
            strokeColor: '#FF00FF',
            strokeOpacity: 0.8,
            strokeStyle: 'dashed',
            fillColor: '#D3D5BF',
            fillOpacity: 0.5
        });

        var marker = new daum.maps.Marker({
         position: latlngyo, // 마커의 좌표
         title: "현위치",
         map: map          // 마커를 표시할 지도 객체
        });

        var arr = new Array();
        arr[0] = ["테스트1",37.6397252,126.671359, "대구 달서구 장기동 790"];
        arr[1] = ["테스트2",35.8502186751,128.516473546, "대구 달서구 장기동 162-1"];
        arr[2] = ["테스트3",35.8507674215,128.520114592, "대구 달서구 용산동 410-9"];
        arr[3] = ["테스트4",35.8491570477,128.528283511, "대구 달서구 용산동 215-9"];
        arr[4] = ["테스트5",35.854902859257784,128.5296955671568, "대구 달서구 용산동 955"];

        var markerTmp;      // 마커
        var polyLineTmp;    // 두지점간 직선거리
        var distanceArr = new Array();
        var distanceStr = "";

        for (var i=0;i<arr.length;i++) {
            markerTmp = new daum.maps.Marker({
                position: new daum.maps.LatLng(arr[i][1],arr[i][2]),
                title: arr[i][0],
                map:map
            });

            polyLineTmp = new daum.maps.Polyline({
                map: map,
                path: [
                    latlngyo, new daum.maps.LatLng(arr[i][1],arr[i][2])
                ],
                strokeWeight: 2,   
                strokeColor: '#FF00FF',
           strokeOpacity: 0.8,
           strokeStyle: 'dashed'
            });
        }
        
        coordXY.innerHTML = "<br>현재 GPS X좌표 : " + x + " , Y좌표 : " + y + " ( 2km 반경 )<br><br>";
        distanceGap.innerHTML = distanceStr;
    });//navigator.geolocation.getCurrentPosition
    
} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

	var locPosition = new kakao.maps.LatLng(37.4812845080678, 126.952713197762),
		message = '현재 위치를 알 수 없어 기본 위치로 이동합니다.'

	currentLatLon['lat'] = 33.450701
	currentLatLon['lon'] = 126.570667

	displayMarker(locPosition, message);
}
/////////////////////////////////////////////////////////////////////////////////
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('경기 성남시 분당구 판교역로 235 에이치스퀘어', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		var message = 'latlng: new kakao.maps.LatLng(' + result[0].y + ', ';
		message += result[0].x + ')';
		
		var resultDiv = document.getElementById('clickLatlng'); 
		resultDiv.innerHTML = message;
		
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">장소</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
}); 



</script>
</body>
</html>