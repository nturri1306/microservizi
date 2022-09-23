# MICROSERVIZI DEMO

lo scopo del documento è

#### - realizzare una serie di microservizi in java con spring boot 

#### - creare le relative immagini docker

#### - pubblicare le immagini docker in un orchestratore di tipo kubernetes il questo caso utilizzeremo OpenShit di RedHat

#### - per la compilazione e pubblicazione delle immagini sarà utilizzato il Jenkins


 scopo del documento è
- realizzare una serie di Microservizi in java con Spring Boot
- creare le relative immagini docker
- pubblicare le immagini docker in un orchestratore di tipo kubernetes (in questo caso utilizzeremo OpenShit di RedHat)
- per la compilazione e pubblicazione delle immagini sarà utilizzato il Jenkins


1. Microservizi
per la demo verrano costruiti 2 microservizi di tipo Rest Api con  Spring Boot e Spring Cloud. 
il primo microservizio PatientWrite serve per registrare i dati generici di un paziente
il secondo microservizio NamingService è un servizio di naming che usa Eurekaserver

### Cos'è Eureka Server?
Eureka Server è la scoperta di servizi per i tuoi microservizi. 
Le applicazioni client possono registrarsi automaticamente con esso e altri microservizi possono contattare Eureka Server per trovare i microservizi di cui hanno bisogno.


### NamingService

```
package it.csa.naming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class NamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServerApplication.class, args);
	}

}

```
application.properties


```
spring.application.name=naming-server
server.port=8761
#i parametri sotto servono ad evitare errori quando il servizio è attivo
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.server.enableSelfPreservation=false
```

8761 è una porta di default per i server di naming

nel pom.xml

sono presenti queste 2 dipendenze che servono pe il funzionamento di eurekaserver
```
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
```

nel file pom.xml all'interno del tag build sono presenti i tag per la creazione dei file docker

```

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<image>
						<name>it.csa.naming/${project.artifactId}:${project.version}</name>
					</image>
					<pullPolicy>IF_NOT_PRESENT</pullPolicy>
				</configuration>
			</plugin>
```

per creare la relativa immagine docker bisogna avere il docker-engine (https://docs.docker.com/engine/) installato sulla macchina di sviluppo

portarsi sulla cartella del progetto del microservizio spring e digitare

mvn spring-boot:build

![This is an image](https://github.com/nturri1306/microservizi/blob/main/cli1.png)

verrà creata immagine del microservizio relativo con il nome specificato nel tag del pom

con il comando "docker images" da shell del computer possiamo vedere l'immagine creata presente

![This is an image](https://github.com/nturri1306/microservizi/blob/main/cli2.png)

la stessa procedura dovrà essere ripetuta per eventuali altri microservizi 

### PatientWrite

è un semplice microservizio di test che contiene un solo metodo per scrivere su un dabatase di h2 di test

da notare il file pom.xml la presenza del eureka client che serve per la registrazione del microservizio nel server di naming
 
 ```
 <dependency>
	<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
```

che verrà specificato nel file application.properties


nel caso si lavora sul cloud è opportuno utlizzare delle variabili di ambiente

```
eureka.client.serviceUrl.defaultZone=${eurekaserver}
```
per dare il nome al servizio verrà utilizzato

```
spring.application.name=patientwrite-service
```

da notare quando sia il microservizio del patient che del naming sono attivi possiamo controllare sul naming la lista dei servizi registrati


![This is an image](https://github.com/nturri1306/microservizi/blob/main/cli3.png)	       
		
		

docker

microservizio patientWrite
docker pull nturri1306/csa:write_0.0.1-SNAPSHOT
microservizio namingServer
docker pull nturri1306/csa:naming-server_0.0.1-SNAPSHOT





