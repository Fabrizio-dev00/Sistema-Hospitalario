# 🏥 Sistema de Gestión Hospitalaria (HIS)

¡Bienvenido!  
Este proyecto corresponde a la **interfaz frontend (HTML/JS)** del Sistema de Gestión Hospitalaria desarrollado con **Spring Boot** en el backend.  
Permite la administración completa (CRUD) de las entidades clave del hospital: médicos, pacientes, citas, historias clínicas y reportes.

---

## ⚙️ Tecnologías Utilizadas

| Tecnología | Descripción |
|-------------|-------------|
| ☕ **Java / Spring Boot** | Backend principal, servicios REST y conexión con la base de datos. |
| 🌐 **HTML5 / CSS3** | Estructura y estilos base de la interfaz de usuario. |
| ✨ **JavaScript (Vanilla)** | Lógica de frontend, manejo de eventos y comunicación asíncrona (Fetch API). |
| 🔗 **APIs REST** | Intercambio de datos estándar entre frontend y backend usando JSON. |

---

## 🚀 Módulos Implementados (CRUD)

| Módulo | Endpoint Principal | Estado | Observaciones |
|---------|--------------------|---------|----------------|
| 🧑‍⚕️ **Médicos** | `/api/medicos` | ✅ Listo | Gestión de especialidades y datos de contacto. |
| 🧍 **Pacientes** | `/api/pacientes` | ✅ Listo | Gestión de datos demográficos y contacto. |
| 📖 **Historias Clínicas** | `/api/historias-clinicas` | ✅ Listo | Gestión de la información médica con edición vía modal. |
| 📋 **Reportes** | `/api/reportes/...` | 📊 Listo | Generación dinámica de reportes (médicos, citas, pacientes, historias). |
| 📅 **Citas** | `/api/citas` | 🚧 En desarrollo | Próximo módulo clave para conectar Médicos y Pacientes. |

---

## 🛠️ Instrucciones de Configuración y Ejecución

### 🔹 Backend (Spring Boot)
1. Asegúrate de que el proyecto de **Spring Boot** esté corriendo correctamente.  
2. Verifica que los endpoints (`/api/medicos`, `/api/pacientes`, `/api/historias-clinicas`, etc.) estén disponibles.  
3. La base de datos debe estar configurada y accesible desde `application.properties` o `application.yml`.

### 🔹 Frontend (HTML / JS)
1. Coloca los archivos HTML (`medicos.html`, `pacientes.html`, `historias.html`, `reportes.html`, etc.) dentro de la carpeta:
src/main/resources/static

markdown
Copiar código
2. Asegúrate de incluir tu hoja de estilos (`css/styles.css`) y los scripts JS necesarios.
3. Inicia el proyecto Spring Boot y abre el navegador en:
http://localhost:8080/index.html

yaml
Copiar código

---

## 💡 Notas de Desarrollo Importantes

- 🚫 **Sin `alert()` ni `confirm()`**:  
Las confirmaciones y mensajes del usuario se manejan mediante **modales** y un sistema visual de notificaciones (`#notification`).

- 🔍 **Diagnóstico rápido**:  
Si la tabla no carga datos, abre la consola del navegador (`F12`) y revisa los logs de las funciones `cargar*()`.  
Es posible que el backend esté devolviendo propiedades con nombres distintos a los esperados.

- 🧱 **Arquitectura modular**:  
Cada entidad cuenta con su propio HTML y script JS, comunicándose directamente con su endpoint REST correspondiente.

---

## 👨‍💻 Autor
Proyecto desarrollado por **Fabrizio Jiménez**  
Estudiante de **Diseño y Desarrollo de Software – TECSUP**  
📅 Año académico 2025
