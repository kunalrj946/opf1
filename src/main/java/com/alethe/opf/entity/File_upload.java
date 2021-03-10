package com.alethe.opf.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Kunal Kumar
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file_upload")
public class File_upload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long file_id; // | bigint | | not null | nextval('file_upload_file_id_seq'::regclass)


	@Column(name = "file_name")
	private String filename; // | character varying(300) | | |

	private String file_ext; // | character varying(10) | | |

	private byte[] file_data; // | bytea | | |

	private Timestamp file_modified_on; // | timestamp without time zone | | |

	private Double file_bytes; // | double precision | | |

	private String file_crc; // | character varying(100) | | |

	private String file_md5; // | character varying(100) | | |

	public File_upload(String filename, String file_ext, byte[] file_data, Timestamp file_modified_on,
			Double file_bytes, String file_crc, String file_md5) {
		super();
		this.filename = filename;
		this.file_ext = file_ext;
		this.file_data = file_data;
		this.file_modified_on = file_modified_on;
		this.file_bytes = file_bytes;
		this.file_crc = file_crc;
		this.file_md5 = file_md5;
	}

}
