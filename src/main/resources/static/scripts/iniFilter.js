document.addEventListener('DOMContentLoaded', function() {
            // Obtener el select y el botón
            const selectedElementP = document.getElementById('paradaId');
            const buttonElement = document.getElementById('newButton');
           
            // Ocultar el botón inicialmente
            buttonElement.style.display = 'none';

            // Escuchar el evento 'change' en el select
            selectedElementP.addEventListener('change', function() {
                // Mostrar el botón solo si se selecciona una opción distinta de la primera (vacía)
                if (selectedElementP.value !== "0") {
                    buttonElement.style.display = 'inline-block'; // Mostrar botón
                } else {
                    buttonElement.style.display = 'none'; // Ocultar botón si no se selecciona nada
                }
            });
        });