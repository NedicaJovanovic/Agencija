package agencija;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ponuda {
	int idTerm;
	String naziv;
	String drzava;
	int datumOd;
	int datumDo;
	int preostaloMesta;
	int cena;
	
	public Ponuda(int idTerm, String naziv, String drzava, int datumOd, int datumDo, int preostaloMesta, int cena) {
		this.idTerm = idTerm;
		this.naziv = naziv;
		this.drzava = drzava;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.preostaloMesta = preostaloMesta;
		this.cena = cena;
	}

	public String toString() {
		return String.format("(%d) %s(%s) %d - %d / %d€ : %d", idTerm, naziv, drzava, datumOd, datumDo, cena, preostaloMesta);
	}
	
	public boolean zakupi() throws SQLException{
		if(preostaloMesta<=0)
			return false;
		
		String sql = "UPDATE Termin SET PreostaloMesta = PreostaloMesta - 1 WHERE idTer = ? AND PreostaloMesta > 0";
		try(PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)){
			ps.setInt(1, idTerm);
			ps.execute();
		}
		preostaloMesta--;	
		return true;
	}
}

		