package com.gitbaby.guestbook.service;

import com.gitbaby.guestbook.dto.GuestbookDTO;
import com.gitbaby.guestbook.dto.PageRequestDTO;
import com.gitbaby.guestbook.dto.PageResponseDTO;
import com.gitbaby.guestbook.entity.Guestbook;
import com.gitbaby.guestbook.entity.QGuestbook;
import com.gitbaby.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Service
@Transactional
public class GuestbookServiceImpl implements GuestbookService{
  private GuestbookRepository repository;

  @Override
  public Long write(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }

  @Override
  public GuestbookDTO read(Long gno) {
    return toDto(repository.findById(gno).orElse(null));
  }

  @Override
  public List<GuestbookDTO> readAll() {
    return repository.findAll(Sort.by(Sort.Direction.DESC, "gno")).stream().map(this::toDto).toList();
  }

  @Override
  public PageResponseDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
    BooleanBuilder getSearch = getSearch(pageRequestDTO);
    return new PageResponseDTO<>(repository.findAll(getSearch, pageRequestDTO.getPageable(Sort.by(Sort.Direction.DESC, "gno"))), this::toDto);
  }

  @Override
  public int modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
    return repository.existsById(guestbookDTO.getGno()) ? 1 : 0;
  }

  @Override
  public int remove(Long gno) {
    repository.deleteById(gno);
    return repository.existsById(gno) ? 0 : 1;
  }

  private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
    String type = requestDTO.getType();

    BooleanBuilder builder = new BooleanBuilder();

    QGuestbook qGuestbook = QGuestbook.guestbook;
    String keyword = requestDTO.getKeyword();
    builder.and(qGuestbook.gno.gt(0));

    if(type == null || type.trim().length() == 0){
      return builder;
    }

    BooleanBuilder conditionBuilder = new BooleanBuilder();

    if(type.contains("t")){
      conditionBuilder.or(qGuestbook.title.contains(keyword));
    }
    if(type.contains("c")){
      conditionBuilder.or(qGuestbook.content.contains(keyword));
    }
    if(type.contains("w")){
      conditionBuilder.or(qGuestbook.writer.contains(keyword));
    }
    builder.and(conditionBuilder);

    return builder;
  }

}
