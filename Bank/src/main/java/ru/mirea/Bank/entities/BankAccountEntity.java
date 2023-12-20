package ru.mirea.Bank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "bankAccount")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int balance;

    @JoinColumn
    @ManyToOne
    private UserEntity accountUser;

}
