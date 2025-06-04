package view;

import model.dto.ProductResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.lang.reflect.Field;
import java.util.List;

public class TableUI<T> {

//    public static void getTableDisplay(List<ProductResponseDto> products) {
//        Table table = new Table(3, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
//        String[] columns = {"UUID", "Product Name", "Expired Date"};
//        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
//        int i = 0;
//        for (String column : columns) {
//            table.addCell(column, center);
//            table.setColumnWidth(i++, 40, 50);
//        }
//        for (ProductResponseDto product : products) {
//            table.addCell(product.uuid(), center);
//            table.addCell(product.name(), center);
//            table.addCell(product.expiryDate().toString(), center);
//        }
//        System.out.println(table.render());
//    }

    public void getTableDisplay(List<T> tList) {
        T t = tList.get(0);
        Field[] fields = t.getClass().getDeclaredFields();
        String[] columns = new String[fields.length];

        // Setup table
        Table table = new Table(columns.length, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        for (int i = 0; i < fields.length; i++) {
            columns[i] = fields[i].getName().toUpperCase();
            table.setColumnWidth(i, 40, 50);
        }

        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        for (String column : columns) {
            table.addCell(column, center);
        }

        for (T t1 : tList) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(t1);
                    table.addCell(String.valueOf(value), center);
                } catch (IllegalAccessException e) {
                    table.addCell("ERR");
                }
            }
        }
        System.out.println(table.render());
    }
}
