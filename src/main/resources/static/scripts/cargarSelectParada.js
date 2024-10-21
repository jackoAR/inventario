document.addEventListener('DOMContentLoaded', function() {

	const selectCorredor = document.getElementById("corredorId");
 	selectCorredor.value = idCorredorDesdeControlador;
 	
 	const corredorIdPreseleccionado = selectCorredor.value;
 	
 	if (corredorIdPreseleccionado !== "0" && corredorIdPreseleccionado !== "") {
        // Si hay un valor preseleccionado válido, cargamos las paradas
        cargarParadas(corredorIdPreseleccionado);
    }
 	
	// Añadimos event listener para cuando se cambie manualmente el select
	selectCorredor.addEventListener('change', function() {
		cargarParadas(selectCorredor.value);
	});
});

function cargarParadas(IdCorredor) {

	if (IdCorredor !== "0") {

		const divContainer = document.getElementById('foto-container');
		divContainer.innerHTML = ''; // Limpiar el div contenedor de insertar las imágenes

		// Llamada fetch para enviar el id al controlador
		fetch(`/api/photos/cargarSelectParada/${IdCorredor}`, {
			method: 'GET', // Usa GET si solo estás enviando el id como parte de la URL
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		})
			.then(response => {
				if (!response.ok) {
					throw new Error('Error al cargar las paradas');
				}
				return response.json();
			})
			.then(paradas => {
				// Aquí puedes procesar los datos que te devuelve el servidor (por ejemplo, las paradas)
				const paradaSelect = document.getElementById("paradaId");

				// Limpiamos las opciones previas
				paradaSelect.innerHTML = '<option value="0">-Seleccione-</option>';

				paradas.forEach(parada => {
					const option = document.createElement("option");
					option.value = parada.id;
					option.text = parada.nombre;
					paradaSelect.appendChild(option);
				});

			})
			.catch(error => console.error('Error:', error));
	} else {
		const paradaSelect = document.getElementById("paradaId");
		paradaSelect.innerHTML = '<option value="0">-Seleccione-</option>';
	}
}