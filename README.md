# Servicio de Gestión de Clientes y Préstamos

Este proyecto implementa APIs REST para la gestión de **clientes** y **préstamos**. Los usuarios pueden realizar operaciones CRUD sobre los datos de los clientes y sus préstamos asociados.

## Requisitos

- Java 17
- Gradle
- Docker
- Spring Boot 3

## Ejecución con Docker Compose

Para ejecutar el proyecto utilizando Docker Compose, sigue estos pasos:

1. Ubícate en la raíz del proyecto donde se encuentra el archivo `docker-compose.yml`.
2. Ejecuta el siguiente comando para construir y levantar los servicios:
    ```bash
    docker-compose up
    ```
3. Los servicios estarán disponibles en los siguientes puertos:
   - **Client Service:** http://localhost:8080
   - **Loan Service:** http://localhost:8081

## Instrucciones para Pruebas con Postman

### 1. Importar las colecciones de Postman

Para probar la API con Postman, sigue estos pasos:

1. Importa los archivos de colección de Postman (`Loan_API_Collection.postman_collection.json` y `Client_API_Collection.postman_collection.json`) en tu instancia de Postman.

### 2. Configurar las Variables Globales

Antes de realizar solicitudes, debes configurar dos variables globales en Postman para apuntar a los servicios locales:

- **`local-loans`**: Se utiliza para las solicitudes relacionadas con préstamos y debe tener el valor `http://localhost:8081`.
- **`local-client`**: Se utiliza para las solicitudes relacionadas con clientes y debe tener el valor `http://localhost:8080`.

Estas variables se usan en la colección de Postman para referenciar los endpoints de los servicios correspondientes.

#### Pasos para configurar las variables globales en Postman:

1. Haz clic en el icono de "Environments" (que parece un ojo) en la parte superior derecha de la ventana de Postman.
2. En la sección "Globals", agrega las siguientes variables:

    | Variable        | Initial Value       | Current Value      |
    |-----------------|---------------------|--------------------|
    | local-loans     | http://localhost:8081 | http://localhost:8081 |
    | local-client    | http://localhost:8080 | http://localhost:8080 |

### 3. Realizar las solicitudes

Ahora puedes usar las colecciones de Postman para enviar solicitudes a tu API local. Las variables `{{local-loans}}` y `{{local-client}}` se sustituirán automáticamente por las URL correspondientes a los servicios de préstamos y clientes.

---

## Endpoints de Client Service

### 1. Crear Cliente: `POST /clients`

Crea un nuevo cliente en el sistema.

- **Payload de solicitud:**
    ```json
    {
        "name": "Juan Perez",
        "address": "123 Calle Principal",
        "phone": "555-1234",
        "password": "password123",
        "state": true
    }
    ```
- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "id": 1,
        "name": "Juan Perez",
        "address": "123 Calle Principal",
        "phone": "555-1234"
    }
    ```

### 2. Obtener Todos los Clientes: `GET /clients`

Obtiene una lista de todos los clientes.

- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    [
        {
            "id": 1,
            "name": "Juan Perez",
            "address": "123 Calle Principal",
            "phone": "555-1234"
        }
    ]
    ```

### 3. Obtener Cliente por ID: `GET /clients/{id}`

Obtiene la información detallada de un cliente por su ID.

- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "id": 1,
        "name": "Juan Perez",
        "address": "123 Calle Principal",
        "phone": "555-1234"
    }
    ```

### 4. Actualizar Cliente: `PUT /clients`

Actualiza los datos de un cliente existente.

- **Payload de solicitud:**
    ```json
    {
        "id": 1,
        "name": "Juan Perez Actualizado",
        "address": "123 Calle Nueva",
        "phone": "555-5678"
    }
    ```
- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "id": 1,
        "name": "Juan Perez Actualizado",
        "address": "123 Calle Nueva",
        "phone": "555-5678"
    }
    ```

### 5. Eliminar Cliente: `DELETE /clients/{id}`

Elimina un cliente basándose en su ID.

- **Parámetro de solicitud:** `id` (ID del cliente a eliminar)
- **Respuesta exitosa:** 200 OK

---

## Endpoints de Loan Service

### 1. Crear Préstamo: `POST /loans`

Crea un nuevo préstamo para un cliente.

- **Payload de solicitud:**
    ```json
    {
        "amount": 1000.0,
        "clientId": 1,
        "date": "2024-12-19",
        "status": "PENDIENTE"
    }
    ```
- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "id": 1,
        "amount": 1000.0,
        "clientId": 1,
        "date": "2024-12-19",
        "status": "PENDIENTE"
    }
    ```

### 2. Obtener Todos los Préstamos Activos: `GET /loans/active`

Obtiene todos los préstamos activos.

- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    [
        {
            "id": 1,
            "amount": 1000.0,
            "status": "PENDIENTE"
        }
    ]
    ```

### 3. Obtener Préstamo por ID: `GET /loans/{id}`

Obtiene la información de un préstamo por su ID.

- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "id": 1,
        "amount": 1000.0,
        "clientId": 1,
        "date": "2024-12-19",
        "status": "PENDIENTE"
    }
    ```

### 4. Actualizar Estado del Préstamo: `PATCH /loans/status`

Actualiza el estado de un préstamo existente.

- **Payload de solicitud:**
    ```json
    {
        "id": 3,
        "status": "PAGADO"
    }
    ```
- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "id": 3,
        "amount": 5000.0,
        "status": "PAGADO"
    }
    ```

### 5. Eliminar Préstamo: `DELETE /loans/{id}`

Elimina un préstamo basándose en su ID.

- **Parámetro de solicitud:** `id` (ID del préstamo a eliminar)
- **Respuesta exitosa:** 200 OK

### 6. Calcular Pago de Préstamo: `GET /loans/{id}/payment`

Calcula el pago de un préstamo.

- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "id": 1,
        "paymentAmount": 150.0
    }
    ```

---

## Conclusión

Este proyecto proporciona un servicio básico para gestionar **clientes** y **préstamos**, utilizando **Spring Boot** y **Docker** para su ejecución. Las pruebas se pueden realizar utilizando **Postman**, siguiendo las instrucciones anteriores para configurar las colecciones y las variables necesarias.