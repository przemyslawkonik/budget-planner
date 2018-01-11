package com.github.przemyslawkonik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.przemyslawkonik.entity.CashFlow;

public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

	@Query("SELECT cf FROM CashFlow cf JOIN Category c ON cf.category.id=c.id WHERE c.user.id=:id")
	public List<CashFlow> findAllByUserId(@Param("id") long id);

}
