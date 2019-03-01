
function setHiddenValue(idButton) {
	document.querySelector('#idClickedButton').value = idButton;
}



function validateForm() {  
	if(document.querySelector('#idClickedButton').value == "addStageFormId"){
		return true;
	}
	if(document.querySelector('#idClickedButton').value == "suppliesId"){
		return true;
	}
	
	if(document.querySelector('#idClickedButton').value == "stagesButtonId"){
		return true;
	}
	
	if(document.querySelector('#idClickedButton').value == "calculateCostId"){
		return true;
	}
	
	if(document.querySelector('#idClickedButton').value == "addSupplyId"){
		return true;
	}
	if(document.querySelector('#idClickedButton').value == "addStageFormId"){
		return true;
	}
	if(document.querySelector('#idClickedButton').value == "addStageFormId"){
		return true;
	}
	if(document.querySelector('#idClickedButton').value == "addStageFormId"){
		return true;
	}
	
	
	if(document.querySelector('#idClickedButton').value == "modifyStageId"
		|| 	document.querySelector('#idClickedButton').value == "deleteStageId"	){
		var i = 0 ;
		radioElements = document.querySelectorAll('input[name="radioSelectedStage"]')
		for(var x=0; x < radioElements.length ; x++ ) {
			if(radioElements[x].checked) {
				i ++;
			}		
		}
		if(i == 0) {
			window.alert("Debe seleccionar una etapa antes de realizar esta operacion")
			return false;
		} else {
			return true;
		}
	}
	
	if(document.querySelector('#idClickedButton').value == "deleteSupplyId" 
	   || document.querySelector('#idClickedButton').value == "updateButtonId"){
		var i = 0 ;
		radioElements = document.querySelectorAll('input[name="radioSelectedSupply"]')
		for(var x=0; x < radioElements.length ; x++ ) {
			if(radioElements[x].checked) {
				i ++;
			}		
		}
		if(i == 0) {
			window.alert("Debe seleccionar un insumo antes de realizar esta operacion")
			return false;
		} else {
			return true;
		}
	}

} 



	
