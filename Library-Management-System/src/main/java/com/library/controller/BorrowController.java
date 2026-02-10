package com.library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	@PostMapping("/{bookId}")
	public BorrowRecord borrowBook(@PathVariable Long bookId, Principal principal) {
		String username = principal.getName();
		return borrowService.borrowBook(username, bookId);
	}
	
	//Return book
	@PutMapping("/return/{recordId}")
	public BorrowRecord returnBook(@PathVariable Long recordId, Principal principal) {
		String username = principal.getName();
		return borrowService.returnBook(recordId,username);
	}
	
	@GetMapping("/history")
	public List<BorrowRecord> getHistory(Principal principal){
		//Logged in user name for JWT Token
		String username = principal.getName();
		
		return borrowService.getBorrowHistory(username);
	}
	
	

}
