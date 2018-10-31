function setHidden(clickedId) {
	 document.querySelector('#rowIdValue').value = clickedId ;
}

/*function validateForm(event) {  
	var i = 0 ;
	radioElements = document.querySelectorAll('input[name="pwd"]')
	for(var x=0; x < radioElements.length ; x++ ) {
		if(radioElements[x].checked) {
			i ++;
		}		
	}
	if(i == 0) {
		window.alert("Debe seleccionar un usuario antes de realizar esta operacion")
		event.preventDefault(); < PAra que no se envie el formulario al servlet
	}
} */ // ESTO ERA ANTES DE ENTERARME EL ATRIBUTO REQUIRED PARA EL RADIOBUTTON ......