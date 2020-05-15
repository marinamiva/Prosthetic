package db.classes;
import db.inteface.*;
import pojos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientManager implements PatientManagerInterface {
	
	private Connection c;
	
	public PatientManager(Connection connection) {
		this.c=connection;
	}
	
	public void register(Patient patient) {
		
	}
	
	public void login(Patient patient) {
		
	}
	
	
	public void viewDate(String telephone) {
		try {

			String sql = "SELECT dof FROM patient WHERE telephone LIKE ?";
			//AS p JOIN doctor AS d ON d.doctor_id=p.doc_id;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%"+telephone+"%");
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Date dateOf = rs.getDate("dof");
				System.out.println("Hello! Your next appointment is: " + dateOf + "\n");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Hospital> showHospitals() {
		// To show all Hospitals in our data base
		ArrayList<Hospital> hospitalList = new ArrayList<Hospital>();
		try {
			String sql = "SELECT * FROM hospital";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int hosId = rs.getInt("hospital_id");
				String hosName = rs.getString("name");
				String hosLocat = rs.getString("location");
				String hosTele = rs.getString("telephone");
				
				Hospital newHospital = new Hospital(hosId, hosName, hosLocat, hosTele);
				hospitalList.add(newHospital);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hospitalList;
	}


	public Hospital selectHospitalByID(int id) {
		Hospital hosSelected= new Hospital();
		try {
			String sql = "SELECT * FROM hospital WHERE hospital_id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int hospital_id = rs.getInt("hospital_id");
				String name = rs.getString("name");
				String location = rs.getString("location");
				String telephone = rs.getString("telephone");
				hosSelected= new Hospital(hospital_id, name, location, telephone);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hosSelected;
		
	}
	
	public void addpatientbyRegister(Patient pat) {
		try {
			String sql = "INSERT INTO patient (name, lastname, telephone)"
					+ " VALUES (?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pat.getName());
			prep.setString(2, pat.getLastname());
			prep.setString(3, pat.getTelephone());
			prep.executeUpdate();
			prep.close();
			}
		catch(Exception e) {
			e.printStackTrace();
			}
	}
	
	public void assignHospitalID(String telephone, int hos_id) {
		try {
			String sql = "UPDATE patient SET hospital_id=? WHERE telephone=?;";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, hos_id);
			prep.setString(2, telephone);
			prep.executeUpdate();
			prep.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	
	
}
