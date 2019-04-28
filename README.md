# Api-Rest-with-Spring-Boot

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
   * Construye los **documents** a partir de los **dtos** de entrada.
   * Delega en los **dtos** la construcción de los **dtos** de respuesta a partir de los **documents**.
   * Delega en los **repositories** el acceso básico a las BD.
   * Delega en los **data_services** procesos de acceso avanzado a las BD.
   * Delega en los **business_services** procesos genéricos avanzados de la capa de negocio.
* `busines_services` Clases de servicios de apoyo, como fachada de construcción de PDF, fachada de tratamiento de JWT, encriptación...
* `data_services` Clases de servicios avanzados de BD.
* `repositories` Clases de acceso a BD mediante el patrón DAO.
   * Operaciones CRUD sobre BD.
   * Consultas a BD.
* `documents` Clases con los documentos persistentes en BD y utilidades.
