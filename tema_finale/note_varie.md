la gui deve mostrare aggiornamenti push dello stato del product-service

gli eventi del sonar è bene che si manifestino con delle updateResource


### Basic robot
- basic robot fa update resource quando passa davanti ad un sonar
- ignore_data mi permette di gestire lo stop del sonar senza fare un passo in avanti (in modo da evitare un disallinemento tra mappa mentale e mappa vera)
- basic_robot ha dentro un attore robot_pos che ogni tanto fa updateResource per fare vedere lo stato corrente della mappa

basic robot, bw_bsc_rbt, bw_bmoves sono da guardare