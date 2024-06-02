![Java Logo](https://logos-download.com/wp-content/uploads/2016/10/Java_logo_icon.png)

# ForoHub: Proyecto API REST con Spring Boot y Java

## Descripción

¡Bienvenido a nuestro último desafío Challenge Back End!

Un foro es un espacio donde todos los participantes de una plataforma pueden plantear sus preguntas sobre determinados tópicos. Aquí en Alura Latam, los estudiantes utilizan el foro para sacar sus dudas sobre los cursos y proyectos en los que participan. Este lugar mágico está lleno de mucho aprendizaje y colaboración entre estudiantes, profesores y moderadores.

Ya sabemos para qué sirve el foro y conocemos su aspecto, ¿pero sabemos cómo funciona detrás de escena? Es decir, ¿dónde se almacenan las informaciones? ¿Cómo se tratan los datos para relacionar un tópico con una respuesta, o cómo se relacionan los usuarios con las respuestas de un tópico?

Este es nuestro desafío, llamado ForoHub: en él, vamos a replicar este proceso a nivel de back end y, para eso, crearemos una API REST usando Spring.
---
## Objetivo del Proyecto

El objetivo de este challenge es implementar una API REST con las siguientes funcionalidades:

- **Crear un nuevo tópico**
- **Mostrar todos los tópicos creados**
- **Mostrar un tópico específico**
- **Actualizar un tópico**
- **Eliminar un tópico**

Es lo que normalmente conocemos como CRUD (CREATE, READ, UPDATE, DELETE) y es muy similar a lo que desarrollamos en LiterAlura, pero ahora de forma completa, agregando las operaciones de UPDATE y DELETE, y usando un framework que facilitará mucho nuestro trabajo.

## Características

- **API REST**: Rutas implementadas siguiendo las mejores prácticas del modelo REST.
- **Validaciones**: Validaciones realizadas según las reglas de negocio.
- **Base de Datos Relacional**: Implementación de una base de datos relacional para la persistencia de la información.
- **Seguridad**: Servicio de autenticación/autorización para restringir el acceso a la información.

---
## Tecnologías Utilizadas
<br>
<p align="center">
    <code class="ico"><img title="Git" width="10%" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mysql/mysql-original-wordmark.svg"></code>
    <code class="ico"><img title="Git" width="10%" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/git/git-original.svg"></code>
    <code class="ico"><img title="Java" width="10%" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg"></code>
    <code class="ico"><img title="Spring" width="10%" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg"></code>
</p>

- **Java**: Versión 17.
- **Spring Boot**: Versión 3.3.0
- **Maven**: Para la gestión de dependencias.
- **MySQL**: Base de datos para la persistencia de los mismos.

---
### Algunos Endpoints del proyecto

- **POST /api/topicos**: Crear un nuevo tópico.
- **GET /api/topicos**: Mostrar todos los tópicos creados.
- **GET /api/topicos/{id}**: Mostrar un tópico específico.
- **PUT /api/topicos/{id}**: Actualizar un tópico.
- **DELETE /api/topicos/{id}**: Eliminar un tópico.

![Diagrama DB](https://trello.com/1/cards/637282884daacb0652dfc38a/attachments/662aa63b41f34527e1bb167b/download/diagrama_base_de_datos_forohub.png)
![Spring Logo](https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/2560px-Spring_Framework_Logo_2018.svg.png)