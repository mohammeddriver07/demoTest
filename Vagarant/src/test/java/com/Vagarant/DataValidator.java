package com.Vagarant;

import java.io.FileReader;
import java.io.IOException;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class DataValidator {
	
	
	public void validateTestCases()
	{
		JSONObject itemsJson = new JSONObject();
		int foreignCount = 0;
		int indianCount = 0;
		
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader("data.json"))
		{
			//Read JSON file
			Object obj = jsonParser.parse(reader);
			JSONObject jo = (JSONObject) obj;
			JSONArray playersArray =  (JSONArray) jo.get("player");
			
			int sizeOfItems = playersArray.size();
			
			if (sizeOfItems>0)
			{
				for(int i = 0; i < sizeOfItems; i++)
				{
					itemsJson = (JSONObject) playersArray.get(i);
					// Iterating to get only roles from the Response Array
					String role = itemsJson.get("role").toString();
					String playername = itemsJson.get("name").toString();
					if(role.equalsIgnoreCase("Wicket-keeper"))
					{
						System.out.println("Wicket Keeper was found in the team: "+playername);
					}
					
					String country = itemsJson.get("country").toString();
					
					if(country.equalsIgnoreCase("India"))
					{
						indianCount+=1;
					}
					else
					{
						foreignCount+=1;
						
					}
				}
				if(foreignCount>4)
				{
					System.out.println("There are more the 4 foreign players in the team");
				}
				else
				{
					System.out.println("There are only "+foreignCount+" foreign players in the team");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		DataValidator dv = new DataValidator();
		dv.validateTestCases();
		
	}

}
