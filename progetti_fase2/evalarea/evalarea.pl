%====================================================================================
% evalarea description   
%====================================================================================
dispatch( stepdone, stepdone(X) ).
dispatch( stepfailed, stepfailed(X) ).
event( sonardata, sonar(DISTANCE) ).
event( vrinfo, vrinfo(A,B) ).
dispatch( vrinfo, vrinfo(A,B) ).
%====================================================================================
context(ctx_evalarea, "localhost",  "TCP", "8075").
 qactor( wenv_caller, ctx_evalarea, "it.unibo.wenv_caller.Wenv_caller").
 static(wenv_caller).
