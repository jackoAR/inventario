document.addEventListener('DOMContentLoaded', function() {
	
	if (idParadaDesdeControlador !== '0' && idParadaDesdeControlador !== null) {
		document.getElementById("paradaId").value = idParadaDesdeControlador;
		enviarIdParada(idParadaDesdeControlador);
	}
});