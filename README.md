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



docker

microservizio patientWrite
docker pull nturri1306/csa:write_0.0.1-SNAPSHOT
microservizio namingServer
docker pull nturri1306/csa:naming-server_0.0.1-SNAPSHOT





