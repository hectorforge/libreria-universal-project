Librería Universal - Sistema de Gestión 📚

📖 Introducción:

Sistema integral de gestión de ventas e inventarios para la Librería Universal. Este proyecto implementa una solución escalable y robusta orientada a resolver las necesidades de un entorno empresarial moderno.

🎯 Objetivos del Proyecto

- Definir contextos de negocio claros mediante DDD.
- Garantizar el desacoplamiento técnico mediante Arquitectura Hexagonal.
- Implementar un modelo de datos distribuido y persistencia independiente.

🏗️ Visión Arquitectónica

El sistema adopta una Arquitectura de Microservicios (MSA) bajo los siguientes principios:

- Database per Service: Cada microservicio posee su propia base de datos PostgreSQL para evitar el acoplamiento a nivel de datos.
- Arquitectura Hexagonal: Separación estricta entre el Dominio, la Aplicación (Casos de uso) e Infraestructura (Adaptadores).
- Comunicación: Intercambio de información mediante APIs RESTful (con miras a implementación de eventos asíncronos).

📁 Estructura del Proyecto

Basado en el estándar de microservicios en Spring Boot:

- services/: Contiene los microservicios independientes (Sales, Inventory, etc.).
- db/: Scripts de inicialización y configuración de contenedores de base de datos.
- docs/: Documentación detallada del avance del proyecto e informes.
- docker-compose.yml: Orquestación de contenedores para levantamiento local del ecosistema.

🚀 Tecnologías

Tecnología - Propósito

| Tecnología | Propósito |
| :--- | :--- |
| **Java 17+** | Lenguaje de programación principal para la lógica de negocio. |
| **Spring Boot** | Framework base para el desarrollo de los microservicios. |
| **PostgreSQL** | Motor de base de datos relacional (Database per Service). |
| **Maven** | Gestión de dependencias y automatización de la construcción del proyecto. |
| **Docker** | Contenerización de servicios y orquestación de bases de datos. |

