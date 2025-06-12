Che significato diamo alla seguente frase: "the company plans to employ a Differential Drive Robot (from now, called cargorobot) for the loading of goods (named products) in the ship’s hold"?

**ci concentriamo sui verbi e sui nomi**

Nomi:
- DDR e cargorobot
    - io so già cos'è un DDR, ma se non lo sapessi dovrei chiederlo al committente (l'azienda) 
    - abbiamo LLMoves, HLMoves, BasicRobot, ecc...
    - abbiamo già del software pronto -> possiamo scegliere quello più opportuno 
        - ovvero quello che si **adatta meglio ai requisiti**
        - in altri termini ancora, **quello con il minor abstraction gap**

Facendo una scelta, stiamo **formalizzando il termine DDR con un pezzo di software**! Ad esempio, HLMoves
- attenzione: formalizzare il DDR con il basic-robot, ovvero un qactor, non è immediatamente comprensibile da chi non ha studiato qak
- dobbiamo quindi condividere anche un link che spiega cosa sono quest'ultimi 

**Formalizzare significa tradurre da linguaggio naturale a qualcosa che la macchina capisce**

**NB**: Dovendo caricare dei prodotti nel deposito HLMoves ha un abstraction gap troppo alto, basic robot si presta meglio

- prodotti
- deposito

Verbi:
- loading
    - **NB**: il basic robot si sa muovere, ma non sa caricare/scaricare i prodotti
        - Non sa fare tutto quello server

**NMB**: ma il cargoRobot sa fare anche questo! **Il cargo-robot è quindi diverso dal basic-robot**


formalizzare i requisiti mi permette di definire che cosa posso chiedere e che cosa mi aspetto di ricevere come risposta
- per questo motivo posso già pensare ad un test plan una volta formalizzati i requisiti
- i test sono finti in quanto l'implementazione ancora non esiste, però possiamo mostrare al committente le risposte a determinati input e chiedergli se è quello il comportamento che intendeva


### sprint1
gli sprint vanno in ordine di rilevanza

nello sprint1 ci si preoccupa del core buisness (il processi di carico). 
- non mi preoccupo del sonar
- non mi preoccupo della gui
- ...
- queste cose le simulo