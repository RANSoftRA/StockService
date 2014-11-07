var CanvasGraph = function() {

}

CanvasGraph.prototype.graph;
CanvasGraph.prototype.xPadding = 30;
CanvasGraph.prototype.yPadding = 30;
CanvasGraph.prototype.data;

CanvasGraph.prototype.draw = function(data) {
	
	this.data = data;
	this.graph = document.getElementById("graph");

	
	var c = this.graph.getContext("2d");
	
	// Clearing the Canvas before draw
	c.clearRect(0,0,this.graph.width, this.graph.height);
	c.lineWidth = 2;
	c.strokeStyle = '#333';
	c.font = 'italic 8pt sans-serif';
	c.textAlign = "center";

	c.beginPath();
	c.moveTo(this.xPadding, 0);
	c.lineTo(this.xPadding, this.graph.height - this.yPadding);
	
	
	c.lineTo(this.graph.width, this.graph.height - this.yPadding);
	c.stroke();

	// Draw the X value texts
	var pos = 0;
	for (var i = (this.data.length-1); i>=0 ; i--) {
		c.fillText(this.data[i].date.substring(5), this.getXPixel(pos), this.graph.height - this.yPadding + 20);
		pos++;
	}

	// Draw the Y value texts
	c.textAlign = "right"
	c.textBaseline = "middle";
	
	//set spacing on Y 
	var ySpacing;
	if(this.getMaxY()>300){
		ySpacing=30;
	}else if(this.getMaxY()>100){
		ySpacing=20;
	}else if(this.getMaxY()>50){
		ySpacing=10;	
	}else{
		ySpacing=5;	
	}
	
	//write Y values
	for (var i = 0; i < this.getMaxY(); i += ySpacing) {
		c.fillText(i, this.xPadding - 10, this.getYPixel(i));
	}
				
	

	c.strokeStyle = '#f00';

	// Draw the line graph
	c.beginPath();
	c.moveTo(this.getXPixel(0), this.getYPixel(data[data.length-1].value));
	pos = 0;
	for (var i = data.length-1; i >0; i--) {
		c.lineTo(this.getXPixel(pos), this.getYPixel(data[i].value));
		pos++;
	}
	c.stroke();

	// Draw the dots
	c.fillStyle = '#333';
	pos = 0;
	for (var i = (data.length-1); i >0; i--) {
		c.beginPath();
		c.arc(this.getXPixel(pos), this.getYPixel(data[i].value), 4, 0, Math.PI * 2, true);
		c.fill();
		pos++;
	}

}

// Returns the max Y value in our data list
CanvasGraph.prototype.getMaxY = function() {
	var max = 0;

	for (var i = 0; i < this.data.length; i++) {
		if (this.data[i].value > max) {
			max = this.data[i].value;
		}
	}

	max += 10 - max % 10;
	return max;
}

// Return the x pixel for a graph point
CanvasGraph.prototype.getXPixel = function(val) {
	return ((this.graph.width - this.xPadding) / this.data.length) * val + (this.xPadding * 1.5);
}

// Return the y pixel for a graph point
CanvasGraph.prototype.getYPixel = function(val) {
	return this.graph.height - (((this.graph.height - this.yPadding) / this.getMaxY()) * val) - this.yPadding;
}
