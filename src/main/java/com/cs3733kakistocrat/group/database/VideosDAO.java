package com.cs3733kakistocrat.group.database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cs3733kakistocrat.group.model.Video;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author kakistocrat
 *
 */
public class VideosDAO {

	java.sql.Connection conn;

    public VideosDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    
    public List<Video> getAllVideos() throws Exception {
    	try {
    		List<Video> videos = new ArrayList<>();
    		
    		Statement statement = conn.createStatement();
            String query = "SELECT * FROM Video";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                videos.add(v);
            }
            resultSet.close();
            statement.close();
    		
    		
    		return videos;
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new Exception("Failed in getting all videos: " + e.getMessage());
    	}
    }
    
    
    private Video generateVideo(ResultSet resultSet) throws Exception {
    	String name  = resultSet.getString("name");
    	String URL = resultSet.getString("url");
    	String character = resultSet.getString("character");
    	String sentence = resultSet.getString("sentence");

    	return new Video (name, URL, character, sentence);
    }
//    public Constant getConstant(String name) throws Exception {
//        
//        try {
//            Constant constant = null;
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM constants WHERE name=?;");
//            ps.setString(1,  name);
//            ResultSet resultSet = ps.executeQuery();
//            
//            while (resultSet.next()) {
//                constant = generateConstant(resultSet);
//            }
//            resultSet.close();
//            ps.close();
//            
//            return constant;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new Exception("Failed in getting constant: " + e.getMessage());
//        }
//    }
//    
//    public boolean updateConstant(Constant constant) throws Exception {
//        try {
//        	String query = "UPDATE constants SET value=? WHERE name=?;";
//        	PreparedStatement ps = conn.prepareStatement(query);
//            ps.setDouble(1, constant.value);
//            ps.setString(2, constant.name);
//            int numAffected = ps.executeUpdate();
//            ps.close();
//            
//            return (numAffected == 1);
//        } catch (Exception e) {
//            throw new Exception("Failed to update report: " + e.getMessage());
//        }
//    }
//    
//    public boolean deleteConstant(Constant constant) throws Exception {
//        try {
//            PreparedStatement ps = conn.prepareStatement("DELETE FROM constants WHERE name = ?;");
//            ps.setString(1, constant.name);
//            int numAffected = ps.executeUpdate();
//            ps.close();
//            
//            return (numAffected == 1);
//
//        } catch (Exception e) {
//            throw new Exception("Failed to insert constant: " + e.getMessage());
//        }
//    }
//
//
//    public boolean addConstant(Constant constant) throws Exception {
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM constants WHERE name = ?;");
//            ps.setString(1, constant.name);
//            ResultSet resultSet = ps.executeQuery();
//            
//            // already present?
//            while (resultSet.next()) {
//                Constant c = generateConstant(resultSet);
//                resultSet.close();
//                return false;
//            }
//
//            ps = conn.prepareStatement("INSERT INTO constants (name,value) values(?,?);");
//            ps.setString(1,  constant.name);
//            ps.setDouble(2,  constant.value);
//            ps.execute();
//            return true;
//
//        } catch (Exception e) {
//            throw new Exception("Failed to insert constant: " + e.getMessage());
//        }
//    }
//
//    public List<Constant> getAllConstants() throws Exception {
//        
//        List<Constant> allConstants = new ArrayList<>();
//        try {
//            Statement statement = conn.createStatement();
//            String query = "SELECT * FROM constants";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                Constant c = generateConstant(resultSet);
//                allConstants.add(c);
//            }
//            resultSet.close();
//            statement.close();
//            return allConstants;
//
//        } catch (Exception e) {
//            throw new Exception("Failed in getting books: " + e.getMessage());
//        }
//    }
//    
//    private Constant generateConstant(ResultSet resultSet) throws Exception {
//        String name  = resultSet.getString("name");
//        Double value = resultSet.getDouble("value");
//        return new Constant (name, value);
//    }

}