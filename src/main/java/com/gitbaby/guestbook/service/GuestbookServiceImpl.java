package com.gitbaby.guestbook.service;

import com.gitbaby.guestbook.dto.GuestbookDTO;
import com.gitbaby.guestbook.entity.Guestbook;
import com.gitbaby.guestbook.repository.GuestbookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    return toDto(repository.findById(gno).orElseThrow());
  }

  @Override
  public List<GuestbookDTO> readAll() {
    List<Guestbook> guestbooks = repository.findAll();
    List<GuestbookDTO> guestbookDTOS = new ArrayList<>();
    for (Guestbook gb : guestbooks) {
      guestbookDTOS.add(toDto(gb));
    }
//    return repository.findAll().stream().map(this::toDto).toList();
    return guestbookDTOS;
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
}
