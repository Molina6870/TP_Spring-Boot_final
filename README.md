# TP_Spring-Boot_final

# Datos del alumno:
Nombre y apellido: Juan Ignacio Molina
Legajo: 50104
Curso: 3K10

## Descripción del Proyecto
	Este proyecto consiste en el desarrollo de una **API REST** utilizando **Spring Boot** para la gestión de productos de un e-commerce.  
La aplicación permite realizar operaciones CRUD (crear, leer, actualizar y eliminar) sobre productos, así como filtrar por categoría y actualizar el stock de manera parcial.

	Se utilizó una base de datos **H2 en memoria** y la documentación fue generada automáticamente con **Swagger OpenAPI**.

---

## Tecnologías utilizadas
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (en memoria)
- Lombok
- Swagger OpenAPI (springdoc-openapi)
- Maven

---

## Configuración y ejecución

1. Clonar o descargar el proyecto.
2. Abrirlo en **IntelliJ IDEA** o cualquier IDE compatible con Spring Boot.
3. Verificar el archivo `application.properties` (ubicado en `src/main/resources`):
   ```properties
   spring.datasource.url=jdbc:h2:mem:productosdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Ejecutar la clase principal:  
   **`ApiRestConSpringBootApplication.java`**

---

## Endpoints principales

| Método | Endpoint | Descripción |
|--------|-----------|-------------|
| **GET** | `/api/productos` | Lista todos los productos |
| **GET** | `/api/productos/{id}` | Obtiene un producto por su ID |
| **GET** | `/api/productos/categoria/{categoria}` | Filtra productos por categoría |
| **POST** | `/api/productos` | Crea un nuevo producto |
| **PUT** | `/api/productos/{id}` | Actualiza todos los campos de un producto existente |
| **PATCH** | `/api/productos/{id}/stock` | Actualiza solo el valor del stock |
| **DELETE** | `/api/productos/{id}` | Elimina un producto por ID |

---

## Estructura del proyecto

```
src/
 ├── main/
 │   ├── java/com/utn/productos/
 │   │   ├── controller/      -> Controladores REST
 │   │   ├── dto/             -> Objetos de transferencia de datos
 │   │   ├── exception/       -> Manejo global de excepciones
 │   │   ├── model/           -> Entidades del dominio (Producto, Categoría)
 │   │   ├── repository/      -> Repositorios JPA
 │   │   ├── service/         -> Lógica de negocio
 │   │   └── ApiRestConSpringBootApplication.java
 │   └── resources/
 │       ├── application.properties
 │       └── static / templates (si aplica)
 └── test/
```

---

## Pruebas con Swagger

1. Iniciar la aplicación.
2. Acceder desde el navegador a:  
   👉 **http://localhost:8080/swagger-ui/index.html**
3. Desde ahí se puede probar cada endpoint: crear, listar, filtrar, actualizar y eliminar productos.

---

## Base de datos H2

- Consola H2 disponible en: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- URL JDBC: `jdbc:h2:mem:productosdb`
- Usuario: `sa`
- Contraseña: *(vacía)*

Ejemplo de consulta:
```sql
SELECT * FROM PRODUCTO;
```

---

## Conclusión
Durante este trabajo se aplicaron los principales conceptos de desarrollo de **APIs REST con Spring Boot**, incluyendo:
- Arquitectura en capas (Controller, Service, Repository)
- Uso de DTOs para transferencia de datos
- Manejo de excepciones centralizado con `@ControllerAdvice`
- Validaciones con `@Valid` y `@NotNull`
- Documentación automática con **Swagger**
- Persistencia con **H2 en memoria**

Este proyecto refuerza las bases del desarrollo backend en Java y la importancia de una estructura modular, validaciones y documentación en las APIs REST.

# Capturas de pantalla
Captura pantalla de una creación de producto exitosa
<img width="975" height="291" alt="image" src="https://github.com/user-attachments/assets/49730906-e541-45ad-b326-9c6d3724e74a" />

Captura de pantalla del Listado de los productos
 <img width="975" height="398" alt="image" src="https://github.com/user-attachments/assets/945dd5cf-0586-4d1e-862d-6ac8682a836f" />

Capturas de pantalla del Listado de los productos por categoría
 <img width="886" height="292" alt="image" src="https://github.com/user-attachments/assets/aab2ec2f-9168-49a6-9afc-de942d37e342" />

<img width="1068" height="438" alt="image" src="https://github.com/user-attachments/assets/5a567c8a-835f-4c22-a469-462fd6009f86" />

 
Captura de pantalla del error 404:
<img width="742" height="294" alt="image" src="https://github.com/user-attachments/assets/84da1a04-2ea1-475f-94fe-aeccfde871b3" />

 
Actualización del producto (PUT)
 <img width="975" height="386" alt="image" src="https://github.com/user-attachments/assets/9cce4dc9-723a-4800-99f0-7c8a05b13098" />


Actualización del producto (PATH)
<img width="975" height="402" alt="image" src="https://github.com/user-attachments/assets/28a70510-0c95-4728-bb84-44ccffc6d35a" />

 
Captura de pantalla de todos los productos (Desde H2)
 <img width="975" height="480" alt="image" src="https://github.com/user-attachments/assets/d5401f7c-35c6-4f94-8671-10d7a5284063" />


  
