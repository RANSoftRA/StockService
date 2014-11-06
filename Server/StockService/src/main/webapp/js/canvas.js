	var graph;
    var xPadding = 30;
    var yPadding = 30;	
	var data=[{"symbol":"AAPL","date":"2014-11-05","value":108.86},{"symbol":"AAPL","date":"2014-11-04","value":108.6},{"symbol":"AAPL","date":"2014-11-03","value":109.4},{"symbol":"AAPL","date":"2014-10-31","value":108},{"symbol":"AAPL","date":"2014-10-30","value":106.98},{"symbol":"AAPL","date":"2014-10-29","value":107.34},{"symbol":"AAPL","date":"2014-10-28","value":106.74},{"symbol":"AAPL","date":"2014-10-27","value":105.11},{"symbol":"AAPL","date":"2014-10-24","value":105.22},{"symbol":"AAPL","date":"2014-10-23","value":104.83},{"symbol":"AAPL","date":"2014-10-22","value":102.99},{"symbol":"AAPL","date":"2014-10-21","value":102.47},{"symbol":"AAPL","date":"2014-10-20","value":99.76},{"symbol":"AAPL","date":"2014-10-17","value":97.67},{"symbol":"AAPL","date":"2014-10-16","value":96.26},{"symbol":"AAPL","date":"2014-10-15","value":97.54},{"symbol":"AAPL","date":"2014-10-14","value":98.75},{"symbol":"AAPL","date":"2014-10-13","value":99.81},{"symbol":"AAPL","date":"2014-10-10","value":100.73},{"symbol":"AAPL","date":"2014-10-09","value":101.02},{"symbol":"AAPL","date":"2014-10-08","value":100.8},{"symbol":"AAPL","date":"2014-10-07","value":98.75},{"symbol":"AAPL","date":"2014-10-06","value":99.62}];

function draw(){	
		
		graph=document.getElementById("graph");
		var c=graph.getContext("2d");	
		
		 c.lineWidth = 2;
         c.strokeStyle = '#333';
         c.font = 'italic 8pt sans-serif';
         c.textAlign = "center";		 
		 
        c.beginPath();
        c.moveTo(xPadding, 0);
        c.lineTo(xPadding, 400 - yPadding);
        c.lineTo(600, 400 - yPadding);
        c.stroke();
		
		// Draw the X value texts
        for(var i = 0; i < data.length; i ++) {
            c.fillText(data[i].date, getXPixel(i), 400 - yPadding + 20);
        }
		
		// Draw the Y value texts
                c.textAlign = "right"
                c.textBaseline = "middle";
                
                for(var i = 0; i < getMaxY(); i += 10) {
                    c.fillText(i, xPadding - 10, getYPixel(i));
                }
                
                c.strokeStyle = '#f00';
                
                // Draw the line graph
                c.beginPath();
                c.moveTo(getXPixel(0), getYPixel(data[0].value));
                for(var i = 1; i < data.length; i ++) {
                    c.lineTo(getXPixel(i), getYPixel(data[i].value));
                }
                c.stroke();
                
                // Draw the dots
                c.fillStyle = '#333';
                
                for(var i = 0; i < data.length; i ++) {  
                    c.beginPath();
                    c.arc(getXPixel(i), getYPixel(data[i].value), 4, 0, Math.PI * 2, true);
                    c.fill();
                }
               
}

            // Returns the max Y value in our data list
function getMaxY() {
    var max = 0;
                
    for(var i = 0; i < data.length; i ++) {
        if(data[i].value > max) {
            max = data[i].value;
        }
	}
                
    max += 10 - max % 10;
    return max;
}
            
// Return the x pixel for a graph point
function getXPixel(val) {
    return ((600 - xPadding) / data.length) * val + (xPadding * 1.5);
}

// Return the y pixel for a graph point
function getYPixel(val) {
    return 400 - (((400 - yPadding) / getMaxY()) * val) - yPadding;
}

