package dev.zbib.shared.enums;

import java.util.EnumSet;

public enum BookingStatus {
    PENDING {
        @Override
        public boolean canTransitionTo(BookingStatus targetStatus) {
            return EnumSet.of(ACCEPTED, REJECTED, CANCELLED_BY_BOOKER).contains(targetStatus);
        }
    },
    ACCEPTED {
        @Override
        public boolean canTransitionTo(BookingStatus targetStatus) {
            return EnumSet.of(CONFIRMED, CANCELLED_BY_OWNER).contains(targetStatus);
        }
    },
    REJECTED {
        @Override
        public boolean canTransitionTo(BookingStatus targetStatus) {
            return false;
        }
    },
    CONFIRMED {
        @Override
        public boolean canTransitionTo(BookingStatus targetStatus) {
            return EnumSet.of(CANCELLED_BY_BOOKER, CANCELLED_BY_OWNER, COMPLETED).contains(targetStatus);
        }
    },
    CANCELLED_BY_BOOKER {
        @Override
        public boolean canTransitionTo(BookingStatus targetStatus) {
            return false;
        }
    },
    CANCELLED_BY_OWNER {
        @Override
        public boolean canTransitionTo(BookingStatus targetStatus) {
            return false;
        }
    },
    COMPLETED {
        @Override
        public boolean canTransitionTo(BookingStatus targetStatus) {
            return false;
        }
    };

    public abstract boolean canTransitionTo(BookingStatus targetStatus);
}