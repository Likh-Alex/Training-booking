# Fitness booking

### The structure of the project

- Database layer
- DAO layer
- Service layer
- Controllers layer

### A simple UML diagram describing relationships between entities

<img src= "https://github.com/Likh-Alex/images/blob/main/fitness-booking-uml.png" alt = "fitness_booking_uml" width = "1200" />

This project was developed corresponding to the SOLID principles, implemented authentication and authorisation.
## Technologies used in the project
- Java
- Spring Core
- Spring Web
- Spring Security
- Hibernate
- Tomcat

## Database used
- MySQL

### In scope of the application the `Client` can have several `Roles`
Such as : `USER` and `ADMIN`

## Accessible endpoints.

### Non authenticated `User` :
- Registration

### Authenticated `User` :

- `Get` all `Workouts`
- `Get` all `Fitness rooms`
- `Get` all `Workout sessions` at specified `Date`
- `Get` all personal `Orders` history
- `Get` all personal `Shopping carts`
- `Post` an `Order`
- `Post` of `Shopping carts` created during the `Registration` of current `User`

### Admin

- `Get` all `Workouts`
- `Get` all `Fitness rooms`
- `Get` all `Shopping carts` by `User`
- `Get` `User` by `email`
- `Get` all `Workout sessions` at specified `Date`
- `Post` a `Fitness room`
- `Post` a `Workout session`
- `Post` a `Workout`
- `Put` a `Workout session`
- `Delete` a `Workout session`

# In order to run the project complete the following steps
- `Fork` this repository and create new project from VCS in your `IDE`
- Download and install [MySQL server and Workbench](https://dev.mysql.com/downloads/mysql/) . Configure the database and **note down your database credentials**. **Start** the **MySQL server** if it is not up.
- In `resources` directory edit the `db.properties` file with your **database credentials** in fields `db.username` and `db.password`
- Setup the `Tomcat`. Deploy as — `war_exploded`, In `deployment tab` - enter home address — `"/"`
- **Start Tomcat**. Once Tomcat is up - you are good to go.
- Default roles `User` and `Admin` together with one `Admin user` are injected at `Tomcat` startup.
- Once `Tomcat` is up - navigate to `/register` endpoint to register a new `User` 
