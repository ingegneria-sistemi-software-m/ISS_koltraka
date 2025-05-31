### Dominio
A Maritime Cargo shipping company (fron now on, simply company) intends to automate the operations of load of freight in the ship’s **cargo hold** (or simply hold). To this end, the company plans to employ a Differential Drive Robot (from now, called **cargorobot**) for the loading of goods (named **products**) in the ship’s hold.

The producty to be loaded must be placed in a container of predefined dimensions and **registered**, by specifying its weight, within a database, by using a proper service (**productservice**). After the registration, the productservice returns a unique product identifier as a natural number PID, PID>0.

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
        - the **hold is already full**, i.e. the 4 slots are alrready occupied.
    - If the request is accepted, the cargoservice **associates a slot to the product PID** and returns the name of the reserved slot. Afttwerds, it **waits** that the product container is delivered to the ioport. **In the meantime, other requests are not elaborated**.

    - abbiamo una richiesta di load al cargoservice
        - questa richiesta conterrà i parametri del container da caricare (pid e peso) recuperati dal richiedente tramite una query a product service
    - cargoservice waits ..., significa che deve posizionare il roboto con il sensore davanti all'io port?

2. is able to detect (by means of the sonar sensor) the presence of the product container at the ioport

3. is able to ensure that the product container is placed by the cargorobot within its reserved slot. At the end of the work:
    - the cargorobot should returns to its HOME location.
    - the cargoservice can process another load-request

    - qua possiamo usare il mapbuilder per costruire la mappa della stanza e usare il planner per definire il percorso da fare
        - **come faccio a marcare un ostacolo come slot?**

4. is able to show the current state of the hold, by mesans of a dynamically updated web-gui.
    - devo modificare wenv con lo stato degli slot?

5. interrupts any activity and turns on a led if the sonar sensor measures a distance D > DFREE for at least 3 secs (perhaps a sonar failure). The service continues its activities as soon as the sonar measures a distance D <= DFREE.
    - quando il cargorobot non sta trasportando un carico devo essere **insensibile** alle distanze del sonar?
        - l'insensibilità mi suggerisce che le misurazioni debbano essere degli eventi in quanto da scartare in questo stato
    - quando invece il cargorobot sta trasportando un carico (è avvenuto D < DFREE/2 davanti ad IOport per almeno 3 secondi) devo essere sensibile alle misurazioni del sonar durante il trasporto

    - abbiamo quindi delle misurazioni come messaggio


### Formalizzazione dei termini del testo dei requisiti
```Lo scopo dello SPRINT0 è quello di formalizzare i singoli termini del testo (usando un qualche linguaggio di programmazione e/o con modelli) e anche quello di fornire una prima visione di insieme del sistema da realizzare.```

devo definire 
- i messaggi
- i contesti (bounded)
- i serivizi
- gli attori
- i pojo
