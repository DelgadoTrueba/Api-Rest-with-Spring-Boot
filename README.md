# Api-Rest-with-Spring-Boot

#### [API en acción.](https://api-rest-with-spring.herokuapp.com/api/v0/swagger-ui.html)

**Travis-ci**:  [![Build Status](https://travis-ci.org/DelgadoTrueba/Api-Rest-with-Spring-Boot.svg?branch=master)](https://travis-ci.org/DelgadoTrueba/Api-Rest-with-Spring-Boot)

**Sonarqube**:  [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.delgadotrueba%3Aapi&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.delgadotrueba%3Aapi)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.delgadotrueba%3Aapi&metric=bugs)](https://sonarcloud.io/dashboard?id=com.delgadotrueba%3Aapi)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=com.delgadotrueba%3Aapi&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=com.delgadotrueba%3Aapi)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.delgadotrueba%3Aapi&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.delgadotrueba%3Aapi)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.delgadotrueba%3Aapi&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.delgadotrueba%3Aapi)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.delgadotrueba%3Aapi&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.delgadotrueba%3Aapi)

## Arquitectura
![](https://github.com/DelgadoTrueba/Api-Rest-with-Spring-Boot/blob/master/docs/vistaDeDesarrollo.png)

### Responsabilidades
* `config` Clases de configuración de **Spring**.
* `exceptions`tratamiento de errores, convierte las excepciones lanzadas en respuestas de error HTTP.
* `rest_controllers` Clases que conforman el **API**.
   * Define el path del recurso.
   * Deben organizar la recepción de datos de la petición.
   * Delega en los **dtos** la validación de campos.
   * Delega en **exceptions** las respuestas de errores **HTTP**.
   * Delega en los **bussines_controllers** la ejecución de la petición.
* `bussines_controllers` Clases que procesan la petición.
   * Desarrollan el proceso que conlleva la ejecución de la petición.
   * Construye los **orms** a partir de los **dtos** de entrada.
   * Delega en los **dtos** la construcción de los **dtos** de respuesta a partir de los **documents**.
   * Delega en los **daos** el acceso básico a las BD.
   * Delega en los **data_services** procesos de acceso avanzado a las BD.
   * Delega en los **business_services** procesos genéricos avanzados de la capa de negocio.
* `busines_services` Clases de servicios de apoyo, como fachada de construcción de PDF, fachada de tratamiento de JWT, encriptación...
* `data_services` Clases de servicios avanzados de BD.
* `daos` Clases de acceso a BD mediante el patrón DAO.
   * Operaciones CRUD sobre BD.
   * Consultas a BD.
* `orms` Clases con los documentos persistentes en BD y utilidades.
