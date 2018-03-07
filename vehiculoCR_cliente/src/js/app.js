var app = new Vue({
	el: "#app",
	data:{
       superficie:{x:4,y:4},
	   practica : {vehiculo:{posicionX:0,posicionY:0}},
       comando: "",
       vehiculo:{posX:0,posY:0},
	   dimensionXuser : 4,
	   dimensionYuser :4,
	   mensajeProceso:"",
	   lienzo: `<table border="1" style="width: 80%">
					<tr >
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td><img src="img/vehiculo.png"  height="25" width="25"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>` 
	   

	},
	
	created :function(){
		var self = this;
		$.ajax({
			  url: 'http://localhost:8080/iniciarpractica',
			  type: 'POST',
			  contentType: 'application/json',
			  data: JSON.stringify({dimensionX: 4, dimensionY:4})
			})
			.done(function(data) {
              self.practica = data;				
              self.dibujarSuperficie();			
			  console.log("success "+data);			  
			})
			.fail(function(err) {
			  console.log("error: "+err);
			});
	},
	computed:{	
	}
	,
	methods:{
		configurarSuperficie:function(){	
            var self = app; 
			$.ajax({
			  url: 'http://localhost:8080/iniciarpractica',
			  type: 'POST',
			  contentType: 'application/json',
			  data: JSON.stringify({dimensionX: self.dimensionXuser, dimensionY: self.dimensionYuser})
			})
			.done(function(data) {
              self.practica = data;				
              self.dibujarSuperficie();			
			  console.log("success "+data);			  
			})
			.fail(function(err) {
			  console.log("error: "+err);
			});
			
		},
		dibujarSuperficie: function(){
			this.lienzo = '';			
			this.lienzo += '<table border="1" style="width: 80%">';
			for(var i = 0;i< this.dimensionXuser; i++){
				this.lienzo += '<tr>';
				for(var j = 0; j<this.dimensionYuser; j++){
					this.lienzo += (i===this.dimensionXuser -1 && j ===0 ) ? '<td><img src="img/vehiculo.png"  height="25" width="25"/></td>':'<td>&nbsp;</td>';
				}
				this.lienzo += '</tr>';
			}
			this.lienzo +='</table>';
		}
		,
		ejecutarComando:function(){
			var self = app; 
			self.mensajeProceso="";
			self.practica.consola.comando = self.comando;
			$.ajax({
			  url: 'http://localhost:8080/corrercomando',
			  type: 'POST',
			  contentType: 'application/json',
			  data: JSON.stringify(self.practica)
			})
			.done(function(data) {
              self.practica = data;
              self.mensajeProceso = data.novedad;			  
              self.reDibujarSuperficie();			
			  console.log("success "+data);			  
			})
			.fail(function(err) {
			  console.log("error: "+err);
			});
		},
		
		reDibujarSuperficie: function(){
			this.lienzo = '';			
			this.lienzo += '<table border="1" style="width: 80%">';
			for(var i = 0;i< this.dimensionXuser; i++){
				this.lienzo += '<tr>';
				for(var j = 0; j<this.dimensionYuser; j++){
					this.lienzo += ((this.dimensionXuser -(i+1)) === this.practica.vehiculo.posicionX && j ===this.practica.vehiculo.posicionY ) ? '<td><img src="img/vehiculo.png"  height="25" width="25"/></td>':'<td>&nbsp;</td>';
				}
				this.lienzo += '</tr>';
			}
			this.lienzo +='</table>';
		}

	}
});