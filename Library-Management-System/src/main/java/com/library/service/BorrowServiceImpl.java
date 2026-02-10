package com.library.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Book;
import com.library.entity.BorrowRecord;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRecordRepository;

@Service
public class BorrowServiceImpl implements BorrowService{
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private BorrowRecordRepository recordRepo;
	@Override
	public BorrowRecord borrowBook(String username, Long bookId) {
		Book book = bookRepo.findById(bookId)
				.orElseThrow(()->new RuntimeException("Book not found!"));
		if (book.getQuantity() <= 0 )
			throw new RuntimeException("Book not in stock");
		//Reduce quantity
		book.setQuantity(book.getQuantity()-1);
		bookRepo.save(book);
		
		//Create borrow record
		BorrowRecord record = new BorrowRecord();
		record.setUsername(username);
		record.setBookId(bookId);
		record.setBorrowDate(LocalDate.now());
		record.setReturned(false);
		return recordRepo.save(record);
	}
	@Override
	public BorrowRecord returnBook(Long recordId) {
		BorrowRecord record = recordRepo.findById(recordId)
				.orElseThrow(()->new RuntimeException("Record not found!"));
		if ( record.isReturned())
			throw new RuntimeException("Already Returned!");
		// Increase quantity
		Book book = bookRepo.findById(recordId)
				.orElseThrow(()->new RuntimeException("Book not found!"));
		book.setQuantity(book.getQuantity()+1);
		bookRepo.save(book);
		// Update record
		record.setReturned(true);
		record.setReturnDate(LocalDate.now());
		return recordRepo.save(record);
	}
	@Override
	public List<BorrowRecord> getBorrowHistory(String username) {
		return recordRepo.findByUsername(username);
	}
	
	
}
