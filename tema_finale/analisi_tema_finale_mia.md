### Dominio
A Maritime Cargo shipping company (fron now on, simply company) intends to automate the operations of load of freight in the ship’s **cargo hold** (or simply hold). To this end, the company plans to employ a Differential Drive Robot (from now, called **cargorobot**) for the loading of goods (named **products**) in the ship’s hold.

The product to be loaded must be placed in a container of predefined dimensions and **registered**, by specifying its weight, within a database, by using a proper service (**productservice**). After the registration, the productservice returns a unique product identifier as a natural number PID, PID>0.

The hold is a rectangular, flat area with an **Input/Output port** (IOPort). The area provides **4 slots** for the product containers.

- The slots depict the hold storage areas, when they are ocuupied by product containes
- The slots5 area is permanentely occupied, while the other slots are initially empty
- The sensor put in front of the IOPort is a **sonar used to detect the presence of a product container**, when it measures a distance D, such that D < DFREE/2, during a reasonable time (e.g. 3 secs).

```sensor put in front of the I/O port??? non è il robot che ha il sensore?```


### Requisiti
The company asks us to build a software systems (named cargoservice) that:
1. is able to receive the **request** to load on the cargo a product container **already registered in the productservice**.
    - The request is **rejected** when:
        - the product-weight is evaluated too high, since the **ship can carry a maximum load** of MaxLoad>0  kg.
        - the **hold is already full**, i.e. the 4 slots are already occupied.
    - If the request is accepted, the cargoservice **associates a slot to the product PID** and returns the name of the reserved slot. Afterwards, it **waits** that the product container is delivered to the ioport. **In the meantime, other requests are not elaborated**.

    - abbiamo una richiesta di load al cargoservice
        - questa richiesta conterrà i parametri del container da caricare (pid e peso) recuperati dal richiedente tramite una query a product service
    - ```cargoservice waits ..., significa che deve posizionare il robot con il sensore davanti all'io port?```
    - quando accetto una richiesta devo:
        1. scegliere uno slot libero da occupare
            - facile, basta scegliere il primo libero che trovo
        2. posizionare il robot davanti all'io-port (non è esplicitamente espresso ma a lezione abbiamo detto così)
            - non facile, posso usare il planner e il mapper per costruire la mappa del deposito e la sequenza di mosse che mi servono per posizionare il robot in un determinato punto
            - ```ma come faccio a capire durante il mapping se un punto è un I/O port oppure uno slot oppure un muro oppure ...?```



2. is able to detect (by means of the sonar sensor) the presence of the product container at the ioport
    - per capire se il container è arrivato all'io/port ho bisogno che molteplici misurazioni del sonar siano sotto a D_FREE/2
    - il sistema è quindi sia proattivo (devo muovere il robot) che reattivo (solamente quando il sonar mi assicura che il container è arrivato)
    - per facilitare la gestione delle misurazioni del sonar sembra opportuno predisporre un sonar service o meglio un **emitter**
        - gli interessati possono **osservare** gli aggiornamenti di questo sonar che invierà dispatch per **avvisare della presenza/mancanza del container**
    - possiamo realizzare queste funzionalità con il sonar_emitter che fa delle **publish** e il cargo_service che fa **subscribe** 
        - e.g *publish "sonar_emitter_topic" -m container_present : container_present(true)*
    - definiamo quindi **due eventi** che verranno inviati come aggiornamento ai subscriber di sonar_emitter
        - *container_present*
        - *container_absent*
  
    Nota: mqtt mi sembra più opportuno rispetto a CoAP in quanto per fare delle update CoAP io devo aggiungere il contesto dell'observer nell'updater, io però in generale potrei avere molte entità che sono interessate a ricevere aggiornamenti da sonar_emitter



3. is able to ensure that the product container is placed by the cargorobot within its reserved slot. At the end of the work:
    - the cargorobot should returns to its HOME location.
    - the cargoservice can process another load-request

    - qua possiamo usare in un primo momento il mapbuilder per costruire la mappa della stanza. Successivamente, durante il servizio, usare il planner per definire il percorso da fare
        - **di nuovo, come faccio a marcare un ostacolo come slot?**

    - per muovere il robot **posso riutilizzare il software già disponibile**(vrqak, mapper e planner)
        - costruisco una mappa del deposito preliminarmente e me la salvo in maniera persistente
        - cargo_service carica questa mappa ed utilizza il servizio planner per scoprire il percorso da seguire che verrà poi percorso sfruttando il servizio vrqak che muove effettivamente il robot
        - **si potrebbero unire il planner e vrqak in un unico servizio (basic robot) che data una posizione crea il piano e sposta il robot tutto in una volta**

    - abbiamo quindi un secondo servizio oltre a cargo_service, basic_robot
    - quando cargo_service avrà bisogno di spostare il robot maderà **un richiesta** a questo servizio contenente posizione e direzione corrente del robot e posizione e direzione del goal  
        - richiesta in quanto in generale il planner può non trovare un percorso

    - ... rimane il problema del mapping del deposito ...
    - ```non è poi immediato capire chi deve mantenere la posizione del robot?```
        - **cargo_service** ha bisogno di sapere la posizione degli slot e dell'io/port
        - **robot_service** ha bisogno di conoscere l'intera configurazione della mappa insieme alla posizione del robot per poter calcolare i suoi percorsi
        - mi viene quindi da pensare che sia **robot_service a dover mantenere la posizione del robot**




4. is able to show the current state of the hold, by mesans of a dynamically updated web-gui.
    - **non ho ben capito, devo modificare wenv con lo stato degli slot?**
    - in ogni caso, sicuramente cargo_service deve mantenere uno **stato** che contiene **mappa** del deposito, **stato e posizione degli slot**, **posizione dell'io/port** 
    - questo stato può essere tutto racchiuso in un unico **oggettone che viene popolato quando si carica la mappa prodotta dal mapper**




5. interrupts any activity and turns on a led if the sonar sensor measures a distance D > DFREE for at least 3 secs (perhaps a sonar failure). The service continues its activities as soon as the sonar measures a distance D <= DFREE.
    - per interrompere l'attività del robot dovrò comunicare con il robot_service
        - predispongono un messaggio di halt 
        - **dispatch** in quanto non c'è una risposta
        - ```gli interrupt mi interrompono subito oppure devo controllare ad ogni passo che non ci sia stao un halt?```


    - quando il cargorobot non sta trasportando un carico devo essere **insensibile** alle distanze del sonar?
        - **l'insensibilità mi suggerisce che le misurazioni debbano essere degli eventi**
    - quando invece il cargorobot sta trasportando un carico (è avvenuto D < DFREE/2 davanti ad IOport per almeno 3 secondi) devo essere sensibile alle misurazioni del sonar durante il trasporto
        - in caso di caduta del container devo transitare stato per capire quando posso riprendere
    - **ripensandoci meglio**, anche qui il sonar emitter fa al caso mio
    - l'accensione del led non è problematica (probabilmente dovrò predisporre un mini servizio dato che non voglio far girare tutto su raspberry ma poco male)

    

### Formalizzazione dei termini del testo dei requisiti
```Lo scopo dello SPRINT0 è quello di formalizzare i singoli termini del testo (usando un qualche linguaggio di programmazione e/o con modelli) e anche quello di fornire una prima visione di insieme del sistema da realizzare.```

devo definire 
- i messaggi
- i contesti (bounded)
- i serivizi
- gli attori
- i pojo
