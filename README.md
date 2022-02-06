## Datasource settings in application.properties

#### Optional: Set to your own local settings.

Current settings:
* postgresql database on //localhost:5432
* database: postgres
* user/owner: springboot
* password: springboot

#### Seeded users

There are currently 3 users.  
Provided 1 optional user to test adding users.
* diana.slater - wBb7n4C! (receptionist role)
* irene.wright - oF6F_@my (administrative worker role)
* connor.butler - 9PZPP@Gt (admin role)
* penelope.sanderson - yTVFB!T3 (optional user)

### Endpoints

#### pets
* GET /pets
* GET /pets/{id}
* POST /pets
* PATCH /pets/{id}
* DELETE /pets/{id}

#### owners
* GET /owners
* GET /owners/{id}
* GET /owners/{id}/pets
* POST /owners
* PATCH /owners/{id}
* DELETE /owners/{id}

#### users
* GET /users
* GET /users/{username}
* GET /users/{username}/authorities
* POST /users
* POST /users/{username}/authorities
* PUT /users/{username}
* PATCH /users/{username}/password
* DELETE /users/{username}
* DELETE /users/{username}/authorities/{authority}

#### A Postman export has been included in the documentation directory.
