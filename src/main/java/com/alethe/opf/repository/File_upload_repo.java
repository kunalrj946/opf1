package com.alethe.opf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.File_upload;

/**
 * Created by Kunal Kumar
 */
@Repository
public interface File_upload_repo extends JpaRepository<File_upload, Long> {

	Optional<File_upload> findByFilename(String filename);

}
