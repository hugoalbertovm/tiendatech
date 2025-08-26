package com.tiendatech.dao;

import com.tiendatech.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FAQDao extends JpaRepository<FAQ, Long> {

    List<FAQ> findAllByActivaTrueOrderByOrdenAscIdAsc();
}
