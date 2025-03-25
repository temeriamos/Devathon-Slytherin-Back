# Slytherin

## Objetivo
Crear un marketplace donde los usuarios puedan listar y comprar objetos mágicos.

## 🚀 Lógica de negocio principal
- Los usuarios pueden listar objetos a la venta con descripción y precio.
- Los objetos pueden ser comprados con monedas mágicas (galeones, sickles, knuts).
- Algunos objetos tienen efectos especiales (desaparecen si no se venden rápido).
- Los usuarios pueden administrar su inventario de objetos (carrito).

---

## 📌 Devathon 9na edición
**Grupo:** Slytherin  
**Organizador:** Programación en Español - [TWITCH](https://www.twitch.tv/programacion_es)

## 🛠️ Tecnologías
- **Backend:** Spring Boot + Maven + MySQL + Swagger

## Guía de instalacion:

Ejecutar la siguiente linea de comando para obtener el repositorio:

```bash
git clone https://github.com/temeriamos/Devathon-Slytherin-Back.git
```
### 📌 Verificar el archivos:

- Verificar base de datos del proyecto  en el archivo `docker-compose.yml`, en este caso usamos el nombre `mpslytherin` y debe estar en las lineas :

    - SPRING_DATASOURCE_URL (app)
        - jdbc:mysql://db:3306/mpslytherin?useSSL=false&allowPublicKeyRetrieval=true
    - MYSQL_DATABASE (db)
        -  MYSQL_DATABASE: mpslytherin

```yml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/mpslytherin?useSSL=false&allowPublicKeyRetrieval=true
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
    volumes:
      - ./src:/app/src  # Monta el código fuente desde tu máquina local al contenedor
    networks:
      - springboot-network

  db:
    image: mysql:8.0
    restart: always
    environment:
      MYSQL_DATABASE: mpslytherin
      MYSQL_ROOT_PASSWORD: root
    ports:
      - "3306:3306"
    networks:
      - springboot-network

networks:
  springboot-network:
    driver: bridge
```
### Probar endpoint de prueba

- url (POST): http://localhost:8080/magicobject/

#### Usando postman:

- body(JSON)

```json
    {
        "name":"Book",
        "description":"First Book",
        "price": 10,
        "imagen":"www.imgur.com/firt_book_slytherin"
    }
```

Resultado: Objeto creado - Book


## 👥 Contribuidores

### 🔹 Backend
#### [jamarbo](https://github.com/jamarbo)  

#### [angcamdes](https://github.com/angcamdes)  

#### [raydberg](https://github.com/raydberg)  

#### [Temeriamos](https://github.com/Temeriamos) 

### 🔹 Frontend

#### [wjmmk](https://github.com/wjmmk)  

#### [ZarakiLancelot](https://github.com/ZarakiLancelot)  

#### [Sandra13988](https://github.com/Sandra13988)  

#### [puriihuaman](https://github.com/puriihuaman)

---

## 📌 Enlaces de interes

- [mapstruct](https://mapstruct.org/)
- [Ejemplo trello](https://trello.com/b/tFswHxVK/kanban-example)