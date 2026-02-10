package com.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long>{
	List<BorrowRecord> findByUsername(String username); 
	Optional<BorrowRecord> findByIdAndUsername(Long id, String username); 
}
