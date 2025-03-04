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







- TDD introdotto


- rischio grave comune nelle applicazioni web: le gui/frontend che fanno azioni pertinenti alla logica di buisness




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


---

ACCORGIMENTI:
- copiare ogni volta le unibolibs **che sono fuori**
- fare apply se non le sta trovando

workflow:
- copiare


MVC
- model == codice che abbiamo già scritto
- view == pagina html
- **controller == springboot!**

...

differenza tra machine-to-machine (microservizi) 
- automatizzazione del testing

e human-to-machine(frontend) interfaces

...

Ajax o Websocket?

...

differenze tra @Controller e @RestController
- una per HMI e l'altra MMI
- una da pagine e l'altra del JSON



guardare service discovery della websocket script