import controller.ProductController;
import model.dto.ProductResponseDto;
import model.dto.UserResponseDto;
import model.enities.User;
import view.TableUI;
import view.UI;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UI.home();
//        new TableUI<UserResponseDto>().getDisplay(List.of(new UserResponseDto("uuid", "email", "name", LocalDate.now())));
    }
}
