%====================================================================================
% sonarled2025 description   
%====================================================================================
mqttBroker("192.168.1.189", "1883", "sensor/sonar").
dispatch( misurazione_disp, misurazione_disp(X) ).
event( misurazione, misurazione(X) ).
event( kernel_rawmsg, kernel_rawmsg(ARG) ).
%====================================================================================
context(ctx_sonar, "localhost",  "TCP", "8075").
 qactor( sonar_publisher, ctx_sonar, "it.unibo.sonar_publisher.Sonar_publisher").
 static(sonar_publisher).
  qactor( observer_builder, ctx_sonar, "it.unibo.observer_builder.Observer_builder").
 static(observer_builder).
  qactor( sonar_observer, ctx_sonar, "it.unibo.sonar_observer.Sonar_observer").
dynamic(sonar_observer). %%Oct2023 
