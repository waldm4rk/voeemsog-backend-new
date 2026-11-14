# VOEEMSOG — original Java backend

A Java/Spring Boot REST API developed for the VOEEMSOG content website. This repository preserves the **original backend implementation**. The application was later migrated to Supabase for hosting-cost reasons; this Java service is **not claimed to be the current production backend**.

## Tech stack

- Java 21, Spring Boot 3.4.4, Spring Web, Spring Security
- PostgreSQL, Spring Data JPA / Hibernate
- Maven Wrapper, Lombok, MapStruct
- A custom admin authentication filter using HTTP-only cookies

## Implemented features

- **Rooms:** content browsing, language-specific translations, text/code filters, danger/size filters, pagination, and admin create/update/delete endpoints.
- **Posts and stories:** content retrieval, categorization, ordering, and admin write endpoints.
- **Music:** album and track browsing and administration.
- **Donations:** Ko-fi webhook endpoint, donation records, and donation-goal tracking.
- **Admin access:** API-key login, temporary cookie tokens, Spring Security filter chain and configured CORS origins.

The project follows controller → service → repository layers. Most persistence is implemented with Spring Data JPA repositories. See `src/main/java/com/voeemsog/voeemsog/`.

## Main API routes (examples)

| Method | Route | Purpose |
| --- | --- | --- |
| GET | `/rooms?lang=ru-RU` | List translated rooms |
| GET | `/rooms/page/1?lang=ru-RU` | Page through rooms (10 per page) |
| GET | `/rooms/code/{code}?lang=ru-RU` | Filter rooms by code |
| GET | `/rooms/name/{name}?lang=ru-RU` | Search translated names |
| GET | `/stories/categories?lang=ru-RU` | Story categories |
| GET | `/stories/{categoryId}?lang=ru-RU` | Stories by category |
| GET | `/music/albums` | Albums |
| GET | `/music/tracks/{albumId}` | Tracks by album |
| POST | `/admin/login` | Admin authentication |
| POST | `/donate/kofi` | Ko-fi callback |

For additional endpoints and exact request shapes, refer to the controller classes. Authentication is required for most `/admin/**` routes.

## Local development (legacy setup)

Requirements: **JDK 21**, PostgreSQL, and Maven (or the included Maven Wrapper).

1. Create a PostgreSQL database, e.g. `voeemsog`, and a database user with access to it.
2. **Provision a compatible schema**. The file `src/main/resources/database/legacy-partial-schema.sql` is an **incomplete historical schema**, not a complete migration or guaranteed bootstrap script. The original database dump was removed from the portfolio copy because it included production-style content. A reproducible full database setup remains future work.
3. Set the variables documented in `.env.example` in your shell or IDE. The `.env` file is **not loaded automatically** by this Spring application.
4. On Linux/macOS: `./mvnw spring-boot:run`; on Windows: `mvnw.cmd spring-boot:run`.

The application reads `DB_URL`, `DB_USER`, `DB_PASSWORD`, `FIRST_API_KEY`, `SECOND_API_KEY`, and `KOFI_KEY`. Local defaults and the database validation policy are in `src/main/resources/application.properties`. The project expects an existing schema (`spring.jpa.hibernate.ddl-auto=validate`).

### Build

```bash
./mvnw clean package
```

The context-load test requires a correctly configured database. **This portfolio pass was reviewed statically; a Maven build and full database-backed integration test have not yet been verified.** Do not assume the sample setup is a turnkey deployment.

### Container image

The `Dockerfile` expects a prebuilt `target/voeemsog-0.0.1-SNAPSHOT.jar`. Build the JAR first, then run `docker build -t voeemsog-backend .`. The database must be provisioned separately.

## Data model notes

`Room` and `RoomTranslation` are separate JPA entities linked by a one-to-many relationship. This allows localized name/description content while keeping room metadata shared. Some queries join translations to filter by language and sort by date.

## Security and engineering caveats

This code is a **historical implementation and learning/portfolio artifact**, not a hardened production service:

- Admin sessions use a server-local in-memory token store with 30-minute TTL. Restarting the process invalidates all sessions; independent replicas would not share tokens.
- CSRF protection is disabled despite cookie-based authentication; revisit this before any deployment.
- The admin login checks configured secret keys directly; there is no rate limiting or brute-force mitigation.
- The Ko-fi callback currently returns success for many processing exceptions, which risks silently discarding legitimate callbacks. Webhook idempotency and transaction boundaries need improvement.
- Donation amounts use `float` rather than `BigDecimal`, and some writes need explicit transactional guarantees.
- The archived schema is partial; schema migration tooling and proper integration tests remain work to do.

See [`docs/INTERVIEW_PREP_RU.md`](docs/INTERVIEW_PREP_RU.md) for the technical review and interview preparation notes.

## Why this repository exists

The original Java backend was independently implemented before the product switched to Supabase to reduce infrastructure costs. This repository documents the original engineering work, including architectural decisions and remaining trade-offs. The current Supabase deployment is a separate implementation.
