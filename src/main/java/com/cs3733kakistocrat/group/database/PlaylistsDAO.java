package com.cs3733kakistocrat.group.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cs3733kakistocrat.group.model.Playlist;


public class PlaylistsDAO {
	
	java.sql.Connection conn;

    public PlaylistsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    
    public List<Playlist> getAllPlaylists() throws Exception {
    	List<Playlist> playlists = new ArrayList<>();
    	try {
    		
    		Statement statement = conn.createStatement();
            String query = "SELECT * FROM playlist";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Playlist p = generatePlaylist(resultSet);
                playlists.add(p);
            }
            resultSet.close();
            statement.close();
    		
    		return playlists;
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new Exception("Failed in getting all playlists: " + e.getMessage());
    	}
    }
    
    public boolean addPlaylist(Playlist playlist) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE name = ?;");
            ps.setString(1, playlist.getName());
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Playlist p = generatePlaylist(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO playlist (name) values(?);");
            ps.setString(1, playlist.getName());
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert playlist: " + e.getMessage());
        }
    }
    

    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
    	String name  = resultSet.getString("name");
    	return new Playlist(name);
    }
    
    

}
