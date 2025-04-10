## Roba QAK
Il Linguaggio qak reso disponibile dalla Qak software factory intende fornire un linguaggio per la definizione di **modelli eseguibili** di un sistema,
basati su un insieme di **concetti** volti a cattuare l'idea che un sistema software (distribuito) possiede le seguenti caratteristiche:
- il sistema è formato da una insieme di attori
- gli attori interagiscono scambiandosi messaggi (di tipo IApplMessage, nel nostro caso)
- un attore è un ente autonomo, capace di elaborare messaggi. **Pertanto la struttura del codice di un attore si presta ad essere modellata come un Automi a stati finiti**
- gli attori sono raggruppati in contesti che li abilitano a interazioni via rete
- i contesti possono essere allocati (deployed) su uno o più nodi computazionali, fisici o virtuali

la software factory genera codice Kotlin
- perchè Kotlin? lo capiremo

abbiamo fatto un **modello** e non un programma
- modello poi anche eseguibile

i file pl sono file prolog e rappresentano la base di conoscenza del sistema (generati automaticamente nel modello)
- dentro hanno dei fatti **prolog** 

ogni attore QAK è una risorsa CoAP, un protocollo derivato dopo che si è capito nelle reti che conviene impostare l'interazione tra i servizi in maniera RESTFul
- CoAP utilizza però UDP e non TCP (protocollo per sistemi constrained)

A quanto pare abbiamo più server quando eseguiamo il nostro modello, e quindi possiamo comunicare in modi diversi
- TCP
- CoAP
- MQTT

Perchè questo è un modello e non un programma? 
- l'intenzione di chi ha progettato questo sistema è di catturare degli aspettati fondamentali e di nasconderne altri nella parte sommersa


**Domanda** gli unici programmi che esistono sono quelli in linguaggio macchina?
capiamo: domanda strettamente legata a quella dei linguaggi

- perchè si è sentito il bisogno di creare Java (o qualsiasi altro linguaggio)?
    - Si sentono delle spinte per avere dei **concetti** di più alto livello rispetto a quelli dei linguaggi del momento 
    - sfogliando un manuale C non troverò mai il concetto di Oggetto
    - Vengono in mente dei concetti che possono **aiutare nello sviluppo del software**
    - Questi concetti esistono nella testa delle persone ma se non esistono nel linguaggio bisogna crearsele con fatica
    - posso sempre crearmi una libreria dato che i linguaggi sono turing completi e quindi posso fare quello che voglio
    - Tuttavia, quello che mi manca è la capacità espressiva: se io non catturo le cose che ho in testa con un linguaggio che ha una certa sintassi faccio molta fatica a trasmettere queste idee ad altri esseri umani (e a buttale giù io stesso). 
        - nota che per la macchina non cambia niente, infatti tutto viene tradotto in linguaggio macchina
    - le librerie stanno solo nel piano semantico ma non comunicano niente delle idee che aveva in testa il creatore della libreria
    - es. di MqttInteraction come libreria che mi cambia la semantica della comunicazione MQTT ma che ci è voluta della documentazione per spiegarla
    - i linguaggi aggiungono anche la sintassi che esprime i concetti che ho in testa in maniera evidente 
        - in QAK abbiamo direttamente nel linguaggio i concetti di "sistema, contesto e attore". In java/kotlin questi concetti non esistono tuttavia li possiamo implementare, tanto è vero che possiamo tradurre i DSL in Java/Kotlin ecc...
        - **chi fa la traduzione?** i plugin che abbiamo installato (oltre a produrre l'ide) mi definiscono le regole per la valutazione dell'AST 

**torniamo alla domanda iniziale**: Perchè questo è un modello e non un programma? 
- in teoria solo il linguaggio macchina produce programmi, tutti gli altri linguaggi infatti hanno concetti che la macchina non ha idea di che cosa siano (oggetti, funzione, ...)

**chatgpt**:
I programmi QAK sono più programmi o modelli eseguibili? 


ChatGPT ha detto:
I programmi QAK sono una via di mezzo tra modelli eseguibili e programmi tradizionali, ma si avvicinano di più al concetto di modello eseguibile.

Perché QAK è un modello eseguibile? 
- Rappresentazione dichiarativa
    - Un file QAK descrive il comportamento di un sistema multi-agente in termini di stati, eventi e transizioni.
    - Non è un semplice script di istruzioni sequenziali (come in C o Java), ma un modello di esecuzione basato su automi a stati finiti (FSM).
- Esecuzione guidata da eventi
    - Gli agenti non eseguono codice in un flusso rigido, ma reagiscono a eventi, proprio come nei modelli di simulazione.
    - Questo lo rende simile a un modello computazionale piuttosto che a un programma imperativo tradizionale.
- Astrazione dalla piattaforma sottostante
    - Un file QAK non è codice nativo, ma una descrizione che viene interpretata da un motore di esecuzione basato su Kotlin e Akka.
    - Questo è tipico dei modelli eseguibili, che definiscono il comportamento senza specificare dettagli di implementazione.

Ma QAK è anche un programma?
In un certo senso, sì, perché può essere eseguito direttamente su una piattaforma runtime basata su Kotlin e Akka. Tuttavia, rispetto a un programma tradizionale:
- Ha un motore di esecuzione dedicato.
- È più dichiarativo e modellato rispetto a un linguaggio classico come C o Java.
- Non definisce direttamente la gestione della memoria o l'uso esplicito della CPU.

Conclusione: QAK è più un modello eseguibile
QAK descrive un modello di interazione tra agenti, che può essere eseguito, ma mantiene un livello di astrazione più alto rispetto a un classico programma imperativo. È simile ai modelli statechart eseguibili usati in ingegneria del software. 🚀


- in pratica, la differenza sta nel fatto che in un DSL io scelgo di esprimere i concetti che voglio io e ancora di più scelgo di NON esprimere i concetti che non mi interessano. I linguaggi GP esprimono concetti generali per ottenere qualsiasi computazione possibile.

Conclusione: un linguaggio è l'unica arma che mi da la capacità di esprimere le mie idee



### Che cosa cerchiamo di fare con i qactor?
mentre con il principio di iron-man abbiamo creato un sistema come giustapposizione di pezzi con i qactor l'interazione diventa nativa dei miei sistemi
- boh, non ho ben capito...

# Perchè abbiamo fatto conway life?
è un sistema con delle mosse molto limitate (assimilabile alle mosse di una MdT). Tuttavia, il comportamento è impredicibile
- con poche insignificanti regole (anche stupide) sono capaci di supportare qualsiasi cosa che mi venga in mente, complessa a piacere
- d'altronde conway life è turing complete 

# in che cosa consiste l'essenza del top-down piuttosto che del bottom-up (differenza tra informatico e ingegnere)?
- non parto dalle mosse del mio linguaggio o di una libreria, piuttosto **parte dalle mosse richieste dal problema**
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





### Roba QAK

una delle prime cose che QAK ci obbliga a definire sono **i tipi di messaggi** che girano nel mio sistema
- i messaggi sono la "colla" che riunisce i vari servizi in cui ho spezzato il mio sistema

Successivamente, prima della definizione di ogni attore, viene definito un **contesto** per la comunicazione

Una buona idea condivisa per la progettazione di un agente in un sistema distribuito è organizzarlo come un ASF... 
- **Perchè???** (guarda linea 6)
- Abbiamo che l'ASF/attore transita in un altro stato quando riceve un messaggio, ma anche con delle **mosse spontanee**
- abbiamo uno e uno solo stato iniziale
- molteplici stati finali
- ho due modi per iniettare degli oggetti dentro ad un attore
    - tramite delle keyword: **withobj** e **using** 
    - tramite dichiarazioni classiche in kotlin
- è meglio scrivere nel DSL o in Kotlin?
    - nel nostro caso utilizzando withobj mi aggiunge dei "tondi" quando genero l'immagine del modello


In questo primo attore abbiamo sostanzialmente sostituito il LifeController vecchio con un attore. Notare che la logica del gioco è comunque implementato dal vecchio gioco tale e quale (monolite)

Interessante: se sono in un determinato stato, transito di stato solamente per le tipologie di messaggio che specifica lo stato in cui sono, il resto viene ignorato
- questo è interessante dal punto di vista della M2M, i vari attori possono spammare messaggi e grazie a QAK sono in grado di specificare MOLTO PIù FACILMENTE quali e come gli voglio gestire rispetto al caso in cui io debba, ad esempio, ignorare a mano tutti i messaggi che non mi interessano  

i messaggi ignorati vengono messi dall'infrastruttura in una coda separata rispetto a quella dell'attore

NB: dove si trova l'infrastruttura per gli attori QAK? NON dentro a dropins, che contiene solo la parte linguistica di QAK, piuttosto si trova dentro alle unibolibs
- se cambia la parte linguistica, cambia dropins
- se cambia il runtime, cambia unibolibs

OutInMqtt manda/riceve messaggi dall'esterno ed eventualemente gli traduce in messaggi all'attore MQTT 

OSS: troviamo delle send ma mai delle receive per scelta di progettazione del linguaggio
Interessante: potrebbe avere senso gestire alcuni messaggi direttamente a livello di infrastruttura piuttosto che a livello di cervello (vedi messaggi cell in InOutMqttForActor)


### Perchè vogliamo rendere ogni cella un microservizio a se stante?
vai a vedere le risposte sui suoi HTML








### messaggi ms0
sintassi minuscola prolog

identificatore a sinistra, payload a destra

tipologie
- request&response
- dispatch (fire&forget)
- **evento**: è un'informazione che una sorgente emette **senza specificare alcun destinatario** (le due precedenti si)
    - cosa fa emit?
    - l'infrastruttura QAK, propaga l'evento a **tutti** gli attori del sistema
    - siano essi locali al contesto dell'attore emittente, che appartenenti ad altri contesti
    - gli eventi vengono inseriti nella coda di ogni attore dall'infrastruttura

Quando un attore entra in un determinato stato
- inizialmente, fa la sua roba
- poi controlla la sua coda
- se è vuota, mi sospendo in attesa di venire risvegliato da un mittente
- se c'è qualcosa vede se transitare di stato oppure se ignorare (e.g. eventi che non voglio gestire)

con publish pubblico su una topic

con emit lancio un evento verso tutti gli attori del sistema

quando invio dei messaggi al mio sistema qulli non gestiti, che non sono eventi, riempono una coda di sistema che mi devo ricordare di gestire 


# RIGUARDA CALLER COAP e MQTT



# Sfida
Voglio rendere ciascuna cella di conway autonoma
- analizza il problema (scordati dei QAK)
- una volta che ho capito il problema, esplora le tecnologie che mi riducono l'abstraction gap e motiva eventuali scelte


inoltre, prendi due cavetti femmina e un led, cerca di capire come accendere un led dal raspberry
- installaci JAVA


Coordinazione di microservizi mediante due principali paradigmi:
- coreografia
- orchestrazione


può essere utile fare un prototipo per capire meglio quali problematiche insorgono (e mi fa capire perchè gli atttori QAK convengono)




# COME MAI PROLOG PER RAPPRESENTARE LE BASI DI CONOSCENZA? per le query?





### ANALISI DEL PROBLEMA FATTA IN MANIERA FUMOSA
in sostanza siamo incerti e abbiamo prodotto fuffa.
- potrebbe essere coreografato...
- magari sarebbe meglio centralizzato...
- potrebbe, magari, ecc.

poca assertività, è normale dato che il problema è complicato. Per comprendere meglio il problema diventa opportuno fare una **prototipazione!**
- In questo senso **il prodotto finale dell'analisi del problema diventa il modello eseguibile!**
- un qualcosa che **gira** e non qualcosa di fumoso



discussione in classe dell'analisi del problema
- mqtt è più vicino al problema rispetto a TCP come protocollo di comunicazione tra le celle 
- **intuizione**: posso assegnare un nome ad ogni cella (e.g. cell_0_0) che guarda un po' scelgo che sia anche il nome del topic della cella
- che semantica utilizzo per i messaggi (eventi, dispatch, request & response)
    - request&response va anche bene tuttavia è un po' ridondante dato che le celle hanno un comportamento simmetrico
    - dispatch quindi è sufficiente
- differenza tra orchestrazione e coreografia
    - ogni cella ripete il suo comportamento ogni DT tempo? oppure aspetto un segnale di sincronizzazione?
    - con DT ho praticamente un clock, e sto dicendo che ogni cella ci mettè al più DT tempo ad effettuare la sua evoluzione (considerando messaggi, computazione, ecc... ) -> coreografia
    - con SYNCH
- chi è che inizializza le celle? il capo centrale però mi serve 


NOTA: prima è utile pensare a qualcosa che sensatamente può funzionare, in un secondo momento penso a come trattare eventuali problemi



facciamo ora il modello

- dyanmicOnly vuol dire che quell'attore non nasce da solo ma deve venire creato



**Compiti**
- sostituire callerTCP con un attore che fa la stessa cosa
- sistema simulato in cui le celle non sono 4 ma sono determinate dinamicamente da un numero dentro ad una configurazione
    - ho bisogno di un builder che tra le altre cose assegna i nomi agli attori
    - vabe, sta roba c'è nell'analisi

Scaricare la roba del rasberry