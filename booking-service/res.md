# Booking Service API Responses

## Booking Management Responses

### Create Booking Response
{{
{
    "status": "success",
    "data": {
        "bookingId": "b-123456789",
        "customer": {
            "userId": "u-987654321",
            "name": "Jane Doe",
            "phone": "+1234567890"
        },
        "service": {
            "type": "Plumbing",
            "description": "Leaking pipe repair",
            "urgency": "HIGH"
        },
        "schedule": {
            "preferredDate": "2024-03-16T09:00:00Z",
            "estimatedDuration": "2h"
        },
        "location": {
            "street": "123 Main St",
            "city": "Boston",
            "state": "MA",
            "zipCode": "02108"
        },
        "status": "PENDING",
        "createdAt": "2024-03-15T10:30:00Z"
    }
}
}}

### Get Booking Details Response
{{
{
    "status": "success",
    "data": {
        "bookingId": "b-123456789",
        "customer": {
            "userId": "u-987654321",
            "name": "Jane Doe",
            "phone": "+1234567890"
        },
        "provider": {
            "providerId": "p-123456789",
            "name": "John Smith",
            "phone": "+1234567890",
            "rating": 4.8
        },
        "service": {
            "type": "Plumbing",
            "description": "Leaking pipe repair",
            "urgency": "HIGH"
        },
        "schedule": {
            "confirmedTime": "2024-03-16T09:00:00Z",
            "estimatedDuration": "2h"
        },
        "location": {
            "street": "123 Main St",
            "city": "Boston",
            "state": "MA",
            "zipCode": "02108"
        },
        "status": "CONFIRMED",
        "price": {
            "baseAmount": 120.00,
            "materials": 30.00,
            "tax": 15.00,
            "total": 165.00,
            "currency": "USD"
        },
        "timeline": [
            {
                "status": "CREATED",
                "timestamp": "2024-03-15T10:30:00Z"
            },
            {
                "status": "CONFIRMED",
                "timestamp": "2024-03-15T10:45:00Z"
            }
        ]
    }
}
}}

### List Bookings Response
{{
{
    "status": "success",
    "data": {
        "bookings": [
            {
                "bookingId": "b-123456789",
                "service": {
                    "type": "Plumbing",
                    "description": "Leaking pipe repair"
                },
                "schedule": {
                    "confirmedTime": "2024-03-16T09:00:00Z"
                },
                "status": "CONFIRMED",
                "price": {
                    "total": 165.00,
                    "currency": "USD"
                }
            }
        ],
        "pagination": {
            "page": 1,
            "perPage": 10,
            "totalPages": 3,
            "totalItems": 25
        }
    }
}
}}

## Payment Responses

### Process Payment Response
{{
{
    "status": "success",
    "data": {
        "paymentId": "pay-123456789",
        "bookingId": "b-123456789",
        "amount": 165.00,
        "currency": "USD",
        "paymentMethod": {
            "type": "CREDIT_CARD",
            "last4": "4242"
        },
        "status": "COMPLETED",
        "timestamp": "2024-03-16T11:30:00Z",
        "receipt": {
            "url": "https://receipts.example.com/pay-123456789",
            "number": "RCP-2024-123456"
        }
    }
}
}}

## Notification Responses

### Booking Notification Response
{{
{
    "status": "success",
    "data": {
        "notificationId": "n-123456789",
        "bookingId": "b-123456789",
        "recipient": {
            "userId": "u-987654321",
            "channels": ["EMAIL", "SMS"]
        },
        "type": "BOOKING_CONFIRMATION",
        "content": {
            "title": "Booking Confirmed",
            "message": "Your booking has been confirmed for March 16, 2024 at 09:00 AM"
        },
        "sentAt": "2024-03-15T10:45:00Z",
        "status": "DELIVERED"
    }
}
}}

## Error Responses

### Booking Conflict Error
{{
{
    "status": "error",
    "error": {
        "code": "BOOKING_CONFLICT",
        "message": "Provider not available for selected time",
        "details": {
            "requestedTime": "2024-03-16T09:00:00Z",
            "availableSlots": [
                "2024-03-16T13:00:00Z",
                "2024-03-16T15:00:00Z"
            ]
        }
    }
}
}}

### Payment Failed Error
{{
{
    "status": "error",
    "error": {
        "code": "PAYMENT_FAILED",
        "message": "Payment processing failed",
        "details": {
            "reason": "INSUFFICIENT_FUNDS",
            "paymentMethod": "CREDIT_CARD",
            "last4": "4242"
        }
    }
}
}} 