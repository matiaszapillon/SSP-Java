/**
 * 
 */
function findPeople() {
	var selectList = document.querySelector('#idUserType') ;
	var userType = selectList.value;
	if(userType == "Cliente"){
		var button = document.querySelector('#buttonModal');
		button.setAttribute("data-target","#modalClient");
		// ESTA  ES LA FORMA DE CAMBIAR LOS ATRIBUTOS DEL HTML.
	} else {
		var button = document.querySelector('#buttonModal');
		button.setAttribute("data-target","#modalEmployee");

	}
}

function setHiddenValues(namePerson, idPerson) {
	var hiddenText = document.querySelector('#hiddenNameIdPerson') ;
	hiddenText.value = namePerson;
	console.log(hiddenText.value);
	var hiddenId = document.querySelector('#hiddenIdPerson') ;
	hiddenId.value = idPerson ;
	console.log(hiddenId.value) ;	
}

function setTextPerson() {	
	console.log(document.querySelector('#textIdPerson'));
	var textPers = document.querySelector('#textIdPerson');
	textPers.value = document.querySelector('#hiddenNameIdPerson').value;
	console.log(textPers.value);	
}
