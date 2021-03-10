package com.alethe.opf.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alethe.opf.dto.CustomerPair;
import com.alethe.opf.dto.UploadFileResponse;
import com.alethe.opf.entity.Business_nature_mst;
import com.alethe.opf.entity.Business_unit_mst;
import com.alethe.opf.entity.Company_mst;
import com.alethe.opf.entity.Customer_mst;
import com.alethe.opf.entity.Customer_segment_mst;
import com.alethe.opf.entity.File_upload;
import com.alethe.opf.entity.So_category_mst;
import com.alethe.opf.entity.User_role_mst;
import com.alethe.opf.entity.User_type_mst;
import com.alethe.opf.entity.Users;
import com.alethe.opf.exception.ResourceNotFoundException;
import com.alethe.opf.repository.Business_nature_mst_repo;
import com.alethe.opf.repository.Business_unit_mst_repo;
import com.alethe.opf.repository.Company_mst_repo;
import com.alethe.opf.repository.Customer_mst_repo;
import com.alethe.opf.repository.Customer_segment_mst_repo;
import com.alethe.opf.repository.File_upload_repo;
import com.alethe.opf.repository.So_category_repo;
import com.alethe.opf.repository.UserRepository;
import com.alethe.opf.repository.User_role_mst_repo;
import com.alethe.opf.repository.User_type_mst_repository;
import com.alethe.opf.utility.Utility;

/**
 * Created by Kunal Kumar
 */
@Service
public class OpfApplicationImpl implements OpfApplicationI, UserDetailsService {

	@Autowired
	private Company_mst_repo comp_repo;

	@Autowired
	private Customer_mst_repo cust_repo;

	@Autowired
	private File_upload_repo fileRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private Customer_segment_mst_repo csmr;

	@Autowired
	private User_type_mst_repository utmr;

	@Autowired
	private User_role_mst_repo urmr;

	@Autowired
	private Business_unit_mst_repo bumr;

	@Autowired
	private Business_nature_mst_repo bnr;

	@Autowired
	private So_category_repo scr;

	@Autowired
	private Customer_mst_repo cmr;

	private static Logger logger = LoggerFactory.getLogger(OpfApplicationImpl.class);

	@Override
	public UserDetails loadUserByUsername(String loginid) throws UsernameNotFoundException {
		Optional<Users> user = userRepo.findByLoginid(loginid);

		user.orElseThrow(() -> new UsernameNotFoundException("user does not exist " + loginid));

		return user.map(UserCustomImpl::new).get();
	}

	@Override
	@Transactional
	public HashMap<String, Object> getCompAllData() {

		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<Company_mst> result = comp_repo.findAll();

		if (!result.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", result);
			content.put("error", hasError);
			content.put("message", errorMsg);

			response.put("content", content);
		} else {
			hasError = true;
			errorMsg = "data not found !";
			content.put("collection", result);
			content.put("error", hasError);
			content.put("message", errorMsg);

			response.put("content", content);

		}
		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getDataById(Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = "";

		Company_mst result = comp_repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));

		errorMsg = "data is found !";
		hasError = false;
		content.put("collection", result);
		content.put("error", hasError);
		content.put("message", errorMsg);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> createCompany(Company_mst Commst) {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		Company_mst result = comp_repo.save(Commst);

		errorMsg = "data inserted !";
		hasError = false;
		content.put("collection", result);
		content.put("error", hasError);
		content.put("message", errorMsg);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional()
	public HashMap<String, Object> updateCompnay(Company_mst comp, Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		Company_mst comp_mst = comp_repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id : " + id));

		// comp_mstsetCompany_id(comp.getCompany_id());
		comp_mst.setCompany_name(comp.getCompany_name());
		comp_mst.setCompany_billing_name(comp.getCompany_billing_name());
		comp_mst.setBill_address1(comp.getBill_address1());
		comp_mst.setBill_address2(comp.getBill_address2());
		comp_mst.setBill_city(comp.getBill_city());
		comp_mst.setBill_state(comp.getBill_state());
		comp_mst.setBill_country(comp.getBill_country());
		comp_mst.setBill_pin(comp.getBill_pin());
		comp_mst.setDisp_address1(comp.getDisp_address1());
		comp_mst.setDisp_address2(comp.getDisp_address2());
		comp_mst.setDisp_city(comp.getDisp_city());
		comp_mst.setDisp_state(comp.getDisp_state());
		comp_mst.setDisp_country(comp.getDisp_country());
		comp_mst.setDisp_pin(comp.getDisp_pin());
		comp_mst.setSo_initials(comp.getSo_initials());
		comp_mst.setGstn(comp.getGstn());
		comp_mst.setPan(comp.getPan());
		comp_mst.setTan(comp.getTan());
		comp_mst.setDisclaimer(comp.getDisclaimer());
		comp_mst.setT_and_c(comp.getT_and_c());
		comp_mst.setModified_on(comp.getModified_on());
		comp_mst.setModified_by(comp.getModified_by());
		comp_mst.setIs_active(comp.getIs_active());
		comp_mst.setIs_deleted(comp.getIs_deleted());

		final Company_mst result = comp_repo.save(comp_mst);

		hasError = false;
		errorMsg = "data updated !";
		content.put("collection", result);
		content.put("message", errorMsg);
		content.put("error", hasError);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> deleteCompnay(Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;

		Company_mst cm = comp_repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));

		comp_repo.delete(cm);
		hasError = false;
		content.put("deleted", Boolean.TRUE);
		content.put("error", hasError);

		response.put("content", content);
		return response;

	}

	@Override
	@Transactional
	public HashMap<String, Object> getCustomerAllData() throws ResourceNotFoundException {

		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<Customer_mst> result = cust_repo.findAll();

		if (!result.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", result);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);

		} else {

			errorMsg = "data not found !";
			hasError = true;
			content.put("collection", result);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		}

		return response;

	}

	@Override
	@Transactional
	public HashMap<String, Object> getCustomerDataById(Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		Customer_mst result = cust_repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("data not found for this id :: " + id));

		hasError = false;
		errorMsg = "data is found !";
		content.put("collection", result);
		content.put("error", hasError);
		content.put("message", errorMsg);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> createCustomer(Customer_mst Commst) {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		Customer_mst result = cust_repo.save(Commst);

		errorMsg = "data inserted !";
		hasError = false;

		content.put("collection", result);
		content.put("error", hasError);
		content.put("message", errorMsg);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> updateCustomer(Customer_mst details, Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		Customer_mst cm = cust_repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));

		// cm.setCustomer_id(details.getCustomer_id());
		cm.setCustomer_name(details.getCustomer_name());
		cm.setCustomer_billing_name(details.getCustomer_billing_name());
		cm.setBill_address1(details.getBill_address1());
		cm.setBill_address2(details.getBill_address2());
		cm.setBill_city(details.getBill_city());
		cm.setBill_state(details.getBill_state());
		cm.setBill_country(details.getBill_country());
		cm.setBill_pin(details.getBill_pin());
		cm.setDisp_address1(details.getDisp_address1());
		cm.setDisp_address2(details.getDisp_address2());
		cm.setDisp_city(details.getDisp_city());
		cm.setDisp_state(details.getDisp_state());
		cm.setDisp_country(details.getDisp_country());
		cm.setDisp_pin(details.getDisp_pin());
		cm.setCustomer_type(details.getCustomer_type());
		cm.setGstn(details.getGstn());
		cm.setPan(details.getPan());
		cm.setTan(details.getTan());
		cm.setModified_on(details.getModified_on());
		cm.setModified_by(details.getModified_by());
		cm.setIs_active(details.getIs_active());
		cm.setIs_deleted(details.getIs_deleted());

		final Customer_mst updatedCustomer = cust_repo.save(cm);

		errorMsg = "data updated !";
		hasError = false;
		content.put("collection", updatedCustomer);
		content.put("error", hasError);
		content.put("message", errorMsg);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> deleteCustomer(Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;

		Customer_mst cm = cust_repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));

		cust_repo.delete(cm);

		hasError = false;
		content.put("deleted", Boolean.TRUE);
		content.put("error", hasError);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> storeFile(MultipartFile file_data) throws IOException, ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		String fileName = "";
		String extension = "";
		String s1 = "";

		fileName = StringUtils.cleanPath(file_data.getOriginalFilename());
		Long crc = (Long) Utility.getCrc(file_data.getBytes().length, file_data.getInputStream());
		logger.debug("Original Image Byte Size - " + file_data.getBytes().length);
		logger.debug("File name :" + fileName);
		logger.debug("ContentType :" + file_data.getContentType());

		s1 = file_data.getContentType().substring(file_data.getContentType().indexOf('/') + 1);
		s1 = s1.trim();

		logger.debug("file extension :" + s1);

		if (s1.equalsIgnoreCase("vnd.openxmlformats-officedocument.wordprocessingml.document")) {

			extension = "document";

		}

		if (s1.equalsIgnoreCase("vnd.oasis.opendocument.text")) {

			extension = "text";
		}

		if (s1.equalsIgnoreCase("pdf") || s1.equalsIgnoreCase("jpeg") || s1.equalsIgnoreCase("png")
				|| s1.equalsIgnoreCase("plain")) {

			extension = s1;
		}

		logger.debug("after filter ContentType is :" + s1);
		logger.debug("after filter extension is  :" + extension);

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {

				errorMsg = "Sorry! Filename contains invalid path sequence " + fileName;
				hasError = true;
				content.put("error", hasError);
				content.put("message", errorMsg);
				response.put("content", content);

			} else if (extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png")
					|| extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("document")
					|| extension.equalsIgnoreCase("plain") || extension.equalsIgnoreCase("msword")
					|| extension.equalsIgnoreCase("odt") || extension.equalsIgnoreCase("jpg")
					|| extension.equalsIgnoreCase("text") || extension.equalsIgnoreCase("msword")) {
				logger.debug("Entered ..");

				File_upload fileupload = new File_upload(fileName, extension, file_data.getBytes(),
						Utility.getCurrentTime(), Double.valueOf(file_data.getBytes().length), crc.toString(), "");

				File_upload bum = fileRepo.save(fileupload);

				if (bum != null) {
					errorMsg = "data uploaded !";
					hasError = false;
					content.put("collection", bum);
					content.put("error", hasError);
					content.put("message", errorMsg);
					response.put("content", content);
				} else {

					errorMsg = "data not uploaded !";
					hasError = true;
					content.put("error", hasError);
					content.put("message", errorMsg);
					response.put("content", content);

				}
				return response;

			} else {
				errorMsg = "File Allow only 'jpeg image' or 'png image' or 'pdf_file' or 'docx_file' or 'txt_file' ";
				hasError = true;
				content.put("error", hasError);
				content.put("message", errorMsg);
				response.put("content", content);

			}
		} catch (IOException ex) {

			errorMsg = "Could not store file " + fileName + ". Please try again!" + ex.getCause();
			hasError = true;
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);

		}
		return response;
	}

	@Override
	@Transactional
	public Object downloadFile(Long fileId) throws ResourceNotFoundException {

		File_upload result = fileRepo.findById(fileId)
				.orElseThrow(() -> new ResourceNotFoundException("File not found for this id " + fileId));

		String file_type = "";

		logger.debug("extension is :" + result.getFile_ext());

		if (result.getFile_ext().equalsIgnoreCase("jpeg") || result.getFile_ext().equalsIgnoreCase("png")) {
			file_type = "image/" + result.getFile_ext();

		} else if (result.getFile_ext().equalsIgnoreCase("pdf")) {

			file_type = "application/" + result.getFile_ext();
		} else if (result.getFile_ext().equalsIgnoreCase("plain") || result.getFile_ext().equalsIgnoreCase("txt")) {

			file_type = "text/" + result.getFile_ext();
		} else if (result.getFile_ext().equalsIgnoreCase("document")) {

			file_type = "application/" + "vnd.openxmlformats-officedocument.wordprocessingml.document";

		} else if (result.getFile_ext().equalsIgnoreCase("text")) {

			file_type = "application/" + "vnd.oasis.opendocument.text";

		} else {

			throw new DataIntegrityViolationException("Data not valid !");
		}

		byte[] file_data = result.getFile_data();
		String filename = result.getFilename();
		logger.debug(" after adding file_type is :" + file_type);

		ResponseEntity<Resource> res = ResponseEntity.ok().contentType(MediaType.parseMediaType(file_type))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + filename + "\"" + "file_size=\"" + result.getFile_bytes() + "\"")
				.body(new ByteArrayResource(file_data));

		return res;

	}

	@Override
	@Transactional
	public HashMap<String, Object> getAllFiles() {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<UploadFileResponse> files = fileRepo.findAll().stream().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/file_upload/getFileById/").path(dbFile.getFile_id().toString()).toUriString();

			return new UploadFileResponse(dbFile.getFile_id(), dbFile.getFilename(), fileDownloadUri,
					dbFile.getFile_ext(), Long.valueOf(dbFile.getFile_data().length), dbFile.getFile_crc(),
					dbFile.getFile_md5());
		}).collect(Collectors.toList());

		errorMsg = "DATA IS FOUND ";
		hasError = false;
		content.put("collection", files);
		content.put("error", hasError);
		content.put("message", errorMsg);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> deleteFileByName(Long fileName) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;

		File_upload file = fileRepo.findById(fileName)
				.orElseThrow(() -> new ResourceNotFoundException("file  not found for this id :" + fileName));

		fileRepo.delete(file);

		hasError = false;
		content.put("deleted", Boolean.TRUE);
		content.put("error", hasError);
		response.put("content", content);

		return response;
	}

//	===================================GET ALL==========================================

	@Override
	@Transactional
	public HashMap<String, Object> getAllSegMstData() throws ResourceNotFoundException {

		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<Customer_segment_mst> csm = csmr.findAllSegment();
		logger.debug("customer segment is :" + csm);
		if (!csm.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", csm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		} else {

			errorMsg = "data not found !";
			hasError = true;
			content.put("collection", csm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		}
		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getAllRoleMstData() throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<User_role_mst> urm = urmr.findAllRole();

		if (!urm.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", urm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		} else {

			errorMsg = "data not found !";
			hasError = true;
			content.put("collection", urm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		}
		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getAllTypeMstData() throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<User_type_mst> utm = utmr.findAllType();

		if (!utm.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", utm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		} else {

			errorMsg = "data not found !";
			hasError = true;
			content.put("collection", utm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		}

		return response;

	}

	@Override
	@Transactional
	public HashMap<String, Object> getAllBusinessUnitMstData() throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<Business_unit_mst> bum = bumr.findAll();

		if (!bum.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", bum);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		} else {

			errorMsg = "data not found !";
			hasError = true;
			content.put("collection", bum);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		}
		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getAllBusinessNatureMstData() throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<Business_nature_mst> bnm = bnr.findAll();
		if (!bnm.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", bnm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		} else {

			errorMsg = "data not found !";
			hasError = true;
			content.put("collection", bnm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		}

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getAllSoCategoryMstData() throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<So_category_mst> scm = scr.findAll();

		if (!scm.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", scm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		} else {
			errorMsg = "data not found !";
			hasError = true;
			content.put("collection", scm);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		}

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getCutomerType(String title) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		List<CustomerPair> ct = cmr.getCustomerPair(title);
		logger.debug(ct.toString());

		if (ct.isEmpty() || ct == null) {

			errorMsg = "data not found for this title :" + title + " ";
			hasError = true;
			content.put("collection", ct);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);

		} else {

			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", ct);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		}
		return response;
	}

}
