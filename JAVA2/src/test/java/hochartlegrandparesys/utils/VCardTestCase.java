package hochartlegrandparesys.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

import org.junit.Test;
import org.junit.Before;

import hochartlegrandparesys.models.Contact;

public class VCardTestCase {
	@Before
	public void shouldDeleteVcards(){
		VCard.deleteVCards();
		String directoryName = System.getProperty("user.dir")+"/VCARDS/";
		File directory = new File(directoryName);
		assertThat(directory.listFiles()).hasSize(0);
	}
	
	@Test
	public void shouldCreateVCard() throws IOException{
		Contact contact = new Contact("firstname", "lastname", "0601020304", "10 rue test 59000 Lille", "test@test.fr", "nickname",
				Date.valueOf("2000-10-09"), 1);
		VCard.exportVCard(contact);
		String directoryName = System.getProperty("user.dir")+"/VCARDS/";
		File f = new File(directoryName+contact.getFirstname()+"_"+contact.getLastname()+".vcf");
		assertThat(f.exists());
		BufferedReader br = null;
        String sCurrentLine;
        br = new BufferedReader(new FileReader(f));
        assertThat(br.readLine()).isEqualTo(("BEGIN:VCARD"));
        assertThat(br.readLine()).isEqualTo(("VERSION:3.0"));
        assertThat(br.readLine()).isEqualTo(("N:"+contact.getFirstname()+";"+contact.getLastname()+";"));
        assertThat(br.readLine()).isEqualTo(("FN:"+contact.getFirstname()+" "+contact.getLastname()));
        assertThat(br.readLine()).isEqualTo(("NICKNAME:"+contact.getNickname()));
        assertThat(br.readLine()).isEqualTo(("BDAY:"+contact.getBirthdate()));
        assertThat(br.readLine()).isEqualTo(("ADR;TYPE=HOME:;;"+contact.getAddress()));
        assertThat(br.readLine()).isEqualTo(("TEL;TYPE=HOME;:"+contact.getPhoneNumber()));
        assertThat(br.readLine()).isEqualTo(("EMAIL;TYPE=HOME;:"+contact.getEmailAddress()));
        assertThat(br.readLine()).isEqualTo(("END:VCARD"));
		br.close();
	}
}
