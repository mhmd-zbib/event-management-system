# Event Management Platform

A microservices-based event management platform that connects customers, event organizers, venue owners, and service providers in a seamless ecosystem. 
**This project is under construciton after a refactor from old microservices**

## Overview

The Event Management Platform is a comprehensive solution that facilitates the entire event management lifecycle, from venue selection to ticket sales. The platform serves four main user types:
- **Customers**: End users who browse and purchase event tickets
- **Event Organizers**: Professionals who create and manage events
- **Venue Owners**: Providers of event spaces
- **Venue Service Providers**: Specialists who offer venue setup and management services

## System Architecture

### Core Services

1. **User Service**
   - User authentication and authorization
   - Role-based access control (RBAC)
   - OAuth2/OpenID Connect integration
   - User profile management
  
1. **Profile Service**
   - User profile management
   - Settings and prefrences

2. **Event Service**
   - Event creation and management
   - Event scheduling
   - Ticket management
   - Event analytics

3. **Venue Service**
   - Venue listings and management
   - Booking calendar
   - Venue analytics
   - Facility management

4. **Provider Service**
   - Service provider profiles
   - Service cataloging
   - Booking management
   - Provider ratings and reviews

5. **Booking Service**
   - Venue reservations
   - Service provider bookings
   - Conflict management
   - Resource allocation

6. **Ticket Service**
   - Ticket generation
   - Inventory management
   - Purchase processing
   - QR code generation

### Supporting Services

7. **Payment Service**
   - Payment processing
   - Refund handling
   - Commission management
   - Financial reporting

8. **Notification Service**
   - Email notifications
   - SMS alerts
   - Push notifications
   - In-app messaging

9. **Analytics Service**
   - Business intelligence
   - Reporting
   - Performance metrics
   - User analytics

## Technical Stack

### Backend
- Java 17
- Spring Boot 3.x
- Spring Cloud
- Spring Security

### Database
- PostgreSQL (transactional data)
- MongoDB (event/venue content)
- Redis (caching)

### Message Queue
- RabbitMQ

### Infrastructure
- Docker
- Kubernetes
- AWS/Azure Cloud

## Key Features

### For Customers
- Event discovery and booking
- Secure ticket purchasing
- Real-time notifications
- Event updates and reminders
- Rating and review system

### For Event Organizers
- Event creation and management
- Venue selection and booking
- Service provider coordination
- Ticket sales management
- Analytics dashboard

### For Venue Owners
- Venue profile management
- Calendar management
- Booking handling
- Revenue tracking
- Facility updates

### For Service Providers
- Service listing management
- Booking calendar
- Service pricing
- Job scheduling
- Payment tracking

## Getting Started

### Prerequisites
```bash
- Java 17+
- Docker & Docker Compose
- Maven 3.8+
- Node.js 18+ (for frontend)
