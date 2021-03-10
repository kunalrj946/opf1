package com.alethe.opf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.So_category_mst;

/**
 * Created by Kunal Kumar
 */
@Repository
public interface So_category_repo extends JpaRepository<So_category_mst, String> {

}
