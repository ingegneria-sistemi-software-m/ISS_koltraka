%====================================================================================
% cargo_service description   
%====================================================================================
request( load, load(PID,PESO) ). %la richiesta di carico di un container contiene il PID del prodotto e il peso del container
reply( load_accepted, load_accepted(SLOT) ). %%for load | la richiesta di carico può essere accettata, con restituzione dello slot assegnato al container
reply( load_refused, load_refused(CAUSA) ). %%for load | oppure può essere rifiutata per due motivi: cur_load > MAX_LOAD, oppure perchè tutti e 4 gli sloto sono occupati
%====================================================================================
context(ctx_cargo_service, "localhost",  "TCP", "8000").
context(ctxvrqak, "127.0.0.1",  "TCP", "8125").
 qactor( vrqak, ctxvrqak, "external").
  qactor( cargo_service, ctx_cargo_service, "it.unibo.cargo_service.Cargo_service").
 static(cargo_service).
