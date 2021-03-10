package com.alethe.opf.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.apache.commons.codec.binary.Base64;


/**
 * Created by Kunal Kumar
 */
public class Utility {

	
	public static Timestamp getCurrentTime()
	{
	
		Date date = new Date(); // getTime() returns current time in milliseconds
		long time = date.getTime();// Passed the milliseconds to constructor of Timestamp class
		Timestamp ts = new Timestamp(time);
	
		return ts;
		
	}
	
	
	
	// compress the image bytes before storing it in the database

	public static byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();

		deflater.setInput(data);

		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {

			int count = deflater.deflate(buffer);

			outputStream.write(buffer, 0, count);

		}

		try {

			outputStream.close();

		} catch (IOException e) {

		}

		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();

	}

	// uncompress the image bytes before returning it to the angular application

	public static byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();

		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		try {

			while (!inflater.finished()) {

				int count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);

			}

			outputStream.close();

		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}

		return outputStream.toByteArray();

	}

	public static Object getCrc(long filebyte , InputStream in) throws IOException {
		
		CRC32 crcMaker = new CRC32();
		byte[] buffer = new byte[(int) filebyte];
		int bytesRead;
		while((bytesRead = in.read(buffer)) != -1) {
		    crcMaker.update(buffer, 0, bytesRead);
		}
		long crc = crcMaker.getValue(); 
		
		return crc;
	}
	
	public static int getFY(String date) throws Exception {

		Date  date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		
		int month;
		int year;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		
		month = cal.get(Calendar.MONTH);
		
		int advance = (month < 3)? 0:1;
		year = cal.get(Calendar.YEAR) + advance;
		
		return year; 
	}
	
	
	private static String getBase64Decode(String password) {
		
		
		byte[] decoded = Base64.decodeBase64(password.getBytes());

        // Print the decoded array
        System.out.println(Arrays.toString(decoded));

        // Convert the decoded byte[] back to the original string and print
        // the result.
        String decodedString = new String(decoded);
        System.out.println(password + " = " + decodedString.trim());
// 
		return decodedString;
		
	}

}
