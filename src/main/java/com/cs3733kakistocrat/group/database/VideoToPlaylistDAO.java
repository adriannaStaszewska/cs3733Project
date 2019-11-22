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
    		
    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist_video WHERE playlist_name=?;");
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO playlist_video (video_id, playlistName, video_position) values(?,?,?);");
            ps.setString(1,  video.getVideoID());
            ps.setString(2,  playlist.getPlaylistName());
            ps.setInt(3,  -1); //not used yet, no need 
            
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to add video to the playlist: " + e.getMessage());
        }
    }
    
    private Video generateVideo(ResultSet resultSet) throws Exception {
    	String video_id = resultSet.getString("video_id");
    	VideosDAO videoDAO = new VideosDAO();
    	Video vid = videoDAO.getVideo(video_id);
    	
    	return vid;
    }
}