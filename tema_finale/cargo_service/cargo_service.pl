%====================================================================================
% cargo_service description   
%====================================================================================
mqttBroker("192.168.1.189", "1883", "sonar_emitter_topic").
event( container_present, container_present(X) ). %emesso dal sonar_emitter tramite MQTT quando il container è stato rilevato dal sonar per un certo numero di secondi
event( container_absent, container_absent(X) ). %emesso dal sonar emitter tramite MQTT quando il container NON è stato rilevato dal sonar per un certo numero di secondi
%====================================================================================
context(ctx_sonar_emitter, "localhost",  "TCP", "8002").
 qactor( sonar_emitter, ctx_sonar_emitter, "it.unibo.sonar_emitter.Sonar_emitter").
 static(sonar_emitter).
