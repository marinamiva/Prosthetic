package db.inteface;
import pojos.*;
import java.sql.*;
import java.util.List;


public interface DoctorManagerInterface {
	/*public Doctor register(Doctor doc);
	public Doctor login(String tel, byte[] password);*/
	public void modify(Patient pat, Date date, int id);
	public void delete(Patient pat);
	public void addPatient(Patient pat);
	public List<Prosthetic> selectProsthetic(String col, String find);
	public void assignDOF(Date date, Patient pac);
	public List<Patient> searchPatientByTelephone(String tel);
	public Patient searchSpecificPatientById(int num_id);
	public void assignProstheticToPatient(int prost_id, int pat_id);
}
