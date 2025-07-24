package com.gitbaby.guestbook.dto.sample;

import com.gitbaby.guestbook.entity.Guestbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GuestbookWriteDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;

  public GuestbookWriteDTO(Guestbook guestBook) {
    this.gno = guestBook.getGno();
    this.title = guestBook.getTitle();
    this.content = guestBook.getContent();
    this.writer = guestBook.getWriter();
  }

  public Guestbook toEntity() {
    return Guestbook.builder().content(content).title(title).writer(writer).build();
  }

}
