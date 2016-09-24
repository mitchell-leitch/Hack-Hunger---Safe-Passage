        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Safe Passage</title>
        <script src="http://code.jquery.com/jquery-latest.min.js" type="application/javascript"></script>
        <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCFZyhCp8lqfIeognHqe-iauOZLEhhzYjY&sensor=false"></script>
        <script>
            $(document).ready(function(){
                var markers = []; // define global array in script tag so you can use it in whole page    
                var infowindow = new google.maps.InfoWindow();
                var bounds = new google.maps.LatLngBounds();
                var myCenter=new google.maps.LatLng(41.881832,-87.623177);
                var mapProp = {
                        center:myCenter, 
                        zoom:11,
                        minZoom:6,          
                        mapTypeId : google.maps.MapTypeId.ROADMAP,
                        mapTypeControl: true           
                    };
                //google map object       
                 var map = new google.maps.Map(document.getElementById("gMap"),mapProp); 

                  //change event of input tag where type=file and  id=filename 
                  $("#filename").change(function(e) {

                     var ext = $("input#filename").val().split(".").pop().toLowerCase();

                     if($.inArray(ext, ["csv"]) == -1) {
                            alert('Upload CSV');
                            return false;
                      }

                     if (e.target.files != undefined) {

                            var reader = new FileReader();
                            reader.onload = function(e) {

                                      var csvval=e.target.result.split("\n");
                                      var csvvalue;                                          

                                      for(var i = 0;i < csvval.length;i++)
                                      {
                                              markers[i] = []; 
                                              csvvalue = csvval[i].split(",");
                                              markers[i][0] = csvvalue[0]; //id
                                              var name = csvvalue[0];
                                              var lat = csvvalue[4]; //latitude
                                              var lng = csvvalue[5]; //longitude
                                              markers[i][1] = new google.maps.Marker({
                                                   position: new google.maps.LatLng(lat,lng),
                                                   map: map,
                                                   title: name,
                                                   info: name,                  
                                              });
                                              google.maps.event.addListener(markers[i][1], 'click', function () {
                                                infowindow.setContent(this.info);
                                                  infowindow.open(map, this);
                                              });
                                       }

                             };
                             reader.readAsText(e.target.files.item(0));
                       }

                     return false;

                    });
            });
        </script>
        </head>

        <body>
            <div style="height:700px; width:1500px;" id="gMap">
            </div>
            <input type="file" id="filename" name="filename"/>
        </body>
        </html>