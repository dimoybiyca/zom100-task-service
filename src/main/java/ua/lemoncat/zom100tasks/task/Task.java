package ua.lemoncat.zom100tasks.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "owner_id", nullable = false, columnDefinition = "varchar(64)")
    @NotNull
    private String ownerId;

    @Column(name = "text", nullable = false, columnDefinition = "varchar(256)")
    @Length(min = 1, max = 256)
    @NotNull
    private String text;

    @Column(name = "status", nullable = false)
    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
