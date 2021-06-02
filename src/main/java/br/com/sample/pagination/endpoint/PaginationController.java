package br.com.sample.pagination.endpoint;

import br.com.sample.pagination.entity.Book;
import br.com.sample.pagination.entity.Response;
import br.com.sample.pagination.usecase.FindAllBooks;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Pagination and Links")
public class PaginationController {

    private final FindAllBooks findAllBooks;

    @GetMapping("/books")
    @Operation(summary = "Returns a paged list")
    public ResponseEntity<Response<Book>> findAll(@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                                            @RequestParam(value = "page-size", required = false, defaultValue = "25") final int size) {
        final Response<Book> response = findAllBooks.execute(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
