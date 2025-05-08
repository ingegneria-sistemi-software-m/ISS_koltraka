%====================================================================================
% sonar_simulator description   
%====================================================================================
event( misurazione, misurazione(X) ).
%====================================================================================
context(ctx_simulator, "localhost",  "TCP", "8360").
 qactor( emitter, ctx_simulator, "it.unibo.emitter.Emitter").
 static(emitter).
  qactor( perceiver, ctx_simulator, "it.unibo.perceiver.Perceiver").
 static(perceiver).
