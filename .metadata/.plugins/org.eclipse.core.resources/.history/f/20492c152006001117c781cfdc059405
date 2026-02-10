package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.BorrowRecord;
import com.library.service.BorrowService;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
	@Autowired 
	private BorrowService borrowService;
	
	//Borrow book
	@PostMapping("/{username}/{bookId}")
	public BorrowRecord borrowBook(@PathVariable String username, @PathVariable Long bookId) {
		return borrowService.borrowBook(username, bookId);
	}
	
	//Return book
	@PutMapping("/return/{recordId}")
	public BorrowRecord returnBook(@PathVariable Long recordId) {
		return borrowService.returnBook(recordId);
	}

}
