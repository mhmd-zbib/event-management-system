# User Service API Responses

## Authentication Responses

### Register User Response
{
    "status": "success",
    "data": {
        "userId": "u-123456789",
        "email": "john@example.com",
        "name": "John Doe",
        "phone": "+1234567890",
        "address": {
            "street": "123 Main St",
            "city": "Boston",
            "state": "MA",
            "zipCode": "02108",
            "country": "USA"
        },
        "accountType": "REGULAR",
        "verificationStatus": {
            "email": false,
            "phone": false
        },
        "createdAt": "2024-03-15T10:30:00Z",
        "token": {
            "accessToken": "eyJhbGciOiJIUzI1NiIs...",
            "refreshToken": "eyJhbGciOiJIUzI1NiIs...",
            "expiresIn": 3600
        }
    }
}

### Login Response
{
    "status": "success",
    "data": {
        "user": {
            "userId": "u-123456789",
            "email": "john@example.com",
            "name": "John Doe",
            "accountType": "REGULAR",
            "lastLogin": "2024-03-15T10:30:00Z"
        },
        "token": {
            "accessToken": "eyJhbGciOiJIUzI1NiIs...",
            "refreshToken": "eyJhbGciOiJIUzI1nIs...",
            "expiresIn": 3600
        },
        "mfaRequired": false
    }
}

### Email Verification Response
{
    "status": "success",
    "data": {
        "userId": "u-123456789",
        "email": "john@example.com",
        "verificationStatus": {
            "email": true,
            "verifiedAt": "2024-03-15T10:35:00Z"
        }
    }
}

### Phone Verification Response
```
{
    "status": "success",
    "data": {
        "userId": "u-123456789",
        "phone": "+1234567890",
        "verificationStatus": {
            "phone": true,
            "verifiedAt": "2024-03-15T10:40:00Z"
        }
    }
}
```
## Profile Management Responses

### Get Profile Response
{
    "status": "success",
    "data": {
        "userId": "u-123456789",
        "email": "john@example.com",
        "name": "John Doe",
        "phone": "+1234567890",
        "address": {
            "street": "123 Main St",
            "city": "Boston",
            "state": "MA",
            "zipCode": "02108",
            "country": "USA"
        },
        "preferences": {
            "notifications": {
                "email": true,
                "sms": true,
                "push": false
            },
            "language": "en",
            "currency": "USD"
        },
        "serviceHistory": {
            "totalBookings": 15,
            "completedBookings": 12,
            "cancelledBookings": 1,
            "lastBooking": "2024-03-14T15:00:00Z"
        },
        "accountStatus": "ACTIVE",
        "memberSince": "2023-01-15T08:00:00Z"
    }
}
}}

### Update Profile Response
{{
{
    "status": "success",
    "data": {
        "userId": "u-123456789",
        "email": "john@example.com",
        "name": "John Doe",
        "phone": "+1234567890",
        "address": {
            "street": "123 Main St",
            "city": "Boston",
            "state": "MA",
            "zipCode": "02108",
            "country": "USA"
        },
        "updatedAt": "2024-03-15T11:00:00Z"
    }
}
}}

## Business Account Management Responses

### Business Verification Response
{{
{
    "status": "success",
    "data": {
        "businessId": "b-123456789",
        "verificationStatus": "APPROVED",
        "verifiedAt": "2024-03-15T12:00:00Z",
        "documents": {
            "businessLicense": {
                "status": "VERIFIED",
                "expiryDate": "2025-03-15"
            },
            "taxId": {
                "status": "VERIFIED"
            }
        }
    }
}
}}

### Team Management Response
{{
{
    "status": "success",
    "data": {
        "businessId": "b-123456789",
        "teamMembers": [
            {
                "userId": "u-987654321",
                "name": "Jane Smith",
                "role": "PROVIDER",
                "specialties": ["Plumbing", "Electrical"],
                "status": "ACTIVE",
                "joinedAt": "2024-02-01T09:00:00Z"
            }
        ],
        "totalMembers": 1,
        "updatedAt": "2024-03-15T13:00:00Z"
    }
}
}}

## Error Responses

### Validation Error
{{
{
    "status": "error",
    "error": {
        "code": "VALIDATION_ERROR",
        "message": "Invalid input data",
        "details": [
            {
                "field": "email",
                "message": "Invalid email format"
            }
        ]
    }
}
}}

### Authentication Error
{{
{
    "status": "error",
    "error": {
        "code": "AUTH_ERROR",
        "message": "Invalid credentials",
        "details": {
            "remainingAttempts": 2
        }
    }
}
}}