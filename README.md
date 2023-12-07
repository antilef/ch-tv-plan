# ch-tv-plan
Microservice to manage a fictitious  "automatic change plan" operation from a fictitious TV enterprise.


## Basic Concept
Este microservicio debe mantener la logica de negocio para mantener el cambio de un plan de TV de una
empresa ficticia, presentado como dos casos de uso principales

- UC1: listar los planes a los que puede cambiarse el cliente
- UC2: realizar un cambio antomatico de plan , tanto un Upgrade como un Downgradem, sin necesidad de 
un ejecutivo

## Detalles de diseño
La idea principal del projecto es el desarrollo de un  microservicio que sea capaz de conectarse con 
otros pequeños servicio y acceso de base de datos con el fin de orquestar una logica de negocio
simple y reducida.

## Notes
docker run --name postgres -v /data:/var/lib/postgresql/data -p 5432:5432 -e POSTGRES_DB=tv-db -e POSTGRES_PASSWORD=antilef -e POSTGRES_USER=antilef -d postgres:16