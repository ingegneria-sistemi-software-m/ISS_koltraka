# ISS_koltraka
Repository del corso Ingegneria dei Sistemi Software a.a. 2024/2025 - DISI - University of Bologna dello studente Koltraka Kevin
* [Materiale didattico](iss25Material/docs/_build/html)

<h2 id="Fase1">Fase 1</h2>

### Dagli oggetti ai microservizi (in Java)
* [conway25Java](conway_stuff/conway25Java): Classi Java che realizzano la parte core del game Life di Conway
* [conwaygui](conway_stuff/conwaygui): Sistema SpringBoot che ingloba [conway25Java](conway_stuff/conway25Java) offrendo una GUI come dispositivo di I/O
* [conway25JavaMqtt](conway_stuff/conway25JavaMqtt): GameLife standalone che interagisce via MQTT con il mondo esterno
* [conwayguialone](conway_stuff/conwayguialone): Servizio SpringBoot che offre la GUI per interagire via MQTT con [conway25JavaMqtt](conway_stuff/conway25JavaMqtt)