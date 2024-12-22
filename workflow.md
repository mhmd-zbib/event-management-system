# Home Fix Service Workflow

## System Workflow Overview

### 1. User Registration and Authentication
1. Users register through User Service
2. System assigns appropriate role (USER/PROVIDER)
3. Providers complete additional profile in Provider Service
4. JWT tokens used for subsequent authenticated requests

### 2. Provider Setup
1. Provider completes profile with skills and certifications
2. Sets availability schedule
3. Defines service areas and pricing
4. System validates and activates provider profile

### 3. Booking Process
1. User searches for providers by service type/location
2. User creates booking request
3. System notifies matching providers
4. Provider accepts/rejects booking
5. User receives notification of provider response
6. If accepted, booking status updates to CONFIRMED

### 4. Service Execution
1. Provider arrives at scheduled time
2. Completes service
3. Marks booking as COMPLETED
4. System triggers payment process
5. User can submit review and rating

### 5. Payment Flow
1. System calculates service cost
2. User submits payment
3. System holds payment in escrow
4. Released to provider after service completion
5. System handles commission deduction

### 6. Review and Rating
1. User submits review after service completion
2. System updates provider's rating
3. Reviews visible on provider's profile
4. Affects provider's search ranking

## Cross-Service Communication
- User Service validates all authenticated requests
- Provider Service checks availability before booking confirmation
- Booking Service coordinates between user and provider
- Event-based notifications for status changes
- Centralized logging and monitoring

## Security Measures
- JWT-based authentication
- Role-based access control
- Encrypted data transmission
- Regular security audits
- Rate limiting on APIs 