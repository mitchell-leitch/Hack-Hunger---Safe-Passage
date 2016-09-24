<html>
    <head>
        <title>Safe Passages</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<style>
body {
    font-family: 'Montserrat', sans-serif;
}
h1{
    text-transform: uppercase; letter-spacing: 0.06em; margin-bottom: 40px;
}

#gMap {
    width: 100%; height: 500px; margin-top: 40px; margin-bottom: 40px;
}

.info-window {
    font-family: 'Montserrat', sans-serif;
}
.info-content {
    color: #999;
}

</style>
    </head>
    <body>

        <div class="container">

            <div class="row">

                <div class="col-lg-8 col-lg-offset-2">

                    <h1><strong>Safe Passages</strong></h1>

            </div> 

        </div> 
        <div id="gMap"></div>
    <input type="file" id="filenameHeat" name="filenameHeat"/>
        <div class="container">

            <div class="row">

                <div class="col-lg-8 col-lg-offset-2">

                    <h3><strong>Who we did this for</strong></h3>

                    <p class="lead"><a href="http://www.usda.gov/wps/portal/usda/usdahome?contentid=2014/03/0048.xml" target="_blank">Only 11% of Eligible Kids in Illinois Participate in Summer Meal Program</a> (2014) Chicago’s participation appears higher than the state average, but still low.</p>

                    <h3><strong>Why we did this</strong></h3>

                    <p class="lead">We already know that violence in Chicago is changing the way that food is distributed in summer. 
Catholic Charities of the Archdiocese of Chicago petitioned for and was awarded a congregate feeding waiver due to gang activity and high rates of retaliatory violence in Chicago. According to Catholic Charities, “children were afraid to sit and eat… because of gang warfare and shootings in the area.” </p>


                    <h3><strong>What’s done about this now</strong></h3>

                    <p class="lead">Safe Passage at CPS has been around since 2009 to monitor kids walking to and from school in at-risk neighborhoods.  A new $112,500 initiative modeled after Safe Passage is being launched to watch over children headed to the parks for summer programs. </p>

                    <h3><strong>What we want to do</strong></h3>

                    <p class="lead">Seems reasonable that unsafe travel to/from the distribution sites would be a deterrent to participation in much the same way.  Let's see if we can quantify that assumption. </p>

                    <h3><strong>What we want to do</strong></h3>

                    <p class="lead">Seems reasonable that unsafe travel to/from the distribution sites would be a deterrent to participation in much the same way.  Let's see if we can quantify that assumption. </p>
                    
                </div>

            </div> 

        </div> 

        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3&amp;sensor=false&amp;key=AIzaSyCFZyhCp8lqfIeognHqe-iauOZLEhhzYjY&libraries=visualization"></script>
  <script>
    $(document).ready(function(){
      var markers = []; // define global array in script tag so you can use it in whole page    
      var infowindow = new google.maps.InfoWindow();
      var bounds = new google.maps.LatLngBounds();
      var myCenter=new google.maps.LatLng(41.881832,-87.623177);

      var mapProp = {
        center:myCenter, 
        zoom:12,
        minZoom:6,          
        mapTypeId : google.maps.MapTypeId.ROADMAP,
        mapTypeControl: true           
      };

      function infoContent(info){
        var infoString = '<h1>' + info.name + '</h1><br><b>' + 'Address:</b> ' + info.address + '<br><b>School:</b> ' + isTrue(info.school) + '<br><b>Breakfast:</b> ' + isAvailable(info.breakfast) + '<br><b>Lunch:</b> ' + isAvailable(info.lunch) + '<br><b>Supper:</b> ' + isAvailable(info.supper) + '<br><b>PM Snack:</b> ' + isAvailable(info.snack) + '<br><b>Distance from Safe Passage: </b> ' + info.distance+ '<br><b>ADP/Eligibles: </b> ' + info.engagement;
        return infoString;
      }

      function isTrue(data){
        if (data == 0){
          return 'False'
        }else{
          return 'True'
        }
      }

      function isAvailable(data){
        if (data == 0){
          return 'Not Available'
        }else{
          return 'Available'
        }
      }
      //google map object       
      var map = new google.maps.Map(document.getElementById("gMap"),mapProp);
    $("#filenameHeat").change(function(e) {
       var ext = $("input#filenameHeat").val().split(".").pop().toLowerCase();

       if($.inArray(ext, ["csv"]) == -1) {
          alert('Upload CSV');
          return false;
        }

       if (e.target.files != undefined) {

        var reader = new FileReader();
        reader.onload = function(e) {

              var csvval=e.target.result.split("\n");
              var csvvalue;                                          
              var heatmapPoints = [];
              for(var i = 1;i < csvval.length;i++)
              {
                  csvvalue = csvval[i].split(",");
                  var date = csvvalue[0];
                  var lat = csvvalue[1]; //latitude
                  var lng = csvvalue[2]; //longitude
                  
                  if (!isNaN(lat) && !isNaN(lng)){ 
                  var data = new google.maps.LatLng(lat,lng);
                  heatmapPoints.push(data);
                 }                  
               }
            var heatmap = new google.maps.visualization.HeatmapLayer({
              data: heatmapPoints,
              map: map
            });
         };
         reader.readAsText(e.target.files.item(0));
         }

       return false;

    });
      map.data.loadGeoJson('CPS_Safe_Passage_Routes_SY1516.geojson'); 
      var depositoriesApi = "http://10.1.106.135:8080/depositories/";

var styles = [{"featureType": "landscape", "stylers": [{"saturation": -100}, {"lightness": 65}, {"visibility": "on"}]}, {"featureType": "poi", "stylers": [{"saturation": -100}, {"lightness": 51}, {"visibility": "simplified"}]}, {"featureType": "road.highway", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "road.arterial", "stylers": [{"saturation": -100}, {"lightness": 30}, {"visibility": "on"}]}, {"featureType": "road.local", "stylers": [{"saturation": -100}, {"lightness": 40}, {"visibility": "on"}]}, {"featureType": "transit", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "administrative.province", "stylers": [{"visibility": "off"}]}, {"featureType": "water", "elementType": "labels", "stylers": [{"visibility": "on"}, {"lightness": -25}, {"saturation": -100}]}, {"featureType": "water", "elementType": "geometry", "stylers": [{"hue": "#ffff00"}, {"lightness": -25}, {"saturation": -97}]}];

        map.set('styles', styles);

      $.getJSON(depositoriesApi, function() {
        console.log( "success" );
      })

.done(function( item ) {

    $.each( item, function( i, item ) {
      markers[i] = []; 
      //item = item[i].split(",");
      markers[i][0] = item.name; //id
      info = [];
      info.name = item.name;
      info.address = item.address + ' ' + item.city + ', ' + item.state + ' ' + item.zip;
      info.school = item.isSchool;
      info.breakfast = item.hasBreakfast;
      info.lunch = item.hasLunch;
      info.supper = item.hasSupper;
      info.snack = item.hasPmSnack;
      info.distance = Math.round(item.nearestRouteDistance) + " meters";
      info.engagement = Math.round(item.engagementScore);
      var name = item.name;
      var lat = item.yCoordinate; //latitude
      var lng = item.xCoordinate; //longitude

      markers[i][1] = new google.maps.Marker({
        position: new google.maps.LatLng(lat,lng),
        animation: google.maps.Animation.DROP,
        map: map,
        title: name,
        info: info,                  
      });

      google.maps.event.addListener(markers[i][1], 'click', function () {
        infowindow.setContent(infoContent(this.info));
        infowindow.open(map, this);
      });

    });

  })
});








</script>
    </body>
</html>