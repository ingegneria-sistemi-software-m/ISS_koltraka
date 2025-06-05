%====================================================================================
% cargo_service description   
%====================================================================================
%====================================================================================
context(ctx_cargo_service, "localhost",  "TCP", "8000").
 qactor( cargo_service, ctx_cargo_service, "it.unibo.cargo_service.Cargo_service").
 static(cargo_service).
