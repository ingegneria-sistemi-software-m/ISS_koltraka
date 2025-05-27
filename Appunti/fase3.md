Che rapporto c'è tra il sopra e il sotto?
- Sopra === logica applicativa === cio che risponde ai requisiti
- Sotto === controllo dell'hardware

Cosa deve fare il sopra?
- implementare la logica applicativa

Il sotto?
- si occupa dei dettagli tecnologici per, ad esempio, acquisire i dati dal sonar / accendere il led 

Quali requisiti attribuisco al sopra?

Al sotto?

se risolvo il problema con python, la logica applicativa è intrecciata con il sotto

Il sonar è un object o un actor?

...



Far pubblicare direttamente al software che controlla il sonar (sotto/basso livello) su una topic mqtt le misurazione del sonar, mi deve turbare in quanto queste cose non dovrebbero essere responsabilità sua.
- ricordi iron-man?
- la responsabilità della comunicazione non devo darla al dispositivo, piuttosto costruisco un armatura che ingloba il software che governa il sonar (che mi scrive sul file stdout) legge le sue misurazioni e sarà lui a pubblicare sulla topic
- **il sonar lo faccio una volta e non lo tocco più**


```Conclusione: il sonar fa solo il sonar; non vogliamo inquinare il suo codice con logica applicativa/comunicazione```

Il sonar non deve essere responsabile delle migliaia di applicazioni che esistono che lo possono sfruttuare! In altre parole, non ha senso che ogni volta che scrivo una applicazione che usa il sonar io debba modificare il codice di quest'ultimo!

**Domanda interessante**: dato un componente software, in presenza di nuovi requisiti, come faccio ad estenderlo?
1. riscrivendo il componente
2. specializzazione / ereditarietà
3. composizione (iron-man)



Abbiamo quindi due componenti:
- componente che controlla il sonar
- i vari adapter

A questi se ne aggiunge un terzo:
- qualcuno che implementa la logica applicativa!
- **un controller!**

Linguaggi risaltano fuori:
- **ogni componente software parla DUE LINGUAGGI**
    - quello di input
    - quello di output
    - ad esempio l'adapter ha come linguaggio di input quello del sonar e come linguaggio di output quello del controller (eventi QAK)


**testing**:
- non mi interessa per nulla come vengono recuperate le misurazioni
- io posso comunque fare dei **unit test** per la mia logica
- **NB**: 


keypoint: prima di buttarmi a fare l'implementazione prima bisogna fare l'analisi

...


**Altra tematica**: emissione di informazione ad uso terzi 

**Domanda**: è meglio usare ELK per il logging oppure è meglio fare in lcale?
- nei sistemi distribuiti bisogna avere una concentrazione dei log in un unico posto, altrimenti dovrei andare a vedere su ogni macchina che log sono stati emessi e non si capirebbe niente.
    - mi da una visione globale del sistema

```mi gestisce problemi di timing ed ordinamento degli eventi?```

Logging fatto in prolog, molto più semplice...
- dentro ogni attore QAK è disponibile intrinsicamente un interprete prolog a cui possiamo già fare query 

la base di conoscenza popolata con le assert è in memoria, non su file


```perchè prolog/smalltalc non sono omega popolari```


**IMPORTANTE**: FARE L'ESERCIZIO DI LOGGING PER CAPIRE COME FAR FLUIRE LE INFORMAZIONI DEL SONAR AD UN TERZO. PARTE DEL PROGETTO FINALE!









### virtual robot
l'ambiente virtuale (essedo un microservizio) come qualsiasi commponente è caratterizzato da un linguaggio di input e un linguaggio di output

abbiamo quattro mosse: avanti, indietro, ruota di 90° a sinistra/destra
- Natali asserisce che questo linguaggio è computazionalmente completo per i DDR

possiamo inviare comandi sia in maniera sincrona (http) che asincrona (WS)
- l'interazione asincrona mi serve anche per ricevere informazioni riguardi terminazioni imporvvisi di comandi, eventi del sonar, ecc...

**NB**: con una comunicazione asincorna posso mandare un altro comanda prima che il precedente termini. Tuttavia, wenv non consente la sovrapposizione di comandi e quindi fa halt di tutto.
- anche nel caso di interazione sincrona il secondo comando sovrapposto viene scartato


**nota**: dentro a node/webGLScene/sceneConfig.js si può configurare la scena; 
- se lo lanci con docker bisogna modificare il file dentro al container o iniettare una configurazione con dei volumi or something






### Analisi e progettazione evalarea
scrivere un programma che calcola l'area della stanza utilizzando il basic robot

step: nuova mossa che mi fa andare avanti per un tempo tale che il robot si sposta di 2R
- invece che usare i cm come unità di misura, utilizzo la lunghezza del robot

io vorrei rendere i miei ragionamenti di livello applicativo **technology indipendent!**
- nel nostro caso se io cambiassi robot, non potrei più interagirci con CRIL in quanto questo è il linguaggio di interazione con il robot virtuale
- io non voglio cambiare tutte le volte il codice ogni volta che cambio il robot
- allora incapsulo i dettagli di comunicazione in un adapter VLLMoves24 (VLowLevelMoves)


Il robot non sa nulla di chi sono i suoi utilizzatori in quanto i suoi messaggi di stato sono **eventi**


step può essere sia asincrono che sincrono
- sincrono mi restituisce true se è riuscito a completare il passo
- asincrono mi da l'informazione sulle collisioni tramite messaggi di stato

**COMPITO**: calcolare l'area 







## La parte più importante!
```data una serie di requisiti come affrontiamo il processo di produzione del software?```

esempio:
- requisito: scrivere un programma che fa fare delle mosse elementari al robot (farlo girare e sbattere sul muro a sinistra)

Poniamoci delle domande:
- innanzitutto esiste già un programma/componente che è in grado di pilotare il robot?
- se si qual'è l'interfaccia/contratto/documentazione/**LINGUAGGIO** che questo componente è in grado di comprendere? 
    - il mio obiettivo è **capire come usare questo pezzo di software**
 
Mettiamo che mi danno un pezzo di software in Java
- a questo punto andrei a cercare un interfaccia con dei metodi del tipo: forward(), backward(), ...

Se il pezzo di software è in C
- io vado a cercare direttamente delle funzioni di movimento

Se il pezzo di software è in Kotlin/QAK 
- io vado a cercare l'attore che è sensibile ai messaggi di movimento

**OSS**: Per un informatico, **il robot NON esiste**; esiste un attore che implementa un robot, oppure una collezione di funzioni che implementa il robot, oppure ancora una classe.



**conclusione**: dipendentemente da come è stato rappresentato il robot (che non esiste) io vado a cercare cose diverse (funzioni, metodi di un interfaccia o un attore e dei messaggi)




**Domanda fondamentale**: quando in un requisito mi evocano **un'entità** di qualche tipo (tipo robot), che domanda mi devo fare?
- il robot è un insieme di funzioni, una classe o un attore?

Abbiamo vari concetti software con cui possiamo rappresentare entità nominate nei requisiti:
- variabili
- funzioni
- classi
- attori

NB: Una prima fase nell'analisi dei requisiti è cercare di capire cosa intende il committente con una determinata parola

Se mi danno un componenente che mi implementa il robot con delle funzioni e basta, è facile che io abbia un abstraction gap più grande a fronte di nuovi requisiti rispetto ad un componente fatto ad attori
- esempio: il robot quando passa davanti al sonar deve fermarsi per 2 secondi e poi riprendere
    - ennesimo esempoi di sistema proattivo e reattivo

Facciamo l'analisi di questo problema
- ...
- sistemi reattivi implicano **operazioni interrompibili**

...

Perchè io comunico con wenv mediante stringhe json? perchè non con dei metodi?
- **perchè io sto comunicando con un attore**


### Usare il pojo VrLLMoves come supporto per il problema è adeguato al problema riguardante il governo del robot
difficile, c'è un altro abstraction gap, ad esempio:
- non posso muovermi facilmente in un punto della mappa


```Osservazione interessante del prof: nei sistemi proattivi e reattivi strutturati come un automa a stati finiti è facile individuare in quale stato bisognerà gestire gli eventi che causano una reazione. Questo è uno dei motivi per cui i QAK semplificano la prototipazione di sistemi di questo tipo. Sviluppare un sistema proattivo e reattivo in un linguaggio che non supporta gli attori bisognerebbe generare dei thread il cui compito è fare da listener di eventi, magari aggiornare una variabile condivisa, corse critiche, ...; insomma, un casino incomprensibile```

quando c'è un abstraction gap bisogna estendere quello che si ha già a disposizione
- scrivendo nuovo software
- andanda a cercare librerie già fatta, linguaggi DSL, ecc.


### Come faccio a spostare il robot in dei punti che voglio io?
Innanzitutto ho bisogno di conoscere dove sono i punti in cui voglio andare. Come faccio?
- ho bisogno di avere un sistema di coordinate con cui assegnare delle posizioni ai miei obiettivi
- Il robot poi deve conoscere la propria posizione

Come faccio a sapere quali sono le posizioni dei miei obiettivi?
- possono essere predefinite
- posso esplorare il mio ambiente e calcolare una mappa

Come faccio a calcolare la mappa del mio ambiente?
- lasciamo stare questo per adesso...

Una volta che ho posizione corrente del robot e posizione del mio obiettivo nel mio sistema di coordinate posso usare i miei movimenti elementari per far raggiungere al robot il mio obiettivo. Come?
- un primo algoritmo che mi viene in mente è di raggiungere la coordinata x e poi la coordinata y un passo alla volta

Cosa succede se ci sono degli ostacoli nel mezzo?
- se ho una mappa dell'ambiente, prima di partire posso calcolare un percorso per raggiungere il mio obiettivo
    - percorso inteso come sequenza di mosse elementari
- se non ho una mappa, e incontro un ostacolo posso provare a:
    - girarmi verso la direzione del mio obiettivo
    - fare uno step
    - rigirarmi per controllare se c'è ancora l'ostacolo
    - quando, vedo che l'ostacolo è sparito posso riprendere con il mio algoritmo naive
    - se l'ostacolo non sparisce posso provare ad utilizzare un algoritmo che fa backtracking sulle scelte fatte per aggirare l'ostacolo


### Keypoints dell'esercitazione su boundary walk con halt stimolato dal sonar
- in java avrei scritto un while
    - ma senza precauzioni non sarei stato reattivo
- oppure avrei potuto scrivere uno step molto lungo
    - di nuovo non sono reattivo
- sistemi reattivi/proattivi implicano la creazione di listener thread che modificano variabili condivise in shared memory ecc...
- sfruttando una struttura del sistema ad ASF
    - diventa facile ... (vedi sopra)


**conclusione interessante**: Il linguaggio non solo è un abilitatore (mi permette di implementare ciò a cui penso) ma **mi vincola** nelle idee che mi vengono in mente; in Java non penserei mai al sistema come un ASF, nonostante questa idea magari è quella che si presta meglio al sistema.  
- Alan Kay diceva che il linguaggio che si usa sono sia delle ali che delle catene
    - ti introduce a delle idee che magari non avresti mai pensato
    - ti obbliga ad usare esattamente solamente le idee e i concetti che ti introduce e che supporta




### step asincrono
è meglio che la risposta allo step sia una *reply* oppure un *evento*?
- se penso al mio step come a una richiesta 

la request non la vedo a livello di messaggio qak, viene implementata piuttosto come vr.stepAsynch()
- l'infrastruttura qak non si lamenta, prende e mette in coda

### analogia mente-corpo
il corpo riceve degli stimoli sensoriali (json da wenv), gli traduce in segnali (linguaggio diverso da quello del corpo | messaggi QAK) che invia attraverso i nervi (websocket o qualsiasi Interaction) alla mente.

la mente invia istruzioni (step) al corpo, il corpo esegue (invia json a wenv)

POJO è il corpo, l'attore (che passo con _myself_) è la mente






### compito
impostare un microservizio che mi permette di usare il robot con il linguaggio aril e con gli step asincroni
- nb: non un pojo ma un microservizio

analizziamo il problema
- la responsabilità del microservizio è comandare il robot una volta stimolato da richieste. 
- Come devono essere fatte queste richieste?
- abbiamo bisogno di un linguaggio che mi esprima sintassi e semantica delle richieste
- dobbiamo definire la struttura dei messaggi 
    - in generale con i servizio abbiamo sempre due tipologie logiche di messaggi
        - comandi (dispatch)
        - richieste 
    - a noi interessa fare delle richieste stepAsynch per un certo numero di ms
    - e quindi definiamo un tipo di messaggio -> request stepAsynch : stepAsynch(Time)
        - NB: Time con la T maiuscola in quanto variabile (senza non puoi fare interpolazione con il $)
    - ci interessa poi anche che il servizio ci risponda dicendoci se lo step è andato a buon fine o meno
        - response stepDone : stepDone(X)
        - responde stepFail : stepFail(T) "T = tempo del step che mi mancava per completare lo step"
    - il robot poi è anche sensibile agli altri comandi aril 
        - siccome sono tanti e di tipo diverso possiamo mandare un dispatch
            - dispatch move : move(X) "X = mossa aril"
    - i vari messaggi move che mando però possono andare bene o male, come faccio a far emettere queste informazioni al microservizio per i suoi clienti
        - creaiamo un evento apposta
    - abbiamo poi il sonar; il servizio dovrà rendere disponibili le informazioni del sonar
        - altro evento ancora   





### qual'è la differenza tra un pojo e servizio?
con gli oggetti ci interagisco tramite chiamate di metodi, con i servizi ci interagisco tramite messaggi 

per rispondere a questo tipo di domande devo pensare a tre cose 
- struttura
- interazione
- funzionamento




Ricorda che ogni attore è anche una risorsa CoAP nomeContext/nomeActor osservabile dagli alieni
- alternativa rispetto a mqtt



### keypoint
```è meglio usare il pojo o il microservizio?```

Scordiamoci momentaneamente dei qak. Per creare un servizio classico normalmente ci si appoggia a dei framework come Spring boot che realizzano la dimensione dell'interazione. L'armatura di ironman

```perchè un microservizio fatto con qak è preferibile ad un microservizio fatto con spring```

posso prendere la specifica del servizio così com'è ed usarla sia in locale che in remoto senza modificare il codice del servizio


questo si unisce agli altri vantaggi di concorrenza semplificata e modellazione dello stato come DFA

### keypoint
```come si progetta un servizio```

dal di dentro o da fuori?

specifico prima i messaggi a cui il servizio dovrà rispondere (mi adeguo alle esigenze del cliente esterno) oppure prima definisco il comportamente del servizio e i clienti si sviluppano (mi adeguo alle esigenze dello sviluppatore)?

non c'è una risposta, gli adapter nascono per soddisfare le esigenze di entrambi









### Mapping della stanza
rappresentare a livello software la stanza è fondamentale
- se nel mio programma non c'è una rappresentazione della stanza (oggetto, attore, struttura dati) la stanza allora non esiste


operazione centrale di robot mapper è doMove()
- il robot mapper non muove il robot, piuttosto muove la sua idea del robot nella sua testa (stato)

mapbuildconmapper.qak. è dove il robot si muove realmente 


una sola assunzione: il bordo superiore è libero


compito:
- guardare uniboplanner25
- scrivere un planexecutor. Un servizio che date le mosse sottoforma di richiesta mi risponde 
    - o con done
    - o con fail e dove è arrivato nella sequenza
- integrarlo con il mapbuilder  