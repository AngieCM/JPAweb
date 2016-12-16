# JPAweb --> Version sin terminar
El trabajo contiene un servlet por cada accion (Login, Registro, Cookies).

La aplicacion cuenta con unas clases Entities, las cuales son las entidades (tablas) con sus repectivos atributos (campos) y sus metodos. 

Una clase se llama Angyusers, donde encontraremos los datos de los usuarios registrados; y la otra clase se llama pointregis, donde estaran registrados las fechas de inicio del juego, fin y puntuacion.

Otra clase que tiene el proyecto es el listener, el cual hace operaciones de inicializacion y limpieza.

Esta vez se ha usado para la base de datos PostgreSQL, y el tomcat para hacer la conexion entre el Netbeans y la base de datos.

Hay un index principal, en el cual, el usuario ingresa los respectivos datos para empezar el juego.

Tambien encontraremos documentos css (hojas de estilo) para mejorar dicha pagina principal (con imagenes y diferentes estilos).

# Pasos previos, JPA Project:
- Instalar JPA Tools
- Nuevo projecto: JPA Project, runtime Tomcat 7.0
- A JPA Facet: EclipseLink.2.X (download library) o Netbeans
- Resolver Errors (si hay)
- Coger la libreria postgresql-9.4.1212 (.jar)
- Ir a New -> JPA Entyties from tables (de las tablas ya creadas en nuestra base de datos)
- El documento persistence.xml lo editamos poniendo que nos cree automaticamente los objetos
- Y crear los modelos de los servicios automaticamente o manual
