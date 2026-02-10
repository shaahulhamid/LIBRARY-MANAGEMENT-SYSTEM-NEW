package com.library.service;

import java.util.List;

import com.library.entity.BorrowRecord;

public interface BorrowService {
	BorrowRecord borrowBook(String username, Long bookId);
	List<BorrowRecord> getBorrowHistory(String username);
	BorrowRecord returnBook(Long recordId, String username);
}
