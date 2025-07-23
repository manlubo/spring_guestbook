package com.gitbaby.guestbook.service;

import com.gitbaby.guestbook.dto.GuestbookDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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
    GuestbookDTO guestbookDTO = service.read(105L);
    log.info(guestbookDTO);
  }

  @Test
  @DisplayName("리스트 가져오기")
  public void testReadAll() {
    List<GuestbookDTO> guestbookDTO = service.readAll();
    guestbookDTO.forEach(log::info);
    Assertions.assertEquals(105, guestbookDTO.size());
  }

  @Test
  @DisplayName("수정 하기")
  public void testModify(){
    int result = service.modify(GuestbookDTO.builder().gno(105L).title("수정된 제목").content("수정된 내용").writer("작성자").build());
    Assertions.assertEquals(1, result);
  }

  @Test
  @DisplayName("삭제하기")
  @Rollback(true)
  public void testRemove(){
    int result = service.remove(105L);
    Assertions.assertEquals(1, result);
  }


}
