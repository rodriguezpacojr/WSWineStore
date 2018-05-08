package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Annotations for include a root in the out of WS
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="state")

public class State
{	 
	private String name;
	private int keyState;
    
    private Connection conn;
    private Connect objC;

    
	//==========================METHODS===========================================
    public List<State> listStates()
	{				
		State obj = new State();
		ArrayList<State> arrC = new ArrayList<State>();		
										
		try
		{
			objC = new Connect();
			conn = objC.getConn();
			String query = "SELECT * FROM state";			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
				
			//Convert all registers from query to objects
			while(res.next())
			{
				obj = new State();
				obj.keyState = res.getInt("keyState");
				obj.name = res.getString("name");
			
				arrC.add(obj);
			}
			conn.close();
		}
		catch(Exception e)
		{	
			
		}
			return arrC;		
	}
    

    @XmlElement(required=true)
    public int getKeyState() {
		return keyState;
	}

	public void setKkeyState(int keyState) {
		this.keyState = keyState;
	}

	@XmlElement(required=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}