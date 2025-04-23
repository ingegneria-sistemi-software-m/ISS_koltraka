%====================================================================================
% evalarea description   
%====================================================================================
dispatch( resume_left_wall, resume_left_wall(X) ).
dispatch( resume_bottom_wall, resume_bottom_wall(X) ).
event( collisione, collisione(X) ).
event( misurazione, misurazione(X) ).
event( ostacolo, ostacolo(X) ).
event( ostacolo_sparito, ostacolo_sparito(X) ).
%====================================================================================
context(ctx_evalarea, "localhost",  "TCP", "8075").
context(ctx_sonar, "192.168.1.158",  "TCP", "8075").
 qactor( sonar_publisher, ctx_sonar, "external").
  qactor( wenv_caller, ctx_evalarea, "it.unibo.wenv_caller.Wenv_caller").
 static(wenv_caller).
