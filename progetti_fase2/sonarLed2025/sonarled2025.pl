%====================================================================================
% sonarled2025 description   
%====================================================================================
event( misurazione, misurazione(X) ).
event( ostacolo, ostacolo(X) ).
event( ostacolo_sparito, ostacolo_sparito(X) ).
%====================================================================================
context(ctx_observers, "localhost",  "TCP", "8360").
context(ctx_sonar, "192.168.1.158",  "TCP", "8075").
 qactor( sonar_publisher, ctx_sonar, "external").
  qactor( observer_builder, ctx_observers, "it.unibo.observer_builder.Observer_builder").
 static(observer_builder).
  qactor( sonar_observer, ctx_observers, "it.unibo.sonar_observer.Sonar_observer").
dynamic(sonar_observer). %%Oct2023 
