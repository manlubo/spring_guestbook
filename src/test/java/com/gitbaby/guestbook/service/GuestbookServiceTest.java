package com.gitbaby.guestbook.service;

import com.gitbaby.guestbook.dto.GuestbookDTO;
import com.gitbaby.guestbook.dto.PageRequestDTO;
import com.gitbaby.guestbook.dto.PageResponseDTO;
import com.gitbaby.guestbook.entity.Guestbook;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@SpringBootTest
public class GuestbookServiceTest {
  @Autowired
  private GuestbookService service;

  @Test
  @DisplayName("객체취득 테스트")
  public void testExist(){
    log.info(service);
  }

  @Test
  @DisplayName("작성 테스트")
  public void testWrite(){
    Long gno = service.write(GuestbookDTO.builder().title("제목").content("내용").writer("작성자").build());
    Assertions.assertNotNull(gno);
    log.info(gno);
  }

  @Test
  @DisplayName("guestbookDTO 하나 가져오기")
  public void testRead(){
    Long gno = 104L;
    GuestbookDTO guestbookDTO = service.read(gno);
    GuestbookDTO expect = GuestbookDTO.builder().title("제목0").content("내용5").writer("작성자").gno(gno).build();

    Assertions.assertEquals(expect.getTitle(), guestbookDTO.getTitle());
    Assertions.assertEquals(expect.getContent(), guestbookDTO.getContent());
    Assertions.assertEquals(expect.getWriter(), guestbookDTO.getWriter());
    log.info(guestbookDTO);
  }

  @Test
  @DisplayName("리스트 가져오기")
  public void testReadAll() {
    List<GuestbookDTO> guestbookDTO = service.readAll();
    guestbookDTO.forEach(log::info);
    Assertions.assertEquals(104, guestbookDTO.size());
  }

  @Test
  @DisplayName("수정 하기")
  @Transactional
  public void testModify(){
    Long gno = 104L;
    GuestbookDTO guestbookDTO = service.read(gno);
    guestbookDTO.setContent("수정 내용");
    service.modify(guestbookDTO);
  }

  @Test
  @DisplayName("삭제하기")
  @Rollback(true)
  public void testRemove(){
  service.remove(105L);

  }

  @Test
  @DisplayName("페이지 리스트 테스트")
  public void testGetList(){
//    BooleanBuilder getSearch = new BooleanBuilder();
//    getSearch.
//    PageResponseDTO<?, ?> guestbookDTOList = service.getList(, PageRequestDTO.builder().page(8).size(5).build());

//    log.info(guestbookDTOList);
  }


}
