# MICROSERVIZI DEMO

lo scopo del documento è

#### - realizzare una serie di microservizi in java con spring boot 

#### - creare le relative immagini docker

#### - pubblicare le immagini docker in un orchestratore di tipo kubernetes il questo caso utilizzeremo OpenShit di RedHat

#### - per la compilazione e pubblicazione delle immagini sarà utilizzato il Jenkins







### 1. Microservizi

per la demo verrano costruiti 2 microservizi di tipo Rest Api con Spring Boot che utilizzeranno un pattern di tipo CQRS / Event Sourcing

il primo serve per registrare i dati di un paziente 

il microservizio non scrive direttamente sul db Mysql ma su una piattaforma che gestisce i dati in tempo reale Apache Kafka

una copia di backup verrà anche scritta su un database di tipo MongoDB per una maggiore coerenza dei dati ed un eventuale recupero 

questo tipo di struttura permette di inviare migliaia di chiamate verso un nodo senza avere nessun tipo di problema di rallentamento

ci sarà un servizio che si occuperà in background della scrittura dei dati sul db MySql con JPA


il secondo microservizio esegue solo operazioni di lettura sui dati inseriti del paziente direttamente sul db




### docker

microservizio patientWrite

docker pull nturri1306/csa:write_0.0.1-SNAPSHOT

microservizio namingServer 

docker pull nturri1306/csa:naming-server_0.0.1-SNAPSHOT





