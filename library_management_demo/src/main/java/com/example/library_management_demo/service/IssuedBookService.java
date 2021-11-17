package com.example.library_management_demo.service;

import com.example.library_management_demo.model.Book;
import com.example.library_management_demo.model.IssuedBook;
import com.example.library_management_demo.repository.IssuedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.library_management_demo.common.Constants.BOOK_NOT_RETURNED;

@Service
public class IssuedBookService {

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    public List<IssuedBook> getAll(){
        return issuedBookRepository.findAll();
    }

    public IssuedBook get(Long id){
        return issuedBookRepository.findById(id).get();
    }

    public Long getCountByBook(Book book){
        return issuedBookRepository.countByBookAndReturned(book, BOOK_NOT_RETURNED);
    }

    public IssuedBook save(IssuedBook issuedBook) {
        return issuedBookRepository.save(issuedBook);
    }

    public IssuedBook addNew(IssuedBook issuedBook){
        issuedBook.setReturned(BOOK_NOT_RETURNED);
        return issuedBookRepository.save(issuedBook);
    }
}
