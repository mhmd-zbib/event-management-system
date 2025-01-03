# Booking Service

The Booking Service manages service requests, scheduling, and booking status updates.

## API Endpoints

### Booking Management
- `POST /api/v1/bookings`
  - Creates new booking request
  - Required: JWT token
  - Fields: serviceType, description, preferredDate, location
  - Returns: Booking details with status

- `GET /api/v1/bookings/{bookingId}`
  - Retrieves booking details
  - Required: JWT token
  - Returns: Complete booking information

- `GET /api/v1/bookings`
  - Lists profile's bookings
  - Required: JWT token
  - Query parameters: status, dateRange
  - Returns: List of bookings

### Provider Booking Operations
- `PUT /api/v1/bookings/{bookingId}/status`
  - Updates booking status
  - Required: JWT token (Provider)
  - Fields: status (ACCEPTED/REJECTED/COMPLETED)
  - Returns: Updated booking status

- `GET /api/v1/bookings/provider/{providerId}`
  - Lists provider's bookings
  - Required: JWT token (Provider)
  - Query parameters: status, dateRange
  - Returns: List of assigned bookings

### Payment Integration
- `POST /api/v1/bookings/{bookingId}/payment`
  - Processes booking payment
  - Required: JWT token
  - Fields: paymentMethod, amount
  - Returns: Payment confirmation

### Notifications
- `POST /api/v1/bookings/{bookingId}/notify`
  - Sends booking notifications
  - Automatic triggers: status changes, reminders
  - Returns: Notification status 