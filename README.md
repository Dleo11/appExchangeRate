
# Conversor de Monedas

Este proyecto es un **sistema de conversión de monedas** que permite a los usuarios convertir entre diferentes monedas utilizando la API de ExchangeRate-API para obtener tasas de cambio actualizadas.

## Características

- Permite convertir entre diferentes monedas.
- Muestra información sobre la última actualización de las tasas de cambio.
- Guarda un registro de las conversiones realizadas en archivos JSON.
- Maneja errores comunes como códigos de moneda incorrectos o problemas de conexión con la API.

## Tecnologías utilizadas

- **Java**: Lenguaje de programación principal.
- **Gson**: Para convertir objetos Java en formato JSON.
- **ExchangeRate-API**: API externa utilizada para obtener tasas de cambio actualizadas.
- **IntelliJ IDEA**: Entorno de desarrollo integrado (IDE) recomendado para trabajar en este proyecto.

## Requisitos

Antes de ejecutar el proyecto, asegúrate de tener los siguientes componentes instalados:

- **JDK 17** o superior.
- Conexión a internet para obtener las tasas de cambio desde la API.
- Dependencias:
  - **Gson** (para manipulación JSON).

## Instalación

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/Dleo11/appExchangeRate.git
   ```

2. **Configurar la API**:
   - Regístrate en [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener una clave API.
   - Configura tu clave API en el archivo `SearchExchangeRate.java`:
     ```java
     String apiKey = "tu_clave_api";
     ```

3. **Instalar dependencias**:
   - Si estás usando un proyecto Maven o Gradle, asegúrate de agregar la dependencia de Gson:
     ```xml
     <dependency>
         <groupId>com.google.code.gson</groupId>
         <artifactId>gson</artifactId>
         <version>2.8.6</version>
     </dependency>
     ```
   - En caso no, descarga el archivo `gson.jar` de la mims página de  GSON de google e insertalo en tu proyecto. 

4. **Ejecutar el proyecto**:
   - Compila y ejecuta la clase `Principal` desde tu IDE o terminal:
     ```bash
     javac Principal.java
     java Principal
     ```

## Uso

1. Al iniciar el programa, se mostrará una lista de las monedas más comunes.
2. Introduce la **moneda base** en formato de código de 3 letras (por ejemplo, `USD`, `EUR`).
3. Introduce la **moneda destino** en formato de código de 3 letras.
4. Ingresa la **cantidad** que deseas convertir.
5. El programa realizará la conversión y mostrará el resultado.
6. Al finalizar, las conversiones realizadas se guardarán en un archivo JSON con la fecha actual.

## Ejemplo de JSON generado

```json
[
  {
    "baseCurrency": "USD",
    "targetCurrency": "PEN",
    "amount": 100,
    "convertedAmount": 370.50,
    "time_last_update_utc": "Mon, 07 Oct 2024 00:00:01",
    "time_next_update_utc": "Tue, 08 Oct 2024 00:00:01",
    "dateConsulta": "2024-10-06 11:07"
  }
]
```

## Manejo de errores

- **Códigos de moneda incorrectos**: Si introduces un código de moneda no válido o incorrecto, el programa te indicará que el código debe tener 3 letras.
- **Cantidad no numérica**: Si introduces un valor que no es numérico en el campo de cantidad, el programa te pedirá ingresar un número válido.
- **Errores en la API**: Si la API no responde o ocurre un error en la comunicación, se mostrará un mensaje claro indicando la naturaleza del error.

## Contribuciones

Si deseas contribuir al proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama nueva para tu feature o arreglo de bugs:
   ```bash
   git checkout -b feature/nueva-feature
   ```
3. Realiza los cambios y haz un commit:
   ```bash
   git commit -m "Añadida nueva feature"
   ```
4. Haz push a tu rama:
   ```bash
   git push origin feature/nueva-feature
   ```
5. Abre un Pull Request y describe tus cambios detalladamente.

## Licencia

Este proyecto está bajo la Licencia MIT. Puedes ver más detalles en el archivo `LICENSE`.
