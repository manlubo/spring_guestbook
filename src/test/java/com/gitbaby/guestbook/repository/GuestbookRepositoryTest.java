package com.gitbaby.guestbook.repository;


import com.gitbaby.guestbook.entity.Guestbook;
import com.gitbaby.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class GuestbookRepositoryTest {
  @Autowired
  private GuestbookRepository repository;

  @Test
  @DisplayName("객체 취득 테스트")
  public void testExist(){
    log.info("{}", repository);
  }

  @Test
  @DisplayName("생성 테스트")
  public void testInsert(){
//    repository.save(Guestbook.builder().title("제목").content("내용").writer("작성자").build());
    LongStream.rangeClosed(1, 100).forEach(i -> repository.save(Guestbook.builder().title("제목" + (i % 10)).content("내용" + ((i + 5) % 10)).writer("작성자").build()));
  }

//  @Test
//  @DisplayName()
//  public void testFindByGno(){
//
//  }




  @Test
  @DisplayName("querydsl 복합 조건 테스트")
  public void testQuery1(){
    Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

    QGuestbook qGuestbook = QGuestbook.guestbook;

    String keyword = "1";

    BooleanBuilder builder = new BooleanBuilder();
//    BooleanExpression expression = qGuestbook.gno.gt(60);
    builder.andAnyOf(qGuestbook.title.contains(keyword), qGuestbook.content.contains(keyword));
    builder.and(qGuestbook.gno.gt(60));


    Page<Guestbook> result = repository.findAll(builder, pageable);

    result.forEach(log::info);

  }
}
