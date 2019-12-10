package com.cs3733kakistocrat.group.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cs3733kakistocrat.group.model.Playlist;
import com.cs3733kakistocrat.group.model.Video;

public class VideoToPlaylistDAO {

	java.sql.Connection conn;

    public VideoToPlaylistDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public List<Video> getAllVideos(String playlistName) throws Exception {
    	try {
    		List<Video> videos = new ArrayList<>();
    		
    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist_video WHERE playlist_name=? ORDER BY video_position asc;");
            ps.setString(1,  playlistName);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                videos.add(v);
            }
            resultSet.close(); 
            ps.close();
    		 
    		return videos;
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new Exception("Failed in getting all videos: " + e.getMessage());
    	}
    } 
    
    public boolean appendVideoToPlaylist(Video video, Playlist playlist) throws Exception {
        try {
        	
        	PreparedStatement ps1 = conn.prepareStatement("SELECT MAX(video_position) FROM playlist_video WHERE playlist_name=?;");
        	ps1.setString(1, playlist.getPlaylistName());

        	ps1.execute();
        	ResultSet results = ps1.getResultSet();
        	results.next();
        	int rowNum = results.getInt(1) + 1;
        	ps1.close();
        	
            PreparedStatement ps = conn.prepareStatement("INSERT INTO playlist_video (video_id, playlist_name, video_position) values(?,?,?);");
            ps.setString(1,  video.getVideoID());
            ps.setString(2,  playlist.getPlaylistName());
            ps.setInt(3,  rowNum);
            
            ps.execute();
            ps.close();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to add video to the playlist: " + e.getMessage());
        }
    }
    
    
    public boolean removeVideo(Video video, Playlist playlist, int position) throws Exception {
        try {
            PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM video WHERE 'video_id' = ? AND 'remote' = 1;");
            ps2.setString(1, video.getVideoID());
            ResultSet resultSet = ps2.executeQuery();
            
            VideosDAO vidDAO = new VideosDAO();
            
            //is remote
            while (resultSet.next()) {
                Video v = generateVideo(resultSet);
                resultSet.close();
                vidDAO.deleteVideo(v);
                break;
            }
            ps2.close();

        	PreparedStatement ps = conn.prepareStatement("DELETE FROM playlist_video WHERE (video_id, playlist_name, video_position) = (?, ?, ?);");
            ps.setString(1, video.getVideoID());
            ps.setString(2,  playlist.getPlaylistName());
            ps.setInt(3,  position);

            
            int numLines = ps.executeUpdate();
            ps.close();
            
            return numLines==1;

        } catch (Exception e) {
        	
        	System.out.println(e.getMessage());
            throw new Exception("Failed to add video to the playlist: " + e.getMessage());
        }
    }
       
    public boolean removeVideoFromAllPlaylists(String videoID) throws Exception{
    	try {
    		PreparedStatement ps = conn.prepareStatement("DELETE FROM playlist_video WHERE 'video_id' = ?;");
    		ps.setString(1, videoID);
    		int numAffected = ps.executeUpdate();
    		ps.close();
    		
    		return true;
    		
    	} catch (Exception e) {
    		throw new Exception("Failed to delete playlist: " + e.getMessage());
	      	}
 	}
    
    private Video generateVideo(ResultSet resultSet) throws Exception {
    	String video_id = resultSet.getString("video_id");
    	VideosDAO videoDAO = new VideosDAO();
    	Video vid = videoDAO.getVideo(video_id);
    	
    	return vid;
    }
}
