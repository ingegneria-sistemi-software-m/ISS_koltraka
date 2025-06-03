### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('cargo_serviceArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_cargo_service', graph_attr=nodeattr):
          cargo_service=Custom('cargo_service','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_robot_service', graph_attr=nodeattr):
          robot_service=Custom('robot_service(ext)','./qakicons/externalQActor.png')
     sys >> Edge( label='container_present', **evattr, decorate='true', fontcolor='darkgreen') >> cargo_service
     sys >> Edge( label='container_absent', **evattr, decorate='true', fontcolor='darkgreen') >> cargo_service
     cargo_service >> Edge(color='magenta', style='solid', decorate='true', label='<move<font color="darkgreen"> move_ok move_ko</font> &nbsp; >',  fontcolor='magenta') >> robot_service
     cargo_service >> Edge(color='blue', style='solid',  decorate='true', label='<halt &nbsp; >',  fontcolor='blue') >> robot_service
diag
