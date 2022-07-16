package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class FileSystem {

	public static boolean doesFileExist( String filePath ) {
		try {
			return !(new File(filePath).exists());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	
	public static FileReader getJSONFile( String fileName ) {
		String filePath = "./src/scene_jsons/" + fileName;
		
		//String path = new File(".").getCanonicalPath();
		System.out.println("Get JSON File ; " + filePath);
		
		if (!doesFileExist(filePath)) {
			return null;
		}
		
		FileReader fileReader = null;
		try {
			File file = new File(filePath);
			fileReader = new FileReader(file);
		} catch(IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileReader;
	}
	
	public static JSONObject loadJSONFile( String filePath ) {

		FileReader jsonReader = getJSONFile( filePath );
		if (jsonReader == null) {
			System.out.println("Could not access JSON file @ " + filePath);
			return null;
		}
		
		System.out.println("Found File: " + filePath);
		
		// Load the JSON data
		JSONObject json = null;
		
		// Parse the JSON data
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(jsonReader);
			json = (JSONObject) obj;
		} catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		// Close File Reader
		try {
			jsonReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Give JSON back
		return json;
	}
	
}
