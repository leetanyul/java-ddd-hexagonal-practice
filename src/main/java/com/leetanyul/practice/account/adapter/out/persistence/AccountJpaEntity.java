package com.leetanyul.practice.account.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AccountJpaEntity {

    @Id
    private Long id;

    private String name;

    private String email;

    @Column(name = "is_admin")
    private boolean isAdmin;
}
