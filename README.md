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
- **Backend:** Spring Boot + Maven + Postgres + Swagger

## Guía de instalacion:

Ejecutar la siguiente linea de comando para obtener el repositorio:

```bash
git clone https://github.com/temeriamos/Devathon-Slytherin-Back.git
```
### 📌 Verificar el archivos:

- Verificar base de datos del proyecto  en el archivo `docker-compose.yml`, en este caso usamos el archivo `.env`, que debe ir en la `raiz del proyecto`.

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/mpslytherin
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=root

POSTGRES_DB=mpslytherin
POSTGRES_USER=postgres
POSTGRES_PASSWORD=root
```

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
      SPRING_DATASOURCE_URL: ${SPRING_DATASOURCE_URL}
      SPRING_DATASOURCE_USERNAME: ${SPRING_DATASOURCE_USERNAME}
      SPRING_DATASOURCE_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}
    volumes:
      - ./src:/app/src  # Monta el código fuente desde tu máquina local al contenedor
    networks:
      - springboot-network

  db:
    image: postgres:latest
    restart: always
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    ports:
      - "5432:5432"
    networks:
      - springboot-network

networks:
  springboot-network:
    driver: bridge

```
### Probar endpoint

- Local (POST): http://localhost:8080/magicobject/
- Server (POST): https://devathon.duckdns.org/magicobject/

#### Endpoind Swagger

- [https://devathon.duckdns.org/](https://devathon.duckdns.org/magicobject/)



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