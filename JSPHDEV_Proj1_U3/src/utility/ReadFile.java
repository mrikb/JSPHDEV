package utility;
import java.io.*; 
import java.util.Scanner;

import exception.*;

import model.Automobile;

public class ReadFile {
	private Automobile automobile = new Automobile();
	
	public Automobile buildAutoObject(String fileName){
		try{
			/**
			 * check whether file name is missing
			 */
			if (fileName.length() == 0){
				throw new AutoOptionException("File name is missing!");
			}
			
			// create FileReader to read file
			FileReader file = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			/**
			 * check whether field "Make" exists, if not, throw exception
			 */
			String autoMake = null;
			String makelLine = buff.readLine();
			makelLine.split(":");
			try{
				if (makelLine.split(":")[0].equals("Make") && makelLine.split(":").length != 2)
					throw new AutoOptionException("'Make' field is missing!");
				else
					autoMake = makelLine.split(":")[1];
			} catch (AutoOptionException e){
				System.out.println(e.getMsg());
				Scanner userInputScanner = new Scanner(System.in);
				System.out.print("\nPlease enter the 'Make' value: ");
		        autoMake = userInputScanner.nextLine();
			} 
			automobile.setMake(autoMake);
			
			/**
			 * check whether field "Model" exists, if not, throw exception.
			 */
			String autoName = null;
			String modelLine = buff.readLine();
			modelLine.split(":");
			try{
				if (modelLine.split(":")[0].equals("Model") && modelLine.split(":").length != 2)
					throw new AutoOptionException("'Model' field is missing! ");
				else
					autoName = modelLine.split(":")[1];
			} catch (AutoOptionException e){
				System.out.println(e.getMsg());
				Scanner userInputScanner = new Scanner(System.in);
				System.out.print("\nPlease enter the 'Model' value: ");
				autoName = userInputScanner.nextLine();
			} 
			automobile.setModel(autoName);
			
			/**
			 * check whether field "Base Price" exists, if not, throw exception.
			 */
			float autoBasePrice = (float) 0.0;
			String basePriceLine = buff.readLine();
			basePriceLine.split(":");
			try{
				if (basePriceLine.split(":")[0].equals("Base Price") && basePriceLine.split(":").length != 2)
					throw new AutoOptionException("'Base Price' field is missing!");
				else
					autoBasePrice = Float.parseFloat(basePriceLine.split(":")[1]);
			} catch (AutoOptionException e){
				System.out.println(e.getMsg());
				Scanner userInputScanner = new Scanner(System.in);
				System.out.print("\nPlease enter the 'Base Price' value: ");
				autoBasePrice = Float.parseFloat(userInputScanner.nextLine());
			}
			automobile.setBaseprice(autoBasePrice);
			
			/** 
			 * Start reading Option Set and Options
			 * Each line in input data file contains all information for one option set.
			 * Option set name and options are separated by ":"
			 * Options are separated by ";"
			 * Option name and price are separated by ","
			 */
			while (eof != true){
				String line = buff.readLine();
				
				if (line == null){
					eof = true;
				} else {
					String[] opset = line.split(":");
					String opsetName = opset[0];
					String opsetString = opset[1];

					String[] opt = opsetString.split(";");
					int optionSize = opt.length;
					
					
					automobile.setOpsetValues(opsetName); 
					for (int optIndex=0; optIndex<optionSize; optIndex++){
						String[] optSplit = opt[optIndex].split(",");
						// check Option data
						String optName = optSplit[0];
						String optString = null;
						try{
							if (optSplit.length != 2)
								throw new AutoOptionException("Missing option data! " +
										"Please check option set '" + opsetName +
										"', option '" + optName);
							else
								optString = optSplit[1];
						} catch (AutoOptionException e){
									System.out.println(e.getMsg());
									Scanner userInputScanner = new Scanner(System.in);
									System.out.print("\nPlease enter the 'Option Price' value: ");
									optString = userInputScanner.nextLine();
						}
						float optPrice = Float.parseFloat(optString);
						automobile.setOptionValues(opsetName, optName, optPrice);
					}
				}
			}
			buff.close();
		} catch (AutoOptionException e){
			System.out.println(e.getMsg());
		} catch (IOException e){
			System.out.println("Error -- " + e.toString());
		}
		return automobile;
	}
}
