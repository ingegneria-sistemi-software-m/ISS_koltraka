%====================================================================================
% evalarea description   
%====================================================================================
request( step, step(TIME) ).
reply( stepdone, stepdone(V) ).  %%for step
reply( stepfailed, stepfailed(DURATION,CAUSE) ).  %%for step
event( sonardata, sonar(DISTANCE) ). %emesso dal SONAR
event( obstacle, obstacle(X) ). %emesso dal supporto
dispatch( continua, continua(X) ).
dispatch( finito, finito(X) ).
%====================================================================================
context(ctx_evalarea, "localhost",  "TCP", "8075").
 qactor( wenv_caller, ctx_evalarea, "it.unibo.wenv_caller.Wenv_caller").
 static(wenv_caller).
