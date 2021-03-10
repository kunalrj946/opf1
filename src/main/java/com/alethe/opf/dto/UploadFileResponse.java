package com.alethe.opf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Kunal Kumar
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponse {
	private Long file_id;
	private String file_name;
	private String fileDownloadUri;
	private String file_type;
	private Long file_size;
	private String file_crc;
	private String file_md5;

}
