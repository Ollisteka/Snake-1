package gameCore;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;

import direction.Dir;
import direction.Direction;

public class StateParser {
	/*
	private static Map<String, Runnable> map = new HashMap<String, Runnable>();
	static {
		map.put("Food",FoodFactory::Produce);
	}*/
	
	public static GameState makeGame(String path) {
    	
		try(BufferedReader br = new BufferedReader(new FileReader(path));) {
    		return new GameState(getMaze(br),getSnake(br),getDir(br),getObjs(br));	
		} catch (NumberFormatException|IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	private static char[][] getMaze(BufferedReader br) throws IOException  {
		String l=br.readLine();
		String[] wh=l.split(" ",2); 
		int height=Integer.parseInt(wh[1]);;
		char[][] maze=new char[height][];
		
		for (int i=0;i < height;i++) {
    		l=br.readLine();
			maze[i]=l.toCharArray();
		}
		return maze; 
	}
	
	private static Point[] getSnake(BufferedReader br) throws IOException {
    		String[] sl = br.readLine().split(" ");
    		Point[] snakeBody=new Point[sl.length/2];
    		for(int i=0;i<sl.length;i+=2) {
    			snakeBody[i/2]=new Point(Integer.parseInt(sl[i]), Integer.parseInt(sl[i+1]));
    		}
		return snakeBody;
	}
	
	private static Direction getDir(BufferedReader br) throws NumberFormatException, IOException {
		int num = Integer.parseInt(br.readLine());
		Dir dir = null;
		if(num == 2) dir = Dir.Down;
		if(num == 8) dir = Dir.Up;
		if(num == 4) dir = Dir.Left;
		if(num == 6) dir = Dir.Right;
		return new Direction(dir);
	}
	
	//@SuppressWarnings("unchecked")
	private static ArrayList<Tuple<String, Integer[]>> getObjs(BufferedReader br) throws IOException {
		String l=br.readLine();
		ArrayList<Tuple<String, Integer[]>> objCount = new ArrayList<Tuple<String, Integer[]>>();
		while(l!=null) {
    		String[] wh1=l.split(" "); 
    		Integer[] tmp= new Integer[wh1.length-1];
    		for(int i=0;i<wh1.length-1;i++)
    		  tmp[i]=Integer.parseInt(wh1[i+1]);
			objCount.add(new Tuple<String, Integer[]>(wh1[0],tmp));
    		l=br.readLine();
		}
		
		return objCount; 
		
	}
}
