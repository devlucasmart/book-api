package com.devlucasmart.book.mappers;

import com.devlucasmart.book.dto.book.BookRequest;
import com.devlucasmart.book.dto.book.BookResponse;
import com.devlucasmart.book.model.BookModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookModel toDomain(BookRequest request);
    BookRequest toRequest(BookModel book);
    BookResponse toResponse(BookModel book);
    List<BookResponse> toListResponse(List<BookModel> book);
}
