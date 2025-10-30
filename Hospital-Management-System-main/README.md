# Hospital Management System

Web app for managing patient records, doctor schedules, and appointments. Chatbot suggests doctors based on symptoms.

## Features
- Patient registration
- Appointment booking
- Medical history storage
- Doctor access to records

## Technologies
- Spring Boot (Java)
- HTML, CSS
- MySQL

## Target
Hospitals and clinics for digital healthcare management.

## Setup
1. Clone the repo
2. Open in IDE (Eclipse/IntelliJ)
3. Create MySQL DB `HMS`
4. Set DB credentials in `application.properties`

   - Open `src/main/resources/application.properties` in your project.
   - Update the following lines with your MySQL username and password:

     ```properties
     spring.datasource.username=YOUR_MYSQL_USERNAME
     spring.datasource.password=YOUR_MYSQL_PASSWORD
     ```
   - Make sure the database name in `spring.datasource.url` matches the database you created (e.g., `HMS`).

5. **Create Admin Credentials in MySQL**

   Before starting the app, manually add an admin user to the database.
   Example table schema for `admin`:

   ```sql
   CREATE TABLE admin (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     mail_id VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL
   );
   ```

   Insert an admin user (replace values as needed):

   ```sql
   INSERT INTO admin (mail_id, password) VALUES ('admin@example.com', 'yourpassword');
   ```

6. Run the app (Spring Boot)
7. Open `http://localhost:8081`

---

**Note:**
- Receptionist and doctor accounts must be added from the Admin Dashboard only.
- Patient accounts must be added from the Receptionist Dashboard only.

---

## Module Dashboards & Features

**Admin Dashboard:**
- Add, update, remove receptionists
- Add, update, remove doctors
- View/manage all staff and doctors
- View appointments and reviews
- Logout

**Receptionist Dashboard:**
- Register new patients
- Book, reschedule, or cancel appointments
- View doctor schedules
- Manage patient records
- Logout

**Doctor Dashboard:**
- View assigned appointments
- Access patient medical records
- Create and manage prescriptions
- Update profile details
- Logout

**Patient Dashboard:**
- View and update personal profile
- Book, reschedule, or cancel appointments
- Upload and view medical reports
- View prescriptions
- Logout

---

## Rule-based Chatbot (Doctor Suggestion)

- The app includes a rule-based chatbot to help patients find the right doctor based on their symptoms.
- The chatbot uses a `disease` table in the database, which maps symptoms to diseases and recommends the appropriate doctor specialization.
- When a patient enters their symptoms, the chatbot suggests possible diseases and the right doctor to consult.

**Example disease table schema:**
```sql
CREATE TABLE disease (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  specialty VARCHAR(255) NOT NULL,
  symptoms TEXT NOT NULL
);
```
- Logout