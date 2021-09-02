package Main;
import GUI.moduleEmploye;
import LoginPage.login;

public class main {

	public static void main(String[] args) {
		changeLNF("Nimbus");
		login login = new login();
		
		/*
		ModuleEmployee employee = new ModuleEmployee();		
		
		*/

	}
	
	 public static void changeLNF(String nameLNF) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
    }
}
