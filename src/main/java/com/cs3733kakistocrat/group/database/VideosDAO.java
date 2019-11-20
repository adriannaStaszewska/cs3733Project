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
    	String character = resultSet.getString("character");
    	String sentence = resultSet.getString("sentence");
    	
    	return new Video (name, URL, character, sentence);
    }

    public boolean deleteVideo(Video video) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM video WHERE video_id = ?;");
            ps.setString(1, video.getVideo_id());
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete video: " + e.getMessage());
        }
    }
    
    public boolean addVideo(Video video) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM video WHERE video_id = ?;");
            ps.setString(1, video.getVideo_id());
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO video (video_id, name, character, sentence, url, remotely_accessible) values(?,?,?,?,??);");
            ps.setString(1,  video.getVideo_id());
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

}