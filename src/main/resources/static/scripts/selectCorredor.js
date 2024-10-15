const selectElement=document.getElementById('corredorId');
const hiddenInputContainer = document.getElementById('hiddenInputContainer');

selectElement.addEventListener('change',function(){
	const CorredorSeleccionado = selectElement.options[selectElement.selectedIndex].text;
	
	hiddenInputContainer.innerHTML = '';
	
	const hiddenInput = document.createElement('input');
	hiddenInput.type = 'hidden';
	
	hiddenInput.name = 'selectCorredor';
	
	hiddenInput.value = CorredorSeleccionado;
	
	hiddenInputContainer.appendChild(hiddenInput);
	
	console.log('valor del input hidden agregado: ', hiddenInput.value);
	
});