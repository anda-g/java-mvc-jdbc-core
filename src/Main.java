import controller.ProductController;
import model.dto.ProductResponseDto;
import model.enities.User;
import view.TableUI;
import view.UI;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        UI.home();
        new TableUI<User>().getDisplay(new User());
    }
}
