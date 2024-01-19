Bands - API 

* Updates on 2024 to use the new bands API URL https://dws-recruiting-bands-api.dwsbrazil.io/api/bands and adjusted the DTOs

Endpoints:
http://localhost:8080/bands/service/v1/bands
  * header: type.sort | ALPHABETICAL_ORDER_ASC | ALPHABETICAL_ORDER_DESC | POPULARITY_ODER_ASC | POPULARITY_ODER_DESC
  * Query parameters: ?name       

http://localhost:8080/bands/service/v1/bands/{id}

