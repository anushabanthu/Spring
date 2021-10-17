Links:
https://start.spring.io/ - To generate project folder structure
https://spring.io/guides/gs/spring-boot/ - Getting started

Notes:
gradle -> used to build and run applications
spring boot web apps -> contains application, controller and service.
package name should be same as folder structure inside src/main/java.
@name -> Annotations. Spring understands annotations and doesnt like java.
application -> @SpringBootApplication -> Class Annotation -> 
	@Bean methods are invoked locally with gradlew.bat bootRun after building the application.
	->can have multiple @Bean methods.
	@Bean -> Methods that will be invoked by SpringBoot when gradlew.bat bootRun is invoked.
			These methods will be invoked only when their return type is CommandLineRunner.
controller -> @RestController -> Class Annotation(used for web apps).Its bean methods(@GetMapping(/) will be executed when localhost is loaded. 
	-> can only import services (@Service)
service -> @Service -> Class Annotation -> @Restcontroller can use @Service by importing the files.
@Autowired -> Variable Annotation-> used to create object for a class automatically.

Steps:
To build and run SpringBootApplication -> gradlew.bat bootRun (@SpringBootApplication @Bean methods will be invoked)
To run web app on local server -> curl localhost:8080 or url: http://localhost:8080 (@RestController @GetMapping("/") will be invoked)
Workarounds:
1)gradle needs 8080 port to run apps. But oracle's process(TNSLSNR.exe) uses it. 
	workaround: kill oracle process everytime after startup.
	
