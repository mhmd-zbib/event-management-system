# User Service

The User Service handles profile identity management, authentication, and authorization with advanced business rules and security measures.

## Core Business Features

### User Types & Roles
- **Regular Users**: End customers seeking services
- **Service Providers**: Professional contractors
- **Business Accounts**: Company-managed provider teams
- **Administrators**: Platform managers with full access

### Business Rules
- Email verification required for account activation
- Phone verification mandatory for providers
- Business accounts require additional verification (tax ID, business license)
- Automatic account suspension after 3 failed login attempts
- Password rotation policy every 90 days for providers

## API Endpoints

### Authentication & Security
- `POST /api/v1/auth/register`
  - Supports individual and business registration flows
  - Required fields: email, password, name, phone, address, accountType
  - Business fields: companyName, taxId, businessLicense
  - Returns: User details with JWT token
  - Triggers: Welcome email, verification process

- `POST /api/v1/auth/verify-email`
  - Email verification endpoint
  - Required: verification token
  - Returns: Activation status

- `POST /api/v1/auth/verify-phone`
  - Phone verification via SMS
  - Required: verification code
  - Returns: Verification status

- `POST /api/v1/auth/login`
  - Multi-factor authentication support
  - Rate limiting: 5 attempts per hour
  - Required fields: email, password, 2FA code (if enabled)
  - Returns: JWT token, refresh token, profile details

### Profile Management
- `GET /api/v1/users/profile`
  - Includes service history, preferences
  - Returns: Detailed profile information, account status

- `PUT /api/v1/users/profile`
  - Validates address through external service
  - Supports partial updates
  - Required: JWT token
  - Updatable fields: name, phone, address, email, preferences
  - Returns: Updated profile details

### Business Account Management
- `POST /api/v1/users/business/verify`
  - Business verification process
  - Required: business documents, proof of address
  - Returns: Verification status

- `POST /api/v1/users/business/team`
  - Manage team members for business accounts
  - Add/remove service providers
  - Required: Business admin rights

### Administrative Operations
- `GET /api/v1/admin/users`
  - Lists users with filtering and pagination
  - Supports advanced search
  - Required: ADMIN role

- `POST /api/v1/admin/users/suspend`
  - Suspends profile accounts
  - Required: ADMIN role, reason code
  - Triggers: Notification emails

## Security & Compliance
- JWT-based authentication with refresh token rotation
- Password encryption using BCrypt with configurable work factor
- Role-based access control with fine-grained permissions
- GDPR compliance features
- Data retention policies
- Activity logging and audit trails

## Integration Points
- Email service for notifications
- SMS gateway for phone verification
- Address validation service
- Payment service for subscription management
- Analytics service for profile behavior tracking

## Scalability Features
- Redis caching for profile sessions
- Database sharding strategy
- Rate limiting per IP and profile
- Async processing for non-critical operations