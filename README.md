# VitaWallet2


Descripción de clases
1. BaseTest.java
¿Qué es?

Clase base que prepara y configura Appium + Chrome antes de cada test, y cierra la sesión después.
Todos los test heredan de esta clase.

¿Qué hace?

Configura las capabilities necesarias para ejecutar pruebas en un dispositivo/emulador Android.

Lanza Chrome en modo móvil para usar WebView del sitio web (en tu caso, VitaWallet).

Se conecta al servidor Appium (http://localhost:4723).

Aplica timeouts e implicit waits.

Cambia automáticamente al contexto WEBVIEW/CHROMIUM, que es obligatorio para automatizar HTML dentro del navegador móvil.

Incluye el método tearDown() para cerrar la sesión del driver al final de la suite.

¿Por qué ?

Porque garantiza que cada test tenga el entorno móvil configurado correctamente, sin repetir código.

2. LoginPage.java
¿Qué es?

Página del patrón Page Object Model (POM)
Representa la pantalla de login en VitaWallet.

¿Qué hace?

Define los localizadores del formulario de login.

Contiene métodos de interacción:

esperar a que el formulario sea visible

escribir email

escribir contraseña

hacer clic en el botón de login

Incluye lógica para cambiar de iframe, si el login está embebido dentro de uno.

¿Por qué ?

Porque separa la lógica de automatización del test.
Los tests quedan más limpios y fáciles de mantener.

3. LoginTest.java
Qué es?

Clase que contiene los casos de prueba relacionados al login.

Qué hace?

Extiende BaseTest → recibe el driver ya configurado.

Instancia LoginPage.

Ejecuta pasos del flujo:

Abrir el sitio (o se abre en BaseTest).

Esperar que el login sea visible.

Completar email.

Completar contraseña.

Clic en iniciar sesión.

Validar el resultado.

Test:

Abre la URL

Espera que el login cargue 

Elimina los pop up

Ingresa email

Ingresa contraseña

Clic en iniciar sesión

Valida la navegación o un elemento del home

¿Por qué es importante?


Es legible, simple y fácil de mantener.

Relación entre las 3 clases
Clase	Rol	Ejemplo
BaseTest	Configura Appium + Chrome	“Abre el navegador en Android y queda listo”
LoginPage	Acciones dentro de la pantalla de login	“Escribe email, escribe password, hace clic”
LoginTest	Lógica del caso de prueba	“Probar login exitoso”


Dependencias:

“Mi proyecto Appium usa tres dependencias clave: Appium Java Client para interactuar con dispositivos móviles, Selenium WebDriver porque Appium funciona sobre WebDriver y necesito manejar WebViews y esperas explícitas, y TestNG como framework de ejecución de pruebas. Todo esto se ejecuta con el plugin Surefire dentro de Maven.”