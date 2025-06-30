%====================================================================================
% cargo_service description   
%====================================================================================
mqttBroker("192.168.1.189", "1883", "sonar_emitter_topic").
request( load_product, load_product(PID) ). %la richiesta di carico di un container che arriva a cargo_service: contiene il PID del prodotto e il peso del container
reply( load_accepted, load_accepted(SLOT) ). %%for load_product | la richiesta di carico può essere accettata, con restituzione dello slot assegnato al container
reply( load_refused, load_refused(CAUSA) ). %%for load_product | oppure può essere rifiutata per due motivi: cur_load > MAX_LOAD, oppure perchè tutti e 4 gli sloto sono occupati
dispatch( refuse, refuse(X) ). %autodispatch che cargo_service si manda da solo per transitare nello stato giusto quando rifiuta una richiesta di carico
request( move, move(x_goal,y_goal) ). %la richiesta di movimento del robot prodotta da cargo_service e ricevuta dal robot_service che conosce la posizione del robot
reply( move_ok, move_ok(OK) ).  %%for move
reply( move_ko, move_ko(CAUSA) ). %%for move | in generale, è possibile che il robot non riesca a raggiungere il punto richiesto (no plan)
dispatch( halt, halt(X) ). %messaggio da inviare al robot_service per interrompere tutte le operazioni quando il container è caduto
event( container_present, container_present(X) ). %emesso dal sonar_emitter tramite MQTT quando il container è stato rilevato dal sonar per un certo numero di secondi
event( container_absent, container_absent(X) ). %emesso dal sonar emitter tramite MQTT quando il container NON è stato rilevato dal sonar per un certo numero di secondi
%====================================================================================
context(ctx_cargo_service, "localhost",  "TCP", "8000").
context(ctx_robot_service, "localhost",  "TCP", "8001").
 qactor( robot_service, ctx_robot_service, "external").
  qactor( cargo_service, ctx_cargo_service, "it.unibo.cargo_service.Cargo_service").
 static(cargo_service).
