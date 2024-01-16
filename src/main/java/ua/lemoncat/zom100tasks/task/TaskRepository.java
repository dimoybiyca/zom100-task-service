package ua.lemoncat.zom100tasks.task;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, UUID> {

    Optional<Task> getByIdAndOwnerId(UUID id, String ownerId);

    List<Task> getAllByOwnerIdOrderByCreationDate(String ownerId);

    @Modifying
    void deleteByIdAndOwnerId(UUID id, String ownerId);
}
