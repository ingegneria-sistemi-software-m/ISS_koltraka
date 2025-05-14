%====================================================================================
% evalarea description   
%====================================================================================
dispatch( resume_left_wall, resume_left_wall(X) ).
dispatch( resume_bottom_wall, resume_bottom_wall(X) ).
event( collisione, collisione(X) ).
event( sonardata, sonar(DISTANCE) ).
%====================================================================================
context(ctx_evalarea, "localhost",  "TCP", "8075").
 qactor( wenv_caller, ctx_evalarea, "it.unibo.wenv_caller.Wenv_caller").
 static(wenv_caller).
