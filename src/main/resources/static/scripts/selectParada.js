const selectElement1=document.getElementById('paradaId');
const hiddenInputContainer1 = document.getElementById('hiddenInputContainer1');

selectElement1.addEventListener('change',function(){
	const ParadaSeleccionada = selectElement1.options[selectElement1.selectedIndex].text;
	
	hiddenInputContainer1.innerHTML = '';
	
	const hiddenInput1 = document.createElement('input');
	hiddenInput1.type = 'hidden';
	
	hiddenInput1.name = 'selectParada';
	
	hiddenInput1.value = ParadaSeleccionada;
	
	hiddenInputContainer1.appendChild(hiddenInput1);
	
	console.log('valor del input hidden agregado: ', hiddenInput1.value);
	
});