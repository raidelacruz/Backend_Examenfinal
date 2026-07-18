# Sistema de Mesa de Ayuda - Proyecto Backend PC3

## 👥 Integrantes y Distribución de Responsabilidades

- **Integrante 1:** [Nombre] - Coordinación general y administración del repositorio.
- **Integrante 2:** [Nombre] - Arquitectura, navegación y estructura del frontend.
- **Integrante 3:** [Nombre] - Componentes, formularios y diseño de interfaz.
- **Integrante 4:** De La Cruz Panduro Rai Caleb - Servicios REST y desarrollo del backend.
- **Integrante 5:** Conislla Lavado Cesar Jesus - Pruebas, validaciones, documentación y control de calidad.

---

## 🛠️ Tecnologías Utilizadas (Backend)

- **Lenguaje:** Java 21
- **Framework:** Spring Boot
- **Persistencia de Datos:** Spring Data JPA
- **Base de Datos:** MySQL
- **Documentación de API:** Springdoc OpenAPI (Swagger)
- **Librerías adicionales:** Lombok

---

## ⚙️ Instalación y Configuración del Servidor

### 1. Configuración de la Base de Datos (MySQL)

Para que el servidor funcione correctamente, es necesario crear una base de datos local en MySQL antes de ejecutar el proyecto:

1. Abrir MySQL Workbench o XAMPP.
2. Ejecutar el siguiente comando SQL para crear la base de datos vacía:
   `CREATE DATABASE mesa_ayuda;`
3. Las tablas (`incidencias`) se generarán automáticamente al iniciar la aplicación.

**Nota:** Si tu servidor MySQL tiene una contraseña, debes ir al archivo `src/main/resources/application.properties` y colocarla en la línea `spring.datasource.password=tu_contraseña`, sino no va a funcionar.

### 2. Ejecución del Backend

1. Abrir el proyecto en un IDE compatible con Java (IntelliJ IDEA, Eclipse, VS Code).
2. Esperar a que Maven descargue las dependencias configuradas en el archivo `pom.xml`.
3. Ejecutar la clase principal `ProyectobackendApplication.java`.
4. El servidor se iniciará en el puerto `8080`.

---

## 🔗 Endpoints y Documentación (API REST)

Una vez que el servidor esté corriendo, la API REST estará disponible en la ruta base:
`http://localhost:8080/api/incidencias`

Para visualizar y probar todas las rutas interactivamente (GET, POST, PUT, DELETE), puedes acceder a la interfaz gráfica de Swagger en tu navegador:
👉 `http://localhost:8080/swagger-ui.html`
