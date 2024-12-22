# Provider Service API Responses

## Provider Profile Responses

### Create Provider Profile Response
{{
{
    "status": "success",
    "data": {
        "providerId": "p-123456789",
        "userId": "u-123456789",
        "name": "John Smith",
        "skills": ["Plumbing", "Electrical", "HVAC"],
        "experience": {
            "yearsOfExperience": 5,
            "certifications": [
                {
                    "name": "Master Plumber License",
                    "issuedBy": "State Board",
                    "expiryDate": "2025-12-31"
                }
            ]
        },
        "workingArea": {
            "radius": 25,
            "baseLocation": {
                "city": "Boston",
                "state": "MA",
                "zipCodes": ["02108", "02109", "02110"]
            }
        },
        "status": "ACTIVE",
        "createdAt": "2024-03-15T10:30:00Z"
    }
}
}}

### Get Provider Profile Response
{{
{
    "status": "success",
    "data": {
        "providerId": "p-123456789",
        "basicInfo": {
            "name": "John Smith",
            "phone": "+1234567890",
            "email": "john@example.com"
        },
        "skills": ["Plumbing", "Electrical", "HVAC"],
        "experience": {
            "yearsOfExperience": 5,
            "certifications": [
                {
                    "name": "Master Plumber License",
                    "issuedBy": "State Board",
                    "expiryDate": "2025-12-31"
                }
            ]
        },
        "ratings": {
            "average": 4.8,
            "totalReviews": 156,
            "breakdown": {
                "5": 120,
                "4": 30,
                "3": 5,
                "2": 1,
                "1": 0
            }
        },
        "completedJobs": 180,
        "workingArea": {
            "radius": 25,
            "baseLocation": {
                "city": "Boston",
                "state": "MA",
                "zipCodes": ["02108", "02109", "02110"]
            }
        },
        "status": "ACTIVE"
    }
}
}}

## Availability Management Responses

### Set Availability Response
{{
{
    "status": "success",
    "data": {
        "providerId": "p-123456789",
        "availability": {
            "regularHours": {
                "monday": {"start": "09:00", "end": "17:00"},
                "tuesday": {"start": "09:00", "end": "17:00"},
                "wednesday": {"start": "09:00", "end": "17:00"},
                "thursday": {"start": "09:00", "end": "17:00"},
                "friday": {"start": "09:00", "end": "17:00"}
            },
            "exceptions": [
                {
                    "date": "2024-03-20",
                    "available": false,
                    "reason": "VACATION"
                }
            ],
            "updatedAt": "2024-03-15T11:00:00Z"
        }
    }
}
}}

### Get Available Slots Response
{{
{
    "status": "success",
    "data": {
        "providerId": "p-123456789",
        "availableSlots": [
            {
                "date": "2024-03-16",
                "slots": [
                    {"start": "09:00", "end": "11:00"},
                    {"start": "13:00", "end": "15:00"}
                ]
            },
            {
                "date": "2024-03-17",
                "slots": [
                    {"start": "09:00", "end": "11:00"},
                    {"start": "15:00", "end": "17:00"}
                ]
            }
        ]
    }
}
}}

## Reviews and Ratings Responses

### Add Review Response
{{
{
    "status": "success",
    "data": {
        "reviewId": "r-123456789",
        "providerId": "p-123456789",
        "rating": 5,
        "comment": "Excellent service, very professional",
        "bookingId": "b-123456789",
        "reviewer": {
            "userId": "u-987654321",
            "name": "Jane Doe"
        },
        "createdAt": "2024-03-15T14:30:00Z"
    }
}
}}

### Get Provider Reviews Response
{{
{
    "status": "success",
    "data": {
        "providerId": "p-123456789",
        "summary": {
            "averageRating": 4.8,
            "totalReviews": 156
        },
        "reviews": [
            {
                "reviewId": "r-123456789",
                "rating": 5,
                "comment": "Excellent service, very professional",
                "reviewer": {
                    "name": "Jane Doe",
                    "verified": true
                },
                "serviceDate": "2024-03-14T15:00:00Z",
                "createdAt": "2024-03-15T14:30:00Z"
            }
        ],
        "pagination": {
            "page": 1,
            "perPage": 10,
            "totalPages": 16,
            "totalItems": 156
        }
    }
}
}}

## Error Responses

### Availability Conflict Error
{{
{
    "status": "error",
    "error": {
        "code": "AVAILABILITY_CONFLICT",
        "message": "Time slot already booked",
        "details": {
            "conflictingBooking": {
                "bookingId": "b-123456789",
                "time": "2024-03-16T09:00:00Z"
            }
        }
    }
}
}}

### Invalid Service Area Error
{{
{
    "status": "error",
    "error": {
        "code": "INVALID_SERVICE_AREA",
        "message": "Location outside service area",
        "details": {
            "requestedZipCode": "02115",
            "maxServiceRadius": 25,
            "distanceFromBase": 30
        }
    }
}
}} 