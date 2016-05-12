<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Marker Animations</title>
    <style>
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
      }
    </style>
  </head>
  <body>
    <div id="map"></div>
    <script>

      // The following example creates a marker in Stockholm, Sweden using a DROP
      // animation. Clicking on the marker will toggle the animation between a BOUNCE
      // animation and no animation.
      	  var marker;
      	  var iconimg;
		  function initMap(){
			var map = new google.maps.Map(document.getElementById('map'), {
			  zoom: 4,
			  center: {lat: 34.0489, lng: -111.0937}
			});
		$.getJSON('PopulateTable', function (responseJson) {
			if(responseJson!=null)
				{
			        $.each(responseJson, function (i, value) {
			        	var lati = value['lat'];
						var longi = value['lon'];
						var t = value['threshold'];
						console.log(lati);
						console.log(longi);
						console.log(t);
						if(t==0)
						{
						iconimg = 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'}
						else {
						iconimg = ('http://maps.google.com/mapfiles/ms/icons/red-dot.png')
						}
						marker = new google.maps.Marker({
							map : map,
							draggable : false,
							icon : iconimg,
							position : {
							lat : lati,
							lng : longi,
							}
			        });
				});
				} 
			    });
      }
	function toggleBounce() {
		if (marker.getAnimation() !== null) {
			marker.setAnimation(null);
		    } else {
			marker.setAnimation(google.maps.Animation.BOUNCE);
		   }
		 }
      
      
    /*function addMarker(latitude,longitude) {
    	var marker = new google.maps.Marker({
			map: map,
			draggable: true,
			position: {lat: latitude, lng: longitude}
    });
   }*/
	/*$.get('PopulateTable',function(responseJson) {
    if(responseJson!=null){
                 $.each(responseJson, function(key,value) { 
    	                         
    	                  });
    	                  }
    	              });*/
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCKHmSc5CwYNhV0679gwzHO1zm_vgyKKw0&callback=initMap"></script>
  </body>
</html>