![Java 11](https://badgen.net/badge/Built%20with/Java%2011?icon=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white)

# Item store

Item Store is a RESTful web application to manage and retrieve data items. This project focused on building a robust API with features including persistent data storage, concurrent data access, data expiration, and efficient search capabilities. Key functionalities included creating, retrieving, updating, and searching data items based on tags and values, with support for pagination and ordering.

## How to run
Open up a terminal and insert these commands at the project root.
```
mvn clean install
cd /setup/docker
docker-compose up
```

The application should now be running locally on port `8080` and
can be accessed at `http://localhost:8080/itemstore/items`. \
If desired test data can be created during application startup.

Items expire after 5 minutes, expiration time is configurable.

Expiration of items is checked every 30 seconds, all items not
updated in the last 5 minutes, will get deleted.

On concurrent access during update of an item, `409` or `Conflict`
will be the response.

Further API specs are documented in the _openapi.yml_ and can be
checked out [here](https://app.swaggerhub.com/apis-docs/RAFFAE/item-store/1.0).

## How to configure
> sys_items_expiration_time-ms

Adjust the expiration time of items by setting this env-variable
in the _docker-compose.yml_ file in milliseconds.

> sys_items_test-data

Disable the creation of test data by setting this env-variable
in the _docker-compose.yml_ file to false.
