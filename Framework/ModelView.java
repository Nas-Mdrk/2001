package model;
import java.util.*;
public class ModelView{
    private String view;
	private HashMap<String,Object> data = new HashMap<>();
    public void setView(String view){
        this.view = view;
    }

    public String getView(){
        return view;
    }
	
	public void addData(String key,Object data){
		this.data.put(key,data);
	}
	
	public HashMap<String,Object> getData(){
		return this.data;
	}
}