package com.cs3733kakistocrat.group.database;

import java.sql.PreparedStatement;
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
            String query = "SELECT * FROM video";
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
    	String character = resultSet.getString("characterName");
    	String sentence = resultSet.getString("sentence");
    	String videoID = resultSet.getString("video_id");
    	
    	return new Video (videoID, name, URL, character, sentence);
    }

    public boolean deleteVideo(Video video) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM video WHERE video_id = ?;");
            System.out.println("Delete: " + video.getVideoID());
            ps.setString(1, video.getVideoID());
            int numAffected = ps.executeUpdate();
            
            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM playlist_video WHERE video_id = ?;");
            ps2.setString(1, video.getVideoID());
            ps2.executeUpdate();
            ps2.close();
            ps.close();
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete video: " + e.getMessage());
        }
    }
    
    public boolean addVideo(Video video) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM video WHERE video_id = ?;");
            ps.setString(1, video.getVideoID());
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO video (video_id, name, characterName, sentence, url, remotely_accessible) values(?, ?, ?, ?, ?, ?);");
            ps.setString(1,  video.getVideoID());
            ps.setString(2,  video.getName());
            ps.setString(3,  video.getCharacter());
            ps.setString(4,  video.getSentence());
            ps.setString(5,  video.getUrl());
            ps.setBoolean(6, video.isRemotely_accessible());
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to add video: " + e.getMessage());
        }
    }
    
    public Video getVideo(String video_id) throws Exception {
    	try {
            Video video = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM video WHERE video_id=?;");
            ps.setString(1,  video_id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                video = generateVideo(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return video;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting video: " + e.getMessage());
        }
    }
    
    public List<Video> searchVideos(String search) throws Exception {
    	try {
    		List<Video> videos = new ArrayList<>();
    		
    		Statement statement = conn.createStatement();
    		String query = "SELECT * FROM video";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                if(v.getCharacter().equals(search) || v.getSentence().contains(search)){
                	videos.add(v);
                }
            }
            resultSet.close();
            statement.close();
    		
    		return videos;
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new Exception("Failed in searching videos: " + e.getMessage());
    	}
    }
    
    public boolean updateRemote(String videoID, boolean status) throws Exception {
    	 Video video = null;
         PreparedStatement ps = conn.prepareStatement("UPDATE video SET remotely_accessible = ? WHERE video_id=?;");
         ps.setString(1,  videoID);
         ResultSet resultSet = ps.executeQuery();
         return true;
         
    	
    }

}