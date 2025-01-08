package dev.zbib.bookingservice.enums;

import dev.zbib.bookingservice.service.StatusTransitionHandler;

import java.util.EnumSet;
import java.util.Set;

public enum BookingStatus implements StatusTransitionHandler {
    PENDING {
        @Override
        public Set<BookingStatus> getAllowedTransitions() {
            return EnumSet.of(ACCEPTED, REJECTED, CANCELLED_BY_BOOKER);
        }
    },
    ACCEPTED {
        @Override
        public Set<BookingStatus> getAllowedTransitions() {
            return EnumSet.of(CONFIRMED, CANCELLED_BY_OWNER);
        }
    },
    REJECTED {
        @Override
        public Set<BookingStatus> getAllowedTransitions() {
            return EnumSet.noneOf(BookingStatus.class);
        }
    },
    CONFIRMED {
        @Override
        public Set<BookingStatus> getAllowedTransitions() {
            return EnumSet.of(CANCELLED_BY_BOOKER, CANCELLED_BY_OWNER, COMPLETED);
        }
    },
    CANCELLED_BY_BOOKER {
        @Override
        public Set<BookingStatus> getAllowedTransitions() {
            return EnumSet.noneOf(BookingStatus.class);
        }
    },
    CANCELLED_BY_OWNER {
        @Override
        public Set<BookingStatus> getAllowedTransitions() {
            return EnumSet.noneOf(BookingStatus.class);
        }
    },
    COMPLETED {
        @Override
        public Set<BookingStatus> getAllowedTransitions() {
            return EnumSet.noneOf(BookingStatus.class);
        }
    };

    public boolean canTransitionTo(BookingStatus targetStatus) {
        return this.getAllowedTransitions().contains(targetStatus);
    }
}
