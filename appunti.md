analisi del problema:
- che cos'è Modena?
- architettura logica del sistema
    - non **come** io risolverei il sistema
    - ma **cosa** mi impongono i requisiti

Gradle: casino

problema che mi fa prendere B e non A:
- manca il concetto di cella e la griglia che è caratteristica del dominio

**OSS**: la rappresentazione interna e la rappresentazione esterna sono completamente disaccoppiate!



**COMPITO**
refactor per aggiungere Cell e Grid

problematica: chi ha la responsabilità di visualizzare lo stato delle celle? 


Aggiungi diario di bordo userDocs/conwayInitial_v0.html

NON È LA DOCUMENTAZIONE DEL PROGETTO, È UN DIARIO DI BORDO
- 

// copiare il contenuto del template da mandare
// inserire anche altre due directory /css /images

requirement analysis: capire quali sono le entità del dominio applicativo dai requisiti
- user stories: mettersi nei panni dei possibili utenti (ad esempio il player) e scrivere 
in linguaggio naturale cosa dovrà fare il sistema dal suo punto di vista




**NB**: rischio grave comune nelle applicazioni web: le gui/frontend che fanno azioni pertinenti alla logica di buisness




### Cosa si fa nel diario di bordo?
- cosa scrivo nei requirement?
    - copio i requirement del committente (o direttamente o come link)
- interessante osservare dai requisiti, che ci siamo concentrati sulla pagina HTML e non sulla interazione con l'applicazione
    - di nuovo separation of concerns
- mettici pensieri come: "non includo il jar della logica di buisness"
- Quali sono le idee che mi fanno arrivare al risultato?
    - analisi dei requisiti
    - analisi del problema
    - progettazione
    - il posto dove lo metto è qualificante
        - le cose che metto nei requisiti sono REQUISITI, non vanno più modificati
        - ...
- le user stories dove le metto?
    - qual'è lo user più importante?


ACCORGIMENTI:
- copiare ogni volta le unibolibs **che sono fuori**
- fare apply se non le sta trovando


MVC
- model == codice che abbiamo già scritto
- view == pagina html
- **controller == springboot!**

differenza tra machine-to-machine (microservizi) 
- automatizzazione del testing

e human-to-machine(frontend) interfaces

Ajax o Websocket?

differenze tra @Controller e @RestController
- una per HMI e l'altra MMI
- una da pagine e l'altra del JSON




**Conway player**: scrivere un'applicazione Java che controlla il gioco, facendo finta di essere l'owner. Ovvero:
- fa il "login"
- inizializza la griglia in un certo modo
- fa start
- fa stop

Analisi:
- la macchina che serve il gioco è capace di inviare/ricevere messaggi su websocket
    - start
    - stop
    - ...
- come faccio a trovare la macchina con il server?
- pianificazione del test?

**NB**: quando si lavora con le websocket è essenziale scrivere il file WebSocketConfiguration (guarda il suo caller)





importanza interazioni asincrone
- non c'è una receive esplicita bloccante
- funzioni di callback

**NB**: è la libreria di supporto alle websocket ad essere responsabile per la ricezione dei messaggi sulla websocket NON il caller. Quando riceve un messaggio la libreria invocherà la funzione di callback che il caller ha registrato tramite annotazione-



mandare messaggi a mano è poco espressivo
- voglio eliminare di star lavorando con TCP/UDP/HTTP/...
- voglio solo il concetto di interconnessione e dato che non è disponibile me lo invento

Voglio aggiungere astrazione in modo da ottenere Technology independece!
- se cambio la tecnologia non cambia la mia applicazione 

capisci meglio la differenza tra usare un pattern Observer nelle unibolibs rispetto al farsi gestire l'asincronismo dalla librerie websocket


tipi di messaggio strutturati

sono sempre asincrono, non mi blocco




# COMPITO PER CASA
Fare l'interazione con l'oggetto interconnessione tra Player e Server
- containerizzare il server in docker

Eventualmente:
**Analisi del problema**:
voglio spezzare la GUI dal game server basata su MQTT simmetrica 




### Alla fine di settimana prossima
fare una paginetta in cui si evidenziano gli aspetti essenziali di quanto fatto in questa prima fase

Keypoints:
1) implementazione in java del gioco conway! 
    - prima cosa che abbiamo fatto è stata il core buisness!
    - non ci siamo lasciati distrarre dalle GUI 
        - DOMAIN DRIVEN SOFTWARE DEVELOPMENT
    - altri principi:
        - divide et impera
        - single responsability
        - e inversione delle dipendenze con l'interfaccia IODev. Questa interfaccia è nata dal core buisness dato che è il gioco stesso ha 
        il requisito di essere mostrato
        - prototipazione per vedere se le cose funzionano
        - "principio di ironman"
            - embedding del sistema già funzionante in un framework che servisse la GUI
            - mettere in evidenza la relazione tra springboot e il sistema
        - **punto delicatissimo**: a quanto pare il fatto che il controller cambi lo stato del gioco modificando delle variabili è delicattisimo
        - chi è che chiama i metodi del LifeController? Il controller di Spring!
            - Che cosa provoca l'invocazione dei metodi del controller di Spring? I messaggi sulla websocket! Distinti dalla 
2) l'invio di messaggi tramite websocket mi apre la possibilità alla M2M interactionù
3) keypoint: vogliamo essere tecnology indipendent

### MQTT
l'interazione pub/sub serve a disaccoppiare mittenti e destinatari, l'unica cosa che condividono è l'indirizzo del broker.
- l'unica dipendenza è quella del broker

concetto di QOS dei messaggi.


se faccio partire il subscriber dopo il pusblisher, il subscriber non vede niente.

Separare in due microservizi il gioco dalla giu è utile perchè in questa maniera:
- il gioco diventa indipendente dalla gui
- il servizio diventa osservabile
- posso avere più microservizi (gui, logger, AI, ...) che ricevono informazioni dal microservizio di gioco

rifare conwaygui nella sua versione mqtt
- nuovo progetto

keypoint: vogliamo essere tecnology indipendent

**NB**: non c'è paragone tra lo sforzo di tempo e pensiero che viene impiegato per implementare una comunicazione con paho rispetto a quello che spendo per implementare una comunicazione con librerie/astrazioni di più alto livello
- sarebbe utile sperimentarlo sulla propria pelle facendolo a mano, sia con paho che con unibolibs
- **compito per casa**: spezzare la gui da life con le due topic viste a lezione! (lifein, guiin)

**OSS**: nel life controller prima non mi preoccupavo dell'input dato che a quello ci pensava la libreria delle websocket la quale invocava direttamente i metodi di lifecontroller. Ora che voglio spezzare mi devo anche preoccupare dell'input e quindi dovrò preoccuparmi di sostituire il vecchio WsIODev

**OSS**: ogni entità del mio sistema ha un nome univoco

Importante ricordare come mai le callback vengono utilizzate in questi protocolli -> asincronicità 


come si implementa una blocking queue?


OSS:
- agente autonomo: un'entità che una volta creata ha un flusso di controllo autonomo (non è un POJO)


- WsConwayguiLifeMqtt sostituisce WsIODev nella gestione dei messaggi dei client
    - inoltre, siccome ora il gioco vive all'interno di un'altro servizio, invia i messaggi di aggiornamento con mqtt e non tramite chiamate di funzione locali
    - lato game invece cambia il life controller che adesso non invierà più indietro messaggi (con la websocket). Ora utilizza MQTT verso il servizio della gui

- containerizzare conway alone e conwaymqtt


fare la transizione coi video