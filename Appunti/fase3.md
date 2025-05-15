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