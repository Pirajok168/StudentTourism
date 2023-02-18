package ru.shared.feature.booking.data

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.shared.feature.booking.data.sql.BookingDormitoriesImpl
import ru.shared.feature.booking.data.sql.IBookingDormitories

internal val bookingModule = DI.Module("bookingModule") {
    bind<IBookingDormitories>() with singleton {
        BookingDormitoriesImpl(
            instance()
        )
    }

    bind<IRepoBooking>() with singleton {
        RepoBookingImpl(
            instance()
        )
    }

}