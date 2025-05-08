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


è meglio usare ELK per il logging oppure no?



keypoint: prima di buttarmi a fare l'implementazione prima bisogna fare l'analisi


logging fatto in prolog