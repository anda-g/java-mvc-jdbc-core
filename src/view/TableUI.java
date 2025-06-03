package view;

import model.dto.ProductResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class TableUI<T> {
    public static void getTableDisplay(List<ProductResponseDto> products) {
        Table table = new Table(3, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        String[] columns = {"UUID", "Product Name", "Expired Date"};
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        int i = 0;
        for (String column : columns) {
            table.addCell(column, center);
            table.setColumnWidth(i++, 40, 50);
        }
        for (ProductResponseDto product : products) {
            table.addCell(product.uuid(), center);
            table.addCell(product.name(), center);
            table.addCell(product.expiryDate().toString(), center);
        }
        System.out.println(table.render());
    }

    public void getDisplay(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        String[] columns = new String[fields.length];
        Table table = new Table(columns.length, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        for (int i = 0; i < columns.length; i++) {
            columns[i] = fields[i].getName().toUpperCase();
            table.setColumnWidth(i, 40, 50);
        }
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        int i = 0;
        for (String column : columns) {
            table.addCell(column, center);
        }
        System.out.println(table.render());;
    }
}
