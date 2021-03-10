package com.alethe.opf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.Business_unit_mst;

/**
 * Created by Kunal Kumar
 */
@Repository
public interface Business_unit_mst_repo extends JpaRepository<Business_unit_mst, String> {

}
