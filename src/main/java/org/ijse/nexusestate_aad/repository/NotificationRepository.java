package org.ijse.nexusestate_aad.repository;

import org.ijse.nexusestate_aad.entity.CustomerExperienceandAI.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}