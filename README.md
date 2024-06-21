# Proyecto de Gestión de Libros

Este proyecto Java utiliza Spring Boot y Hibernate para gestionar una base de datos de libros y autores, con la capacidad de buscar libros por idioma y mostrar autores por fecha de fallecimiento.

## Características

- Gestión de libros y autores.
- Búsqueda de libros por idioma.
- Listado de autores por fecha de fallecimiento.

## Tecnologías utilizadas

- Java 11
- Spring Boot 2.5.0
- Hibernate 5.4.30
- H2 Database (para desarrollo)
- Maven

## Requisitos

- Java JDK 11 o superior
- Maven 3.6.x

## Instalación y configuración

1. Clona el repositorio:

    ```bash
    git clone https://github.com/tu-usuario/proyecto-gestion-libros.git
    cd proyecto-gestion-libros
    ```

2. Construye el proyecto:

    ```bash
    mvn clean package
    ```

3. Ejecuta la aplicación:

    ```bash
    mvn spring-boot:run
    ```

## Uso

### Búsqueda de libros por idioma

Para buscar libros por idioma, ejecuta la opción correspondiente en la consola.

### Listado de autores por fecha de fallecimiento

Para obtener una lista de autores que fallecieron antes de un año específico, ejecuta la opción correspondiente en la consola.

## Contribuciones

Las contribuciones son bienvenidas. Si tienes sugerencias, mejoras o problemas, por favor abre un issue o una pull request.

## Licencia

Este proyecto está licenciado bajo la [Licencia MIT](https://opensource.org/licenses/MIT).
