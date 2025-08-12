Quarkus ABAC demo project
=========================

This project contains a minimal Quarkus application demonstrating:
- Attribute-based access control (ABAC) with flexible key/value attribute tables
- Policy + PolicyRule entities with a deny-overrides conflict resolution
- A simple REST endpoint for access checks: GET /access-check?subject=...&action=...&resource=...&context=...
- Unit/integration tests (QuarkusTest)

To run:
- Install JDK 17 and Maven
- mvn clean quarkus:dev

Notes:
- This is a demo skeleton; operator semantics are limited to '=' and '!=' for now.
- PolicyRule uses columns (policy_id, rule_id, target_type, key, op, value) which map to the import.sql.
