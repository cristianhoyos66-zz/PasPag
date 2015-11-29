#PasPag
                                                  
####Directorios:   
  * **Application:**  
  
    * **PasPagControllers:** Estos son los controladores generador a partir de las entidades; con algunas consultas añadidas manualmente.
    
    * **PasPagEAR-interfaces:** Se encuentran las interfaces que serán usadas a partir de los métodos en cada cliente.

    * **PasPagEAR:** Acá se encuentra toda la aplicación empresarial; es decir, los módulos EJB, web Y ear. Nótese    que en el directorio Source Packages de la aplicación que contiene los EJB (PasPagEAR-ejb) hay subdirectorios, que contienen la implementación de las interfaces (co.com.ces4.ejb) y la cola de mensajes(co.com.ces4.jms). En este último los recursos que se deben crear en el administrador del glassfish para que puedan conectarse a PasPag, porque una vez desplegada PasPagEAR, se crea el jndi portable para los EJB; así que no es necesario crear estos; únicamente los recursos de la cola de mensajes.
    
    * **PasPagEARClientExamples:** Acá se encuentran los ejemplos para hacer uso de los **_session bean_**; teniendo en cuenta el archivo **_ServiceLocator.properties_** (que debe ser un archivo por aplicación relacionada; en este caso sólo existe uno donde org.omg.CORBA.ORBInitialHost apunta a localhost, porque es un servidor local).  
    
    * **PasPagEntities:** Se encuentra el mapeo de todo el ORM.
    * **PasPagViews:** Se encuentran las vistas implementadas para el ORM, los controladores generados a partir de este, las consultas JPQL y las consultas de criteria api.
    
  * **documentation:** Se pueden encontrar el **_diagrama entidad-relación_** y el **_diagrama de clases_**.
  * **Pendientes:** Aún falta la implementación de la cola de mensajes, pero esta ya podría estar escuchando, creando los recursos de esta en el administrador del glassfish y además estaré subiendo algunos métodos para los EJB.  
  
