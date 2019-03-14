package hochartlegrandparesys.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import hochartlegrandparesys.models.Contact;

public class VCard {
	
	/**
	 * exports the vcards of all given contacts
	 * @param listcontacts
	 */
	public static void exportAllVCards(List<Contact> listcontacts) {
		for(Contact contact : listcontacts) {
			exportVCard(contact);
		}
	}
	
	/**
	 * exports the vcard of the given contact
	 * @param contact
	 */
	public static void exportVCard(Contact contact){
		String directoryName = System.getProperty("user.dir")+"/VCARDS/";
		File directory = new File(directoryName);
		if(!directory.exists()) {
			directory.mkdir();
		}
		File f = new File(directoryName+contact.getFirstname()+"_"+contact.getLastname()+".vcf");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream fop=new FileOutputStream(f);
			String str = getVCardString(contact);
			fop.write(str.getBytes());
			fop.flush();
			fop.close();
			System.out.println("VCard exported to "+f);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param contact
	 * @return the string which has to be written in the vcard
	 */
	private static String getVCardString(Contact contact) {
		String vcard;
		vcard="BEGIN:VCARD\r\n" + 
			  "VERSION:3.0\r\n";
		vcard+="N:"+contact.getFirstname()+";"+contact.getLastname()+";\r\n";
		vcard+="FN:"+contact.getFirstname()+" "+contact.getLastname()+"\r\n";
		vcard+="NICKNAME:"+contact.getNickname()+"\r\n";
		vcard+="BDAY:"+contact.getBirthdate()+"\r\n";
		vcard+="ADR;TYPE=HOME:;;"+contact.getAddress()+"\r\n";
		vcard+="TEL;TYPE=HOME;:"+contact.getPhoneNumber()+"\r\n";
		vcard+="EMAIL;TYPE=HOME;:"+contact.getEmailAddress()+"\r\n";
		vcard+="END:VCARD";
		return vcard;
	}
	/**
	 * empty the folder used to stock vcards
	 */
	public static void deleteVCards() {
		String directoryName = System.getProperty("user.dir")+"/VCARDS/";
		File directory = new File(directoryName);
		if(directory.exists()) {
			for(File file : directory.listFiles()) {
				file.delete();
			}
			System.out.println("VCards deleted");
		}
	}
}
