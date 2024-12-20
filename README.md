# Servicio de Gestión de Clientes

Este proyecto implementa APIs REST para la gestión de clientes (`person-service`) y cuentas (`account-service`). Permite a los usuarios realizar operaciones CRUD sobre los datos de los clientes y sus cuentas asociadas.

## Requisitos

- Java 17
- Gradle
- Docker
- Spring Boot 3

## Ejecución con Docker Compose

Para ejecutar el proyecto usando Docker Compose, sigue estos pasos:

- Ubícate en la raíz del proyecto donde se encuentra el archivo `docker-compose.yml`.
- Ejecuta el siguiente comando para construir y levantar los servicios:
    ```bash
    docker-compose up
    ```
- Los servicios estarán disponibles en los siguientes puertos:
  - Person Service: http://localhost:8080
  - Account Service: http://localhost:8081

# Instrucciones para Pruebas con Postman

Para probar la API con Postman, sigue estos pasos:

1. Importa el archivo de colección de Postman (`Postulacion.postman_collection.json`) en tu instancia de Postman.

2. Antes de realizar solicitudes, debes configurar dos variables globales en Postman para apuntar a los servicios locales:

    - `local-account`: Se utiliza para las solicitudes relacionadas con cuentas y debe tener el valor `http://localhost:8081`.
    - `local-person`: Se utiliza para las solicitudes relacionadas con clientes y debe tener el valor `http://localhost:8080`.

   Estas variables se usan en la colección de Postman para referenciar los endpoints de los servicios correspondientes.

3. Para configurar las variables globales, sigue estos pasos:

    a. Haz clic en el icono de "Environments" (que parece un ojo) en la parte superior derecha de la ventana de Postman.
    
    b. En la sección "Globals" que aparece a la derecha, agrega las siguientes variables:
    
        | Variable       | Initial Value            | Current Value           |
        |----------------|--------------------------|-------------------------|
        | local-account  | http://localhost:8081    | http://localhost:8081   |
        | local-person   | http://localhost:8080    | http://localhost:8080   |

    c. Asegúrate de que las variables estén marcadas como "enabled" (activadas) y luego cierra la ventana de "Environments".

   ![image](https://github.com/pparedesam/desafio-devsu/assets/18691981/5d259c09-1594-4946-96c2-0b5ced09a71b)


5. Ahora puedes usar la colección de Postman para enviar solicitudes a tu API local. Las variables `{{local-account}}` y `{{local-person}}` se sustituirán automáticamente por las URL correspondientes a los servicios de cuentas y clientes.


## Endpoints de Person Service

### 1. Crear Cliente: `POST /v1/client`

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

### 2. Actualizar Cliente: `PUT /v1/client`

Actualiza los datos de un cliente existente.

- **Payload de solicitud:**
    ```json
    {
        "idClient": 1,
        "name": "Juan Perez Actualizado",
        "address": "123 Calle Nueva",
        "phone": "555-5678",
        "password": "newPassword123",
        "state": true
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

### 3. Obtener Todos los Clientes: `GET /v1/client`

Retorna una lista de todos los clientes registrados.

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

### 4. Obtener Cliente por ID: `GET /v1/client/{id}`

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

### 5. Obtener Cliente por Nombre: `GET /v1/client/name?name={nombre}`

Obtiene la información de un cliente por su nombre.

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

### 6. Eliminar Cliente: `DELETE /v1/client`

Elimina un cliente del sistema basándose en su ID.

- **Parámetro de solicitud:** `idClient` (ID del cliente a eliminar)
- **Respuesta exitosa:** 200 OK


## Endpoints de Account Service

### 1. Crear Cuenta: `POST /v1/account`

Crea una nueva cuenta para un cliente.

- **Payload de solicitud:**
    ```json
    {
        "accountType": "Ahorro",
        "initialBalance": 1000.00,
        "status": true,
        "idClient": 1
    }
    ```
- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "idAccount": 1,
        "accountType": "Ahorro",
        "initialBalance": 1000.00,
        "status": true,
        "nameClient": "Juan Perez",
        "accountNumber": "123456789"
    }
    ```

### 2. Eliminar Cuenta: `DELETE /v1/account`

Elimina una cuenta basándose en su ID.

- **Parámetro de solicitud:** `idAccount` (ID de la cuenta a eliminar)
- **Respuesta exitosa:** 200 OK

### 3. Obtener Cuentas: `GET /v1/account`

Retorna una lista de todas las cuentas registradas.

- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    [
        {
            "idAccount": 1,
            "accountType": "Ahorro",
            "initialBalance": 1000.00,
            "status": true,
            "nameClient": "Juan Perez",
            "accountNumber": "123456789"
        }
    ]
    ```

### 4. Actualizar Cuenta: `PUT /v1/account`

Actualiza la información de una cuenta existente.

- **Payload de solicitud:**
    ```json
    {
        "id": 1,
        "type": "Corriente",
        "balance": 1200.00,
        "number": "987654321",
        "state": true,
        "idClient": 1
    }
    ```
- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "id": 1,
        "type": "Corriente",
        "balance": 1200.00,
        "number": "987654321",
        "state": true,
        "idClient": 1
    }
    ```

### 5. Crear Movimiento: `POST /v1/movement`

Registra un nuevo movimiento en una cuenta.

- **Payload de solicitud:**
    ```json
    {
        "date": "2023-03-15",
        "idAccount": 1,
        "value": 200.00
    }
    ```
- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "numero": "123456789",
        "accountType": "Ahorro",
        "initialBalance": 800.00,
        "state": true,
        "movement": "Retiro"
    }
    ```

### 6. Eliminar Movimiento: `DELETE /v1/movement`

Elimina un movimiento basándose en su ID.

- **Parámetro de solicitud:** `idMovement` (ID del movimiento a eliminar)
- **Respuesta exitosa:** 200 OK

### 7. Obtener Movimientos: `GET /v1/movement`

Retorna una lista de todos los movimientos registrados en el sistema.

- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    [
        {
            "numero": "123456789",
            "accountType": "Ahorro",
            "initialBalance": 1000.00,
            "state": true,
            "movement": "Depósito"
        }
    ]
    ```

### 8. Actualizar Movimiento: `PUT /v1/movement`

Actualiza la información de un movimiento existente.

- **Payload de solicitud:**
    ```json
    {
        "id": 1,
        "date": "2023-03-16",
        "typeMovement": "Depósito",
        "value": 100.00,
        "balance": 900.00,
        "id_account": 1
    }
    ```
- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    {
        "id": 1,
        "date": "2023-03-16",
        "typeMovement": "Depósito",
        "value": 100.00,
        "balance": 900.00,
        "account": { ... }
    }
    ```

### 9. Obtener Movimientos por Cliente: `GET /v1/reports`

Retorna un reporte de movimientos para un cliente en un rango de fechas.

- **Parámetros de solicitud:** `dateIni`, `dateLast`, `idClient`
- **Respuesta exitosa:** 200 OK
- **Ejemplo de respuesta:**
    ```json
    [
        {
            "date": "2023-03-15",
            "name": "Juan Perez",
            "number": "123456789",
            "type": "Retiro",
            "balance": 800.00,
            "state": true,
            "value": 200.00,
            "available": 800.00
        }
    ]
    ```
#   E l e k t r a P r a c t i c e  
 