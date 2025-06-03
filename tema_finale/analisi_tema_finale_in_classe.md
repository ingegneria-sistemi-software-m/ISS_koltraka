### Quanti bounded context?
Nota: i boundend context dovrebbero saltare fuori analizzando il dominio e i requisiti, NON perchè un servizio mi è più comodo a livello di implementazione 


risposta oracolo:
1. contesto gestione del carico (equivalente al mio cargo_service)
    - **NB**: questo contesto include anche il movimento del robot. 
    - questo è quindi un bounded context che contiene più microservizi 
2. sonar_emitter
3. inventario stiva
    - gestisce lo stato fisico della stiva, tiene traccia dello stato degli slot
    - questo è un aggregato 
    - puoi pensarlo come struttura dati (lista dentro a cargo_service)
        - **va bene mischiare due bounded context in un singolo microservizio?**
    - oppure come servizio a se stante (anche db in caso di stive molto grandi)
4. **gui**
    - mostrare lo stato corrente della stiva
    - in un'architettura a microservizi spesso si separa la logica di scrittura transazionale, da quella di lettura per query
        - pattern CQRS
5. memorizzazione del prodotto (product service)
    - **anche questo è da fare**
    - il cargo_service dovrà fare query per recuperare il peso di un determinato PID
    - **la richiesta al cargo_service dovrà quindi contenere solo il PID**


Per modellare questi contesti bisogna definire come interagiscono tramite una mappa di contesto, ovvero la definizione di 



### Piano di lavoro 
sviluppo indipendente





### Narrowing sul contesto di gestione carico
quali componenti software implementano questo context? questi componenti sono POJO o attori?
- pojo per entità passive (dati / logica stateless)
    - slot
- attori per entità attive che hanno un proprio flusso di controllo
    - gestione carico
        - responsabile dell'orchestrazione della gestione del carico
        - tu fai questo, tu fai quello, ...
        - **MENTE**
    - ...

dalle cose che deve fare il bounded context mi salta fuori un **TEST PLAN**







### Modellazione formale 
lasciamo stare le considerazioni in linguaggio naturale. Usiamo il linguaggio di modellazione che abbiamo studiato: QAK!

**ogni bounded context diventa un contesto QAK**

Le context map vengono implementate dai messaggi

**Attenzione!**
- Nello sprint 0 ci occupiamo dell'**analisi dei requisiti**
- **NON devo definire dei messaggi che non sono esplicitati nei requisiti**, altrimenti mi limito
- gli altri messaggi salteranno fuori nella fase di analisi del problema degli sprint successivi

I requisiti mi definiscono
- requisito 1: Request load_product e relative reply
    - chi manda le richieste?
    - un cliente qualunque... **NON fa parte del sistema**
    - i clienti possono essere simulati con un mock object / test unit

Come sempre, vogliamo essere tech-indipendent. Rappresentare lo stato dello stiva come un struttura dati secca (lista) non mi rende indipendente
- voglio interagire con questo stato senza preoccuparmi di come siano effettivamente rappresentati i dati. 

se ho il dubbio pojo/attore, ricordiamoci che **l'attore è intrinsecamente thread-safe**
- a questo punto il bounded context per lo stato stiva mi viene da dire che è un qactor appartenente al contesto del cargo-service





**Dai requisiti arriviamo fino a qua! Il resto dei requisiti non specificano abbastanza per prendere altre decisioni.** Dobbiamo differire



```chiedere a natali dei modelli di AI``` 