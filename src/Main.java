import service.FileMenu;
import service.MenuService;
import servlet.StudentJournalServlet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final StudentJournalServlet studentJournalServlet = new StudentJournalServlet();
    private static final FileMenu fileMenu = new FileMenu();

    public static void main(String[] args) {
        boolean isAppend = fileMenu.getFileMenu();
        studentJournalServlet.getServlet(MenuService.menu(), isAppend);
    }
}