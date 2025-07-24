package com.gitbaby.guestbook.service;

import com.gitbaby.guestbook.dto.GuestbookDTO;
import com.gitbaby.guestbook.dto.PageRequestDTO;
import com.gitbaby.guestbook.dto.PageResponseDTO;
import com.gitbaby.guestbook.entity.Guestbook;
import com.gitbaby.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface GuestbookService {

  Long write(GuestbookDTO guestbookDTO);
  GuestbookDTO read(Long gno);
  List<GuestbookDTO> readAll();
  PageResponseDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO);
  int modify(GuestbookDTO guestbookDTO);
  int remove(Long gno);


  default Guestbook toEntity(GuestbookDTO guestbookDTO){
    return Guestbook.builder()
            .gno(guestbookDTO.getGno())
            .title(guestbookDTO.getTitle())
            .content(guestbookDTO.getContent())
            .writer(guestbookDTO.getWriter())
            .build();
  }

  default GuestbookDTO toDto(Guestbook  guestbook){
    return guestbook == null ? null : GuestbookDTO.builder()
            .gno(guestbook.getGno())
            .title(guestbook.getTitle())
            .content(guestbook.getContent())
            .writer(guestbook.getWriter())
            .regDate(guestbook.getRegDate())
            .modDate(guestbook.getModDate())
            .build();
  }
}
