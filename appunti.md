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


### Discussione relazione fine fase 1
...

- guarda indagine Iron-Man
- guarda indagine comunicazione vs interazione















## Roba QAK
la software factory genera codice Kotlin
- perchè Kotlin? lo capiremo

abbiamo fatto un modello e non un programma
- modello poi anche eseguibile

i file pl sono file prolog e rappresentano la base di conoscenza del sistema (generati automaticamente nel modello)
- dentro hanno dei fatti **prolog** 

ogni attore QAK è una risorsa CoAP, un protocollo derivato dopo che si è capito nelle reti che conviene impostare l'interazione tra i servizi in maniera RESTFul
- CoAP utilizza però UDP e non TCP

A quanto pare abbiamo più server quando eseguiamo il nostro modello, e quindi possiamo comunicare in modi diversi
- TCP
- CoAP
- MQTT

Perchè questo è un modello e non un programma? 
- l'intenzione di chi ha progettato questo sistema è di catturare degli aspettati fondamentali e di nasconderne altri nella parte sommersa

gli unici programmi che esistono sono quelli in linguaggio macchina?
- capiamo? domanda strettamente legata a quella dei linguaggi
- perchè si è sentito il bisogno di creare Java (o qualsiasi altro linguaggio)?
    - Si sentono delle spinte per avere dei **concetti** di più alto livello rispetto a quelli dei linguaggi del momento 
    - sfogliando un manuale C non troverò mai il concetto di Oggetto
    - Vengono in mente dei concetti che possono **aiutare nello sviluppo del software**
    - Questi concetti esistono nella testa delle persone ma se non esistono nel linguaggio bisogna crearsele con fatica
    - posso crearmi una libreria dato che i linguaggi sono turing completi e quindi posso fare quello che voglio
    - Tuttavia, quello che mi manca è la capacità espressiva: se io non catturo le cose che ho in testa con un linguaggio che ha una certa sintassi faccio molta fatica a trasmettere queste idee ad altri esseri umani. 
        - nota che per la macchina non cambia niente
    - le librerie stanno solo nel piano semantico ma non comunicano niente delle idee che aveva in testa il creatore della libreria
    - es. di MqttInteraction come libreria che mi cambia la semantica della comunicazione MQTT ma che ci è voluta della documentazione per spiegarla
    - i linguaggi aggiungono anche la sintassi che esprime i concetti che ho in testa in maniera evidente 
        - in QAK abbiamo direttamente nel linguaggio i concetti di "sistema, contesto e attore". In java/kotlin questi concetti non esistono tuttavia li possiamo implementare, tanto è vero che possiamo tradurre i DSL in Java/Kotlin ecc...
        - **chi fa la traduzione?** i plugin che abbiamo installato (oltre a produrre l'ide) mi definiscono le regole per la valutazione dell'AST 

torniamo alla domanda iniziale: Perchè questo è un modello e non un programma? 
- in teoria solo il linguaggio macchina produce programmi, tutti gli altri linguaggi infatti hanno concetti che la macchina non ha idea di che cosa siano (oggetti, funzione, ...)
- in pratica, la differenza sta nel fatto che in un DSL io scelgo di esprimere i concetti che voglio io e ancora di più scelgo di NON esprimere i concetti che non mi interessano. I linguaggi GP esprimono concetti generali per ottenere qualsiasi computazione possibile.

Conclusione: un linguaggio è l'unica arma che mi da la capacità di esprimere le mie idee


ed aggiungere l'auto-valutazione sul sito
**mettere la motivazione per il B**


### Che cosa cerchiamo di fare con i qactor?
mentre con il principio di iron-man abbiamo creato un sistema come giustapposizione di pezzi con i qactor l'interazione diventa nativa dei miei sistemi
- boh, non ho ben capito...








# Perchè abbiamo fatto conway life?
è un sistema con delle mosse molto limitate (assimilabile alle mosse di una MdT). Tuttavia, il comportamento è impredicibile
- con poche insignificanti regole (anche stupide) sono capaci di supportare qualsiasi cosa che mi venga in mente, complessa a piacere
- d'altronde conway life è turing complete 


# in che cosa consiste l'essenza del top-down piuttosto che del bottom-up (differenza tra informatico e ingegnere)?
- non parto dalle mosse del mio linguaggio o di una libreria, piuttosto parte dalle mosse richieste dal problema
- a questo punto controlliamo se il linguaggio supporta queste mosse o meno
- se si, godo; altrimenti, abstraction gap, come colmarlo?
    - esiste qualche libreria nel linguaggio che ho scelto che mi implementa le mosse che mi servono?
    - se non esiste, esiste qualche altro linguaggio, o una libreria di un altro linguaggio, che implementa questo linguaggio?




# Che cosa si fa quando si viene presentati ad un problema?
sindrome del foglio bianco

ricorda la differenza tra programmi e modelli
- UML e non UPL (Unified Programming Language)



**NMB**: siccome io lavoro nel campo dei sistmi distribuiti, e in questo campo le entità sono "vive" (agenti, idea fumosa), UML NON si presta bene a modellare problemi in questo dominio
- mi invita a pensare al mio problema come ad un insieme di classi (di morti)
- mi invita a pensare alla interazione come ad un cedimento di controllo tramite la chiamata di procedura (morti)
- in questo campo io voglio pensare a degli agenti vivi che interagiscono mediante scambio di messaggi
- **ho un abstraction gap**
- l'UML lo butto in quanto non adatto (godo)
- che cosa uso?
- **voglio sostituire il metamodello UML con un'altro metamodello**

**Che cos'è un metamodello?**
- te lo puoi immaginare ma lo definiamo meglio in futuro
- vai a chiedere a CHATGPT i vari metalivelli di UML (M0, M1, ...)
- se modelli qualcosa con UML dentro la testa hai i concetti del livello M2 di UML
- se, come nel nostro caso, abbiamo bisogno di un ML diverso da M2 abbiamo bisogno di un meta-meta-modello che me lo modelli (M3) 

tutta questa teoria nasce da UML, xText mi permette di implementarla


perchè voglio fare 400 attori? che vantaggi ho?
perchè non concentrato?