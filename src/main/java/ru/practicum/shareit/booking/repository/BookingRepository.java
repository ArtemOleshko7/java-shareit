package ru.practicum.shareit.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByItemIdAndStartAfterOrderByStartAsc(int itemId, LocalDateTime time);

    List<Booking> findAllByItemIdInAndStartAfterOrderByStartAsc(Collection<Integer> itemIds, LocalDateTime time);

    List<Booking> findAllByBookerIdOrderByStartDesc(int bookerId);

    List<Booking> findAllByBookerIdAndEndBeforeOrderByStartDesc(int bookerId, LocalDateTime time);

    Optional<Booking> findByBookerIdAndItemIdAndEndBeforeOrderByStartDesc(int bookerId, int itemId, LocalDateTime time);

    List<Booking> findAllByBookerIdAndStartAfterOrderByStartDesc(int bookerId, LocalDateTime time);

    @Query("SELECT b from Booking AS b " +
            "WHERE b.booker.id = ?1 " +
            "AND ?2 > b.start " +
            "AND ?2 < b.end ")
    List<Booking> findAllCurrentBookings(int bookerId, LocalDateTime time);

    List<Booking> findAllByBookerIdAndStatusOrderByStartDesc(int bookerId, BookingStatus status);

    List<Booking> findAllByItemIdInAndStatusOrderByStartDesc(Collection<Integer> itemIds, BookingStatus status);

    List<Booking> findAllByItemIdInOrderByStartDesc(Collection<Integer> itemIds);

    List<Booking> findAllByItemIdInAndStartAfterOrderByStartDesc(Collection<Integer> itemIds, LocalDateTime time);

    List<Booking> findAllByItemIdInAndEndBeforeOrderByStartDesc(Collection<Integer> itemIds, LocalDateTime time);

    @Query("SELECT b from Booking AS b " +
            "WHERE b.item.id in ?1 " +
            "AND ?2 > b.start " +
            "AND ?2 < b.end ")
    List<Booking> findAllCurrentBookings(Collection<Integer> itemIds, LocalDateTime time);
}