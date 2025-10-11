package file.imports.impl;

import com.financas.gestaofinanceira.domain.dto.UserResponseDTO;
import file.imports.contract.FileImporter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class XlsxImporter implements FileImporter {

    @Override
    public List<UserResponseDTO> importFile(InputStream inputStream) throws Exception {

        try(XSSFWorkbook workbook = new XSSFWorkbook(inputStream)){
            XSSFSheet sheet = workbook.getSheetAt(0); //qual aba do excel estarei pegando, no caso, a primeira aba
            Iterator<Row> rowIterator = sheet.iterator();

            if(rowIterator.hasNext()) rowIterator.next();

            return parseRowsToUserResponseDtoList(rowIterator);
        }
    }

    private List<UserResponseDTO> parseRowsToUserResponseDtoList(Iterator<Row> rowIterator) {
            List<UserResponseDTO> users = new ArrayList<>();

            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isRowValid(row)){
                    users.add(parseRowsToUserResponseDto(row));
                }
            }

            return users;
    }

    private UserResponseDTO parseRowsToUserResponseDto(Row row) {
        AtomicInteger indice = new AtomicInteger(0);
        UserResponseDTO dto = new UserResponseDTO();
        dto.setName(row.getCell(indice.getAndIncrement()).getStringCellValue());
        dto.setCpf(row.getCell(indice.getAndIncrement()).getStringCellValue());
        dto.setUsername(row.getCell(indice.getAndIncrement()).getStringCellValue());
        dto.setEmail(row.getCell(indice.getAndIncrement()).getStringCellValue());
        dto.setMonthlyIncome(row.getCell(indice.getAndIncrement()).getNumericCellValue());
        return dto;
    }

    private boolean isRowValid(Row row) {
        return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK;
    }
}
