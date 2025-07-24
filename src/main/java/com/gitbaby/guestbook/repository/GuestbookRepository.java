package com.gitbaby.guestbook.repository;

import com.gitbaby.guestbook.dto.GuestbookDTO;
import com.gitbaby.guestbook.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, QuerydslPredicateExecutor<Guestbook> {
}
