# Provider Service

The Provider Service manages service provider profiles, skills, availability, and ratings.

## API Endpoints

### Provider Profile Management
- `POST /api/v1/providers`
  - Creates provider profile
  - Required: JWT token, userId
  - Fields: skills, experience, certifications, workingArea
  - Returns: Provider profile details

- `GET /api/v1/providers/{providerId}`
  - Retrieves provider details
  - Returns: Complete provider profile

- `PUT /api/v1/providers/{providerId}`
  - Updates provider profile
  - Required: JWT token
  - Updatable fields: skills, experience, certifications, workingArea
  - Returns: Updated provider profile

### Availability Management
- `POST /api/v1/providers/{providerId}/availability`
  - Sets provider availability
  - Required: JWT token
  - Fields: availableDates, workingHours
  - Returns: Updated availability

- `GET /api/v1/providers/{providerId}/availability`
  - Retrieves provider availability
  - Returns: Available time slots

### Search and Filters
- `GET /api/v1/providers/search`
  - Searches providers
  - Query parameters: skill, location, rating
  - Returns: List of matching providers

### Ratings and Reviews
- `POST /api/v1/providers/{providerId}/reviews`
  - Adds review for provider
  - Required: JWT token, completed booking
  - Fields: rating, comment
  - Returns: Updated provider rating

- `GET /api/v1/providers/{providerId}/reviews`
  - Retrieves provider reviews
  - Returns: List of reviews and average rating 