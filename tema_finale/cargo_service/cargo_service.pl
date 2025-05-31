%====================================================================================
% cargo_service description   
%====================================================================================
request( load, load(PID,PESO) ). %la richiesta di carico di un container che arriva a cargo_service: contiene il PID del prodotto e il peso del container
reply( load_accepted, load_accepted(SLOT) ). %%for load | la richiesta di carico può essere accettata, con restituzione dello slot assegnato al container
reply( load_refused, load_refused(CAUSA) ). %%for load | oppure può essere rifiutata per due motivi: cur_load > MAX_LOAD, oppure perchè tutti e 4 gli sloto sono occupati
request( move, move(x_robot,y_robot,x_goal,y_goal) ). %la richiesta di movimento del robot prodotta da cargo_service e ricevuta dal basic_robot
reply( move_ok, move_ok(NEW_MAP_STATE) ). %%for move | potrei aggiornare lo stato direttamente dentro a cargo_service ma se lo ritorno da basic_robot è meglio
reply( move_ko, move_ko(NEW_MAP_STATE,CAUSA) ). %%for move | in generale, è possibile che il robot non riesca a raggiungere il punto richiesto (no plan)
event( sonar_measurement, sonar_measurement(MISURA) ). %misura della distanza del robot dal container davanti alla IO port e durante il trasporto
%====================================================================================
context(ctx_cargo_service, "localhost",  "TCP", "8000").
context(ctx_basic_robot, "localhost",  "TCP", "8001").
 qactor( basic_robot, ctx_basic_robot, "external").
  qactor( cargo_service, ctx_cargo_service, "it.unibo.cargo_service.Cargo_service").
 static(cargo_service).
