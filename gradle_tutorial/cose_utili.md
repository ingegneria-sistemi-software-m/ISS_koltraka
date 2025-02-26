1. **Che differenza c'è tra _gradle_ e _gradlew_?**
    - La differenza principale tra gradle e gradlew riguarda come viene eseguito Gradle e la gestione della versione di Gradle.
    - Gradle
        - Il comando gradle esegue Gradle utilizzando la versione installata globalmente nel sistema.
        - Problemi di compatibilità: Se più progetti usano versioni diverse di Gradle, devi gestire manualmente la versione corretta.
    - Gradlew:
        - Gradle utilizzerà la versione specificata nel progetto. Se quella versione non è installata, la scaricherà automaticamente.
        - Il primo utilizzo può essere più lento, perché deve scaricare Gradle se non è già presente nella cartella .gradle/wrapper.

2. **A che cosa serve il plugin eclipse?**
    - Il plugin eclipse fornisce una serie di funzionalità per generare la configurazione di Eclipse da un progetto Gradle, **rendendo più facile l'importazione e la gestione di un progetto Gradle in Eclipse**. Alcune delle principali funzionalità includono:

        - Generazione di .project e .classpath: Il plugin genera automaticamente il file .project di Eclipse, che è necessario per riconoscere il progetto come un progetto Eclipse. Crea anche il file .classpath, che definisce il classpath per il progetto, inclusi i riferimenti alle dipendenze e le directory sorgente. 

        - Supporto per progetti Java: Per i progetti Java, il plugin configura automaticamente le cartelle di sorgente (src/main/java) e le cartelle di output (build/classes). **Gestisce anche le dipendenze dichiarate nel file build.gradle, assicurandosi che siano incluse nel classpath** di Eclipse.
