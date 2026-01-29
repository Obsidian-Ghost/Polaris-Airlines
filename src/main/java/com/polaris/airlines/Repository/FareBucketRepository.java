package com.polaris.airlines.Repository;

import com.polaris.airlines.Entity.FareBucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface FareBucketRepository extends JpaRepository<FareBucket, UUID> {
    List<FareBucket> findByFlightInstanceIdIn(List<UUID> flightInstanceIds);
}