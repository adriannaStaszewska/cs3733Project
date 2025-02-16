package com.cs3733kakistocrat.group.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cs3733kakistocrat.group.model.RemoteSite;
import com.mysql.jdbc.ResultSetMetaData;

public class RemotesDAO {

	java.sql.Connection conn;

	public RemotesDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public List<RemoteSite> getAllRemotes() throws Exception {
		List<RemoteSite> remotes = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM remote_url";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				RemoteSite r = generateRemoteSite(resultSet);
				remotes.add(r);
			}
			resultSet.close();
			statement.close();
			return remotes;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting all remotes: " + e.getMessage());
		}
	}

	private RemoteSite generateRemoteSite(ResultSet resultSet) throws Exception {
//		ResultSetMetaData rsmd = resultSet.getMetaData();
//		
//		int cols= rsmd.getColumnCount();
//		while(resultSet.next()) {
//			for(int i=1; i<= 2;i++) {
//				if(i>1)
//					System.out.print(", ");
//				String ColVal = resultSet.getString(i);
//				System.out.print(ColVal +" "+rsmd.getColumnName(i));
//			}
//		}
		String url = resultSet.getString("url");
		String api_key = resultSet.getString("api_key");
		return new RemoteSite(url,api_key);
	}

	public boolean addRemote(RemoteSite remote) throws Exception {

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM remote_url WHERE url = ?;");
			
			ps.setString(1, remote.getUrl());
			ResultSet resultSet = ps.executeQuery();

			// already present?
			while (resultSet.next()) {
				String r = resultSet.getString("url");
				resultSet.close();
				return false;
			}
			System.out.println(remote.getUrl());
			ps = conn.prepareStatement("INSERT INTO remote_url (url, api_key) values(?, ?);");
			ps.setString(1, remote.getUrl());
			ps.setString(2, remote.getApi_key());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert remote url: " + e.getMessage());
		}
	}
    public RemoteSite getRemote(String url) throws Exception {
        
        try {
            RemoteSite remote = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM remote_url WHERE url=?;");
            ps.setString(1,  url);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                remote = generateRemoteSite(resultSet);
            } 
            resultSet.close();
            ps.close();
            
            return remote;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting playlist: " + e.getMessage());
        }
    }
    public boolean removeRemote(RemoteSite remote) throws Exception{
   	 try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM remote_url WHERE url = ?;");
            ps.setString(1, remote.getUrl());
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete playlist: " + e.getMessage());
        }
   }

}
