import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// OBJ info
// http://www.martinreddy.net/gfx/3d/OBJ.spec

public class ObjFileProcessor {

	private static ArrayList<String> whitelistCommands = new ArrayList<String>( Arrays.asList("v", "vn", "f") );
	
	private static Color getRandomColor() {
		return new Color((int)(Math.random() * 0x1000000));
	}
	
	public static ArrayList<Triangle> decodeObjFile(File objFile, double scale) {

		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Vertex> vertexNormal = new ArrayList<Vertex>();
	
		ArrayList<Integer[]> genTriangles = new ArrayList<Integer[]>();
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		
		try {
	        
			BufferedReader in = new BufferedReader(new FileReader(objFile));
	        
	        String line = null;
	        
	        while ((line = in.readLine()) != null) {
	        	char[] cmd = line.toCharArray();
	            if (whitelistCommands.contains(line.split("\\s+")[0])) {
	            	System.out.println( line + "\n" + cmd[0] + "\n" );
	            	if (cmd[0] == 'v') {
	            		System.out.println( "Vertex" );
	            		String[] data = line.split("\\s+");
	            		System.out.println(data);
	            		if (data.length == 4) { 
	            			vertices.add( new Vertex( Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]) ) );
	            		}
	            	} else if(cmd[0] == 'v' && cmd[1] == 'n') {
	            		System.out.println( "Vertex Normal" );
	            		String[] data = line.split("\\s+");
	            		if (data.length == 4) { 
	            			vertexNormal.add( new Vertex( Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]) ) );
	            		}
	            	} else if (cmd[0] == 'f') {
	            		String[] data = line.split("\\s+");
	            		System.out.println( "Face: " + data + data.length );
	            		if (data.length > 3) { 
	            			int topLeft = Integer.parseInt( data[1].substring(0, 1) );
	            			int topRight = Integer.parseInt( data[2].substring(0, 1) );
	            			int bottomLeft = Integer.parseInt( data[3].substring(0, 1) );
	            			// f 1/1/1 5/2/1 7/3/1 **3/4/1
	            			// 1 5 7 3 = vertex numbers
	            			if ( vertices.get(topLeft) != null && vertices.get(topRight) != null && vertices.get(bottomLeft) != null ) {
	            				
	            				genTriangles.add( new Integer[] {topLeft, topRight, bottomLeft} );
	            				
	            				try {
	            					int num = Integer.parseInt( data[4].substring(0, 1) );
	            					genTriangles.add( new Integer[] {topLeft, topRight, num} );
	            				} catch (Exception e) {
	            					System.out.println("no second triangle for face found. " + e.getCause() );
	            				}
	            				
	            				
	            			}
	            		}
	            	}
	        	}
	        }
	        
	        System.out.println();
	        in.close();
	        
	    } catch(IOException ex) {
	    	System.err.println("Failed to read obj file: " + ex);
	    }
		
		for (Integer[] intArray : genTriangles) {
			
			Triangle tri = new Triangle( 
				vertices.get( intArray[0] ),
				vertices.get( intArray[1] ), 
				vertices.get( intArray[2] ), 
				getRandomColor()
			);
			
			tri.resize(scale);
			triangles.add(tri);
		}
		
		System.out.println(vertices);
		//System.out.println();
		
		return triangles;
		
	}
	
}
