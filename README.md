🏥 Sistema de Gestión Hospitalaria (HIS)
¡Bienvenido! Este proyecto es la interfaz frontend (HTML/JS) para un sistema de gestión hospitalaria desarrollado con Spring Boot en el backend. Permite el control total (CRUD) de las entidades clave del hospital.

⚙️ Tecnologías Utilizadas
Tecnología

Descripción

Java / Spring Boot

☕ Backend principal, servicios REST y conexión a la base de datos.

HTML5 / CSS3

🌐 Estructura y estilos base de la interfaz.

JavaScript (Vainilla)

✨ Lógica de frontend, manejo de eventos y comunicación asíncrona (Fetch API) con el backend.

APIs REST

🔗 Comunicación estándar entre el frontend y el backend (JSON).

🚀 Módulos Implementados (CRUD)
Hemos desarrollado y optimizado los siguientes módulos con funcionalidad completa de Crear, Leer, Actualizar y Desactivar (CRUD):

Módulo

Endpoint Principal

Estatus

Observaciones Clave

🧑‍⚕️ Médicos

/api/medicos

✅ Listo

Gestión de especialidades y datos de contacto.

🧍 Pacientes

/api/pacientes

✅ Listo

Gestión de datos demográficos y contacto.

📖 Historias Clínicas

/api/historias-clinicas

✅ Listo

Gestión de la información médica con sistema de edición vía modal.

📋 Reportes

/api/reportes/...

📊 Listo

Generación dinámica de tablas y descarga de datos.

📅 Citas

(Pendiente)

🚧 En Desarrollo

Próximo módulo clave para conectar Médicos y Pacientes.

🛠️ Instrucciones de Configuración y Ejecución
Backend (Spring Boot):

Asegúrate de que tu proyecto Spring Boot esté ejecutándose.

Verifica que los puertos y los endpoints (/api/medicos, /api/pacientes, etc.) estén disponibles.

Frontend (HTML):

Los archivos HTML (como medicos.html, pacientes.html, historias.html, reportes.html) deben estar ubicados en la carpeta src/main/resources/static de tu proyecto Spring Boot para que se sirvan correctamente.

Acceso:

Navega a la URL base del frontend, generalmente http://localhost:8080/index.html (o el nombre de tu archivo principal).

💡 Notas de Desarrollo Importantes
Sin alert() ni confirm(): Toda la interacción crítica con el usuario (edición y eliminación) se maneja mediante modales y el sistema de notificaciones (#notification).

Diagnóstico: Si la tabla no carga, abre la consola (F12) y verifica el console.log en las funciones cargar*() para asegurar que el backend esté devolviendo el JSON con los nombres de propiedades correctos.
