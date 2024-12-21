package org.distributed.consensus.repository;

import jakarta.persistence.LockModeType;
import org.distributed.consensus.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.roomId = :roomId AND b.finish > :start AND b.start < :finish")
    List<Booking> findOverlappingBookings(@Param("roomId") long roomId, @Param("start") Date start, @Param("finish") Date finish);

    @Query("SELECT b FROM Booking b WHERE b.roomId = :roomId AND b.finish > :start AND b.start < :finish")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Booking> findOverlappingBookingsWithLock(@Param("roomId") long roomId, @Param("start") Date start, @Param("finish") Date finish);
}

