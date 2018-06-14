Para escalar un servicio necesitaremos de un docker que contenga rancher.

Despues correremos 

sudo docker run -d --restart=unless-stopped -p 8080:8080 rancher/server

Despues accesamos a la pagina de la administracion del rancher

http://IP_Maquina:PTO_Maquina/admin/access/github

Ahi añadiremosn loos equipos que ocuparemos como host

Para ello accesaremos a Infrastructure> Hosts

Le daremosn clic en el boton de añadir 

nos brindara una cadena que se le ingresara a los equipos que ocuparemos como host, despues de correrlo

Si todo sale bien se pordran apreciar que estan conectatas en la pagina de administracion accediendo de nuevo a  Infrastructure> Hosts

Configuraremos el servicio ingresando a Default>Define Service 

ahi colocaremos nombre, descripcion y desde que imagen lo va a desplegar en este caso seria la que tenemos en dgpecurso06/examen2

Despues en esa misma pagina solo que hasta abajo selecionaremos la pestaña de Command donde colocaremos consola none

y seleccionaremos la pestaña de Security/Host en Log Driver JSON-FILE , le damos clic en crear

Despues nos vamos a la parte superior derecha y despleigamosn las opciones para ADD SERVER donde selecionaremos ADD LOAD BALANCER

LO configuraremos el v¡balanceador con nombre , el puerto donde lo desplegara y el puerdo donde corre nuestro ranche, la igual que el servicio que balanciara y le damos crear.

Despues nos vamos para el servicio de nuevo en Default y en el icono de advertencia(letra i envuelta en un circulo) nos desplegara un donde podemos desplegar la escalabiliadad del servicio dando clic en el icono de mas  que se encuentra en el apartado de Containers , en este caso seria hasta llegar al numero tres


