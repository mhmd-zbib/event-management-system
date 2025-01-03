# Home Fix Service

A **Spring Boot microservices-based application** aimed at addressing common household repair needs. The platform
enables users to seamlessly book service providers for various tasks, including plumbing, electrical repairs, glasswork,
tiling, and more.

## Key Features

- **User Service**: Manages profile profiles and roles, including service providers.
- **Provider Service**: Stores detailed information about service providers, such as their skills and schedules.
- **Booking Service**: Handles the booking workflow between users and providers, ensuring smooth coordination.

## Project Architecture

This project utilizes a **microservices architecture**, dividing responsibilities into distinct components:

1. **User Service**:
    - Maintains profile data, differentiating standard users from service providers through role assignments.
    - Stores basic profile information, while provider-specific details are housed in the Provider Service database.

2. **Provider Service**:
    - Manages provider profiles, including qualifications, skills, and availability.

3. **Booking Service**:
    - Orchestrates the service booking process, allowing users to request services and providers to respond by accepting
      or declining.

## Technologies Used

- **Backend Framework**: Spring Boot (Java)
- **Database**: Relational databases such as MySQL or PostgreSQL
- **Communication**: RESTful APIs for service interaction
- **Architecture**: Microservices

## Usage

1. **User and Provider Registration**:
    - Users sign up to access and book services.
    - Service providers register, adding their credentials and relevant professional details.

2. **Service Booking**:
    - Users browse available services, submit booking requests, and await provider responses.
    - Providers review and accept or decline requests based on availability.

3. **Track Bookings**:
    - Both users and providers can view and manage booking statuses using the Booking Service.

 
 