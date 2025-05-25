%====================================================================================
% vrqak25 description   
%====================================================================================
mqttBroker("192.168.1.189", "1883", "vrevents").
dispatch( halt, halt(X) ).
dispatch( move, move(M) ).
request( cmd, cmd(MOVE,T) ). %MOVE = w|s|a|d|p   mosse del virtual robot
reply( cmddone, cmddone(R) ).  %%for cmd
reply( cmdfailed, cmdfailed(T,CAUSE) ).  %%for cmd
dispatch( vrinfo, vrinfo(SOURCE,INFO) ). %inviato dal supporto all'owner (per questo è un dispatch)
event( sonarval, distance(DISTANCE) ). %emesso in out
event( sonardata, sonar(DISTANCE) ). %emesso dal SONAR
request( step, step(TIME) ).
reply( stepdone, stepdone(V) ).  %%for step
reply( stepfailed, stepfailed(PERCENT,CAUSE) ).  %%for step
%====================================================================================
context(ctxvrqak, "localhost",  "TCP", "8125").
 qactor( vrqak, ctxvrqak, "it.unibo.vrqak.Vrqak").
 static(vrqak).
