function setHidden(clickedRadioButtonId) {
	 document.querySelector('#rowIdValue').value = clickedRadioButtonId ;
}
function setHiddenValue(idButton) {
	
	document.querySelector('#idClickedButton').value = idButton;
}
function validateForm() {  
	if(document.querySelector('#idClickedButton').value == "addButton"){
		return true
	}
	var i = 0 ;
	radioElements = document.querySelectorAll('input[name="radioABM"]')
	for(var x=0; x < radioElements.length ; x++ ) {
		if(radioElements[x].checked) {
			i ++;
		}		
	}
	if(i == 0) {
		window.alert("Debe seleccionar un usuario antes de realizar esta operacion")
		return false;
	} else {
		return true;
	}
} 

	
