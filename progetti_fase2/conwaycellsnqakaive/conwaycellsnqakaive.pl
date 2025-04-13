%====================================================================================
% conwaycellsnqakaive description   
%====================================================================================
mqttBroker("localhost", "1883", "lifeevents").
event( startthegame, startthegame(X) ).
event( stopthecell, stopthecell(X) ).
event( synch, synch(X) ).
event( clearCell, clearCell(X) ).
event( curstate, curstate(NB,STATE) ).
event( kernel_rawmsg, kernel_rawmsg(ARG) ).
dispatch( changeCellState, changeCellState(X,Y) ).
dispatch( allnbreceived, allnbreceived(N) ).
%====================================================================================
context(ctxcells, "localhost",  "TCP", "8360").
 qactor( caller, ctxcells, "it.unibo.caller.Caller").
 static(caller).
  qactor( cell_0_0, ctxcells, "it.unibo.cell_0_0.Cell_0_0").
 static(cell_0_0).
  qactor( cell_0_1, ctxcells, "it.unibo.cell_0_1.Cell_0_1").
 static(cell_0_1).
  qactor( cell_1_0, ctxcells, "it.unibo.cell_1_0.Cell_1_0").
 static(cell_1_0).
  qactor( cell_1_1, ctxcells, "it.unibo.cell_1_1.Cell_1_1").
 static(cell_1_1).
