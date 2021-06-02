package br.com.sample.pagination.usecase;

import br.com.sample.pagination.entity.Book;
import br.com.sample.pagination.entity.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindAllBooks extends BasePagedUseCase<Book> {

    public Response<Book> execute(final int page, final int size) {

        List<Book> books = createBooks();

        int fromIndex = (page - 1) * size;

        List<Book> listBooks = books.subList(fromIndex, Math.min(fromIndex + size, books.size()));
        return super.execute(listBooks, page, size, books.size());
    }

    private List<Book> createBooks() {
        List<Book> books = new ArrayList<>();

        for (int i=1;i<=50;i++) {
            books.add(Book.builder()
                    .name("name " + i)
                    .author("author " + i)
                    .pages(i)
                    .build());
        }

        return books;
    }
}
