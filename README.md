# Backend-Assignment---UndoSchool
# Global Class Offering Booking System

## Tech Stack
* **Framework:** Java 17, Spring Boot 3.x* **Database:** PostgreSQL 15
* **Libraries:** Spring Data JPA, Lombok

## Setup & Running Instructions
1. Clone the repository.
2. Update database credentials in `src/main/resources/application.properties`.
3. Execute `./mvnw spring-boot:run`.

## Timezone Architecture Detail
All times are safely stored in UTC natively via PostgreSQL `timestamptz`. Timestamps payload delivery conforms to ISO-8601 formatting context. Client displays render local representations smoothly.

## Concurrency and Conflict Mitigation Approach
* **Isolation Integrity:** Utilizes `PESSIMISTIC_WRITE` locks via JPA during transactional reservation handling phases to completely drop risk surfaces concerning duplicate race-condition calls.
* **Algorithmic Overlap Inspection:** Performs cross-intersection math computations cleanly normalized at query runtime.
